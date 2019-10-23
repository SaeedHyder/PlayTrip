package com.app.playtrip.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.playtrip.R;
import com.app.playtrip.entities.BannerEntity;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class RecyclerViewAdapterHomeBottom extends RecyclerView.Adapter<RecyclerViewAdapterHomeBottom.RestaurantViewHolder> {
    private ArrayList<BannerEntity> mRestaurants = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapterHomeBottom(Context context, ArrayList<BannerEntity> restaurants) {
        mContext = context;
        mRestaurants = restaurants;
    }

    @Override
    public RecyclerViewAdapterHomeBottom.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_bottom, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RestaurantViewHolder holder, int position) {
      //  holder.bindRestaurant(mRestaurants.get(position));
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindRestaurant(BannerEntity restaurant) {

        }
    }
}