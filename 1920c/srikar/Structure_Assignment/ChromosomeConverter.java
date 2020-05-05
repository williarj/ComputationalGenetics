import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ChromosomeConverter {
	public static void main(String[] args) throws FileNotFoundException {
	    File myFile = new File("/Users/srikarnamburi/Documents/ComputationalGenetics/1920c/srikar/Structure_Assignment/samp.txt");
	    Scanner myReader = new Scanner(myFile);
	    //Skip first three lines
	    for(int i = 0; i<3; i++) {
	    	myReader.nextLine();
	    }
	    //Put ever 2 lines into arraylist for one individual
	    HashMap<Integer,ArrayList<String>> myIndividuals = new HashMap<Integer,ArrayList<String>>();
	    int k = 0;
	    ArrayList<String> myStuff;
	    while(myReader.hasNextLine()) {
	    	myStuff = new ArrayList<String>();
	    	myStuff.add(myReader.nextLine());
	    	myStuff.add(myReader.nextLine());
	    	myIndividuals.put(k, myStuff);
	    	k++;
	    }
	    //Computing Sum
	    HashMap<Integer,String> myFinalIndividuals = new HashMap<Integer,String>();
	    for(int key: myIndividuals.keySet()) {
	    	String myString = "";
	    	for(int i = 0; i<myIndividuals.get(key).get(0).length();i++) {
	    		if(myIndividuals.get(key).get(0).charAt(i) == '0' && myIndividuals.get(key).get(1).charAt(i) == '0') {
	    			myString = myString + "0";
	    		}
	    		else if((myIndividuals.get(key).get(0).charAt(i) == '1' && myIndividuals.get(key).get(1).charAt(i) == '0') || (myIndividuals.get(key).get(0).charAt(i) == '0' && myIndividuals.get(key).get(1).charAt(i) == '1')) {
	    			myString = myString + "1";
	    		}
	    		else if(myIndividuals.get(key).get(0).charAt(i) == '1' && myIndividuals.get(key).get(1).charAt(i) == '1') {
	    			myString = myString + "2";
	    		}
	    		else {
	    			System.out.println("Something has gone wrong");
	    		}
	    	}
	    	myFinalIndividuals.put(key, myString);
	    }
	    //Printing it out to a file
	    PrintStream mystream = new PrintStream(new File("/Users/srikarnamburi/Documents/ComputationalGenetics/1920c/srikar/Structure_Assignment/simulation.txt"));
	    System.setOut(mystream);
	    for(int i = 0; i<myFinalIndividuals.get(0).length(); i++) {
	    	System.out.print( String.format("Site%d \t", i));
	    }
	    System.out.print("\n");
	    for(int i = 0; i<myFinalIndividuals.size(); i++) {
	    	System.out.print(String.format("Sample%d \t", i));
	    	for(int j = 0; j<myFinalIndividuals.get(i).length();j++) {
	    		System.out.print(myFinalIndividuals.get(i).charAt(j) + "\t \t");
	    	}
	    	System.out.print("\n");
	    }
	    
	}
}


