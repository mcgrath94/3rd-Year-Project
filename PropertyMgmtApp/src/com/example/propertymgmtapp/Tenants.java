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

public class Tenants extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tenants);
		// Show the Up button in the action bar.
		setupActionBar();
		
		
		//Set up listeners for the buttons         
        View addTenantButton = findViewById(R.id.btnAddTenant);         
        addTenantButton.setOnClickListener(this); 
        
        View searchTenantButton = findViewById(R.id.btnSearchTenants);         
        searchTenantButton.setOnClickListener(this);
        
        TextView tv = (TextView) findViewById(R.id.tvwSQLtenant);
		DbHandler info = new DbHandler(this);
		info.open();
		String tenantData = info.getTenantData();
		info.close();
		tv.setText(tenantData);
		
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
		getMenuInflater().inflate(R.menu.tenants, menu);
		return true;
	}

	/*@Override
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
	}*/

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch (v.getId()){   
    	case R.id.btnAddTenant:    
    		Intent i = new Intent(this, AddTenant.class);   
    		startActivity(i);    
    		break;   
    	}
    	
    	switch (v.getId()){   
    	case R.id.btnSearchTenants:    
    		Intent i2 = new Intent(this, SearchTenants.class);   
    		startActivity(i2);    
    		break;   
    	}
  }


		

}
