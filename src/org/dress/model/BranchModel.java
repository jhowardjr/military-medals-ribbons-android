package org.dress.model;

import org.dress.ApplicationContext;
import org.dress.R;

import android.content.Context;

public class BranchModel {

	private static Context mContext = ApplicationContext.getContext();

	public static RibbonViewNode getBranchNode() {
		String[] mDesc = mContext.getResources().getStringArray(
				R.array.branch_array);
		String[] mLongDesc = mContext.getResources().getStringArray(
				R.array.branch_array);
		String[] mImage = mContext.getResources().getStringArray(
				R.array.branch_image);
		return new RibbonViewNode(mDesc, mLongDesc, mImage, false);

	}

}
