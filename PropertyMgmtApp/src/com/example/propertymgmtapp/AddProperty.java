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

public class AddProperty extends Activity implements OnClickListener {
	
	EditText propName, propAddr, propOccupants, propRent, propNotes;
	Button saveProp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_property);

		
		saveProp = (Button) findViewById(R.id.btnSaveprp);
		propName = (EditText) findViewById(R.id.etPropName);
		propAddr = (EditText) findViewById(R.id.etPropAddr);
		propOccupants = (EditText) findViewById(R.id.etOccupants);
		propRent = (EditText) findViewById(R.id.etRent);
		propNotes = (EditText) findViewById(R.id.etPropNotes);
		
		saveProp.setOnClickListener(this);
		
		//Set up listeners for the buttons         
        View saveButton = findViewById(R.id.btnSaveprp);         
        saveButton.setOnClickListener(this); 
		
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		
	
		switch(v.getId())
		{
		case R.id.btnSaveprp:
			
			boolean isSuccessful = true;
			try{
			String pName = propName.getText().toString(); 	   		//gets text for prop name
			String pAddr = propAddr.getText().toString();	   		//gets text for prop address
			String pOccupants = propOccupants.getText().toString();	//gets text for prop occupants
			String pRent = propRent.getText().toString();	  		//gets text for prop rent
			String pNotes = propNotes.getText().toString();	  		//gets text for prop rent
			
			//get new instance of DataHandler - pass in this 
			DbHandler entry = new DbHandler(AddProperty.this); 
			
			entry.open();
			entry.createEntry(pName, pAddr, pOccupants, pRent, pNotes);
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
					Toast.makeText(getBaseContext(), "Property successfully added to database!", Toast.LENGTH_SHORT).show();
				}
			}
		}
	
		
			Intent i = new Intent(this, MyProperties.class);   
    		startActivity(i);
		
	}

}
