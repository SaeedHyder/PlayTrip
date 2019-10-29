package com.app.playtrip.entities;

import java.util.ArrayList;

public class Data<M> {
    private ArrayList<M> data = new ArrayList<>();
    private int pages;
    private int total_records;

    public ArrayList<M> getData() {
        return data;
    }

    public void setData(ArrayList<M> data) {
        this.data = data;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal_records() {
        return total_records;
    }

    public void setTotal_records(int total_records) {
        this.total_records = total_records;
    }
}
