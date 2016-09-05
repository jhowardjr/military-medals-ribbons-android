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
	TableLayout table;
	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;
	Context context;

	public RibbonRackView(Context context) {
		super(context);
		this.context = context;
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
		table.draw(canvas);
		canvas.restore();
	}

	public void fillTableLayout(ArrayList<Integer> list) {

		LinkedList<Integer> queue = new LinkedList<Integer>();

		for (Integer id : list) {

			queue.add(id);

		}

		table = new TableLayout(context);
		
		LayoutParams params = new LinearLayout.LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);

		while (queue.size() > 0) {

			table.addView(buildRack(queue), params);
		}

		table.setGravity(Gravity.CENTER);

		table.setBackgroundResource(R.drawable.rackbackground);
	}

	public TableLayout getTableLayout() {
		
		return table;
		
	}

	private TableRow buildRack(LinkedList<Integer> stack) {

		int width = context.getResources().getDrawable(R.drawable.r005)
				.getIntrinsicWidth();
		
		int height = context.getResources().getDrawable(R.drawable.r005)
				.getIntrinsicHeight();

		TableRow row = new TableRow(context);

		int top = stack.size() % 3;

		if (top == 0) {
			top = 3;
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
		ImageView iv = new ImageView(context);
		iv.setImageResource(i);
		return iv;

	}

	private class ScaleListener extends
			ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(0.1f, Math.min(mScaleFactor, 5.0f));

			invalidate();
			return true;
		}

	}
}
