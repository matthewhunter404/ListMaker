package matt.listmaker;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListDBHelper dbHelper = new ListDBHelper(this);
    private MainListAdapter mObjectAdapter;
    private ListView mListView;
    private ListItem moo;
    private List<ListObject> mListObjects = new ArrayList<ListObject>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView mainListview = (ListView) findViewById(R.id.ListObjectDisplayList);
        mObjectAdapter = new MainListAdapter(this, R.layout.object_list_item, R.id.object_list_item_textview, mListObjects);
        mainListview.setAdapter(mObjectAdapter);
        syncListObjects();
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList pArrayList = new ArrayList();
                ListObject test = new ListObject("List Name", -1, pArrayList);
                storeNewListObject(test);
            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    void storeNewListObject(ListObject pListObject) {
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
