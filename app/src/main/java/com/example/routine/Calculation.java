package com.example.routine;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.content.Context;
import android.provider.OpenableColumns;
import android.widget.Toast;

public class Calculation {

    public static String[] day = {"Saturday", "Sunday", "Monday", "Tuesday",
            "Wednesday", "Thursday", "Friday"};
    public static Context context;

    public static String[] dayFileContents = new String[8];

    public Calculation(Context context) {
        super();
        Calculation.context = context;
    }

    public static void fileRead() {
        for (int i = 0; i < day.length; i++) {
            try {
                dayFileContents[i] = "";
                FileInputStream fis = context.openFileInput(day[i]);
                BufferedInputStream bis = new BufferedInputStream(fis);

                while (bis.available() != 0) {
                    char c = (char) bis.read();
                    dayFileContents[i] += c;
                }
                bis.close();
                fis.close();
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void fileDelete(int position, String content) {

        int c = 0;
        int l = dayFileContents[position].length();

        String temp = "", fileToWrite = "";

        for (int i = 0; i < l; i++) {
            if (dayFileContents[position].charAt(i) == '*') {
                c++;
            }
            if (c == 4) {
                c = 0;
                temp += "*";
                if (temp.equals(content)) {
                    temp = "";
                    continue;
                } else
                    fileToWrite += temp;
                temp = "";

            } else
                temp += dayFileContents[position].charAt(i);

        }
        //Toast.makeText(context, content +"\n" + fileToWrite , Toast.LENGTH_LONG).show();

        try {
            FileOutputStream fos = context.openFileOutput(day[position], 0);
            fos.write(fileToWrite.getBytes());
            fos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void fileEdit(int position, String content) {
        int c = 0;
        String temp = "", modifiedFile = "";

        for (int i = 0; i < dayFileContents[position].length(); i++) {
            if (dayFileContents[position].charAt(i) == '*') {
                c++;
            }

            if (c == 4) {
                c = 0;
                temp += "*";

                if (temp.equals(content)) {

                    temp = "";
                    continue;
                } else
                    modifiedFile += temp;
                temp = "";

            } else
                temp += dayFileContents[position].charAt(i);
        }

        dayFileContents[position] = modifiedFile;
    }

    public static void clearAllData() {

        for (int i = 0; i < day.length; i++) {
            try {
                FileOutputStream fos = context.openFileOutput(day[i], 0);
                fos.close();
            } catch (FileNotFoundException e) {

                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
