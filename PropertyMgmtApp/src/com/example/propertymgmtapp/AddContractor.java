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

public class AddContractor extends Activity implements OnClickListener{
	
	EditText conName, conMobile, conEmail, conAddr, conType, conCharge, conNotes;
	Button saveCon;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcontractor);
		
		saveCon = (Button) findViewById(R.id.btnSaveCon);
		conName = (EditText) findViewById(R.id.etConName);
		conMobile = (EditText) findViewById(R.id.etConMobile);
		conEmail = (EditText) findViewById(R.id.etConEmail);
		conAddr = (EditText) findViewById(R.id.etConAddr);
		conType = (EditText) findViewById(R.id.etConType);
		conCharge = (EditText) findViewById(R.id.etConCharge);
		conNotes = (EditText) findViewById(R.id.etConNotes);
		
		saveCon.setOnClickListener(this);
		
		//Set up listeners for the buttons         
        View saveButton = findViewById(R.id.btnSaveCon);         
        saveButton.setOnClickListener(this); 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_contractor, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.btnSaveCon:
			
			boolean isSuccessful = true;
			try{
			String cName = conName.getText().toString(); 	   		
			String cMobile = conMobile.getText().toString();
			String cEmail = conEmail.getText().toString();
			String cAddr = conAddr.getText().toString();	   		
			String cType = conType.getText().toString();
			String cCharge= conCharge.getText().toString();
			String cNotes = conNotes.getText().toString();	  		
			
			//get new instance of DataHandler - pass in this 
			DbHandler entry = new DbHandler(AddContractor.this); 
			
			entry.open();
			entry.createContractorEntry(cName, cMobile, cEmail, cAddr, cType, cCharge, cNotes);
			//entry.createEntry(pName, pAddr, pNotes);
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
					Dialog d = new Dialog(this);
					d.setTitle("Contractor Added!");
					TextView tv = new TextView(this);
					tv.setText("Contractor successfully added to database");
					d.setContentView(tv);
					d.show();
				}
			}
		
			Intent i = new Intent(this, Contractor.class);   
    		startActivity(i);
		}
	}

}
