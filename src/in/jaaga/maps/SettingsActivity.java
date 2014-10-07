package in.jaaga.maps;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

public class SettingsActivity extends Activity {

	String[] mapType = { "Normal", "Hybrid", "Satellite", "Terrain", "None" };
	EditText title;
	EditText Lat;
	EditText Lan;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		// Show the Up button in the action bar.
		setupActionBar();

		// Different Type of Maps
		Spinner type = (Spinner) findViewById(R.id.spinner1);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, mapType);
		type.setAdapter(adapter);
		type.setSelection(Common.TYPE);

		type.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View v,
					int position, long id) {
				Common.TYPE = position;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		// Adding Location
		CheckBox loc = (CheckBox) findViewById(R.id.loc);
		loc.setChecked(Common.LOCATION);
		loc.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Common.LOCATION = arg1;
			}
		});

		// Traffic info
		CheckBox traffic = (CheckBox) findViewById(R.id.traffic);
		traffic.setChecked(Common.TRAFFIC);
		traffic.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Common.TRAFFIC = arg1;
			}
		});

		//Show Buildings
		CheckBox building = (CheckBox) findViewById(R.id.building);
		building.setChecked(Common.BUILDINGS);
		building.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
				Common.BUILDINGS = arg1;
			}
		});

		// Adding Marker
		CheckBox marker = (CheckBox) findViewById(R.id.marker);
		final LinearLayout latLag = (LinearLayout) findViewById(R.id.lay1);
		title = (EditText) findViewById(R.id.title);
		Lat = (EditText) findViewById(R.id.lat);
		Lan = (EditText) findViewById(R.id.lan);

		marker.setChecked(Common.MARKER);

		if (marker.isChecked()) {
			latLag.setVisibility(View.VISIBLE);

			title.setText(Common.MARKER_TITLE);

			Double latitude = Common.LAT;
			if (latitude != 0.0) {
				String mlat = Double.toString(latitude);
				Lat.setText(mlat);
			}
			Double longitude = Common.LAG;
			if (longitude != 0.0) {
				String mlan = Double.toString(longitude);
				Lan.setText(mlan);
			}
		} else {
			latLag.setVisibility(View.GONE);
		}
		marker.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				Common.MARKER = isChecked;
				if (isChecked) {
					latLag.setVisibility(View.VISIBLE);
				} else {
					latLag.setVisibility(View.GONE);
				}
			}
		});

	}

	/**
	 * Set up the {@link android.app.ActionBar}.
	 */
	private void setupActionBar() {

		getActionBar().setDisplayHomeAsUpEnabled(true);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back

			String mTitle = title.getText().toString();
			if (!(mTitle.isEmpty())) {
				Common.MARKER_TITLE = mTitle;
			}
			String mLat = Lat.getText().toString();
			if (!(mLat.isEmpty())) {
				Common.LAT = Double.parseDouble(mLat);
			}
			String mLan = Lan.getText().toString();
			if (!(mLan.isEmpty())) {
				Common.LAG = Double.parseDouble(mLan);
			}

			Log.i("set", "Title: " + mTitle + " lat: " + mLat);
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
