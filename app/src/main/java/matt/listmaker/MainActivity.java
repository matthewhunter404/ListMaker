package matt.listmaker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListDBHelper dbHelper = new ListDBHelper(this);
    private MainListAdapter mObjectAdapter;
    private RecyclerView mRecyclerView;
    private Context mContext=this; //This needs to be declared seperately as it is used in onClickListeners, where the "this" keyword is interpreted by the compiler as refereing to the listener itself.
    private List<ListObject> mListObjects = new ArrayList<ListObject>();
    private RecyclerView.LayoutManager mLayoutManager; //RecyclerViews need a LayoutManager
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.MainCoordinatorlayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.ListObjectDisplayList);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mObjectAdapter =  new MainListAdapter(this, R.layout.object_list_row, R.id.object_list_row_textview, mListObjects,mCoordinatorLayout);
        mRecyclerView.setAdapter(mObjectAdapter);

        ItemTouchHelper.Callback callback = new ListItemTouchHelper(mObjectAdapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);


        syncListObjects();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getAndStoreNewListObject();
            }
        });

        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(this, mRecyclerView,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        Intent ListActivity_intent = new Intent(MainActivity.this, ListActivity.class);
                        Log.d("Test",Integer.toString(position)+" "+ mListObjects.get(position).getListObjectName() + " " + Integer.toString(mListObjects.get(position).getUniqueID()));
                        ListActivity_intent.putExtra("ListObjectUniqueID", mListObjects.get(position).getUniqueID());
                        MainActivity.this.startActivity(ListActivity_intent);
                    }

                })
        );



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        else if (id == R.id.delete_all) {
            dbHelper.clearDatabase();
            mListObjects.clear();
            mObjectAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    //Accepts user input and returns a ListObject with the entered information
    void getAndStoreNewListObject()
    {
        AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
        alert.setTitle("Title");
        alert.setMessage("Message");
        // Set an EditText view to get user input
        final EditText input = new EditText(mContext);
        alert.setView(input);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int pPosition) {
                ListObject tListObbject =new ListObject();
                tListObbject.setListObjectName(input.getText().toString());
                tListObbject.setListItemsArray(new ArrayList());
                tListObbject.setUniqueID(-1);
                storeListObject(tListObbject);
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.show();
    }

    void storeListObject(ListObject pListObject) {
        testLogmListObjects();
        dbHelper.addListObject(pListObject);
        syncListObjects();
        testLogmListObjects();
    }

    void syncListObjects() {
        //List<ListObject> ListObjects = new ArrayList<ListObject>();
        mListObjects.clear();
        mListObjects.addAll(dbHelper.getAllListObjects());
        //Log.d("5th list object--",mListObjects.get(5).getListObjectName());
        mObjectAdapter.notifyDataSetChanged();
    }

    void testLogmListObjects()
    {
        for(int k=0;k<mListObjects.size();k++) {
            Log.d("Test",Integer.toString(k)+" "+ mListObjects.get(k).getListObjectName() + " " + Integer.toString(mListObjects.get(k).getUniqueID()));
        }
    }


}
