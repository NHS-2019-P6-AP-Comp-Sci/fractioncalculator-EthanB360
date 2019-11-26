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
		// loop to take continuous inputs until user types "quit"
		while (userInput.equals("quit") != true) {
			String check = produceAnswer(userInput);
			System.out.println(check);
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
		// separate all numbers
		String secNum = input.substring(space + 3);
		String firstNum = input.substring(0, space);
		String w1 = wholeOne(firstNum);
		String n1 = numeratorOne(firstNum);
		String d1 = denominatorOne(firstNum);
		String w2 = wholeTwo(secNum, input);
		String n2 = numeratorTwo(secNum);
		String d2 = denominatorTwo(secNum);
		// parse all numbers
		int whole1 = Integer.parseInt(w1);
		int numerator1 = Integer.parseInt(n1);
		int denominator1 = Integer.parseInt(d1);
		int whole2 = Integer.parseInt(w2);
		int numerator2 = Integer.parseInt(n2);
		int denominator2 = Integer.parseInt(d2);
		String answer = "temp";
		// find operator
		String operator = input.substring(space + 1, space + 2);
		// call each method for the 4 different operators
		if (operator.equals("+") == true) {
			answer = addition(whole1, numerator1, denominator1, whole2, numerator2, denominator2, firstNum, secNum);
		} else if (operator.equals("-") == true) {
			answer = subtraction(whole1, numerator1, denominator1, whole2, numerator2, denominator2);
		} else if (operator.equals("*") == true) {
			answer = multiplication(whole1, numerator1, denominator1, whole2, numerator2, denominator2, firstNum,
					secNum);
		} else if (operator.equals("/") == true) {
			answer = division(whole1, numerator1, denominator1, whole2, numerator2, denominator2, firstNum, secNum);
			// error statement for wrong formating
		} else {
			System.out.println("ERROR! VAT ARE YOU DOING");
			System.out.println(operator + "check");
		}
		return answer;
	}

// TODO: Fill in the space below with any helper methods that you think you will
// need
//lines 76-223 work checkpoint 3******************************************
	// find whole of first number
	public static String wholeOne(String firstNum) {
		int checkWhole = firstNum.indexOf('_');
		int checkFrac = firstNum.indexOf('/');
		String firstWhole = "temp";
		if (checkWhole == -1 && checkFrac != -1) {
			firstWhole = "0";
		} else if (checkWhole == -1) {
			firstWhole = firstNum;
		} else {
			firstWhole = firstNum.substring(0, checkWhole);
		}
		return firstWhole;
	}

	// find numerator of first number
	public static String numeratorOne(String firstNum) {
		int checkWhole = firstNum.indexOf('_');
		int checkFrac = firstNum.indexOf('/');
		String firstNumerator = "temp";
		if (checkFrac == -1) {
			firstNumerator = "0";
		} else {
			firstNumerator = firstNum.substring(checkWhole + 1, checkFrac);
		}
		return firstNumerator;
	}

	// find denominator of first number
	public static String denominatorOne(String firstNum) {
		int checkFrac = firstNum.indexOf('/');
		String firstDenominator = "temp";
		if (checkFrac == -1) {
			firstDenominator = "1";
		} else {
			firstDenominator = firstNum.substring(checkFrac + 1);
		}
		return firstDenominator;
	}

	// find whole of second number
	public static String wholeTwo(String secNum, String input) {
		int checkWhole = secNum.indexOf('_');
		int checkFrac = secNum.indexOf('/');
		String sWhole = "temp";
		if (checkWhole == -1 && checkFrac != -1) {
			sWhole = "0";
		} else if (checkWhole == -1) {
			sWhole = secNum;
		} else {
			sWhole = secNum.substring(0, checkWhole);
		}
		return sWhole;
	}

	// find numerator of second number
	public static String numeratorTwo(String secNum) {
		int checkWhole = secNum.indexOf('_');
		int checkFrac = secNum.indexOf('/');
		String sNumerator = "temp";
		if (checkFrac == -1) {
			sNumerator = "0";
		} else {
			sNumerator = secNum.substring(checkWhole + 1, checkFrac);
		}
		return sNumerator;
	}

	// find denominator of second number
	public static String denominatorTwo(String secNum) {
		int checkFrac = secNum.indexOf('/');
		String sDenominator = "temp";
		if (checkFrac == -1) {
			sDenominator = "1";
		} else {
			sDenominator = secNum.substring(checkFrac + 1);
		}
		return sDenominator;
	}

	// method for addition
	public static String addition(int w1, int n1, int d1, int w2, int n2, int d2, String first, String second) {
		// combine denominator
		int denominator = d1 * d2;
		// multiply numerators with respective denominators
		int numerator1 = n1 * d2;
		int numerator2 = n2 * d1;
		// find whole number
		int whole = w1 + w2;
		// check for negative numbers
		if (first.indexOf("-") != -1 && second.indexOf("-") != -1) {
			numerator1 *= -1;
			numerator2 *= -1;
		} else if (second.indexOf("-") != -1 && first.indexOf("-") == -1) {
			numerator2 *= -1;
		} else if (second.indexOf("-") == -1 && first.indexOf("-") != -1) {
			numerator1 *= -1;
		}
		// find whole numerator
		int numerator = numerator1 + numerator2;
		// simplify numerator if the numerator is greater or equal to denominator
		// (meaning it adds on to the whole)
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
		}
		// combine whole, numerator, and denominator into singular string
		String total = whole + "_" + Math.abs(numerator) + "/" + denominator;
		// test to see if numerator and denominator cancel (simplify into just whole)
		double testNum = numerator;
		double testDem = denominator;
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
			// test to see if there is no whole number (make total just a fraction)
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		// total =
		// simplifier(total);*****************************************************
		return total;
	}

	// method for subtraction
	public static String subtraction(int w1, int n1, int d1, int w2, int n2, int d2) {
		// combine denominator
		int denominator = d1 * d2;
		// multiply numerators with respective denominators
		int numerator1 = n1 * d2;
		int numerator2 = n2 * d1;
		int numerator = numerator1 - numerator2;
		// find whole number
		int whole = w1 - w2;
		// simplify numerator if the numerator is greater or equal to denominator
		// (meaning it adds on to the whole)
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
		}
		// combine whole, numerator, and denominator into singular string
		String total = whole + "_" + Math.abs(numerator) + "/" + denominator;
		// test to see if numerator and denominator cancel (simplify into just whole)
		double testNum = numerator;
		double testDem = denominator;
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
			// test to see if there is no whole number (make total just a fraction)
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		// total =
		// simplifier(total);*************************************************************8
		return total;
	}

	// method for multiplication
	public static String multiplication(int w1, int n1, int d1, int w2, int n2, int d2, String first, String second) {
		// multiply (find) denominator
		int denominator = d1 * d2;
		// find individual numerators
		int numerator1 = w1 * d1;
		int numerator2 = w2 * d2;
		if (first.indexOf("-") != -1 && second.indexOf("-") != -1) {
			n1 *= -1;
			n2 *= -1;
		} else if (second.indexOf("-") != -1 && first.indexOf("-") == -1) {
			n2 *= -1;
		}
		/*
		 * else if(second.indexOf("-") == -1 && first.indexOf("-") != -1) {
		 * 
		 * }
		 */
		numerator1 += n1; // 12
		numerator2 += n2;// w2
		int numerator = numerator1 * numerator2;// 24
		int whole = 0;
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator; // 12
			numerator = Math.abs((addWhole * denominator) - numerator); // 0
			whole += addWhole; // 12
		}
		/*
		 * if (whole * -1 == Math.abs(whole)) { whole = Math.abs(whole) * -1; }
		 */
		String total = whole + "_" + numerator + "/" + denominator;
		double testNum = numerator;
		double testDem = denominator;
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
			// test to see if there is no whole number (make total just a fraction)
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		// total =
		// simplifier(total);*****************************************************
		return total;
	}

	public static String division(int w1, int n1, int d1, int w2, int n2, int d2, String first, String second) {
		// multiply (find) denominator
		int denominator = d1;
		// find individual numerators
		int numerator1 = w1 * d1;
		int numerator2 = w2 * d2;
		if (first.indexOf("-") != -1 && second.indexOf("-") != -1) {
			n1 *= -1;
			n2 *= -1;
		} else if (second.indexOf("-") != -1 && first.indexOf("-") == -1 && second.indexOf("_") != -1) {
			n2 *= -1;
		}
		/*
		 * else if(second.indexOf("-") == -1 && first.indexOf("-") != -1) {
		 * 
		 * }
		 */
		numerator1 += n1;
		numerator2 += n2;
		int numerator = numerator1 * d2;
		denominator *= numerator2;
		int whole = 0;
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator; // 12
			numerator = Math.abs((addWhole * denominator) - numerator); // 0
			whole += addWhole; // 12
			denominator = Math.abs(denominator);
		}
		/*
		 * if (whole * -1 == Math.abs(whole)) { whole = Math.abs(whole) * -1; }
		 */
		String total = whole + "_" + numerator + "/" + denominator;
		double testNum = numerator;
		double testDem = denominator;
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
			// test to see if there is no whole number (make total just a fraction)
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		// simplifier(total);*****************************************************
		return total;
	}
	/*
	 * public static String simplifier(String total) { if (total.indexOf("_") == -1
	 * && total.indexOf("/") == -1) { return total; } else if (total.indexOf("_") ==
	 * -1 && total.indexOf("/") != -1) { return total; } else { if
	 * (total.contains("_")) { int under = total.indexOf("_"); int divisor =
	 * total.indexOf("/"); String one = total.substring(0, under); String two =
	 * total.substring(under + 1, divisor); String three = total.substring(divisor +
	 * 1); int orig = Integer.parseInt(one); int num = Integer.parseInt(two); int
	 * denom = Integer.parseInt(three); if (orig < 0) { num = num * -1; } orig =
	 * orig * denom; num = num + orig; total = num + "/" + denom; } else if
	 * (!(total.contains("/"))) { total = total + "/1"; } return total;
	 */
	/*
	 * String secondFrac = total.substring(total.indexOf("_") + 1); String wholeNum
	 * = total.substring(0,total.indexOf("_")); int whole =
	 * Integer.parseInt(wholeNum); String firstNum = secondFrac.substring(0,
	 * secondFrac.indexOf("/")); String secondNum =
	 * secondFrac.substring(secondFrac.indexOf("/") + 1); int first =
	 * Integer.parseInt(firstNum); int second = Integer.parseInt(secondNum); total =
	 * whole + "_" + first + "/" + second; return total; }
	 * 
	 * }
	 */

}
