/**
 * @author Mr. Rasmussen
 */

package fracCalc;

import java.util.*;

public class FracCalc {

	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner c = new Scanner(System.in);
		String userInput = c.nextLine();
		while (userInput.equals("quit") != true) {
			produceAnswer(userInput);
			System.out.println("type \"quit\" to exit program");
			userInput = c.nextLine();
		}
		c.close();
	}
	// ** IMPORTANT ** DO NOT DELETE THIS FUNCTION. This function will be used to
	// test your code
	// This function takes a String 'input' and produces the result
	//
	// input is a fraction string that needs to be evaluated. For your program, this
	// will be the user input.
	// e.g. input ==> "1/2 + 3/4"
	//
	// The function should return the result of the fraction after it has been
	// calculated
	// e.g. return ==> "1_1/4"
	public static String produceAnswer(String input) {
		// TODO: Implement this function to produce the solution to the input
		int space = input.indexOf(" ");
		String secNum = input.substring(space + 3);
		/*String secTest = "0";
		String firstNum = input.substring(0, space);
		String operator = input.substring(space + 1);
		String whole = "a";
		String numerator = "a";
		String denominator = "a";
		whole = secNum.substring(0,secNum.indexOf("_"));
		numerator = secNum.substring(secNum.indexOf("_") + 1, secNum.indexOf("/"));
		denominator = secNum.substring(secNum.indexOf("/")+1);
		secTest = ("whole:" + whole + " numerator:" + numerator + " denominator:" + denominator);
		System.out.println("whole:" + whole + " numerator:" + numerator + " denominator:" + denominator);*/
		String w2 = whole(secNum, input);
		String n2 = numerator(secNum);
		String d2 = denominator(secNum);
		return "whole:" + w2 + " numerator:" + n2 + " denominator:" + d2;
	}
// TODO: Fill in the space below with any helper methods that you think you will
// need
		public static String whole (String secNum, String input) {
			int checkWhole = secNum.indexOf('_');
			int checkFrac = secNum.indexOf('/');
			int space = secNum.indexOf(" ");
			String swhole = "temp";
			if (checkWhole == -1 && checkFrac != -1) {
				swhole = "0";
			}
			else if (checkWhole == -1) {
				swhole = secNum;
			}
			else {
				swhole = secNum.substring(0, checkWhole);	
			}
			return swhole;
		}
		public static String numerator (String secNum) {
			int checkWhole = secNum.indexOf('_');
			int checkFrac = secNum.indexOf('/');
			int space = secNum.indexOf(" ");
			String snumerator = "temp";
			if (checkFrac == -1) {
				snumerator = "0";
			}
			else {
				snumerator = secNum.substring(checkWhole + 1, checkFrac);
			}
			return snumerator;
		}
			public static String denominator (String secNum) {
				int checkWhole = secNum.indexOf('_');
				int checkFrac = secNum.indexOf('/');
				int space = secNum.indexOf(" ");
				String sdenominator = "temp";
				if (checkFrac == -1) {
					sdenominator = "1";
				}
				else {
					sdenominator = secNum.substring(checkFrac + 1);
				}
				return sdenominator;
			}
}
