package com.example.routine;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class Add extends Activity {

	TextView from;
	TextView to;
	TextView addingDay;

	EditText subject;
	EditText teacher;

	Button add;

	int TIME_DIALOUGE_ID = 0;
	int POS;
	int currentHour, currentMinute;

	boolean flag1, flag2;

	String FILE_NAME, subjectName, teacherName, classFrom, classTo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.add);
		initialize();

		from.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOUGE_ID); // Time Picker Will be Shown
				flag1 = true;
				flag2 = false;

			}
		});

		to.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				flag2 = true;
				flag1 = false;
				showDialog(TIME_DIALOUGE_ID); // Time Picker Will be Shown

			}
		});

		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String a = subject.getText().toString();
				String c = from.getText().toString();
				String d = to.getText().toString();
				String b = teacher.getText().toString();

				// Toast.makeText(Add.this, a, Toast.LENGTH_SHORT).show();
				if (!a.equals("") && !b.equals("")) {

					try {
						String temp = a + "*" + b + "*" + c + "*" + d + "*";
						Calculation.dayFileContents[POS] += temp;

						FileOutputStream fis = openFileOutput(FILE_NAME,
								MODE_PRIVATE);
						fis.write(Calculation.dayFileContents[POS].getBytes());
						fis.close();

					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

					subject.setText("");
					teacher.setText("");

					// Hiding Keyboard
					InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
					inputMethodManager.hideSoftInputFromWindow(
							teacher.getWindowToken(), 0);

					Toast.makeText(Add.this, "Saved Successfully",
							Toast.LENGTH_SHORT).show();
					Add.this.finish();
				} else {
					if (a.equals(""))
						Toast.makeText(Add.this, "Input Subject Name!",
								Toast.LENGTH_SHORT).show();
					else if (b.equals(""))
						Toast.makeText(Add.this, "Input Course Code!",
								Toast.LENGTH_SHORT).show();
				}
			}

		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);

	}

	private void initialize() {
		from = (TextView) findViewById(R.id.addfromtimetbx);
		to = (TextView) findViewById(R.id.addtotimetbx);
		addingDay = (TextView) findViewById(R.id.addaddingdaytbx);

		subject = (EditText) findViewById(R.id.addsubjectedtbx);
		teacher = (EditText) findViewById(R.id.addteacheredtbx);

		add = (Button) findViewById(R.id.addaddbtn);

		// Extra String Passed With Intent
		subjectName = getIntent().getStringExtra("subject");
		teacherName = getIntent().getStringExtra("teacher");
		classFrom = getIntent().getStringExtra("from");
		classTo = getIntent().getStringExtra("to");
		POS = getIntent().getIntExtra("position", 0);
		FILE_NAME = Calculation.day[POS];

		subject.setText(subjectName);
		teacher.setText(teacherName);
		from.setText(classFrom);
		to.setText(classTo);
		addingDay.setText("Saving Routine At: " + FILE_NAME);

		// If Edit is clicked, Saving The Modified version To An Array But Not
		// In File
		if (!subjectName.equals(""))
			Calculation.fileEdit(POS, subjectName + "*" + teacherName + "*"
					+ classFrom + "*" + classTo + "*");

	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case 0:
			return new TimePickerDialog(this, mTimeSetListener, currentHour,
					currentMinute, false);

		default:
			break;
		}
		return null;
	}

	private TimePickerDialog.OnTimeSetListener mTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			currentHour = hourOfDay;
			currentMinute = minute;
			updateText(currentHour, currentMinute);
		}
	};

	private void updateText(int hours, int mins) {

		String timeSet = "", hourString = "";
		if (hours > 12) {
			hours -= 12;
			timeSet = "PM";
		} else if (hours == 0) {
			hours += 12;
			timeSet = "AM";
		} else if (hours == 12) {
			hours = 12;
			timeSet = "PM";
		} else
			timeSet = "AM";
		
		if(hours > 0 && hours < 10){
			hourString = "0" + String.valueOf(hours);
		}
		else
			hourString = String.valueOf(hours);
		
		String minutes = "";
		if (mins < 10)
			minutes = "0" + mins;
		else
			minutes = String.valueOf(mins);

		// Append in a StringBuilder
		String aTime = new StringBuilder().append(hourString).append(':')
				.append(minutes).append(" ").append(timeSet).toString();

		//Toast.makeText(this, String.valueOf(hours), Toast.LENGTH_SHORT).show();

		if (flag1 == true) {
			from.setText(aTime);
		} else if (flag2 == true) {
			to.setText(aTime);
		}

	}
}
