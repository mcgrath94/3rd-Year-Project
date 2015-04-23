package com.example.propertymgmtapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Contractor extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contractor);
		
		//Set up listeners for the buttons         
        View addConButton = findViewById(R.id.btnAddCon);         
        addConButton.setOnClickListener(this);
        
        TextView tv = (TextView) findViewById(R.id.tvwSQLcon);
		DbHandler info = new DbHandler(this);
		info.open();
		String conData = info.getContractorData();
		info.close();
		tv.setText(conData);
		
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contractor, menu);
		return true;
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
		   
	    	switch (v.getId()){   
	    	case R.id.btnAddCon:    
	    		Intent i = new Intent(this, AddContractor.class);   
	    		startActivity(i);    
	    		break;   
	    	}
		
	}

}
