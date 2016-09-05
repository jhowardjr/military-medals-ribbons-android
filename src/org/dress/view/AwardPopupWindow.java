package org.dress.view;

import org.dress.R;

import android.content.Context;
import android.text.method.ScrollingMovementMethod;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class AwardPopupWindow extends PopupWindow implements OnClickListener {

	Context context;

	public AwardPopupWindow(int imgResId, String longdesc, ViewGroup parent) {
		super();
		context = parent.getContext();
		
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.awardpopuplayout, null);
		
	     
		ImageView ribbonImageView = (ImageView) layout.findViewById(R.id.awardImage);
		ribbonImageView.setImageResource(imgResId);
		ribbonImageView.setPadding(8, 0, 8, 8);
		ribbonImageView.setAdjustViewBounds(true);
			
		TextView ribbonTextView = (TextView) layout.findViewById(R.id.awardTextView);
		ribbonTextView.setText(longdesc);
		ribbonTextView.setGravity(Gravity.CENTER);
		ribbonTextView.setPadding(8, 8, 8, 8);
		ribbonTextView.setMaxLines(6);
		ribbonTextView.setMovementMethod(new ScrollingMovementMethod());
		ribbonTextView.setScrollBarStyle(LinearLayout.SCROLLBARS_INSIDE_OVERLAY);
		ribbonTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
		
		this.setContentView(layout);
	
		this.setFocusable(true);
	}

	@Override
	public void onClick(View v) {
		this.showAtLocation(v, Gravity.CENTER, 0, 0);
		int width = context.getResources()
				.getDrawable(R.drawable.popupbackground).getIntrinsicWidth();
		int height = context.getResources()
				.getDrawable(R.drawable.popupbackground).getIntrinsicHeight();
		this.update(0, 0, width + 45, height);

	}

}
