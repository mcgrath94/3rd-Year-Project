package com.example.propertymgmtapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class SearchProperties extends Activity implements OnClickListener {
	
	EditText propId, propName, propAddr, propOccupants, propRent, propNotes;
	Button viewAllProps, findProp, modifyProp, deleteProp;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchproperties);
		
		viewAllProps = (Button) findViewById(R.id.btnViewProps);
		findProp = (Button) findViewById(R.id.btnFindProp);
		modifyProp = (Button) findViewById(R.id.btnModifyProp);
		deleteProp = (Button) findViewById(R.id.btnDeleteProp);
		
		
		propId = (EditText) findViewById(R.id.edtFindId);
		propName = (EditText) findViewById(R.id.edtPropName);
		propAddr = (EditText) findViewById(R.id.edtPropAddr);
		propOccupants = (EditText) findViewById(R.id.edtOccupants);
		propRent = (EditText) findViewById(R.id.edtRent);
		propNotes = (EditText) findViewById(R.id.edtPropNotes);
		
		findProp.setOnClickListener(this);
		
		//Set up listeners for the buttons         
        View viewAllPropButton = findViewById(R.id.btnViewProps);         
        viewAllPropButton.setOnClickListener(this); 
        
        View findButton = findViewById(R.id.btnFindProp);         
        findButton.setOnClickListener(this); 
        
        View modifyButton = findViewById(R.id.btnModifyProp);         
        modifyButton.setOnClickListener(this);
        
        View deleteButton = findViewById(R.id.btnDeleteProp);         
        deleteButton.setOnClickListener(this);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_properties, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		switch(v.getId())
		{
		case R.id.btnViewProps:
			Intent i = new Intent(this, MyProperties.class);   
    		startActivity(i);
    		break;
    		
    		
    		//find properties
		case R.id.btnFindProp:
			try{
				String s = propId.getText().toString();
				long l = Long.parseLong(s);
				
				DbHandler dhandler = new DbHandler(this);
				dhandler.open();
				String returnedName = dhandler.getName(l);
				String returnedAddress = dhandler.getAddress(l);
				String returnedOccupants = dhandler.getOccupants(l);
				String returnedRent = dhandler.getRent(l);
				String returnedNotes = dhandler.getNotes(l);
				dhandler.close();
				
				propName.setText(returnedName);
				propAddr.setText(returnedAddress);
				propOccupants.setText(returnedOccupants);
				propRent.setText(returnedRent);
				propNotes.setText(returnedNotes);
			}
			catch(Exception e)
			{			
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText("Couldn't find property in database");
				d.setContentView(tv);
				d.show();
			}
			
			break;
			
		case R.id.btnModifyProp:
			try{
				String pmName = propName.getText().toString(); 	   		//gets text for prop name
				String pmAddr = propAddr.getText().toString();	   		//gets text for prop address
				String pmOccupants = propOccupants.getText().toString();	//gets text for prop occupants
				String pmRent = propRent.getText().toString();	  		//gets text for prop rent
				String pmNotes = propNotes.getText().toString();	  		//gets text for prop rent
				
				String sx = propId.getText().toString();
				long lx = Long.parseLong(sx);
				
				DbHandler modifyHandler = new DbHandler(this);
				modifyHandler.open();
				modifyHandler.modifyProp(lx, pmName, pmAddr, pmOccupants, pmRent, pmNotes);
				modifyHandler.close();
				
				Toast.makeText(getBaseContext(), "Property successfully changed in database!", Toast.LENGTH_SHORT).show();

			}
			catch(Exception e)
			{			
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText("Couldn't modify property in database");
				d.setContentView(tv);
				d.show();
			}
			
			Intent i2 = new Intent(this, MyProperties.class);   
    		startActivity(i2);
			
			
			break;
			
		case R.id.btnDeleteProp:
			try{
				String sd = propId.getText().toString();
				long ld = Long.parseLong(sd);
				
				DbHandler deleteHandler = new DbHandler(this);
				deleteHandler.open();
				deleteHandler.deleteProp(ld);
				deleteHandler.close();
				
				Toast.makeText(getBaseContext(), "Property successfully deleted from database!", Toast.LENGTH_SHORT).show();

			}
			catch(Exception e)
			{			
				Dialog d = new Dialog(this);
				d.setTitle("Failed!");
				TextView tv = new TextView(this);
				tv.setText("Couldn't delete property from database");
				d.setContentView(tv);
				d.show();
			}			
			
			Intent i3 = new Intent(this, MyProperties.class);   
    		startActivity(i3);
			
			break;
		}
		
	}

}
