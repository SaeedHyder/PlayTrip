package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeTopBinder extends RecyclerViewBinder<BannerEntity> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private RecyclerClickListner clickListner;

    public HomeTopBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.item_home_top);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;

        this.clickListner = clickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(BannerEntity entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;
    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_itemBg)
        ImageView ivItemBg;
        @BindView(R.id.tv_itemHHeading)
        TextView tvItemHHeading;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}