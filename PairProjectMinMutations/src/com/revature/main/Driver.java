package com.revature.main;

import java.io.*;

import com.revature.mutate.*;

public class Driver {

	public static void main(String[] args) {
		try {
			writeInputToFile();
			// create an ObjectInputStream for the file we created before
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("bank.txt"));
			// read and print the start and end and cast it as string
			String start = (String) ois.readObject();
			String end = (String) ois.readObject();
			System.out.println("" + start);
			System.out.println("" + end);
			// read and print array and cast it as string
			String[] bank = (String[]) ois.readObject();
			String s2 = String.join(",", bank);
			System.out.println("" + s2);
			int done = MinMutations.calculateMutations(start,end,bank);
			System.out.println(done);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void writeInputToFile() {

		try {
			FileOutputStream out = new FileOutputStream("bank.txt");
			ObjectOutputStream oout = new ObjectOutputStream(out);
			String start = "AACCGGTT";
			String end = "AAACGGTA";
			String[] bank = { "AACCGGTA", "AACCGCTA", "AAACGGTA" };
			oout.writeObject(start);
			oout.writeObject(end);
			oout.writeObject(bank);
			oout.flush();
			oout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
