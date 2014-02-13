package org.dress.model.adapter;

import java.util.ArrayList;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class RibbonDbAdapter {
	

	private RibbonDbHelper mDbHelper;
	private SQLiteDatabase mDb;
	private static final String SQL = "SELECT ZRIBBONNAME,ZRIBBONDESC,ZRIBBONIMAGE FROM ZRIBBON WHERE ZORGRIBBON = ?";

	public static final int ARMY_DB_ID = 1;
	public static final int NAVY_DB_ID = 2;
	public static final int AIRFORCE_DB_ID = 3;
	public static final int MARINES_DB_ID = 4;
	public static final int COAST_DB_ID = 5;
	public static final int LA_DB_ID = 6;
	public static final int CAPC_DB_ID = 7;
	public static final int CAPS_DB_ID = 8;
	public static final int ARMY_ROTC_DB_ID = 9;
	public static final int AIR_FORCE_ROTC_DB_ID = 10;
	public static final int NAVY_JROTC_DB_ID = 11;
	public static final int ARMY_JROTC_DB_ID = 12;
	public static final int AIR_FORCE_JROTC_DB_ID = 13;
	public static final int NAVY_ROTC_DB_ID = 14;
	
	private ArrayList<String> mDesc = new ArrayList<String>();
	private ArrayList<String> mLongDesc = new ArrayList<String>();
	private ArrayList<String> mImage = new ArrayList<String>();

	public RibbonDbAdapter(Context context) {
		mDbHelper = new RibbonDbHelper(context);
	}

	private static class RibbonDbHelper extends SQLiteAssetHelper {
		private static final int DATABASE_VERSION = 01;
		private static final String DB_NAME = "MedalsRibbons";

		RibbonDbHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
		}
	}

	public RibbonDbAdapter open() {
		mDb = mDbHelper.getReadableDatabase();
		return this;
	}

	public String[] getDesc() {

		String[] str = new String[mDesc.size()];
		mDesc.toArray(str);

		return str;
	}

	public String[] getLongDesc() {

		String[] str = new String[mLongDesc.size()];
		mLongDesc.toArray(str);

		return str;
	}

	public String[] getImage() {

		String[] str = new String[mImage.size()];
		mImage.toArray(str);

		return str;
	}

	public void queryRibbon(int i) {
		String[] params = { Integer.toString(i) };
		Cursor cursor = mDb.rawQuery(SQL, params);

		cursor.moveToFirst();

		mDesc.add(cursor.getString(0));
		mLongDesc.add(cursor.getString(1));
		mImage.add(cursor.getString(2));

		while (cursor.moveToNext()) {
			mDesc.add(cursor.getString(0));
			mLongDesc.add(cursor.getString(1));
			mImage.add(cursor.getString(2));
		}

	}

	public void close() {
		mDbHelper.close();
	}

}