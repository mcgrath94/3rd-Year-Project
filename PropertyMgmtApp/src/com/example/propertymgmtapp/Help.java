package com.example.propertymgmtapp;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class Help extends TabActivity {

	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_help);

	        TabHost tabHost = getTabHost();

	        // setNewTab(context, tabHost, tag, title, icon, contentID);
	        this.setNewTab(this, tabHost, "tab1", R.string.textTabTitle1, android.R.drawable.star_on, R.id.tab1);
	        this.setNewTab(this, tabHost, "tab2", R.string.textTabTitle2, android.R.drawable.star_on, R.id.tab2);
	        this.setNewTab(this, tabHost, "tab3", R.string.textTabTitle3, android.R.drawable.star_on, R.id.tab3);

	        //tabHost.setCurrentTabByTag("tab2"); //-- optional to set a tab programmatically.
	    }

	    private void setNewTab(Context context, TabHost tabHost, String tag, int title, int icon, int contentID ){
	    	TabSpec tabSpec = tabHost.newTabSpec(tag);
	    	String titleString = getString(title);
	        tabSpec.setIndicator(titleString, context.getResources().getDrawable(android.R.drawable.star_on));
	    	tabSpec.setContent(contentID);
	        tabHost.addTab(tabSpec);
	    }
	}
