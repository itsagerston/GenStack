import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;
import java.io.IOException;

/**
 * AGGenStack iterates through a file, searching for any opening or closing
 * delimiters - "(", ")", "{", "}", "[", or "]". It then checks, using a generic
 * stack, if the delimiters are properly matched up, and outputs if there is
 * an error.
 *
 * @author Aaron Gerston
 */
public class AGGenStack {

	Scanner sc;
	BufferedReader br;
	String filename;
	int lineNumber;
	String line;
	char[] charmander; // Character array of a given line of text
	GenStack gs;
	
	/**
	 * AGGenStack Constructor...
	 *
	 * @param arg - commandline argument, referring to a file
	 */
	public AGGenStack(String arg) {
		filename = arg;
		sc = new Scanner(System.in);
	}
	
	/**
	 * readFile iterates through the file and searches for delimiters, outputting errors if necessary.
	 * This method is the body of the program.
	 */
	public void readFile() {
		try {
			gs = new GenStack<Character>(2);
			br = new BufferedReader(new FileReader(filename));
		lineNumber = 0;
		while(br.ready()) {
			line = br.readLine();
			charmander = line.toCharArray();
			for (int i = 0; i < charmander.length; ++i) {
				if (charmander[i] == '(' || charmander[i] == '[' || charmander[i] == '{') {
					gs.push(charmander[i]);
				}
				else if (charmander[i] == ')') {
					try {
						if ((Character) gs.peek() == '(') {
							gs.pop();
						}
						else {
							System.out.println("ERROR on line "+lineNumber+": incorrect closing delimiter (parenthesis)!");
						}
					}
					catch (Stack350Exception e) {
						System.out.println("ERROR on line "+lineNumber+": incorrect closing delimiter (parenthesis)!");
					}
				}
				else if (charmander[i] == ']') {
					try {
						if ((Character) gs.peek() == '[') {
							gs.pop();
						}
						else {
							System.out.println("ERROR on line "+lineNumber+": incorrect closing delimiter (square bracket)!");
						}
					}
					catch (Stack350Exception e) {
						System.out.println("ERROR on line "+lineNumber+": incorrect closing delimiter (square bracket)!");
					}
				}
				else if (charmander[i] == '}') {
					try {
						if ((Character) gs.peek() == '{') {
							gs.pop();
						}
						else {
							System.out.println("ERROR on line "+lineNumber+": incorrect closing delimiter (curly brace)!");
						}
					}
					catch (Stack350Exception e) {
						System.out.println("ERROR on line "+lineNumber+": incorrect closing delimiter (curly brace)!");
					}
				}
				}
				++lineNumber;
			}
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		if (gs.isEmpty()) {
			System.out.println("Done!");
			nextFile();
		}
		else {
			System.out.println("Missing closing delimiter at end of file, line "+lineNumber);
			nextFile();
		}
	}
	
	/**
	 * nextFile prompts the user if he would like to run the program again.
	 * If yes, the file is sent through the readFile method.
	 * If no, the program ends.
	 */
	public void nextFile() {
		System.out.println("Would you like to run the program again? Yes (y) or No (n)?");
		String YorN = sc.nextLine();
		while (!(YorN.equals("y") || YorN.equals("n"))) {
			System.out.println("Please type 'y' or 'n'.");
			YorN = sc.nextLine();
		}
		if (YorN.equals("y")) {
			System.out.println("Please enter the name of the file.");
			filename = sc.nextLine();
			readFile();
		}
	}
	
	/**
	 * Main method...
	 */
	public static void main(String[] args) {
		AGGenStack AG = new AGGenStack(args[0]);
		AG.readFile();
	}
}