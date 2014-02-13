package org.dress.controller;

import java.util.ArrayList;

import org.dress.R;

import org.dress.model.RibbonViewFactory;
import org.dress.model.RibbonViewNode;
import org.dress.view.RibbonRackView;
import org.dress.view.adapter.ImageAdapter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.LinearLayout;

public class Rack extends ActionBarActivity {

	private static final String PREFS_NAME = "CONFIG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.rack);

		ActionBar actionBar = getSupportActionBar();

		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,
				Activity.MODE_PRIVATE);
		String rack = settings.getString("RACK", "");

		int position = settings.getInt("BRANCH", BranchList.DEFAULT_BRANCH);

		String viewHeader = this.getResources().getStringArray(
				R.array.branch_array)[position];

		actionBar.setTitle(viewHeader);
		actionBar.setDisplayHomeAsUpEnabled(true);

		RibbonViewNode node = RibbonViewFactory.getNode(Rack.this, position);

		ImageAdapter ia = new ImageAdapter(Rack.this, node);

		String[] ta = ia.getTypedArray();

		String mDrawableName = null;

		Integer resid = 0;

		ArrayList<Integer> ribbons = new ArrayList<Integer>();

		for (int i = 0; i <= ta.length - 1; i++) {
			mDrawableName = ta[i].replace(".png", "");
			resid = this.getResources().getIdentifier(mDrawableName,
					"drawable", this.getPackageName());

			if (rack.contains(resid.toString())) {

				ribbons.add(resid);

			}

		}

		RibbonRackView view = new RibbonRackView(Rack.this);
		view.fillTableLayout(ribbons);

		addContentView(view.getTableLayout(), new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT));

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.share_rack, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {

		case R.id.menu_share:
			Intent sendIntent = new Intent();
			sendIntent.setAction(Intent.ACTION_SEND);
			sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
			sendIntent.setType("text/plain");
			startActivity(sendIntent); 
			break;

		default:
			Intent home = new Intent(Rack.this, BranchList.class);
			home.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			this.startActivity(home);

		}

		return false;
	}

}
