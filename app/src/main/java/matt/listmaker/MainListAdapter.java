package matt.listmaker;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import matt.listmaker.ListObject;
import matt.listmaker.R;

/**
 * Created by matt on 2016/06/07.
 */
public class MainListAdapter extends ArrayAdapter<ListObject> {

    Context context;
    int layoutResourceId;
    List<ListObject> data = null;

    public MainListAdapter(Context context, int layoutResourceId, int textlayoutResourceId,  List<ListObject> data) {
        super(context, layoutResourceId,textlayoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        //vListItem.setOnClickListener(this);
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
        holder = new MainAdapterHolder();
        holder.List_Name_Textview = (TextView)row.findViewById(R.id.object_list_row_textview);
        String displaytext = data.get(position).getListObjectName();
        holder.List_Name_Textview.setText(displaytext);
        return row;
    }
    //The main adapter holder is simply a class to hold various views, so that they don't have to be constantly found every time the the program needs to
    //alther the data in them in some way. These variables are grouped together in one class for convenience
    static class MainAdapterHolder
    {
        TextView List_Name_Textview;
    }


}

