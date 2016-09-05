package org.dress.model;

import org.dress.model.adapter.RibbonDbAdapter;

public class RibbonModel {

	public static RibbonViewNode getRibbonNode(int ribbonId) {
		RibbonDbAdapter db = new RibbonDbAdapter();
		db.open();
		db.queryRibbon(ribbonId);
		String[] mDesc = db.getDesc();
		String[] mLongDesc = db.getLongDesc();
		String[] mImage = db.getImage();
		db.close();
		return new RibbonViewNode(mDesc, mLongDesc, mImage, true);
	}

}
