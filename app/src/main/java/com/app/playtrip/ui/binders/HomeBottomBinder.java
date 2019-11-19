package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.trending.TrendingEntity;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecycleHomeClickListner;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeBottomBinder extends RecyclerViewBinder<TrendingEntity> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private RecycleHomeClickListner clickListner;

    public HomeBottomBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecycleHomeClickListner clickListner) {
        super(R.layout.item_home_bottom);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;

        this.clickListner = clickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(TrendingEntity entity, int position, Object viewHolder, Context context) {

        final ViewHolder holder = (ViewHolder) viewHolder;

        Picasso.with(context).load(entity.getDetails().getImageUrl()).error(R.drawable.bg).into(holder.ivProfileImage);
        holder.txtName.setText(entity.getName());

        holder.llMainFrame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity,position,"User");

            }
        });

    }



      static class ViewHolder extends BaseViewHolder{
        @BindView(R.id.ivProfileImage)
        CircleImageView ivProfileImage;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.llMainFrame)
        LinearLayout llMainFrame;
        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}