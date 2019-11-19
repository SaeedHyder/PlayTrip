package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.BannerEntity;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MakeVideoBinder extends RecyclerViewBinder<VideoInnerData> {


    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private RecyclerClickListner clickListner;

    public MakeVideoBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.item_trip);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.clickListner = clickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public void bindView(VideoInnerData entity, int position, Object viewHolder, Context context) {
        final ViewHolder holder = (ViewHolder) viewHolder;
        holder.tvHeading.setText(""+entity.getTitle());
        holder.tvItemVSubHeading.setText(""+entity.getCaption());
        holder.tvLocation.setText(""+entity.getUser().getDetails().getCity());
        holder.tvVTime.setText(""+entity.getVideo_length());
        Picasso.with(context).load(entity.getThumbnail_image_url()).error(R.drawable.bg).into(holder.imgItemTrim);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListner.onClick(entity,position);

            }
        });


    }


    static
    class ViewHolder extends BaseViewHolder {

        @BindView(R.id.img_itemTrim)
        ImageView imgItemTrim;
        @BindView(R.id.tv_heading)
        TextView tvHeading;
        @BindView(R.id.tv_itemVSubHeading)
        TextView tvItemVSubHeading;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_v_time)
        TextView tvVTime;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}