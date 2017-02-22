package matt.listmaker;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by MattsDesktop on 05/02/2017.
 */

public class ListActivityListAdapter extends ArrayAdapter<ListItem> {
    Context context;
    int layoutResourceId;
    List<ListItem> data = null;


    public ListActivityListAdapter(Context context, int layoutResourceId, int textlayoutResourceId,  List<ListItem> data) {
        super(context, layoutResourceId,textlayoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        //vListItem.setOnClickListener(this);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListActivityListAdapter.ListActivityAdapterHolder holder = null;

        if(row == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            row.setTag(holder);
        }
        else
        {
            holder = (ListActivityListAdapter.ListActivityAdapterHolder)row.getTag();
        }
        holder = new ListActivityListAdapter.ListActivityAdapterHolder();
        holder.Item_Name_Textview = (TextView)row.findViewById(R.id.item_list_textview);
        String displaytext = data.get(position).getItemText();
        holder.Item_Name_Textview.setText(displaytext);
        return row;
    }



    //The main adapter holder is simply a class to hold various views, so that they don't have to be constantly found every time the the program needs to
    //alther the data in them in some way. These variables are grouped together in one class for convenience
    static class ListActivityAdapterHolder
    {
        TextView Item_Name_Textview;
    }
}
