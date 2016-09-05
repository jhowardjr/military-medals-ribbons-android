package org.dress.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class AwardCheckBox extends CheckBox implements OnCheckedChangeListener {

	public static final String PREFS_NAME = "CONFIG";
	private String imgResIdString;
	private Context context;

	public AwardCheckBox(Context context, int imgResId) {
		super(context);
		this.context = context;
		this.imgResIdString = Integer.toString(imgResId);
		String pattern = "";
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_PRIVATE);

		String rack = settings.getString("RACK", "");

		pattern = "#" + imgResIdString + "#";

		if (rack.contains(pattern)) {
			this.setChecked(true);
		}

	}

	private void checkButtonClick(final String imgResIdString, boolean isChecked) {
		SharedPreferences settings = context.getSharedPreferences(PREFS_NAME,
				Context.MODE_WORLD_READABLE);

		String rack = settings.getString("RACK", "");

		SharedPreferences.Editor editor = settings.edit();

		if (isChecked) {
			String pattern = "#" + imgResIdString + "#";
			if (!rack.contains(pattern)) {

				rack = rack + "#" + imgResIdString + "#";
				editor.putString("RACK", rack);
				editor.commit();

			}

		} else {
			rack = rack.replaceFirst("#" + imgResIdString + "#", "");
			editor.remove("RACK");
			editor.putString("RACK", rack);
			editor.commit();
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {

		checkButtonClick(imgResIdString, isChecked);

	}

}
