package matt.listmaker;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
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

public class ListActivityListAdapter extends RecyclerView.Adapter<ListActivityListAdapter.ListActivityViewHolder> {
    Context context;
    int layoutResourceId;
    List<ListItem> data = null;


    public ListActivityListAdapter (Context context, int layoutResourceId,  List<ListItem> data) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        //vListItem.setOnClickListener(this);
    }
    // Create new views (invoked by the layout manager)
    @Override
    public ListActivityListAdapter.ListActivityViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        TextView v = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ListActivityViewHolder vh = new ListActivityViewHolder(v);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ListActivityViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(data.get(position).getItemText());

    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return data.size();
    }

    //The main adapter holder is simply a class to hold various views, so that they don't have to be constantly found every time the the program needs to
    //alter the data in them in some way. These variables are grouped together in one class for convenience
    //From the Android developer website:
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ListActivityViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public ListActivityViewHolder(TextView v) {
            super(v);
            mTextView = v;
        }
    }

}
