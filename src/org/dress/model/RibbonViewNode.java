package org.dress.model;

public class RibbonViewNode {

	public String[] mDesc;
	public String[] mLongDesc;
	public String[] mImage;
	public boolean enableCheckBox;

	RibbonViewNode(String[] desc, String[] longdesc, String[] image,
			boolean enableCB) {

		mImage = image;
		mDesc = desc;
		mLongDesc = longdesc;
		enableCheckBox = enableCB;

	}

}
