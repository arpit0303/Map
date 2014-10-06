package in.jaaga.maps;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;

public class MainActivity extends Activity {

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
				overflowMenu.getMenuInflater().inflate(R.menu.main, overflowMenu.getMenu());
				
				overflowMenu.setOnMenuItemClickListener(new OnMenuItemClickListener() {
					
					@Override
					public boolean onMenuItemClick(MenuItem item) {
						int id = item.getItemId();
						switch (id) {
						case R.id.action_setting:
							return true;

						default:
							break;
						}
						return false;
					}
				});
				
				overflowMenu.show();
			}
		});
        
        
    }
    
}
