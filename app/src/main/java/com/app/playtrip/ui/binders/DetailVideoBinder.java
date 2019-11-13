package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailVideoBinder extends RecyclerViewBinder<VideoInnerData> {


    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private RecyclerClickListner clickListner;

    public DetailVideoBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.item_video_long);
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
        holder.tvItemVHeading.setText(""+entity.getTitle());
        holder.tvItemVSubHeading.setText(""+entity.getCaption());
        //holder.tvLocation.setText(""+entity.getLocation_id());
      //  holder.tvVTime.setText(""+entity.getVideo_length());
        holder.tvView.setText(""+entity.getVideo_view_count());
        holder.tvShare.setText(""+entity.getVideo_share_count());
        holder.tvComment.setText(""+entity.getVideo_comment_count());
        holder.tvLikes.setText(""+entity.getVideo_like_count());



        Picasso.with(context).load(entity.getThumbnail_image_url()).error(R.drawable.bg).into(holder.ivItemBg);


    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_itemBg)
        ImageView ivItemBg;
        @BindView(R.id.tv_itemVHeading)
        TextView tvItemVHeading;
        @BindView(R.id.tv_itemVSubHeading)
        TextView tvItemVSubHeading;
        @BindView(R.id.tv_location)
        TextView tvLocation;
        @BindView(R.id.tv_v_time)
        TextView tvVTime;
        @BindView(R.id.tv_view)
        TextView tvView;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.tv_comment)
        TextView tvComment;
        @BindView(R.id.tv_likes)
        TextView tvLikes;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}