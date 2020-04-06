/**
 * 
 */
package csse490PICalc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author heinrich
 *
 */
public class main {

	/**
	 * @param args
	 */

	static HashMap<Integer, Mutation> mutations = new HashMap<Integer, Mutation>();
	static HashMap<Integer, Mutation> mutations2 = new HashMap<Integer, Mutation>();

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

			mutations.put(m.id, m);

			System.out.println(mutations.get(m.id));

		}

		System.out.println("enter pop data");

		ArrayList<Genome> individuals = new ArrayList<Genome>();

		current = "";

		while (!current.equals("stop")) {

			String gene = sc.nextLine();

			current = gene;

			if (current.equals("stop")) {
				break;
			}

			individuals.add(new Genome(gene));

		}

		for (Genome g : individuals) {
			System.out.println(g.individual);
		}

		double pn = 0;
		double ps = 0;

		for (Genome g1 : individuals) {
			for (Genome g2 : individuals) {

				if (g1 != (g2)) {

					ArrayList<Mutation> diff = g1.muts;
					diff.removeAll(g2.muts);

					for (Mutation m : diff) {
						if (m.type) {
							ps++; // m1 syn -> Ps
							//System.out.println("ps++");
						} else {
							pn++;
						}
					}

				}

			}
		}

		System.out.println("pn/ps: " + (pn / ps) + ", pn:" + (pn) + " ps:" + ps);

		///

		System.out.println("Dn/Ds");

		current = "";

		System.out.println("enter 'stop' to end mutation input 2");

		while (!current.equals("stop")) {

			String mutation = sc.nextLine();

			current = mutation;

			if (current.equals("stop")) {
				break;
			}
			Mutation m = new Mutation(mutation);

			mutations2.put(m.id, m);

			System.out.println(mutations2.get(m.id));

		}

		// Genome p2
		
		System.out.println("ready");

		String gene = sc.nextLine();

		Genome otherG = new Genome(gene, false);

		double dn = 0;
		double ds = 0;

		ArrayList<Mutation> diff = individuals.get(0).muts;
		
		//remove
		
		ArrayList<Integer> positions = new ArrayList<Integer>();
		for(Mutation m : otherG.muts) {
			positions.add(m.pos);
		}
		ArrayList<Mutation> toR = new ArrayList<Mutation>();
		for(Mutation m : diff) {
			if(positions.contains(m.pos)) {
				toR.add(m);
			}
		}
		
		diff.remove(toR);

		for (Mutation m : diff) {
			if (m.type) {
				ds++; // m1 syn -> Ps
				System.out.println("ps++");
			} else {
				dn++;
			}
		}
		
		System.out.println("dn/ds: " + (dn / ds) + ", dn:" + (dn) + " ds:" + ds);

		sc.close();
	}

	static class Mutation {

		int id;

		int pos;

		boolean type; // m1=true

		public Mutation(String input) {

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

		public String toString() {

			return String.format("id: %2d, pos: %2d type (m1 = true) = %b", id, pos, type);

		}

	}

	static class Genome {

		String individual;

		ArrayList<Mutation> muts = new ArrayList<Mutation>();

		public Genome(String input) {
			Scanner sc = new Scanner(input);

			individual = sc.next();

			sc.next();

			while (sc.hasNext()) {
				muts.add(mutations.get((sc.nextInt())));
			}

			sc.close();

			// System.out.println(this.toString());
		}

		public Genome(String input, boolean b) {

			Scanner sc = new Scanner(input);

			individual = sc.next();

			sc.next();

			while (sc.hasNext()) {
				muts.add(mutations2.get((sc.nextInt())));
			}

			sc.close();

			// System.out.println(this.toString());
		}

		public String toString() {
			String toR = individual;

			for (Mutation m : muts) {
				toR += " " + m;
			}

			return toR;
		}

	}

}
