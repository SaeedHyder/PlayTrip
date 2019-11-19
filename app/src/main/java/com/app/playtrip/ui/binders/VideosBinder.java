package com.app.playtrip.ui.binders;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.app.playtrip.R;
import com.app.playtrip.activities.DockActivity;
import com.app.playtrip.entities.video.VideoInnerData;
import com.app.playtrip.helpers.BasePreferenceHelper;
import com.app.playtrip.interfaces.RecyclerClickListner;
import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;
import com.nostra13.universalimageloader.core.ImageLoader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideosBinder extends RecyclerViewBinder<VideoInnerData> {

    private DockActivity dockActivity;
    private BasePreferenceHelper prefHelper;
    private ImageLoader imageLoader;
    private RecyclerClickListner clickListner;

    public VideosBinder(DockActivity dockActivity, BasePreferenceHelper prefHelper, RecyclerClickListner clickListner) {
        super(R.layout.item_gallery_item);
        this.dockActivity = dockActivity;
        this.prefHelper = prefHelper;
        this.imageLoader = ImageLoader.getInstance();
        this.clickListner = clickListner;
    }

    @Override
    public BaseViewHolder createViewHolder(View view) {
        return new ViewHolder(view);
    }


    @Override
    public void bindView(VideoInnerData entity, int position, Object holder, Context context) {
        ViewHolder viewHolder=(ViewHolder)holder;


        if (position == 0){
            viewHolder.tvItemBottom.setVisibility(View.GONE);
            viewHolder.tvItemTop.setVisibility(View.VISIBLE);

        }else {
            viewHolder.tvItemBottom.setVisibility(View.VISIBLE);
            viewHolder.tvItemTop.setVisibility(View.GONE);
        }

        viewHolder.txtHeading.setText(entity.getTitle());
    }


    static class ViewHolder extends BaseViewHolder {
        @BindView(R.id.txtHeading)
        TextView txtHeading;
        @BindView(R.id.tv_galleryItemShare)
        TextView tvGalleryItemShare;
        @BindView(R.id.tv_itemTop)
        TextView tvItemTop;
        @BindView(R.id.tv_itemBottom)
        TextView tvItemBottom;


        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


