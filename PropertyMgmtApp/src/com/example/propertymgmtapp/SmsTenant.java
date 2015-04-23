package com.example.propertymgmtapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SmsTenant extends Activity implements OnClickListener{
	
	EditText getTenantId, tenantMobile, tenantSmsText;
	Button getTenantMobile, sendTenantSms, cancelTenantSms, viewAllTenants;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sms_tenant);
		
		viewAllTenants = (Button) findViewById(R.id.btnSmsViewTenants);
		getTenantMobile = (Button) findViewById(R.id.btnSmsGetTenant);
		sendTenantSms = (Button) findViewById(R.id.btnSendTenantSms);
		cancelTenantSms = (Button) findViewById(R.id.btnCancelTenantSms);

		getTenantId = (EditText) findViewById(R.id.etTenantSmsId);
		tenantMobile = (EditText) findViewById(R.id.etTenantSmsMobile);
		tenantSmsText = (EditText) findViewById(R.id.etTenatSmsText);
		
		//findTenant.setOnClickListener(this);
		
		//Set up listeners for the buttons         
        View getSmsTenantButton = findViewById(R.id.btnSmsGetTenant);         
        getSmsTenantButton.setOnClickListener(this); 
        
        View sendTenantSmsButton = findViewById(R.id.btnSendTenantSms);         
        sendTenantSmsButton.setOnClickListener(this);
        
        View viewTenantsSmsButton = findViewById(R.id.btnSmsViewTenants);         
        viewTenantsSmsButton.setOnClickListener(this);
        
        View cancalTenantSmsButton = findViewById(R.id.btnCancelTenantSms);         
        cancalTenantSmsButton.setOnClickListener(this);
	}
	
   /*  try {
            // Get the default instance of the SmsManager
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(tenantMobile.getText().toString(),
                    null, 
                    tenantSmsText.getText().toString(),
                    null,
                    null);
            Toast.makeText(getApplicationContext(), "Your sms has successfully sent!",
                    Toast.LENGTH_LONG).show();
        } catch (Exception ex) {
            Toast.makeText(getApplicationContext(),"Your sms has failed...",
                    Toast.LENGTH_LONG).show();
            ex.printStackTrace();
        }*/

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sms_tenant, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.btnSmsViewTenants:
			Intent i = new Intent(this, Tenants.class);   
			startActivity(i);
			break;
			
			
			//find tenant mobile
		case R.id.btnSmsGetTenant:
			try{
		
				String tenantSms = getTenantId.getText().toString();
				long longTenSms = Long.parseLong(tenantSms);
				
				DbHandler smshandler = new DbHandler(this);
				smshandler.open();
				String rtnTenSmsMob = smshandler.getTenSmsMob(longTenSms);
				smshandler.close();
				
				tenantMobile.setText(rtnTenSmsMob);
				
				Toast.makeText(getBaseContext(), "Tenant Found!", Toast.LENGTH_SHORT).show();

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
			
		case R.id.btnSendTenantSms:
		   
		        try {
		            // Get the default instance of the SmsManager
		            SmsManager smsManager = SmsManager.getDefault();
		            smsManager.sendTextMessage(tenantMobile.getText().toString(),
		                    null, 
		                    tenantSmsText.getText().toString(),
		                    null,
		                    null);
		            Toast.makeText(getApplicationContext(), "Your sms has successfully sent!",
		                    Toast.LENGTH_LONG).show();
		            
		        	} 
		        	catch (Exception ex) {
		            ex.printStackTrace();
		        }
		break;
		
		case R.id.btnCancelTenantSms:
			Intent i3 = new Intent(this, MainActivity.class);   
			startActivity(i3);
			Toast.makeText(getApplicationContext(), "SMS sending cancelled!",
                    Toast.LENGTH_LONG).show();
			break;
	}
	}

}
