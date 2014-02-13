package org.dress.model;

import org.dress.model.adapter.RibbonDbAdapter;

import android.content.Context;

public final class RibbonModel {
	private RibbonModel() {
		
	}
	
	public static RibbonViewNode getRibbonNode(Context context, int ribbonId) {
		RibbonDbAdapter db = new RibbonDbAdapter(context);
		db.open();
		db.queryRibbon(ribbonId);
		String[] mDesc = db.getDesc();
		String[] mLongDesc = db.getLongDesc();
		String[] mImage = db.getImage();
		db.close();
		return new RibbonViewNode(mDesc, mLongDesc, mImage, true);
	}

}
