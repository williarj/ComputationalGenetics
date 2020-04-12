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
		
		for(Mutation m : mutations) {
			System.out.println(m);
		}

	}
	
	static class Mutation implements Comparable<Mutation> {

		int id;

		int pos;

		boolean type; // m1=true

		public Mutation(String input){

			Scanner sc = new Scanner(input);

			id = sc.nextInt();

			sc.next();

			String mutType = sc.next();

			if (mutType.equals("m1")) {
				type = true;
			} else {
				type = false;
			}

			pos = sc.nextInt();

			sc.close();

			// System.out.println(this.toString());

		}
		
		
		public int compareTo(Mutation m) {
			return this.pos - m.pos;
		}

		@Override
		public String toString() {

			return String.format("id: %2d, pos: %2d type (m1 = true) = %b", id, pos, type);

		}
		
		
		
		

	}
	
	

}
