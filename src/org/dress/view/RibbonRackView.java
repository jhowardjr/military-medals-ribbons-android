package org.dress.view;

import java.util.ArrayList;
import java.util.LinkedList;

import org.dress.R;

import android.content.Context;
import android.graphics.Canvas;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.ImageView.ScaleType;

public class RibbonRackView extends View {
	private TableLayout mTable;
	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;
	private Context mContext;
	private static final int NUM_OF_COLUMNS = 3;
	private static final float MIN_SCALE = 0.1f;
	private static final float MAX_SCALE = 5.1f;

	public RibbonRackView(Context context) {
		super(context);
		this.mContext = context;
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		mScaleDetector.onTouchEvent(ev);
		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.save();
		canvas.scale(mScaleFactor, mScaleFactor);
		mTable.draw(canvas);
		canvas.restore();
	}

	public void fillTableLayout(ArrayList<Integer> list) {

		LinkedList<Integer> queue = new LinkedList<Integer>();

		for (Integer id : list) {

			queue.add(id);

		}

		mTable = new TableLayout(mContext);

		LayoutParams params = new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);

		while (queue.size() > 0) {

			mTable.addView(buildRack(queue), params);
		}

		mTable.setGravity(Gravity.CENTER);

		mTable.setBackgroundResource(R.drawable.rackbackground);
	}

	public TableLayout getTableLayout() {

		return mTable;

	}

	private TableRow buildRack(LinkedList<Integer> stack) {

		int width = mContext.getResources().getDrawable(R.drawable.r005)
				.getIntrinsicWidth();

		int height = mContext.getResources().getDrawable(R.drawable.r005)
				.getIntrinsicHeight();

		TableRow row = new TableRow(mContext);

		int top = stack.size() % NUM_OF_COLUMNS;

		if (top == 0) {
			top = NUM_OF_COLUMNS;
		}
		for (int i = 0; i < top; i++) {

			ImageView iv = getImage(stack.remove());

			iv.setAdjustViewBounds(true);

			iv.setScaleType(ScaleType.CENTER_CROP);

			row.addView(iv, new TableRow.LayoutParams(width, height));
		}

		row.setPadding(2, 2, 2, 2);

		row.setGravity(Gravity.CENTER);

		return row;

	}

	private ImageView getImage(int i) {
		ImageView iv = new ImageView(mContext);
		iv.setImageResource(i);
		return iv;

	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(MIN_SCALE,
					Math.min(mScaleFactor, MAX_SCALE));

			invalidate();
			return true;
		}

	}
}
