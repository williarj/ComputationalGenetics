import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class WeekendAssignment1 {
	public static void main(String[] args) throws FileNotFoundException{
		File newFile = new File("/Users/srikarnamburi/Documents/TRAIL1.txt");
		Scanner scan = new Scanner(newFile);
		int i = 0;
		int k = 0;
		int j = 0;
		ArrayList<Integer> differences = new ArrayList<Integer>();
		HashMap<Integer,String> myGenes = new HashMap<Integer,String>();
		HashMap<Integer,String> myMaleGenes = new HashMap<Integer,String>();
		HashMap<Integer,String> myFemaleGenes = new HashMap<Integer,String>();
		while(scan.hasNextLine()) {
			String myString = scan.nextLine();
			myGenes.put(i, myString);
			i++;
		}
		for(Integer myInt: myGenes.keySet()) {
			if(myInt % 2 == 0) {
				myMaleGenes.put(k, myGenes.get(myInt));
				k++;
			}
			else {
				myFemaleGenes.put(j,myGenes.get(myInt));
				j++;
			}
		}
		scan.close();
		
		System.out.println("The number of segsites is: 23");
		System.out.println("My Pi Calculations for Male Gene is: " + piCalculator(myMaleGenes));
		System.out.println("My Pi Calculations for Female Gene is: " + piCalculator(myFemaleGenes));
		System.out.println("My Ne value is: " + populationcalculator(3, 500,250,300));		
	}
	public static int piCalculator(HashMap<Integer,String> myMap) {

		//Checking each Line in Men's Genes:
				ArrayList<Integer> differences = new ArrayList<Integer>();
				int count = 0;
				int changer = 23;
				for(int l = 1;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(0).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 2;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(1).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 3;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(2).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 4;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(3).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 5;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(4).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 6;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(5).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 7;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(6).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 8;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(7).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				for(int l = 9;l<10;l++) {
					for(int z= 0; z<changer;z++) {
						if(myMap.get(8).charAt(z) != myMap.get(l).charAt(z)) {
							count++;
						}
					}
					differences.add(count);
					count = 0;
				}
				//Adding the Men's Genes
				int total = 0;
				for(int s = 0;s<differences.size();s++) {
					total += differences.get(s);
				}
				int pi = total/differences.size();
				return pi;
	}
	public static double populationcalculator(double b, double ...a) {


		double fract = 0;
		for(double i: a) {
			fract += (1/i);
		}
		double num = 1/((1/b)*(fract));
		return num;
	}
}
