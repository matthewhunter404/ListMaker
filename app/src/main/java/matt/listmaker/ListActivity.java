package matt.listmaker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MattsDesktop on 02/02/2017.
 */

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    private ListActivityListAdapter mItemAdapter;
    private Context mContext=this;
    private List<ListItem> mListItems = new ArrayList<ListItem>();
    private ListObject mListObject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(R.id.ListObjectDisplayList);
        mItemAdapter = new ListActivityListAdapter(this, R.layout.item_list_row, R.id.item_list_textview, mListItems);
        mListView.setAdapter(mItemAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
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
