package com.revature.main;

import java.util.NoSuchElementException;
import java.util.Scanner;
import com.revature.calc.*;

public class Driver {
	// takes input from user, and parses their query to perform the appropriate
	// calculator operation
	public static void main(String[] args) {
		System.out.println("Give me a formula");
		System.out.println("Must have spaces between numbers and operator:");
		Scanner userInput = new Scanner(System.in);
		String equation = userInput.nextLine();
		System.out.println("you input : " + equation);
		// equation = equation.replaceAll("\\s+", "");
		// System.out.println("you input without space : " + equation);
		Scanner parseEq = new Scanner(equation);
		String e1 = parseEq.next();
		try {
			String e2 = parseEq.next();
			String e3 = parseEq.next();
			//System.out.println(e1 + " was first");
			//System.out.println(e2 + " was second");
			//System.out.println(e3 + " was third");
			int inum1;
			int inum2;
			float fnum1;
			float fnum2;
			if (e2.equals("+")) {
				// addition
				if (isInteger(e1) && isInteger(e3)) {
					int ans = Calculator.add(Integer.parseInt(e1), Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isFloat(e1) && isInteger(e3)) {
					float ans = Calculator.add(Float.parseFloat(e1), (float) Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isInteger(e1) && isFloat(e3)) {
					float ans = Calculator.add((float) Integer.parseInt(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				} else {
					float ans = Calculator.add(Float.parseFloat(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				}
			} else if (e2.equals("-")) {
				if (isInteger(e1) && isInteger(e3)) {
					int ans = Calculator.sub(Integer.parseInt(e1), Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isFloat(e1) && isInteger(e3)) {
					float ans = Calculator.sub(Float.parseFloat(e1), (float) Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isInteger(e1) && isFloat(e3)) {
					float ans = Calculator.sub((float) Integer.parseInt(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				} else {
					float ans = Calculator.sub(Float.parseFloat(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				}
			} else if (e2.equals("*")) {
				if (isInteger(e1) && isInteger(e3)) {
					int ans = Calculator.mul(Integer.parseInt(e1), Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isFloat(e1) && isInteger(e3)) {
					float ans = Calculator.mul(Float.parseFloat(e1), (float) Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isInteger(e1) && isFloat(e3)) {
					float ans = Calculator.mul((float) Integer.parseInt(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				} else {
					float ans = Calculator.mul(Float.parseFloat(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				}
			} else {
				// division... hopefully
				if (isInteger(e1) && isInteger(e3)) {
					int ans = Calculator.div(Integer.parseInt(e1), Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isFloat(e1) && isInteger(e3)) {
					float ans = Calculator.div(Float.parseFloat(e1), (float) Integer.parseInt(e3));
					System.out.println("answer is " + ans);
				} else if (isInteger(e1) && isFloat(e3)) {
					float ans = Calculator.div((float) Integer.parseInt(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				} else {
					float ans = Calculator.div(Float.parseFloat(e1), Float.parseFloat(e3));
					System.out.println("answer is " + ans);
				}
			}
		} catch (NoSuchElementException e) {
			System.out.println("Please format with spaces between numbers and operator");
		}

	}

	public static boolean isInteger(String s) {
		try {
			int num = Integer.parseInt(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	public static boolean isFloat(String s) {
		try {
			float num = Float.parseFloat(s);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

}
