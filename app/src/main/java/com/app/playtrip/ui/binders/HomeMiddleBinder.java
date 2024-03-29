package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecycleHomeClickListner;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class HomeMiddleBinder extends RecyclerViewBinder<VideoInnerData> {



    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private RecycleHomeClickListner clickListner;

    public HomeMiddleBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecycleHomeClickListner clickListner) {
        super(R.layout.item_home_middle);
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
        holder.tvItemHHeading.setText(entity.getTitle());
        holder.tvView.setText(entity.getVideo_view_count());
        holder.tvShare.setText(entity.getVideo_share_count());
        holder.tvComment.setText(entity.getVideo_comment_count());
        holder.tvLikes.setText(entity.getVideo_like_count());
        holder.tvItemHUN.setText(entity.getUser().getName());
        holder.tvItemHLRight.setText(entity.getCaption());
        holder.tvItemHLRightUpper.setText(entity.getLocation().getCity());
        holder.tvItemHDate.setText("" + entity.getTranslations().get(0).getCreated_at());
        Picasso.with(context).load(entity.getThumbnail_image_url()).error(R.drawable.bg).into(holder.ivItemBg);
        Picasso.with(context).load(entity.getUser().getDetails().getImageUrl()).error(R.drawable.s21_img).into(holder.civItemHL);


    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.iv_itemBg)
        ImageView ivItemBg;
        @BindView(R.id.tv_itemHHeading)
        TextView tvItemHHeading;
        @BindView(R.id.civ_itemHL)
        CircleImageView civItemHL;
        @BindView(R.id.tv_itemHUN)
        TextView tvItemHUN;
        @BindView(R.id.tv_itemHDate)
        TextView tvItemHDate;
        @BindView(R.id.tv_itemHLRightUpper)
        TextView tvItemHLRightUpper;
        @BindView(R.id.tv_itemHLRight)
        TextView tvItemHLRight;
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