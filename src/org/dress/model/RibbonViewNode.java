package org.dress.model;

public class RibbonViewNode {

	private String[] mDesc;
	private String[] mLongDesc;
	private String[] mImage;
	private boolean mEnableCheckBox;

	RibbonViewNode(String[] desc, String[] longdesc, String[] image,
			boolean enableCB) {

		setImage(image);
		setDesc(desc);
		setLongDesc(longdesc);
		setEnableCheckBox(enableCB);

	}

	public String[] getDesc() {
		return mDesc;
	}

	public void setDesc(String[] desc) {
		this.mDesc = desc;
	}

	public String[] getLongDesc() {
		return mLongDesc;
	}

	public void setLongDesc(String[] longDesc) {
		this.mLongDesc = longDesc;
	}

	public String[] getImage() {
		return mImage;
	}

	public void setImage(String[] image) {
		this.mImage = image;
	}

	public boolean isEnableCheckBox() {
		return mEnableCheckBox;
	}

	public void setEnableCheckBox(boolean enableCheckBox) {
		this.mEnableCheckBox = enableCheckBox;
	}

}
