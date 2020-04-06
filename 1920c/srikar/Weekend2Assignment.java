import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Weekend2Assignment {
	public static void main(String[] args) {
		try {
			//Mutations
			Scanner scanner = new Scanner(new File("/Users/srikarnamburi/Desktop/CSSE490/Weekend2Assignment/KathiData/sampleMutations.txt"));
			scanner.nextLine();
			HashMap<String,ArrayList<String>> mySampleMutations = new HashMap<String,ArrayList<String>>();
			while (scanner.hasNextLine()) {
				String x = scanner.nextLine();
				String[] myArray = x.split(" ");
				ArrayList<String> myStrings = new ArrayList<String>();
				myStrings.add(myArray[2]);
				myStrings.add(myArray[3]);
				mySampleMutations.put(myArray[0], myStrings);
			}
			scanner.close();
			
			//Genomes
			Scanner genomeScanner = new Scanner(new File("/Users/srikarnamburi/Desktop/CSSE490/Weekend2Assignment/KathiData/sampleGenomes.txt"));
			HashMap<String,ArrayList<String>> mySampleGenomes = new HashMap<String,ArrayList<String>>();
			genomeScanner.nextLine();
			while(genomeScanner.hasNextLine()) {
				String x = genomeScanner.nextLine();
				String[] myArray = x.split(" ");
				ArrayList<String> myStrings = new ArrayList<String>();
				for(int i = 2; i <myArray.length-2;i++) {
					myStrings.add(myArray[i]);
				}
				mySampleGenomes.put(myArray[0].substring(myArray[0].length()-1), myStrings);
			}
			genomeScanner.close();
			//Functions
			HashMap<String, ArrayList<ArrayList<String>>> positionCovert = positionConverter(mySampleMutations,mySampleGenomes);
			deleter(positionCovert);
			
			//Mutations
			Scanner divergenceMutationScanner = new Scanner(new File("/Users/srikarnamburi/Desktop/CSSE490/Weekend2Assignment/KathiData/divergenceMutations.txt"));
			divergenceMutationScanner.nextLine();
			HashMap<String,ArrayList<String>> myDivergenceMutations = new HashMap<String,ArrayList<String>>();
			while (divergenceMutationScanner.hasNextLine()) {
				String x = divergenceMutationScanner.nextLine();
				String[] myArray = x.split(" ");
				ArrayList<String> myStrings = new ArrayList<String>();
				myStrings.add(myArray[2]);
				myStrings.add(myArray[3]);
				myDivergenceMutations.put(myArray[0], myStrings);
			}
			divergenceMutationScanner.close();
			
			//Genomes
			Scanner divergenceGenomeScanner = new Scanner(new File("/Users/srikarnamburi/Desktop/CSSE490/Weekend2Assignment/KathiData/divergenceGenomes.txt"));
			HashMap<String,ArrayList<String>> myDivergenceGenomes = new HashMap<String,ArrayList<String>>();
			divergenceGenomeScanner.nextLine();
			while(divergenceGenomeScanner.hasNextLine()) {
				String x = divergenceGenomeScanner.nextLine();
				String[] myArray = x.split(" ");
				ArrayList<String> myStrings = new ArrayList<String>();
				for(int i = 2; i <myArray.length-2;i++) {
					myStrings.add(myArray[i]);
				}
				myDivergenceGenomes.put(myArray[0].substring(myArray[0].length()-1), myStrings);
			}
			divergenceGenomeScanner.close();
			
			HashMap<String, ArrayList<ArrayList<String>>> divergenceConverter = positionConverter(myDivergenceMutations,myDivergenceGenomes);
			diverseDeleter(positionCovert, divergenceConverter);
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	public static HashMap<String, ArrayList<ArrayList<String>>> positionConverter(HashMap<String,ArrayList<String>> myMutations, HashMap<String,ArrayList<String>> myGenomes) {
		HashMap<String,ArrayList<ArrayList<String>>> myPositionConverterGenomes = new HashMap<String,ArrayList<ArrayList<String>>>();
		for(String s: myGenomes.keySet()) {
			ArrayList<ArrayList<String>> myIntegers = new ArrayList<ArrayList<String>>();
 			for(String a: myGenomes.get(s)) {
				myIntegers.add(myMutations.get(a));
			}
 			myPositionConverterGenomes.put(s, myIntegers);
		}
		return myPositionConverterGenomes;
	}
	public static void deleter(HashMap<String, ArrayList<ArrayList<String>>> myPositionConverter) {
		//1 and 2	
		HashMap<String,String> myFirstHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myFirst = myPositionConverter.get(Integer.toString(0));
		for(int i = 0; i<myFirst.size();i++) {
			myFirstHash.put(myFirst.get(i).get(1), myFirst.get(i).get(0));
		}
		HashMap<String,String> mySecondHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> mySecond = myPositionConverter.get(Integer.toString(1));
		for(int i = 0; i<mySecond.size();i++) {
			mySecondHash.put(mySecond.get(i).get(1), mySecond.get(i).get(0));
		}
		HashMap<String,String> myThirdHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myThird = myPositionConverter.get(Integer.toString(2));
		for(int i = 0; i<myThird.size();i++) {
			myThirdHash.put(myThird.get(i).get(1), myThird.get(i).get(0));
		}
		
		HashMap<String,String> myFourthHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myFourth = myPositionConverter.get(Integer.toString(3));
		for(int i = 0; i<myFourth.size();i++) {
			myFourthHash.put(myFourth.get(i).get(1), myFourth.get(i).get(0));
		}
		HashMap<String,String> myFifthHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myFifth = myPositionConverter.get(Integer.toString(4));
		for(int i = 0; i<myFifth.size();i++) {
			myFifthHash.put(myFifth.get(i).get(1), myFifth.get(i).get(0));
		}
		HashMap<String,String> mySixthHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> mySixth = myPositionConverter.get(Integer.toString(5));
		for(int i = 0; i<mySixth.size();i++) {
			mySixthHash.put(mySixth.get(i).get(1), mySixth.get(i).get(0));
		}
		HashMap<String,String> mySeventhHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> mySeventh = myPositionConverter.get(Integer.toString(6));
		for(int i = 0; i<mySeventh.size();i++) {
			mySeventhHash.put(mySeventh.get(i).get(1), mySeventh.get(i).get(0));
		}
		HashMap<String,String> myEigthHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myEigth = myPositionConverter.get(Integer.toString(7));
		for(int i = 0; i<myFourth.size();i++) {
			myEigthHash.put(myEigth.get(i).get(1), myEigth.get(i).get(0));
		}
		HashMap<String,String> myNinthHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myNinth = myPositionConverter.get(Integer.toString(8));
		for(int i = 0; i<myFourth.size();i++) {
			myNinthHash.put(myNinth.get(i).get(1), myNinth.get(i).get(0));
		}
		HashMap<String,String> myTenthHash = new HashMap<String,String>();
		ArrayList<ArrayList<String>> myTenth = myPositionConverter.get(Integer.toString(9));
		for(int i = 0; i<myFourth.size();i++) {
			myTenthHash.put(myTenth.get(i).get(1), myTenth.get(i).get(0));
		}
		
		ConcurrentHashMap<String,String> myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		ConcurrentHashMap<String,String> mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		ConcurrentHashMap<String,String> myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		ConcurrentHashMap<String,String> myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		ConcurrentHashMap<String,String> myFifthHashCopy = new ConcurrentHashMap<String,String>(myFifthHash);
		ConcurrentHashMap<String,String> mySixthHashCopy = new ConcurrentHashMap<String,String>(mySixthHash);
		ConcurrentHashMap<String,String> mySeventhHashCopy = new ConcurrentHashMap<String,String>(mySeventhHash);
		ConcurrentHashMap<String,String> myEigthHashCopy = new ConcurrentHashMap<String,String>(myEigthHash);
		ConcurrentHashMap<String,String> myNinthHashCopy = new ConcurrentHashMap<String,String>(myNinthHash);
		ConcurrentHashMap<String,String> myTenthHashCopy = new ConcurrentHashMap<String,String>(myTenthHash);
		
		
		int dn = 0;
		int ds = 0;
		//1st and 2nd
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: mySecondHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}


		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 3rd
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: myThirdHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		
		//1st and 4th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: myFourthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 5th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: myFifthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 6th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: mySixthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 7th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: mySeventhHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 8th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 9th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//1st and 10th
		for(String srinesh: myFirstHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFirstHashCopy = new ConcurrentHashMap<String,String>(myFirstHash);
		//2nd and 3rd
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: myThirdHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 4th
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: myFourthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 5th 
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: myFifthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 6th
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: mySixthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 7th
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: mySeventhHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 8th
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 9th
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//2nd and 10th
		for(String srinesh: mySecondHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySecondHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySecondHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySecondHashCopy = new ConcurrentHashMap<String,String>(mySecondHash);
		//3rd and 4th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: myFourthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//3rd and 5th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: myFifthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//3rd and 6th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: mySixthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//3rd and 7th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: mySeventhHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//3rd and 8th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//3rd and 9th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//3rd and 10th
		for(String srinesh: myThirdHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myThirdHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myThirdHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myThirdHashCopy = new ConcurrentHashMap<String,String>(myThirdHash);
		//4th and 5th
		for(String srinesh: myFourthHashCopy.keySet()) {
			for(String srikar: myFifthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFourthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFourthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		//4th and 6th
		for(String srinesh: myFourthHashCopy.keySet()) {
			for(String srikar: mySixthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFourthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFourthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		//4th and 7th
		for(String srinesh: myFourthHashCopy.keySet()) {
			for(String srikar: mySeventhHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFourthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFourthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		//4th and 8th
		for(String srinesh: myFourthHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFourthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFourthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		//4th and 9th 
		for(String srinesh: myFourthHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFourthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFourthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		//4th and 10th
		for(String srinesh: myFourthHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFourthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFourthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFourthHashCopy = new ConcurrentHashMap<String,String>(myFourthHash);
		//5th and 6th
		for(String srinesh: myFifthHashCopy.keySet()) {
			for(String srikar: mySixthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFifthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFifthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFifthHashCopy = new ConcurrentHashMap<String,String>(myFifthHash);
		//5th and 7th
		for(String srinesh: myFifthHashCopy.keySet()) {
			for(String srikar: mySeventhHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFifthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFifthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFifthHashCopy = new ConcurrentHashMap<String,String>(myFifthHash);
		//5th and 8th
		for(String srinesh: myFifthHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFifthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFifthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFifthHashCopy = new ConcurrentHashMap<String,String>(myFifthHash);
		//5th and 9th
		for(String srinesh: myFifthHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFifthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFifthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFifthHashCopy = new ConcurrentHashMap<String,String>(myFifthHash);
		//5th and 10th
		for(String srinesh: myFifthHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFifthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFifthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myFifthHashCopy = new ConcurrentHashMap<String,String>(myFifthHash);
		//6th and 7th
		for(String srinesh: mySixthHashCopy.keySet()) {
			for(String srikar: mySeventhHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySixthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySixthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySixthHashCopy = new ConcurrentHashMap<String,String>(mySixthHash);
		//6th and 8th
		for(String srinesh: mySixthHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySixthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySixthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySixthHashCopy = new ConcurrentHashMap<String,String>(mySixthHash);
		//6th and 9th
		for(String srinesh: mySixthHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySixthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySixthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySixthHashCopy = new ConcurrentHashMap<String,String>(mySixthHash);
		//6th and 10th
		for(String srinesh: mySixthHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySixthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySixthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySixthHashCopy = new ConcurrentHashMap<String,String>(mySixthHash);
		//7th and 8th
		for(String srinesh: mySeventhHashCopy.keySet()) {
			for(String srikar: myEigthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySeventhHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySeventhHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySeventhHashCopy = new ConcurrentHashMap<String,String>(mySeventhHash);
		//7th and 9th
		for(String srinesh: mySeventhHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySeventhHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySeventhHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySeventhHashCopy = new ConcurrentHashMap<String,String>(mySeventhHash);
		//7th and 10th
		for(String srinesh: mySeventhHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					mySeventhHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : mySeventhHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		mySeventhHashCopy = new ConcurrentHashMap<String,String>(mySeventhHash);
		//8th and 9th
		for(String srinesh: myEigthHashCopy.keySet()) {
			for(String srikar: myNinthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myEigthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myEigthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myEigthHashCopy = new ConcurrentHashMap<String,String>(myEigthHash);
		//8th and 10th
		for(String srinesh: myEigthHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myEigthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myEigthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myEigthHashCopy = new ConcurrentHashMap<String,String>(myEigthHash);
		//9th and 10th
		for(String srinesh: myNinthHashCopy.keySet()) {
			for(String srikar: myTenthHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myNinthHashCopy.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myNinthHashCopy.values()) {
			if(i.equals("m2")) {
				dn = dn +1;
			}
			else {
				ds = ds + 1;
			}
		}
		myNinthHashCopy = new ConcurrentHashMap<String,String>(myNinthHash);
		System.out.println("Non-Synonymous Value: " + dn);
		System.out.println("Synonomous Value: " + ds);
		System.out.println("Pn/Ps: " + (double)dn/ds);
		
	}
	public static void diverseDeleter(HashMap<String, ArrayList<ArrayList<String>>> samplePositionConverter, HashMap<String, ArrayList<ArrayList<String>>> divergencePositionConverter) {
		int pn = 0;
		int ps = 0;
		ConcurrentHashMap<String,String> myFirstHash = new ConcurrentHashMap<String,String>();
		ArrayList<ArrayList<String>> myFirst = samplePositionConverter.get(Integer.toString(0));
		for(int i = 0; i<myFirst.size();i++) {
			myFirstHash.put(myFirst.get(i).get(1), myFirst.get(i).get(0));
		}
		
		ConcurrentHashMap<String,String> myDivergenceFirstHash = new ConcurrentHashMap<String,String>();
		ArrayList<ArrayList<String>> myDivergeFirst = divergencePositionConverter.get(Integer.toString(0));
		for(int i = 0; i<myDivergeFirst.size();i++) {
			myDivergenceFirstHash.put(myDivergeFirst.get(i).get(1), myDivergeFirst.get(i).get(0));
		}
		for(String srinesh: myFirstHash.keySet()) {
			for(String srikar: myDivergenceFirstHash.keySet()) {
				if(srinesh.equals(srikar)) {
					myFirstHash.remove(srinesh);
					break;
				}
			}
		}
		for(String i : myFirstHash.values()) {
			if(i.equals("m2")) {
				pn = pn +1;
			}
			else {
				ps = ps + 1;
			}
		}
		System.out.println("Non-Synonymous Value: " + pn);
		System.out.println("Synonomous Value: " + ps);
		System.out.println("dN/dS: " + (double)pn/ps);
		
	}
	
}
