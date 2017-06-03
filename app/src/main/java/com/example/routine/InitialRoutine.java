package com.example.routine;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;

public class InitialRoutine {

	static String FILENAME = "Initial Routine";
	public static Context context;

	public static int isFile() {
		int flag = 0;

		try {
			FileInputStream fis = context.openFileInput(FILENAME);
			BufferedInputStream bis = new BufferedInputStream(fis);

			flag = bis.read();
			bis.close();
			fis.close();

		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return flag;
	}

	public static void disableRoutine() {
		try {
			FileOutputStream fos = context.openFileOutput(FILENAME, 0);
			fos.write("1".getBytes());
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void ru_cse_13_14_3_1()
	{
		String[] routine = new String[7];

		// Routine
		routine[0] = "Software Engneering*CSE3111*09:20 AM*10:20 AM*"
				+ "Database Management Systems*CSE3121*10:25 AM*11:25 AM*"
				+ "Database Management Systems Lab*CSE3122*11:30 AM*1:35 PM*";

		routine[1] = "Computer Networks*CSE3151*08:15 AM*09:15 AM*"
				+ "Digital Signal Processing*CSE3131*10:25 AM*11:25 AM*"
				+ "Compiler Design Lab*CSE3142*11:30 AM*1:35 PM*";

		routine[2] = "Database Management Systems*CSE3121*09:20 AM*10:20 AM*"
				+ "Compiler Design*CSE3141*10:25 AM*11:25 PM*"
				+ "Software Engneering*CSE3111*11:30 AM*12:30 PM*"
				+ "Software Engneering Lab III*CSE3162*12:35 PM*01:35 PM*";

		routine[3] = "Compiler Design*CSE3141*08:15 AM*09:15 AM*"
				+ "Computer Networks*CSE3151*09:20 AM*10:20 AM*"
				+ "Digital Signal Processing*CSE3131*10:25 AM*11:25 AM*"
				+ "Digital Signal Processing Lab*CSE3132*11:30 AM*01:35 PM*";

		routine[4] = "Software Engneering*CSE3111*08:15 AM*09:15 AM*"
				+ "Software Engneering Lab*CSE3112*09:20 AM*11:25 AM*"
				+ "Database Management Systems*CSE3121*11:30 AM*12:35 PM*";

		routine[5] = "Compiler Design*CSE3141*08:15 AM*09:15 AM*"
				+ "Computer Networks*CSE3151*09:20 AM*10:20 AM*"
				+ "Digital Signal Processing*CSE3131*10:25 AM*11:25 AM*"
				+ "Computer Networks Lab*CSE3152*11:30 AM*01:35 PM*";

		routine[6] = "";

		for (int i = 0; i < Calculation.day.length; i++) {
			try {
				FileOutputStream fos = context.openFileOutput(
						Calculation.day[i], 0);

				fos.write(routine[i].getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void ru_cse_13_14_3_2()
	{
		String[] routine = new String[7];

		// Routine
		routine[0] = "System Analysis and Design*CSE3211 (room : 120)*09:20 AM*10:20 AM*"
				+ "Microproccessor and Assembly Language*CSE3231 (room : 120)*10:25 AM*11:25 AM*"
				+ "Microproccessor and Assembly Language Lab*CSE3232 (room : 217)*11:30 AM*1:35 PM*";

		routine[1] = "Communication Engneering*ICE3261 (room : 217)*09:20 AM*10:20 AM*"
				+ "Operating System and System Programming*CSE3241 (room : 120)*10:25 AM*11:25 AM*"
				+ "Operating System and System Programming Lab*CSE3242 (room : 217)*11:30 AM*1:35 PM*";

		routine[2] = "Microproccessor and Assembly Language*CSE3231 (room : 120)*10:25 AM*11:25 AM*"
				+ "System Analysis and Design*CSE3211 (room : 217)*11:30 AM*12:30 PM*"
				+ "Computer Graphics*CSE3221 (room : 217)*12:35 AM*01:35 PM*";

		routine[3] = "Operating System and System Programming*CSE3241 (room : 120)*09:20 AM*10:20 AM*"
				+ "Communication Engneering*ICE3261 (room : 217)*10:25 AM*11:25 AM*"
				+ "Communication Engneering Lab*ICE3261 (room : 217)*11:30 AM*01:35 PM*";

		routine[4] = "Microproccessor and Assembly Language*CSE3231 (room : 217)*08:15 AM*09:15 AM*"
				+ "Computer Graphics Lab*CSE3221 (room : 217)*09:20 AM*11:25 PM*"
				+ "System Analysis and Design*CSE3211 (room : 217)*11:30 AM*12:30 PM*"
				+ "Computer Graphics*CSE3221 (room : 217)*12:35 AM*01:35 PM*";

		routine[5] =  "Communication Engneering*ICE3261 (room : 217)*10:25 AM*11:25 AM*"
				+ "Operating System and System Programming*CSE3241 (room : 217)*11:30 AM*12:30 PM*"
				+ "Computer Graphics*CSE3221 (room : 217)*12:35 AM*01:35 PM*";

		routine[6] = "";

		for (int i = 0; i < Calculation.day.length; i++) {
			try {
				FileOutputStream fos = context.openFileOutput(
						Calculation.day[i], 0);

				fos.write(routine[i].getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public static void ru_cse_13_14_2_2() {
		String[] routine = new String[7];

		// Routine
		routine[0] = "Computer Architecture And Organization*CSE2231*09:20 AM*10:20 AM*"
				+ "Design and Analysis of Algorithm*CSE2221*10:25 AM*11:25 AM*"
				+ "Design and Analysis of Algorithm Lab*CSE2222*11:30 AM*1:30 PM*";

		routine[1] = "Numerical Methods*MATH2231*09:20 AM*10:20 AM*"
				+ "Cyber and Intellectual Property Law*CSE2211*10:25 AM*11:25 AM*"
				+ "Object Oriented Programming and Design Methods with Java Lab*CSE2212*11:30 AM*1:30 PM*";

		routine[2] = "Design and Analysis of Algorithm*CSE2221*10:25 AM*11:25 AM*"
				+ "Object Oriented Programming and Design Methods*CSE2211*11:30 AM*12:30 PM*"
				+ "Software Development Lab II*CSE2242*12:35 PM*1:30 PM*";

		routine[3] = "Cyber and Intellectual Property Law*CSE2211*09:20 AM*10:20 AM*"
				+ "Computer Architecture And Organization*CSE2231*10:25 AM*11:25 AM*"
				+ "Computer Architecture And Organization Lab*CSE2232*11:30 AM*1:30 PM*";

		routine[4] = "Design and Analysis of Algorithm*CSE2221*09:20 AM*10:20 AM*"
				+ "Cyber and Intellectual Property Law*CSE2211*10:25 AM*11:25 AM*"
				+ "Computer Architecture And Organization*CSE2231*11:30 AM*12:30 PM*";

		routine[5] = "Object Oriented Programming and Design Methods*CSE2211*09:20 AM*10:20 AM*"
				+ "Numerical Methods*MATH2231*10:25 AM*11:25 AM*"
				+ "Numerical Methods Lab*MATH2232*11:30 AM*1:30 PM*";

		routine[6] = "";

		for (int i = 0; i < Calculation.day.length; i++) {
			try {
				FileOutputStream fos = context.openFileOutput(
						Calculation.day[i], 0);

				fos.write(routine[i].getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
