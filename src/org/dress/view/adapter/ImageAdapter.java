package org.dress.view.adapter;

import org.dress.R;
import org.dress.model.RibbonViewNode;
import org.dress.view.AwardCheckBox;
import org.dress.view.AwardPopupWindow;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter {
	private static final String PREFS_NAME = "CONFIG";
	private Context mContext;
	private String[] mDesc = null;
	private String[] mLongDesc = null;
	private String[] mImage = null;
	private boolean mCheckBox = false;

	public ImageAdapter(Context context, RibbonViewNode node) {
		mContext = context;
		mDesc = node.getDesc();
		mLongDesc = node.getLongDesc();
		mImage = node.getImage();
		mCheckBox = node.isEnableCheckBox();
	}

	public int getCount() {
		return mImage.length;
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		String mDrawableName = mImage[position].replace(".png", "");
		int resID = mContext.getResources().getIdentifier(mDrawableName,
				"drawable", mContext.getPackageName());
		return resID;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		View cellView = convertView;
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		String mDrawableName = mImage[position].replace(".png", "");
		int resID = mContext.getResources().getIdentifier(mDrawableName,
				"drawable", mContext.getPackageName());

		Typeface typeFace = Typeface.createFromAsset(mContext.getAssets(),
				"fonts/Roboto-Condensed.ttf");

		if (mCheckBox) {
			cellView = inflater.inflate(R.layout.ribbonlayout, null);
			ImageView ribbonImageView = (ImageView) cellView
					.findViewById(R.id.ribbonImageView);
			ribbonImageView.setImageResource(resID);

			TextView ribbonTextView = (TextView) cellView
					.findViewById(R.id.ribbonTextView);
			ribbonTextView.setTypeface(typeFace);
			ribbonTextView.setText(mDesc[position]);

			SharedPreferences settings = mContext.getSharedPreferences(
					PREFS_NAME, Context.MODE_PRIVATE);
			String rack = settings.getString("RACK", "");
			String pattern = "#" + Integer.toString(resID) + "#";

			CheckBox checkBox = (CheckBox) cellView
					.findViewById(R.id.ribbonCheckBox);
			checkBox.setOnCheckedChangeListener(new AwardCheckBox(parent
					.getContext(), resID));

			if (rack.contains(pattern)) {
				checkBox.setChecked(true);
			}

			final AwardPopupWindow popupWindow = new AwardPopupWindow(resID,
		     mLongDesc[position],mContext);
			 ((LinearLayout) cellView).setClickable(true);
			 ((LinearLayout) cellView).setOnClickListener(popupWindow);
			
			 TextView awardTextView = (TextView) popupWindow.getContentView()
			 .findViewById(R.id.awardTextView);
			 awardTextView.setTypeface(typeFace);
			
			 Button button = (Button)
			 popupWindow.getContentView().findViewById(
			 R.id.closeButton);
			 button.setOnClickListener(new OnClickListener() {
			
			 @Override
			 public void onClick(View arg0) {
			 popupWindow.dismiss();
			 }
			 });

		} else {

			cellView = inflater.inflate(R.layout.branchlayout, null);
			TextView branchTextView = (TextView) cellView
					.findViewById(R.id.branchTextView);
			branchTextView.setTypeface(typeFace);
			branchTextView.setText(mDesc[position]);

		}

		return cellView;

	}

	public String[] getTypedArray() {
		return mImage;

	}

}