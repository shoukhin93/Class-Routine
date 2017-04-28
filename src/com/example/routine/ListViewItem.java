package com.example.routine;

import android.widget.Toast;

public class ListViewItem implements Comparable<ListViewItem> {

	String subjectName, teacherName, from, to;

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public ListViewItem(String subjectName, String teacherName, String from,
			String to) {
		super();
		this.subjectName = subjectName;
		this.teacherName = teacherName;
		this.from = from;
		this.to = to;
	}

	@Override
	public int compareTo(ListViewItem another) {

		// if value is between 1.00 to 9.59 length will be 7
		if (this.from.length() == 7) {
			this.from = "0" + this.from;
		}
		if (another.from.length() == 7) {
			another.from = "0" + another.from;
		}
		
		String tempString1 = this.from;
		String tempString2 = another.from;
		String tempString = "";
		
		if (this.from.charAt(from.length() - 2) == 'P') {
			tempString = "";
			
			tempString += from.charAt(0);
			tempString+= from.charAt(1);

			int temp = Integer.parseInt(tempString);
			temp += 12;
			
			
			tempString = String.valueOf(temp);

			for(int i = 2; i < this.from.length(); i++)
				tempString += this.from.charAt(i);
			//System.out.println(tempString);
			tempString1 = tempString;
		}
		
		if (another.from.charAt(another.from.length() - 2) == 'P') {
			
			tempString = "";
			
			tempString += another.from.charAt(0);
			tempString += another.from.charAt(1);
			
			int temp = Integer.parseInt(tempString);
			temp += 12;
			
			tempString = String.valueOf(temp);

			for(int i = 2; i < another.from.length(); i++)
				tempString += another.from.charAt(i);
			
			tempString2 = tempString;
		}

		return (tempString1).compareTo(tempString2);
	}
}
