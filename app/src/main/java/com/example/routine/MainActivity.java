package com.example.routine;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.view.GestureDetectorCompat;
import android.view.ContextMenu;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private GestureDetectorCompat gDetect;

	Button back, next;

	TextView textView, mainroutinefortbx;

	ArrayList<ListViewItem> arrayList;
	BaseAdapter adapter;

	ListView listView;

	int MAX = 6;
	int MIN = 0;
	int POS = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_main);

		// Initializing EVERYTHING
		initialize();

		// Initial Routine
		InitialRoutine();

		// Reading files and adding to list view
		Calculation.context = this;
		Calculation.fileRead();
		showListViewItems();

		// If next button is clicked, next day's information will be showed
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				POS++;
				if (POS > MAX)
					POS = MIN;
				showListViewItems();

			}
		});

		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				POS--;
				if (POS < MIN)
					POS = MAX;
				showListViewItems();

			}
		});
	}

	private void InitialRoutine() {
		InitialRoutine.context = this;
		int flag = InitialRoutine.isFile(); // If App Runs for First Time

		// Writing data and disabling First Time Run Option
		if (flag == 0) {
			InitialRoutine.ru_cse_13_14_3_2();
			InitialRoutine.disableRoutine();
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		this.gDetect.onTouchEvent(event);
		return super.onTouchEvent(event);
	}

	public class GestureListener extends
			GestureDetector.SimpleOnGestureListener {
		private float flingMin = 100;
		private float velocityMin = 100;

		@Override
		public boolean onDown(MotionEvent event) {
			return true;
		}

		@Override
		public boolean onFling(MotionEvent event1, MotionEvent event2,
				float velocityX, float velocityY) {
			// user will move forward through messages on fling up or left
			boolean forward = false;
			// user will move backward through messages on fling down or right
			boolean backward = false;

			// calculate the change in X position within the fling gesture
			float horizontalDiff = event2.getX() - event1.getX();
			// calculate the change in Y position within the fling gesture
			float verticalDiff = event2.getY() - event1.getY();
			float absHDiff = Math.abs(horizontalDiff);
			float absVDiff = Math.abs(verticalDiff);
			float absVelocityX = Math.abs(velocityX);
			float absVelocityY = Math.abs(velocityY);

			if (absHDiff > absVDiff && absHDiff > flingMin
					&& absVelocityX > velocityMin) {
				if (horizontalDiff > 0)
					backward = true;
				else
					forward = true;
			} else if (absVDiff > flingMin && absVelocityY > velocityMin) {
				if (verticalDiff > 0)
					backward = true;
				else
					forward = true;
			}

			if (forward) {
				POS++;
				if (POS > MAX)
					POS = MIN;
				showListViewItems();
			} else if (backward) {
				POS--;
				if (POS < MIN)
					POS = MAX;
				showListViewItems();
			}

			return true;
		}
	}

	// Function to show and decode the contents from file
	private void showListViewItems() {
		Calculation.fileRead();
		textView.setText(Calculation.day[POS]);
		arrayList.clear();

		// File to be decoded
		String temp = Calculation.dayFileContents[POS];
		int c = 0;
		String[] ara = new String[4];

		// Decoding file and showing
		for (int i = 0; i < temp.length(); i++) {
			if (temp.charAt(i) != '*') {
				String temp1 = "";
				while (i < temp.length() && temp.charAt(i) != '*') {
					temp1 += temp.charAt(i);
					i++;
				}

				ara[c++] = temp1;

				if (c == 4) {
					ListViewItem item = new ListViewItem(ara[0], ara[1],
							ara[2], ara[3]);
					arrayList.add(item);

					c = 0;

				}
			}
		}

		Collections.sort(arrayList);

		adapter.notifyDataSetChanged();

	}

	// Function to be initialized everything
	private void initialize() {
		listView = (ListView) findViewById(R.id.mainlistview);
		textView = (TextView) findViewById(R.id.t);
		mainroutinefortbx = (TextView) findViewById(R.id.mainroutinefortbx);

		gDetect = new GestureDetectorCompat(this, new GestureListener());

		back = (Button) findViewById(R.id.mainbuttonback);
		next = (Button) findViewById(R.id.mainbuttonnext);

		arrayList = new ArrayList<ListViewItem>();

		// Adapter to view data in listview
		adapter = new BaseAdapter() {

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				if (convertView == null) {
					convertView = inflater.inflate(R.layout.listview, null);
				}

				TextView one = (TextView) convertView
						.findViewById(R.id.listviewtv1);

				TextView two = (TextView) convertView
						.findViewById(R.id.listviewtv2);

				TextView three = (TextView) convertView
						.findViewById(R.id.listviewtv3);

				one.setText(arrayList.get(position).getSubjectName());
				two.setText(arrayList.get(position).getTeacherName());
				three.setText("From " + arrayList.get(position).getFrom()
						+ " To " + arrayList.get(position).getTo());

				return convertView;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return arrayList.get(position);
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return arrayList.size();
			}
		};

		listView.setAdapter(adapter);
		registerForContextMenu(listView); // registering for long click menu

		// Current day of week, initializing to show today's routine at start
		Calendar calendar = Calendar.getInstance();
		POS = calendar.get(Calendar.DAY_OF_WEEK);

		if (POS > MAX)
			POS = 0;

		// Type face
		Typeface type = Typeface
				.createFromAsset(getAssets(), "fonts/ARIAL.TTF");

		mainroutinefortbx.setTypeface(type);
		textView.setTypeface(type);

	}

	// Function to show main menu item
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// Function to show list view on long click menu item
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.mainlistview) {
			menu.setHeaderTitle("Select an option: ");
			String[] menuItems = getResources().getStringArray(
					R.array.longclickmenu);
			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	// Function to show list view on click selected menu Item
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		View view = info.targetView;

		int menuItemIndex = item.getItemId();

		String[] menuItems = getResources().getStringArray(
				R.array.longclickmenu);
		String menuItemName = menuItems[menuItemIndex];

		String listItemName1 = ((TextView) view.findViewById(R.id.listviewtv1))
				.getText().toString();
		String listItemName2 = ((TextView) view.findViewById(R.id.listviewtv2))
				.getText().toString();
		String listItemName3 = ((TextView) view.findViewById(R.id.listviewtv3))
				.getText().toString();

		// Making Substring For Passing Prime Data
		String modifiedListItem1 = listItemName1;
		String modifiedListItem2 = listItemName2;
		String modifiedListItem3 = listItemName3.substring(5, 13);
		String modifiedListItem4 = listItemName3.substring(17,
				listItemName3.length());

		// If edit option selected
		if (menuItemName.equals("Edit")) {
			Intent intent = new Intent(this, Add.class);
			intent.putExtra("position", POS);

			intent.putExtra("subject", modifiedListItem1);
			intent.putExtra("teacher", modifiedListItem2);
			intent.putExtra("from", modifiedListItem3);
			intent.putExtra("to", modifiedListItem4);

			startActivity(intent);

		}
		// If Delete Item Selected
		else if (menuItemName.equals("Delete")) {
			ListViewItem listViewItem = arrayList.get(info.position);
			String content = modifiedListItem1 + "*"
					+ listViewItem.getTeacherName() + "*"
					+ listViewItem.getFrom() + "*" + listViewItem.getTo() + "*";

			 //Toast.makeText(this, content , Toast.LENGTH_LONG).show();
			Calculation.fileDelete(POS, content);

			arrayList.remove(info.position);
			adapter.notifyDataSetChanged();
			Toast.makeText(this, "Deleted Successfully", Toast.LENGTH_SHORT)
					.show();
		}

		return true;
	}

	// Function To Select Main Menu Item
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == R.id.add) {
			Intent intent = new Intent(this, Add.class);
			intent.putExtra("position", POS);
			intent.putExtra("subject", "");
			intent.putExtra("teacher", "");
			intent.putExtra("from", "12:00 PM");
			intent.putExtra("to", "12:40 PM");
			startActivity(intent);
		}

		else if (item.getItemId() == android.R.id.home) {
			this.finish();
		}

		else if (item.getItemId() == R.id.clearalldata) {
			clearAllData();
		} else if (item.getItemId() == R.id.about) {
			Intent intent = new Intent(MainActivity.this, About.class);
			startActivity(intent);
		}

		else if (item.getItemId() == R.id.exit) {
			this.finish();
		}
		return super.onOptionsItemSelected(item);
	}

	private void clearAllData() {
		AlertDialog.Builder exitAlert = new AlertDialog.Builder(
				MainActivity.this);
		exitAlert.setMessage("Are You Sure To Clear All Data?");
		exitAlert.setCancelable(false);

		// if yes is clicked
		exitAlert.setPositiveButton("Yes",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// Clearing All Data
						Calculation.clearAllData();
						showListViewItems();
						Toast.makeText(MainActivity.this, "Cleared All Data",
								Toast.LENGTH_SHORT).show();

					}
				});

		// if no is clicked
		exitAlert.setNegativeButton("No",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.cancel();

					}
				});

		AlertDialog alert = exitAlert.create();
		alert.show();

	}

	@Override
	protected void onResume() {
		super.onResume();
		
		showListViewItems();
	}
}
