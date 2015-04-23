package com.example.propertymgmtapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MyProperties extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myproperties);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		//Set up listeners for the buttons         
        View searchPropButton = findViewById(R.id.btnSearchProp);         
        searchPropButton.setOnClickListener(this); 
        
        View addPropertyButton = findViewById(R.id.btnAddProperty);         
        addPropertyButton.setOnClickListener(this);
        
        TextView tv = (TextView) findViewById(R.id.tvSQLinfo);
		DbHandler info = new DbHandler(this);
		info.open();
		String data = info.getData();
		info.close();
		tv.setText(data);
        
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
		getMenuInflater().inflate(R.menu.my_properties, menu);
		return true;
	}
	
	  public void onClick(View v) {   
	    	switch (v.getId()){   
	    	case R.id.btnAddProperty:    
	    		Intent i = new Intent(this, AddProperty.class);   
	    		startActivity(i);    
	    		break;   
	    	}
	    	
	    	switch (v.getId()){   
	    	case R.id.btnSearchProp:    
	    		Intent i2 = new Intent(this, SearchProperties.class);   
	    		startActivity(i2);    
	    		break;   
	    	}
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
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
