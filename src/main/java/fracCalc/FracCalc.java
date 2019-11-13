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
		produceAnswer(userInput);
		System.out.println(produceAnswer(userInput));
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
	public static String produceAnswer(String input)
    {
        // TODO: Implement this function to produce the solution to the input
    	int space = input.indexOf(" ");
    	//int fraction = input.length();
    	String firstNum = input.substring(0,space);
    	//int fNum = Integer.parseInt(firstNum);
    	String secNum = input.substring(space + 3);
    	//int sNum = Integer.parseInt(secNum);
    	//String operator = input.substring(space + 1);
    	//if (operator == "+") {
    		
    		//}
	return secNum;
}

// TODO: Fill in the space below with any helper methods that you think you will
// need

}
