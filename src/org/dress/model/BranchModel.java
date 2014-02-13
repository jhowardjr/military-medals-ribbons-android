package org.dress.model;

import org.dress.R;

import android.content.Context;

public final class BranchModel {
	private BranchModel() {
		
	}
	
	public static RibbonViewNode getBranchNode(Context context) {
		String[] mDesc = context.getResources().getStringArray(
				R.array.branch_array);
		String[] mLongDesc = context.getResources().getStringArray(
				R.array.branch_array);
		String[] mImage = context.getResources().getStringArray(
				R.array.branch_image);
		return new RibbonViewNode(mDesc, mLongDesc, mImage, false);

	}
}
