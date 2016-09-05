package org.dress.model.adapter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import org.dress.ApplicationContext;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class RibbonDbAdapter {

	private RibbonDbHelper dbhelper;
	private SQLiteDatabase db;
	private static final String SQL = "SELECT ZRIBBONNAME,ZRIBBONDESC,ZRIBBONIMAGE FROM ZRIBBON WHERE ZORGRIBBON = ?";
	ArrayList<String> desc = new ArrayList<String>();
	ArrayList<String> longdesc = new ArrayList<String>();
	ArrayList<String> image = new ArrayList<String>();

	public RibbonDbAdapter() {
		dbhelper = new RibbonDbHelper(ApplicationContext.getContext());
		dbhelper.createNewDatabase();
	}

	private static class RibbonDbHelper extends SQLiteOpenHelper {
		private static final int DATABASE_VERSION = 01;
		private static final String DB_NAME = "MedalsRibbons";
		private static final String DB_PATH = "/data/data/org.dress/databases/";
		private static final String TAG = "DbManager";
		private final Context context;

		RibbonDbHelper(Context context) {
			super(context, DB_NAME, null, DATABASE_VERSION);
			this.context = context;

		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

		}

		public void createNewDatabase() {
			InputStream assetsDB = null;
			try {
				assetsDB = this.context.getAssets().open(DB_NAME);
				OutputStream dbOut = new FileOutputStream(DB_PATH + DB_NAME);

				byte[] buffer = new byte[1024];
				int length;
				while ((length = assetsDB.read(buffer)) > 0) {
					dbOut.write(buffer, 0, length);
				}

				dbOut.flush();
				dbOut.close();
				assetsDB.close();
				Log.i(TAG, "New database created...");
			} catch (IOException e) {
				Log.e(TAG, "Could not create new database...");
				e.printStackTrace();
			}
		}

	}

	public RibbonDbAdapter open() {
		db = dbhelper.getReadableDatabase();
		return this;
	}

	public String[] getDesc() {

		String str[] = new String[desc.size()];
		desc.toArray(str);

		return str;
	}

	public String[] getLongDesc() {

		String str[] = new String[longdesc.size()];
		longdesc.toArray(str);

		return str;
	}

	public String[] getImage() {

		String str[] = new String[image.size()];
		image.toArray(str);

		return str;
	}

	public void queryRibbon(int i) {
		String[] params = { Integer.toString(i) };
		Cursor cursor = db.rawQuery(SQL, params);

		cursor.moveToFirst();

		desc.add(cursor.getString(0));
		longdesc.add(cursor.getString(1));
		image.add(cursor.getString(2));

		while (cursor.moveToNext()) {
			desc.add(cursor.getString(0));
			longdesc.add(cursor.getString(1));
			image.add(cursor.getString(2));
		}

	}

	public void close() {
		dbhelper.close();
	}

}