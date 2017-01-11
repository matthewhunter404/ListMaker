package com.example.matt.listmaker;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import matt.listmaker.ListObject;
import matt.listmaker.R;

/**
 * Created by matt on 2016/06/07.
 */
public class MainListAdapter extends ArrayAdapter<ListObject> {

    Context context;
    int layoutResourceId;
    ListObject data[] = null;

    public MainListAdapter(Context context, int layoutResourceId, int textlayoutResourceId,  ListObject[] data) {
        super(context, layoutResourceId,textlayoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        MainAdapterHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            row.setTag(holder);
        }
        else
        {
            holder = (MainAdapterHolder)row.getTag();
        }

        return row;
    }

    static class MainAdapterHolder
    {
        TextView List_Name_textview;
    }
}

