package matt.listmaker;

import android.content.Context;
import android.widget.ArrayAdapter;

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
}
