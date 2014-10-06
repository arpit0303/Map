package in.jaaga.maps;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MainActivity extends Activity {

	GoogleMap maps;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		getActionBar().hide();

		ImageButton overflowMenu = (ImageButton) findViewById(R.id.overflow_menu);

		overflowMenu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				PopupMenu overflowMenu = new PopupMenu(MainActivity.this, v);
				overflowMenu.getMenuInflater().inflate(R.menu.main,
						overflowMenu.getMenu());

				overflowMenu
						.setOnMenuItemClickListener(new OnMenuItemClickListener() {

							@Override
							public boolean onMenuItemClick(MenuItem item) {
								int id = item.getItemId();
								switch (id) {
								case R.id.action_setting:
									Intent intent = new Intent(
											MainActivity.this,
											SettingsActivity.class);
									startActivity(intent);
									return true;

								default:
									return false;
								}
							}
						});
				overflowMenu.show();
			}
		});

		maps = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();
		switch (Common.TYPE) {
		case 0:
			maps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			overflowMenu.setImageResource(R.drawable.ic_action_core_overflow);
			break;
		case 1:
			maps.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			overflowMenu.setImageResource(R.drawable.m1);
			break;
		case 2:
			maps.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			overflowMenu.setImageResource(R.drawable.m1);
			break;
		case 3:
			maps.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			overflowMenu.setImageResource(R.drawable.ic_action_core_overflow);
			break;
		case 4:
			maps.setMapType(GoogleMap.MAP_TYPE_NONE);
			overflowMenu.setImageResource(R.drawable.ic_action_core_overflow);
			break;
		default:
			maps.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		}

		maps.setMyLocationEnabled(Common.LOCATION);
		maps.setTrafficEnabled(Common.TRAFFIC);
		maps.setBuildingsEnabled(Common.BUILDINGS);

		
		maps.addMarker(new MarkerOptions()
				.position(new LatLng(Common.LAT, Common.LAG))
				.title(Common.MARKER_TITLE)
				.visible(Common.MARKER)
				.draggable(true));

	}

}
