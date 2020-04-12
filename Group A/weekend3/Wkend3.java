/**
 * 
 */
package csse490PICalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import csse490PICalc.main.Mutation;

/**
 * @author heinrich
 *
 */
public class Wkend3 {

	/**
	 * @param args
	 */
	
	static ArrayList<Mutation> mutations = new ArrayList<Mutation>();
	
	static int buffer = 0;
	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);

		String current = "";

		System.out.println("enter 'stop' to end mutation input");

		while (!current.equals("stop")) {

			String mutation = sc.nextLine();

			current = mutation;

			if (current.equals("stop")) {
				break;
			}
			Mutation m = new Mutation(mutation);

			mutations.add(m);

		}
		
		mutations.sort(null);
		
		
		buffer = mutations.get(mutations.size()-1).pos / (mutations.size());
		System.out.println(buffer);
		
		
		for(Mutation m : mutations) {
			System.out.println(m);
			
			
		}
		
		range();

	}
	
	public static void range() {
		
		ArrayList<Integer[]> geneRanges = new ArrayList<Integer[]>();
		
		Integer[] range = new Integer[2];
		Boolean starting = true;
		
		for(int i = 0; i < mutations.size(); i++) {
			
			if(mutations.get(i).type.equals("m1")) {
				continue;
			}
			
			if(starting) {
				range[0] = mutations.get(i).pos - buffer/2;
				starting = false;
			}
			
			if(mutations.get(i+1).type.equals("m1")) {
				range[1] =  mutations.get(i).pos + buffer/2;
				geneRanges.add(range);
				
				range = new Integer[2];
				
				starting = true;
			}
		}
		
		
		System.out.println();
		System.out.println("Predicted gene ranges:");
		
		
		for(Integer[] r : geneRanges) {
			
			
			System.out.println("Starting from "+r[0] +" to "+r[1]);
			
			
		}
		
	}
	
	
	static class Mutation implements Comparable<Mutation> {

		int id;

		int pos;

		String type;

		public Mutation(String input){

			Scanner sc = new Scanner(input);

			id = sc.nextInt();

			sc.next();

			type = sc.next();

			pos = sc.nextInt();

			sc.close();

		}
		
		
		
		public int compareTo(Mutation m) {
			return this.pos - m.pos;
		}
		
		@Override
		public boolean equals(Object m) {
			
			if(m.getClass().equals(this.getClass())) {
				Mutation mu = (Mutation) m;
				if(this.pos == mu.pos && this.type.equals(mu.type)) {
					return true;
				}
			}
			
			return false;
			
		}

		@Override
		public String toString() {

			Boolean gene = true;
			
			if(type.equals("m1")) {
				gene = false;
			}
			
			return String.format("Gene: %b, pos (buffered): %2d -> %2d, type = %s, id: %2d", gene, pos-buffer/2,pos+buffer/2, type, id); //id: %2d,  //id

		}
		
		
		
		

	}
	
	

}
