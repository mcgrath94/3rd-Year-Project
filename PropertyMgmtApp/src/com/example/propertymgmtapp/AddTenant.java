package com.example.propertymgmtapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddTenant extends Activity implements OnClickListener{
	
	EditText tenantName, tenantMob, tenantEmail, tenantAddr, tenantRent, tenantDeposit, tenantNotes;
	Button saveTenant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addtenant);
		
		saveTenant = (Button) findViewById(R.id.btnSaveTenant);
		tenantName = (EditText) findViewById(R.id.etTenantName);
		tenantMob = (EditText) findViewById(R.id.etTenantMobile);
		tenantEmail = (EditText) findViewById(R.id.etTenantEmail);
		tenantAddr = (EditText) findViewById(R.id.etTenantAddr);
		tenantRent = (EditText) findViewById(R.id.etTenantRent);
		tenantDeposit = (EditText) findViewById(R.id.etTenantDeposit);
		tenantNotes = (EditText) findViewById(R.id.etTenantNotes);

		
		saveTenant.setOnClickListener(this);
		
		//Set up listeners for the buttons         
        View saveButton = findViewById(R.id.btnSaveTenant);         
        saveButton.setOnClickListener(this); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_tenant, menu);
		return true;
	}

	public void onClick(View v1) {
		// TODO Auto-generated method stub
		
		switch(v1.getId())
		{
		case R.id.btnSaveTenant:
			
			boolean isSuccessful = true;
			try{
			String tName = tenantName.getText().toString(); 	   		//gets text for tenant name
			String tMob = tenantMob.getText().toString(); 	   		//gets text for tenant number
			String tEmail = tenantEmail.getText().toString(); 	   		//gets text for tenant email
			String tAddr = tenantAddr.getText().toString();	   		//gets text for tenant address
			String tRent = tenantRent.getText().toString();	  		//gets text for tenant rent
			String tDeposit = tenantDeposit.getText().toString(); 	   		//gets text for tenant deposit
			String tNotes = tenantNotes.getText().toString();	  		//gets text for tenant notes
			
			//get new instance of DataHandler - pass in this 
			DbHandler entry = new DbHandler(AddTenant.this); 
			
			entry.open();
			entry.createTenantsEntry(tName, tMob, tEmail, tAddr, tRent, tDeposit, tNotes);
			entry.close();
			}
			
			catch(Exception e)
			{
				isSuccessful = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			}
			finally{
				if(isSuccessful)
				{					
					Toast.makeText(getBaseContext(), "Tenant successfully added to database!", Toast.LENGTH_SHORT).show();
				}
			}
		
			Intent i = new Intent(this, Tenants.class);   
    		startActivity(i);
		}
	}

}

