package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.FollowingEnt;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.bumptech.glide.Glide;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FollowingBinder extends RecyclerViewBinder<FollowingEnt> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;
    private boolean isFollowed;

    public FollowingBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner,boolean isFollowed) {
        super(R.layout.item_following);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.clickListner = clickListner;
        this.isFollowed=isFollowed;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    public void bindView(FollowingEnt entity, int position, Object holder, Context context) {

        ViewHolder viewHolder=(ViewHolder)holder;
        if(isFollowed){
            viewHolder.btnFollow.setVisibility(View.GONE);
        }else{
            viewHolder.btnFollow.setVisibility(View.VISIBLE);
        }

        viewHolder.txtName.setText(entity.getName()+"");
        Picasso.with(context).load(entity.getDetails().getImageUrl()).error(R.drawable.s2_img2).into(viewHolder.ivImage);

    }

    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.ivImage)
        CircleImageView ivImage;
        @BindView(R.id.txtName)
        TextView txtName;
        @BindView(R.id.btnFollow)
        TextView btnFollow;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
