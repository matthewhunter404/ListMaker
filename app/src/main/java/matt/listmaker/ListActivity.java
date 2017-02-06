package matt.listmaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MattsDesktop on 02/02/2017.
 */

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    private ListActivityListAdapter mItemAdapter;
    private List<ListItem> mListItems = new ArrayList<ListItem>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(R.id.ListObjectDisplayList);
        mItemAdapter = new ListActivityListAdapter(this, R.layout.item_list_row, R.id.item_list_textview, mListItems);
        mListView.setAdapter(mItemAdapter);
    }



}
