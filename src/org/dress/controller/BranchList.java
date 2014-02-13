package org.dress.controller;

import org.dress.R;
import org.dress.model.RibbonViewFactory;
import org.dress.model.RibbonViewNode;
import org.dress.model.adapter.RibbonDbAdapter;
import org.dress.view.adapter.ImageAdapter;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BranchList extends ActionBarActivity implements
		OnItemClickListener {
	/** Called when the activity is first created. */
	public static final String PREFS_NAME = "CONFIG";
	public static final int DEFAULT_BRANCH = 99;
	private ListView mListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		RibbonDbAdapter db = new RibbonDbAdapter(BranchList.this);
		db.open();
		db.close();
		this.setContentView(R.layout.branch);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setTitle(R.string.branch_header);
		mListView = (ListView) findViewById(android.R.id.list);
		mListView.setOnItemClickListener(this);

		mListView.setDrawSelectorOnTop(true);

		mListView.setLongClickable(false);
		RibbonViewNode node = RibbonViewFactory.getNode(BranchList.this,
				DEFAULT_BRANCH);
		mListView.setAdapter(new ImageAdapter(BranchList.this, node));

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View v, int position, long id) {
		SharedPreferences settings = this.getSharedPreferences(PREFS_NAME,
				MODE_PRIVATE);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("BRANCH", position);
		editor.commit();

		startActivity(new Intent(BranchList.this, RibbonList.class));

	}
}