package org.dress.controller;

import org.dress.R;
import org.dress.model.RibbonViewFactory;
import org.dress.model.RibbonViewNode;
import org.dress.view.adapter.ImageAdapter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

public class RibbonList extends ActionBarActivity {
	/** Called when the activity is first created. */
	public static final String PREFS_NAME = "CONFIG";
	private ListView mListView;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.ribbon);
		
		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,
				MODE_PRIVATE);

		int position = settings.getInt("BRANCH", BranchList.DEFAULT_BRANCH);
		
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		String viewHeader = null;
		viewHeader = this.getResources().getStringArray(R.array.branch_array)[position];

		actionBar.setTitle(viewHeader);
		actionBar.setDisplayShowTitleEnabled(true);
		mListView = (ListView) findViewById(android.R.id.list);
		mListView.setBackgroundColor(Color.LTGRAY);

		RibbonViewNode node = RibbonViewFactory.getNode(RibbonList.this, position);
		mListView.setAdapter(new ImageAdapter(RibbonList.this, node));
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.build_rack, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		
		case R.id.menu_ribbon:
		 this.startActivity(new Intent(RibbonList.this, Rack.class));
		 break;
		
		default:
		 Intent home = new Intent(RibbonList.this, BranchList.class);
		 home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 this.startActivity(home);
		
		}

		return false;
	}

}