import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;
import org.apache.commons.math3.stat.inference.ChiSquareTest;

public class CSSE490Week3 {
	
	private static String name = "";
	private static String nons = "m2";
	private static String syn = "m1";
	private static boolean skip = false;//if "genomes:" is missings

	public static void main(String a[]) {
		//If we were being clean about this I would make the name, nons, and syn
		//arguments. This would also be a multiline comment
		try {
			File divergence = new File(name+"divergence.txt");//relocate/rename file
			Scanner dsc = new Scanner(divergence);
			HashMap<String, String[]> divMuts = new HashMap<>();
			if(skip) {
				dsc.nextLine();
				dsc.nextLine();
			}
			while(dsc.hasNextLine()) {
				String line = dsc.nextLine();
				if(line.startsWith("Genomes:")) {
					break;
				}
				String[] arr = line.split(" ");
				
				divMuts.put(arr[3], arr);
			}
			dsc.close();
			dsc = null;
			int synDiv = 0;
			int nonsynDiv = 0;
			//find dN and dS
			ArrayList<Integer> ones = new ArrayList<>();
			ArrayList<Integer> twos = new ArrayList<>();
			for(String[] arr: divMuts.values()) {
				if(arr[2].equals(nons)) twos.add(Integer.valueOf(arr[3]));
				else if(arr[2].equals(syn)) ones.add(Integer.valueOf(arr[3]));
				else System.out.println("neither synonymous nor nonsynonymous...");
			}
			ones.sort(new IntComp());
			twos.sort(new IntComp());
			StringBuilder sb = new StringBuilder();
			int m = 0;
			int onesIndex=0;
			int twosIndex=0;
			while(true) {
				boolean added = false;
				if(m<ones.get(ones.size()-1) && ones.get(onesIndex)==m) {
					sb.append(1);
					added = true;
					onesIndex++;
				}
				if(m>=twos.get(twos.size()-1)) {
					if(m>=ones.get(ones.size()-1)) {
						break;
					}
					m++;
					sb.append(0);
					continue;
				}
				else if(!added && twos.get(twosIndex)==m) {
					sb.append(2);
					added = true;
					twosIndex++;
				}
				if(!added) sb.append(0);
				m++;
			}
			String divString = sb.toString();
			
			File sample = new File(name+"sample.txt");
			Scanner msc = new Scanner(sample);
			if(skip) {
				msc.nextLine();
				msc.nextLine();
			}
			HashMap<String, String[]> samMuts = new HashMap<>();
			String firstLine = "";
			while(msc.hasNextLine()) {
				String line = msc.nextLine();
				if(!line.contains("m")) {
					firstLine = line;
					break;
				}
				if(line.startsWith("Genomes:")) {
					break;
				}
				String[] arr = line.split(" ");
				
				samMuts.put(arr[0], arr);
			}
			m=0;
			ones.clear();
			twos.clear();
			onesIndex=0;
			twosIndex=0;
			if(firstLine=="") {
				firstLine = msc.nextLine();
			}
			String[] ar = firstLine.split(" ");
			for(int l = 2; l<ar.length; l++) {
				String key = ar[l];
				String[] val = samMuts.get(key);
				if(val[2].equals(syn)) {
					ones.add(Integer.valueOf(val[3]));
				}
				else if(val[2].equals(nons)) {
					twos.add(Integer.valueOf(val[3]));
				}
				m++;
			}
			ones.sort(new IntComp());
			twos.sort(new IntComp());
			sb = new StringBuilder();
			while(!ones.isEmpty() && !twos.isEmpty()) {
				boolean added = false;
				if(m<ones.get(ones.size()-1) && ones.get(onesIndex)==m) {
					sb.append(1);
					onesIndex++;
					added = true;
				}
				if(m>=twos.get(twos.size()-1)) {
					if(m>=ones.get(ones.size()-1)) {
						break;
					}
					m++;
					sb.append(0);
					continue;
				}
				if(!added && twos.get(twosIndex)==m) {
					sb.append(2);
					added = true;
					twosIndex++;
				}
				if(!added) sb.append(0);
				m++;
			}
			String divString2 = sb.toString();
			Pair diffs = diff(divString, divString2);
			
			ArrayList<String[]> genomes = new ArrayList<>();
			while(msc.hasNextLine()) {
				String line = msc.nextLine();
				String[] arr = line.split(" ");
				arr[0] = arr[0].substring(3); //cut off "p2:"
				genomes.add(arr);
			}
			msc.close();
			
			
			
			
			double piSyn = 0.0;
			double piNon=0.0;
			for(int i = 0; i<genomes.size(); i++) {
				for(int j=i+1; j<genomes.size(); j++) {
					for(int k = 2; k<genomes.get(i).length; k++) {
						String mut = genomes.get(i)[k];
						boolean found = false;
						for(String mutj: genomes.get(j)) {
							if(mut.equals(mutj)) {
								if(samMuts.get(mut)[2].equals(syn)) {
									piSyn++;
									if(samMuts.get(mutj)[2].equals(syn)) {
										piSyn++;
									}
									if(samMuts.get(mutj)[2].equals(nons)) {
										piNon++;
									}
								}
								if(samMuts.get(mut)[2].equals(nons)) {
									piNon++;
									if(samMuts.get(mutj)[2].equals(syn)) {
										piSyn++;
									}
									if(samMuts.get(mutj)[2].equals(nons)) {
										piNon++;
									}
								}
							}
						}
						if(found) {
							if(samMuts.get(mut)[2].equals(syn)) {
								piSyn++;
							}
							else piNon++;
						}
					}
				}
			}
			double[] expected = {(double)diffs.i, (double)diffs.j};
			
			double[] expected2 = {(double)synDiv/(double)nonsynDiv};
			long[] actual = {(long) piSyn, (long) piNon};
			double[] actual2 = {(double)piSyn/(double)piNon};
			System.out.println((double)diffs.j/(double)diffs.i);
			System.out.println((double)actual[1]/(double)actual[0]);
		}
		catch(Exception e) {
//			if(e instanceof NullPointerException) {
//				System.out.println("check to make sure right scanner used");
//			}
			e.printStackTrace();
		}
	}
	
	private static Pair diff(String a, String b) {
		System.out.print("Lengths: ");
		System.out.print(a.length());
		System.out.print(", ");
		System.out.println(b.length());
		int i = 0;
		int count1 = 0;
		int count2 = 0;
		while(i<a.length()) {
			char c = a.charAt(i);
			char d = b.charAt(i);
			if(c!=d) {
				if(c-'0'==1) count1++;
				if(c-'0'==2) count2++;
				if(d-'0'==1) count1++;
				if(d-'0'==2) count2++;
			}
			i++;
		}
		return new Pair(count1, count2);
	}
	
	static class Pair {
		int i;
		int j;
		public Pair(int i, int j) {
			this.i=i;
			this.j=j;
		}
	}
	
	static class IntComp implements Comparator<Integer> {

		@Override
		public int compare(Integer arg0, Integer arg1) {
			// TODO Auto-generated method stub
			return arg0.compareTo(arg1);
		}
	}
}
