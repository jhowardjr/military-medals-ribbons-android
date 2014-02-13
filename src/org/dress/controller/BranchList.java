package org.dress.controller;

import org.dress.R;
import org.dress.model.RibbonViewFactory;
import org.dress.model.RibbonViewNode;
import org.dress.view.adapter.ImageAdapter;

import android.app.ActionBar;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class BranchList extends ListActivity {
	/** Called when the activity is first created. */
	public static final String PREFS_NAME = "CONFIG";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.branch);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle(R.string.branch_header);

		this.getListView().setDrawSelectorOnTop(true);

		this.getListView().setLongClickable(false);
		RibbonViewNode node = RibbonViewFactory.getNode(99);
		setListAdapter(new ImageAdapter(node));

	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,
				MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("BRANCH", position);
		editor.commit();

		startActivity(new Intent("org.dress.RIBBONLIST"));

	}
}