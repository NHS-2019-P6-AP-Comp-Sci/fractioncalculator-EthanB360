/**
 * @author Mr. Rasmussen
 */
package fracCalc;

import java.util.*;

public class FracCalc {
	public static void main(String[] args) {
		// TODO: Read the input from the user and call produceAnswer with an equation
		Scanner c = new Scanner(System.in);
		// take user input for numbers
		String userInput = c.nextLine();
		// loop to take continuous inputs until user types "quit"
		while (userInput.equals("quit") != true) {
			String check = produceAnswer(userInput);
			System.out.println(check);
			System.out.println("type \"quit\" to exit program");
			userInput = c.nextLine();
		}
		// closes scanner when program finishes
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
		String answer = "temp";
		int space = input.indexOf(" ");
		// find operator
		// for multiple operators
		int count = 0;
		// Sees how many operations there are
		for (int i = 0; i < input.length(); i++) {
			char next = input.charAt(i);
			if (next == ' ') {
				count++;
			}
		}
		count /= 2;
		// checks if there are no operands-return the fraction
		if (count == 0) {
			return input;
		}
		boolean error = false;
		String changing = input;
		String total = "";
		while (count > 0 && error == false) {
			int first = changing.indexOf(" ") + 1;
			String catcherror = changing.substring(first);
			int second = catcherror.indexOf(" ");
			// Error catching
			if (second != 1) {
				error = true;
				answer = "ERROR: Input is in an invalid format.";
				// original final checkpoint answer (without multiple ops)
			} else if (count == 1) {
				// separate all numbers
				space = changing.indexOf(" ");
				int operand = space + 1;
				char operation = changing.charAt(operand);
				String operator = changing.substring(space + 1, space + 2);
				String one = changing.substring(0, space);
				changing = changing.substring(operand + 2);
				String two = changing.substring(0);
				String w1 = wholeOne(one);
				String n1 = numeratorOne(one);
				String d1 = denominatorOne(one);
				String w2 = wholeTwo(two, input);
				String n2 = numeratorTwo(two);
				String d2 = denominatorTwo(two);
				// error check for invalid formating eg. 1 ++ 2 (multiple operators for 1
				// statement OR 1+2 (invalid spacing)
				if (operator.equals("+") == false && operator.equals("-") == false && operator.equals("*") == false
						&& operator.equals("/") == false) {
					answer = "ERROR: Input is in an invalid format.";
					return answer;
				}
				// error check for 0 as denominator
				if (d1.equals("0") == true || d2.equals("0") == true) {
					answer = "ERROR: Cannot divide by zero.";
					return answer;
				}
				// call each method for the 4 different operators
				if (operation == ('+')) {
					answer = addition(w1, n1, d1, w2, n2, d2, one, two);
				} else if (operation == ('-')) {
						answer = subtraction(w1, n1, d1, w2, n2, d2, one, two);
				} else if (operation == ('*')) {
					answer = multiplication(w1, n1, d1, w2, n2, d2, one, two);
				} else if (operation == ('/')) {
					answer = division(w1, n1, d1, w2, n2, d2, one, two);
					// error statement for wrong formating
				} else {
					answer = "ERROR: Input is in an invalid format.";
					break;
				}
				return answer;
			} else {
				// separate numbers
				space = changing.indexOf(" ");
				int operand = space + 1;
				char operation = changing.charAt(operand);
				String operator = changing.substring(space + 1, space + 2);
				String one = changing.substring(0, space);
				changing = changing.substring(operand + 2);
				int twO = changing.indexOf(" ");
				String two = changing.substring(0, twO);
				changing = changing.substring(twO);
				String w1 = wholeOne(one);
				String n1 = numeratorOne(one);
				String d1 = denominatorOne(one);
				String w2 = wholeTwo(two, input);
				String n2 = numeratorTwo(two);
				String d2 = denominatorTwo(two);
				// error check for invalid formating eg. 1 ++ 2 (multiple operators for 1
				// statement OR 1+2 (invalid spacing)
				if (operator.equals("+") == false && operator.equals("-") == false && operator.equals("*") == false
						&& operator.equals("/") == false) {
					answer = "ERROR: Input is in an invalid format.";
					return answer;
				}
				// call each method for the 4 different operators
				if (operation == ('+')) {
					total = addition(w1, n1, d1, w2, n2, d2, one, two);
				} else if (operation == ('-')) {
					if (two.indexOf("-") != -1 && one.indexOf("-") != -1) {
						total = addition(w1, n1, d1, w2, n2, d2, one, two);
					} else {
					total = subtraction(w1, n1, d1, w2, n2, d2, one, two);
					}
				} else if (operation == ('*')) {
					total = multiplication(w1, n1, d1, w2, n2, d2, one, two);
				} else if (operation == ('/')) {
					total = division(w1, n1, d1, w2, n2, d2, one, two);
					// error statement for wrong formating
				} else {
					total = "ERROR: Input is in an invalid format.";
					break;
				}
				changing = total + changing;
			}
			count--;
		}
		return answer;
	}

// TODO: Fill in the space below with any helper methods that you think you will
// need
	// find whole of first number
	public static String wholeOne(String firstNum) {
		int checkWhole = firstNum.indexOf('_');
		int checkFrac = firstNum.indexOf('/');
		String firstWhole = "temp";
		// no whole in this case
		if (checkWhole == -1 && checkFrac != -1) {
			firstWhole = "0";
			// only whole in this case, no fraction
		} else if (checkWhole == -1) {
			firstWhole = firstNum;
			// regular format (has whole and fraction)
		} else {
			firstWhole = firstNum.substring(0, checkWhole);
		}
		return firstWhole;
	}

	// find whole of second number (same process as wholeOne but using second
	// number)
	public static String wholeTwo(String secNum, String input) {
		int checkWhole = secNum.indexOf('_');
		int checkFrac = secNum.indexOf('/');
		String secondWhole = "temp";
		if (checkWhole == -1 && checkFrac != -1) {
			secondWhole = "0";
		} else if (checkWhole == -1) {
			secondWhole = secNum;
		} else {
			secondWhole = secNum.substring(0, checkWhole);
		}
		return secondWhole;
	}

	// find numerator of first number
	public static String numeratorOne(String firstNum) {
		int checkWhole = firstNum.indexOf('_');
		int checkFrac = firstNum.indexOf('/');
		String firstNumerator = "temp";
		// if there is no fraction numerator = 0
		if (checkFrac == -1) {
			firstNumerator = "0";
			// regular format of fraction (whole_numerator/denominator)
		} else {
			firstNumerator = firstNum.substring(checkWhole + 1, checkFrac);
		}
		return firstNumerator;
	}

	// find numerator of second number (same process as numeratorOne but using
	// second number)
	public static String numeratorTwo(String secNum) {
		int checkWhole = secNum.indexOf('_');
		int checkFrac = secNum.indexOf('/');
		String secondNumerator = "temp";
		if (checkFrac == -1) {
			secondNumerator = "0";
		} else {
			secondNumerator = secNum.substring(checkWhole + 1, checkFrac);
		}
		return secondNumerator;
	}

	// find denominator of first number
	public static String denominatorOne(String firstNum) {
		int checkFrac = firstNum.indexOf('/');
		String firstDenominator = "temp";
		// if there is no fraction denominator = 1
		if (checkFrac == -1) {
			firstDenominator = "1";
			// regular format of fraction (numerator/denominator)
		} else {
			firstDenominator = firstNum.substring(checkFrac + 1);
		}
		return firstDenominator;
	}

	// find denominator of second number (same process as denominatorOne but using
	// second number)
	public static String denominatorTwo(String secNum) {
		int checkFrac = secNum.indexOf('/');
		String secondDenominator = "temp";
		if (checkFrac == -1) {
			secondDenominator = "1";
		} else {
			secondDenominator = secNum.substring(checkFrac + 1);
		}
		return secondDenominator;
	}

	// method for addition
	public static String addition(String wh1, String nu1, String de1, String wh2, String nu2, String de2, String first,
			String second) {
		// parse all numbers into integers
		int w1 = Integer.parseInt(wh1);
		int n1 = Integer.parseInt(nu1);
		int d1 = Integer.parseInt(de1);
		int w2 = Integer.parseInt(wh2);
		int n2 = Integer.parseInt(nu2);
		int d2 = Integer.parseInt(de2);
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
			// only applies when first number has a whole
		} else if (second.indexOf("-") != -1 && first.indexOf("-") == -1 && first.indexOf("_") != -1) {
			numerator2 *= -1;
			// only applies when second number has a whole
		} else if (second.indexOf("-") == -1 && first.indexOf("-") != -1 && second.indexOf("_") != -1) {
			numerator1 *= -1;
		}
		// find whole numerator
		int numerator = numerator1 + numerator2;
		/*
		 * simplify numerator if the numerator is greater or equal to denominator
		 * (meaning it adds on to the whole)
		 */
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator;
			// absolute value used so numerator cannot have a negative in front of it
			// (formatting/conventions)
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
		} else if (numerator / denominator <= -1) {
			int addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
		}
		// simplify numerator and denominator
		int oldNumerator = numerator;
		numerator = simpNum(numerator, denominator);
		denominator = simpDenom(oldNumerator, denominator);
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
		return total;
	}

	// method for subtraction (almost identical to addition)
	public static String subtraction(String wh1, String nu1, String de1, String wh2, String nu2, String de2,
			String first, String second) {
		int checkWhole = 0;
		int addWhole = 0;
		// parse all numbers into integers
		int w1 = Integer.parseInt(wh1);
		int n1 = Integer.parseInt(nu1);
		int d1 = Integer.parseInt(de1);
		int w2 = Integer.parseInt(wh2);
		int n2 = Integer.parseInt(nu2);
		int d2 = Integer.parseInt(de2);
		// find numerators and denominator
		int denominator = d1 * d2;
		int numerator1 = n1 * d2;
		int numerator2 = n2 * d1;
		// subtract numerators instead of add
		int numerator = numerator1 - numerator2;
		// check if both mixed numbers are negative (add value instead of subtract)
		if (second.indexOf("-") != -1 && first.indexOf("-") == -1) {
			numerator = numerator1 + numerator2;
			// check if first value is negative (add instead of subtract, multiply total
			// numerator to make it negative)
		} else if (second.indexOf("-") == -1 && first.indexOf("-") != -1) {
			numerator = numerator1 + numerator2;
			numerator *= -1;
		}
		// whole is subtract instead of add
		int whole = w1 - w2;
		if (numerator / denominator >= 1) {
			addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
		} else if (numerator / denominator <= -1) {
			addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			if (numerator > n1) {
				numerator *= -1;
			}
			whole += addWhole;
			/*
			 * check for special exception of second number being bigger than the first and
			 * resulting in a fraction with whole = 0
			 */
		} else if (numerator1 > numerator2 && Math.abs(w1) < Math.abs(w2)) {
			numerator1 = n1 * d2 + w1 * (d1 * d2);
			numerator2 = n2 * d1 + w2 * (d1 * d2);
			numerator = numerator1 - numerator2;
			whole = numerator / denominator;
			// check for numerator being smaller than denominator but still existing
			// (without a whole)
		} else if ((numerator / denominator == 0 && first.indexOf("_") == -1 && first.indexOf("/") == -1
				&& second.indexOf("/") != -1)
				|| (numerator / denominator == 0 && second.indexOf("_") == -1 && second.indexOf("/") == -1)
						&& first.indexOf("/") != -1) {
			checkWhole = whole * denominator;
			numerator = checkWhole + numerator;
			addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole -= addWhole;
		} 
		//check for cases where numerator is still negative but whole is positive
		if (numerator < 0 && numerator / denominator == 0 && whole > 0 && second.indexOf("-") == -1) {
			checkWhole = whole * denominator;
			numerator = checkWhole + numerator;
			addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole = addWhole;
		}
		int oldNumerator = numerator;
		numerator = simpNum(numerator, denominator);
		denominator = simpDenom(oldNumerator, denominator);
		String total = whole + "_" + Math.abs(numerator) + "/" + denominator;
		double testNum = numerator;
		double testDem = denominator;
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		return total;
	}

	// method for multiplication
	public static String multiplication(String wh1, String nu1, String de1, String wh2, String nu2, String de2,
			String first, String second) {
		// parse all numbers into integers
		int w1 = Integer.parseInt(wh1);
		int n1 = Integer.parseInt(nu1);
		int d1 = Integer.parseInt(de1);
		int w2 = Integer.parseInt(wh2);
		int n2 = Integer.parseInt(nu2);
		int d2 = Integer.parseInt(de2);
		// multiply (find) denominator
		int denominator = d1 * d2;
		// find individual numerators
		int numerator1 = w1 * d1;
		int numerator2 = w2 * d2;
		String total = "temp";
		// check for negatives in both numbers
		if (first.indexOf("-") != -1 && second.indexOf("-") != -1) {
			n1 *= -1;
			n2 *= -1;
			// check for negatives in first number when the first number is a whole
		} else if (second.indexOf("-") == -1 && first.indexOf("-") != -1 && first.indexOf("_") != -1) {
			n1 *= -1;
			// check for negatives in second number when the second number is a whole
		} else if (second.indexOf("-") != -1 && first.indexOf("-") == -1 && second.indexOf("_") != -1) {
			n2 *= -1;
		}
		numerator1 += n1;
		numerator2 += n2;
		// combine numerators
		int numerator = numerator1 * numerator2;
		int whole = 0;
		// "simplify" fraction by adding excess into whole
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator;
			// ensure proper formatting/conventions (cannot have negative numerator if there
			// is a whole)
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
			// catch all fractions that do not divide into a positive result
		} else if (second.indexOf("-") != -1 || first.indexOf("-") != -1) {
			int addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
		}
		// simplify numerator and denominator
		int oldNumerator = numerator;
		numerator = simpNum(numerator, denominator);
		denominator = simpDenom(oldNumerator, denominator);
		// assemble total string
		total = whole + "_" + numerator + "/" + denominator;
		double testNum = numerator;
		double testDem = denominator;
		// test to see if there is no fraction (make total just a whole number)
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
			// test to see if there is no whole number (make total just a fraction)
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		return total;
	}

	// method for division (almost identical to multiplication)
	public static String division(String wh1, String nu1, String de1, String wh2, String nu2, String de2, String first,
			String second) {
		// parse all numbers into integers
		int w1 = Integer.parseInt(wh1);
		int n1 = Integer.parseInt(nu1);
		int d1 = Integer.parseInt(de1);
		int w2 = Integer.parseInt(wh2);
		int n2 = Integer.parseInt(nu2);
		int d2 = Integer.parseInt(de2);
		// find denominator
		int denominator = d1;
		// find individual numerators
		int numerator1 = w1 * d1;
		int numerator2 = w2 * d2;
		if (first.indexOf("-") != -1 && second.indexOf("-") != -1) {
			n1 *= -1;
			n2 *= -1;
			// check for negative second number
		} else if (second.indexOf("-") != -1 && first.indexOf("-") == -1) {
			n2 *= -1;
			// check special exception of no whole number for both numbers
			if (first.indexOf("_") == -1 && second.indexOf("_") == -1) {
				n2 *= -1;
			}
			// check for negative first number
		} else if (second.indexOf("-") == -1 && first.indexOf("-") != -1) {
			n1 *= -1;
		}
		numerator1 += n1;
		numerator2 += n2;
		// find total numerator
		int numerator = numerator1 * d2;
		// find total denominator;
		denominator *= numerator2;
		int whole = 0;
		if (numerator / denominator >= 1) {
			int addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
			denominator = Math.abs(denominator);
			/*
			 * check for all negative numbers that numerator > denominator that need to be
			 * added to whole
			 */
		} else if (numerator / denominator <= -1) {
			int addWhole = numerator / denominator;
			numerator = Math.abs((addWhole * denominator) - numerator);
			whole += addWhole;
			// denominator has to be positive
			denominator = Math.abs(denominator);
		}
		int oldNumerator = numerator;
		numerator = simpNum(numerator, denominator);
		denominator = simpDenom(oldNumerator, denominator);
		String total = whole + "_" + numerator + "/" + denominator;
		double testNum = numerator;
		double testDem = denominator;
		if ((testNum / testDem) == 0.0) {
			total = whole + "";
		} else if (whole == 0) {
			total = numerator + "/" + denominator;
		}
		// check if denominator is negative (special exception)
		if (denominator < 0 && numerator > 0) {
			// moves negative from denominator to numerator
			numerator *= -1;
			denominator *= -1;
			// if denominator is 1 makes total just numerator (gets rid of denominator)
			if (denominator == 1) {
				total = numerator + "";
				// "re-simplify" numerator and denominator
			} else {
				numerator = simpNum(numerator, denominator);
				denominator = simpDenom(oldNumerator, denominator);
				total = numerator + "/" + denominator;
			}
		}
		return total;
	}

	// simplifier program for numerator
	public static int simpNum(int numerator, int denominator) {
		// loop to simplify (checks all values possible under denominator-changes if
		// denominator and numerator simplify)
		for (int i = 1; i < denominator; i++) {
			int checkNum = numerator % i;
			int checkDenom = denominator % i;
			/*
			 * checks if both denominator and numerator can simplify with number i (by
			 * leaving no denominator)
			 */
			if (checkDenom == 0 && checkNum == 0) {
				// divides both numerator and denominator to simplify
				numerator = numerator / i;
				denominator = denominator / i;
				// resets i if it works to check for all factors
				i = 1;
			}
			/*
			 * if does not simplify i will run until it is 1 less than denominator, then
			 * that means the fraction is in simplest form
			 */
		}
		return numerator;
	}

	// simplifier for denominator (identical to numerator but returns denominator)
	public static int simpDenom(int numerator, int denominator) {
		for (int i = 1; i < denominator; i++) {
			int checkNum = numerator % i;
			int checkDenom = denominator % i;
			if (checkDenom == 0 && checkNum == 0) {
				numerator = numerator / i;
				denominator = denominator / i;
				i = 1;
			}
		}
		return denominator;
	}
}