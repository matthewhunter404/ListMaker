package matt.listmaker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MattsDesktop on 02/02/2017.
 */

public class ListActivity extends AppCompatActivity {
    private ListDBHelper dbHelper = new ListDBHelper(this);
    private RecyclerView mRecyclerView;
    private ListActivityListAdapter mItemAdapter;
    private Context mContext=this;
    private List<ListItem> mListItems = new ArrayList<ListItem>();
    private ListObject mListObject;
    private RecyclerView.LayoutManager mLayoutManager; //RecyclerViews need a LayoutManager

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        Bundle bundle = getIntent().getExtras();
        int uniqueID=bundle.getInt("ListObjectUniqueID");
        Log.d("Test",Integer.toString(uniqueID));
        mListObject=dbHelper.getListObject(uniqueID);

        mRecyclerView = (RecyclerView) findViewById(R.id.ListItemDisplayList);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mItemAdapter = new ListActivityListAdapter(this, R.layout.item_list_row, mListObject.getListItemsArray());
        mRecyclerView.setAdapter(mItemAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getNewListItem();
            }
        });

    }
    void getNewListItem(){
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Title");
        alert.setMessage("Message");
        // Set an EditText view to get user input
        final EditText input = new EditText(mContext);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pPosition) {
                ListItem newListItem=new ListItem(-1,input.getText().toString(),mListObject.getUniqueID());
                mListItems.add(newListItem);
                mListObject.addListItem(newListItem);
                //Todo: Find appropriate place to put this database update. It might be here though.
                dbHelper.replaceListObject(mListObject); //This might be more efficient in terms of Database calls to place at the functions called when the activity comes to an end. But how suddenly can the function be called to stop?
                mItemAdapter.notifyDataSetChanged(); //Updates the items shown in the ListView to reflect theis most recent addition.
            }
        });
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();

    }


}
