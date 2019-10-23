package com.app.playtrip.ui.viewbinders.abstracts;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.app.playtrip.R;

public abstract class RecyclerViewBinder<BannerEntity> {
	private int LayoutResId;
	public RecyclerViewBinder(int LayoutResId) {
		this.LayoutResId = LayoutResId;
	}

	public View createView (Context activity) {
		LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(R.layout.item_home_middle, null );
		view.setTag( createViewHolder( view ) );
		return view;

	}

	public abstract BaseViewHolder createViewHolder (View view);

	/**
	 * @param entity object with view DataUser
	 * @param position view postion in recycler view
	 * @param viewHolder holder to hold views
	 * @param context
	 */

	public abstract void bindView(BannerEntity entity, int position, Object viewHolder, Context context);

	public static class BaseViewHolder extends RecyclerView.ViewHolder {

		public BaseViewHolder(View itemView) {
			super(itemView);
		}
	}
}
