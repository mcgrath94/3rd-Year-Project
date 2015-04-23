package com.example.propertymgmtapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class SearchTenants extends Activity implements OnClickListener{
	
	EditText tenantId, tenantName, tenantMobile, tenantEmail, tenantAddr, tenantRent, tenantDeposit, tenantNotes;
	Button viewAllTenants, findTenant, modifyTenant, deleteTenant, sendSMS;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchtenants);
		// Show the Up button in the action bar.
		setupActionBar();
		
		viewAllTenants = (Button) findViewById(R.id.btnViewAllTenants);
		findTenant = (Button) findViewById(R.id.btnFindTenant);
		modifyTenant = (Button) findViewById(R.id.btnModifyTenant);
		deleteTenant = (Button) findViewById(R.id.btnDeleteTenant);
		
		
		tenantId = (EditText) findViewById(R.id.edtTenantId);
		tenantName = (EditText) findViewById(R.id.edtTenantName);
		tenantMobile = (EditText) findViewById(R.id.edtTenantMobile);
		tenantEmail = (EditText) findViewById(R.id.edtTenantEmail);
		tenantAddr = (EditText) findViewById(R.id.edtTenantAddress);
		tenantRent = (EditText) findViewById(R.id.edtTenantRent);
		tenantDeposit = (EditText) findViewById(R.id.edtTenantDeposit);
		tenantNotes = (EditText) findViewById(R.id.edtTenantNotes);
		
		findTenant.setOnClickListener(this);
		
		//Set up listeners for the buttons         
        View viewAllTenantsButton = findViewById(R.id.btnViewAllTenants);         
        viewAllTenantsButton.setOnClickListener(this); 
        
        View findTenantButton = findViewById(R.id.btnFindTenant);         
        findTenantButton.setOnClickListener(this); 
        
        View modifyTenantButton = findViewById(R.id.btnModifyTenant);         
        modifyTenantButton.setOnClickListener(this);
        
        View deleteTenantButton = findViewById(R.id.btnDeleteTenant);         
        deleteTenantButton.setOnClickListener(this);
		
		
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
		getMenuInflater().inflate(R.menu.search_tenants, menu);
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
		return super.onOptionsItemSelected(item);*/
	
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
		

		switch(v.getId())
		{
		case R.id.btnViewAllTenants:
			Intent i = new Intent(this, Tenants.class);   
			startActivity(i);
			break;
			
			
			//find properties
		case R.id.btnFindTenant:
			try{
		
				String tenant = tenantId.getText().toString();
				long longTen = Long.parseLong(tenant);
				
				DbHandler fthandler = new DbHandler(this);
				fthandler.open();
				String returnedTenName = fthandler.getTenName(longTen);
				String returnedTenMob = fthandler.getTenMob(longTen);
				String returnedTenEmail = fthandler.getTenEmail(longTen);
				String returnedTenAddress = fthandler.getTenAddress(longTen);;
				String returnedTenRent = fthandler.getTenRent(longTen);
				String returnedTenDeposit = fthandler.getTenDeposit(longTen);
				String returnedTenNotes = fthandler.getTenNotes(longTen);
				fthandler.close();
				
				tenantName.setText(returnedTenName);
				tenantMobile.setText(returnedTenMob);
				tenantEmail.setText(returnedTenEmail);
				tenantAddr.setText(returnedTenAddress);
				tenantRent.setText(returnedTenRent);
				tenantDeposit.setText(returnedTenDeposit);
				tenantNotes.setText(returnedTenNotes);
			}
			catch(Exception e)
			{			
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText("Couldn't find tenant in database");
				d.setContentView(tv);
				d.show();
			}
			
			break;
			
		case R.id.btnModifyTenant:
			try{
				String tmName = tenantName.getText().toString(); 	   		//gets text for tenant name
				String tmMobile = tenantMobile.getText().toString(); 	   		//gets text for tenant number
				String tmEmail = tenantEmail.getText().toString(); 	   		//gets text for tenant email
				String tmAddr = tenantAddr.getText().toString();	   		//gets text for tenant address
				String tmRent = tenantRent.getText().toString();	  		//gets text for tenant rent
				String tmDeposit = tenantDeposit.getText().toString(); 	   		//gets text for tenant deposit
				String tmNotes = tenantNotes.getText().toString();	  		//gets text for tenant notes
				
				String tenantM = tenantId.getText().toString();
				long longTenM = Long.parseLong(tenantM);
				
				DbHandler modifyTenHandler = new DbHandler(this);
				modifyTenHandler.open();
				modifyTenHandler.modifyTen(longTenM, tmName, tmMobile, tmEmail, tmAddr, tmRent, tmDeposit, tmNotes);
				modifyTenHandler.close();
				
				Toast.makeText(getBaseContext(), "Tenant successfully changed in database!", Toast.LENGTH_SHORT).show();
			}
			catch(Exception e)
			{			
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText("Couldn't modify tenant in database");
				d.setContentView(tv);
				d.show();
			}
			
			Intent i2 = new Intent(this, Tenants.class);   
    		startActivity(i2);
			
			
			break;
			
		case R.id.btnDeleteTenant:
			try{
				String tenantD = tenantId.getText().toString();
				long longTenD = Long.parseLong(tenantD);
				
				DbHandler deleteTenHandler = new DbHandler(this);
				deleteTenHandler.open();
				deleteTenHandler.deleteTen(longTenD);
				deleteTenHandler.close();
				
				Toast.makeText(getBaseContext(), "Tenant successfully deleted from database!", Toast.LENGTH_SHORT).show();
			}
			catch(Exception e)
			{			
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText("Couldn't delete tenant from database");
				d.setContentView(tv);
				d.show();
			}
			
			Intent i3 = new Intent(this, Tenants.class);   
    		startActivity(i3);
			
			break;
		}
	}
}
