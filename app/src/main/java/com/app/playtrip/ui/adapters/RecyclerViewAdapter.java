package com.app.playtrip.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.app.playtrip.ui.viewbinders.abstracts.RecyclerViewBinder;

import java.util.List;

/**
 * Created on 8/10/2017.
 */

public class RecyclerViewAdapter<BannerEntity> extends RecyclerView.Adapter<RecyclerViewBinder.BaseViewHolder> {
    private List<BannerEntity> collections;
    private RecyclerViewBinder<BannerEntity> viewBinder;
    private Context mContext;
    

    public RecyclerViewAdapter(List<BannerEntity> collections, RecyclerViewBinder<BannerEntity> viewBinder, Context context) {
        this.collections = collections;
        this.viewBinder = viewBinder;
        this.mContext = context;
        
    }


    @Override
    public RecyclerViewBinder.BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return (RecyclerViewBinder.BaseViewHolder) this.viewBinder.createViewHolder(this.viewBinder.createView(this.mContext));
    }

    @Override
    public void onBindViewHolder(RecyclerViewBinder.BaseViewHolder holder, int position) {
      //  BannerEntity entity = (BannerEntity)this.collections.get(position);
      //  this.viewBinder.bindView(entity,position,holder,this.mContext);
    }

    @Override
    public int getItemCount() {
        return 5;
    }
    public BannerEntity getItemFromList(int index ) {
        return collections.get( index );
    }

    public List<BannerEntity> getList() {
        return collections;
    }
    /**
     * Clears the internal list
     */
    public void clearList() {
        collections.clear();
        notifyDataSetChanged();
    }

    /**
     * Adds a entity to the list and calls {@link #notifyDataSetChanged()}.
     * Should not be used if lots of NotificationDummy are added.
     *
     * @see #addAll(List)
     */
    public void add(BannerEntity entity ) {
        collections.add( entity );
        notifyDataSetChanged();
    }

    /**
     * Adds a NotificationDummy to the list and calls
     * {@link #notifyDataSetChanged()}. Can be used {
     * {@link List#subList(int, int)}.
     *
     * @see #addAll(List)
     */
    public void addAll( List<BannerEntity> entityList ) {
        collections.addAll( entityList );
        notifyDataSetChanged();
    }
}
