package org.dress.view;

import org.dress.R;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class AwardPopupWindow extends PopupWindow implements OnClickListener {

	private Context mContext;
	private static final int RIBBON_TEXT_SIZE = 16;
	private static final int OFFSET = 45;
	private static final int PADDING = 8;
	private static final int MAX_LINES = 6;

	public AwardPopupWindow(Context context) {
		super(context);
	}

	public AwardPopupWindow(int imgResId, String longdesc, Context context) {
		super(context);
		mContext = context;

		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.awardpopuplayout, null);

		ImageView ribbonImageView = (ImageView) layout
				.findViewById(R.id.awardImage);
		ribbonImageView.setImageResource(imgResId);
		ribbonImageView.setPadding(PADDING, PADDING - PADDING, PADDING, PADDING);
		ribbonImageView.setAdjustViewBounds(true);

		TextView ribbonTextView = (TextView) layout
				.findViewById(R.id.awardTextView);
		ribbonTextView.setText(longdesc);
		ribbonTextView.setGravity(Gravity.CENTER);
		ribbonTextView.setPadding(PADDING, PADDING, PADDING, PADDING);
		ribbonTextView.setMaxLines(MAX_LINES);
		ribbonTextView.setMovementMethod(new ScrollingMovementMethod());
		ribbonTextView
				.setScrollBarStyle(LinearLayout.SCROLLBARS_INSIDE_OVERLAY);
		ribbonTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP,
				RIBBON_TEXT_SIZE);

		setContentView(layout);

		setFocusable(true);
	}

	@Override
	public void onClick(View v) {
		showAtLocation(v, Gravity.CENTER, 0, 0);
		int width = mContext.getResources()
				.getDrawable(R.drawable.popupbackground).getIntrinsicWidth();
		int height = mContext.getResources()
				.getDrawable(R.drawable.popupbackground).getIntrinsicHeight();
		update(0, 0, width + OFFSET, height);

	}

}
