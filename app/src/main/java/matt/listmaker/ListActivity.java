package matt.listmaker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

/**
 * Created by MattsDesktop on 02/02/2017.
 */

public class ListActivity extends AppCompatActivity {
    private ListView mListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        mListView = (ListView) findViewById(R.id.ListObjectDisplayList);
    }



}
