package matt.listmaker;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import matt.listmaker.ListObject;
import matt.listmaker.R;

/**
 * Created by matt on 2016/06/07.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainAdapterHolder> implements ItemTouchHelperAdapter{

    Context context;
    int layoutResourceId;
    List<ListObject> data = null;
    private ListDBHelper dbHelper;
    private CoordinatorLayout mCoordinatorLayout;

    public MainListAdapter(Context context, int layoutResourceId, int textlayoutResourceId,  List<ListObject> data,CoordinatorLayout pCoordinatorLayout) {
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        dbHelper= new ListDBHelper(context);
        mCoordinatorLayout=pCoordinatorLayout; //I can't shake the feeling that this could be derived directly from the already passed context and that it might be simpler than this

        //vListItem.setOnClickListener(this);
    }
    // Create new views (invoked by the layout manager)
    @Override
    public MainListAdapter.MainAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        FrameLayout ListItemView = (FrameLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.object_list_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        MainAdapterHolder vh = new MainAdapterHolder(ListItemView);
        return vh;
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MainAdapterHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(data.get(position).getListObjectName());

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
    public static class MainAdapterHolder extends RecyclerView.ViewHolder
    {
        public TextView mTextView;
        public Button mButton;
        public MainAdapterHolder(FrameLayout v) {
            super(v);
            mTextView = (TextView) v.findViewById((R.id.object_list_row_textview));
            mButton= (Button) v.findViewById((R.id.undo_button));
            mButton.setEnabled(false);
            mButton.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onItemDismiss(int position) {
        dbHelper.removeListObject(data.get(position).getUniqueID());
        data.remove(position);
        notifyItemRemoved(position);
        Snackbar snackbar = Snackbar
                .make(mCoordinatorLayout, "List Deleted!", Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(mCoordinatorLayout, "List restored!", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                    }
                });
        snackbar.show();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(data, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(data, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition); //It’s very important to call notifyItemRemoved() and notifyItemMoved() so the Adapter is aware of the changes.
    }

}

