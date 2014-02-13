package org.dress.controller;

import org.dress.R;
import org.dress.model.RibbonViewFactory;
import org.dress.model.RibbonViewNode;
import org.dress.view.adapter.ImageAdapter;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class RibbonList extends ListActivity {
	/** Called when the activity is first created. */
	public static final String PREFS_NAME = "CONFIG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.ribbon);
		
		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,
				MODE_PRIVATE);

		int position = settings.getInt("BRANCH", 99);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		String viewHeader = null;
		viewHeader = this.getResources().getStringArray(R.array.branch_array)[position];

		actionBar.setTitle(viewHeader);
		actionBar.setDisplayShowTitleEnabled(true);

		this.getListView().setBackgroundColor(Color.LTGRAY);

		RibbonViewNode node = RibbonViewFactory.getNode(position);

		setListAdapter(new ImageAdapter(node));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.build_rack, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);
		
		switch (item.getItemId()) {
		
		case R.id.menu_ribbon:
		 this.startActivity(new Intent("org.dress.RACK"));
		 break;
		
		default:
		 Intent home = new Intent(RibbonList.this,BranchList.class);
		 home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		 this.startActivity(home);
		
		}

		return false;
	}

}