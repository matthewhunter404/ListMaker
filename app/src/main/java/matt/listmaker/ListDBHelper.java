package matt.listmaker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by MattsDesktop on 04/01/2017.
 */
public class ListDBHelper extends SQLiteOpenHelper {
    //______________________________________________________________________________________________
    //DBContract
    //______________________________________________________________________________________________
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "ListMakerDB";

    // The names of the two main Tables
    private static final String TABLE_LIST_OBJECTS = "ListMakerObjects";
    private static final String TABLE_LIST_ITEMS = "ListMakerItems";

    // ListMakerObject Table Columns names
    private static final String KEY_LIST_ID = "id";
    private static final String KEY_NAME = "name";

    // ListMakerItem Table Columns names
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_TEXT = "text";

    //______________________________________________________________________________________________
    public ListDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create ListMakerObjects
        String CREATE_PICASSO_TABLE = "CREATE TABLE " + TABLE_LIST_OBJECTS + "("
                + KEY_LIST_ID  + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" +")";
        db.execSQL(CREATE_PICASSO_TABLE);
        //Create CommentTable
        String CREATE_TABLE_COMMENTS = "CREATE TABLE " + TABLE_LIST_ITEMS + "("
                + KEY_ITEM_ID + " INTEGER PRIMARY KEY,"+ KEY_TEXT + " TEXT" +")";
        db.execSQL(CREATE_TABLE_COMMENTS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Currently the OnUpgrade will simply drop older table if existed.
        //TODO: Gain a better understanding of under what circumstances onUpgrade is called and how to implement this such that user data is retained even on a database update.
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST_OBJECTS );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LIST_ITEMS);
        // Create tables again
        onCreate(db);
    }
    //TODO:Implement these:
    // Adding new List, the function takes a ListObject Object and creates the appropriate Database entries.
    public void addListObject(ListObject pListObject) {

    }
    // Returns a ListObject from the database, the function takes a int key to the ListObject entry, gets the fields from it and builds the ListObject Object, which is then returned.
    public ListObject getListObject(int pListObjectKey) {
        ListObject rListObject =new ListObject();

        return rListObject;
    }
    // Removing a List from the database, the function takes a int key to the ListObject entry to be deleted, finds it and then removes it.
    public void removeListObject(int pListObjectKey) {

    }

    //TODO:Implement these:
    // Adding new List, the function takes a ListObject Object and creates the appropriate Database entries.
    public void addListItem(ListItem pListItem) {

    }
    // Returns a ListObject from the database, the function takes a int key to the ListObject entry, gets the fields from it and builds the ListObject Object, which is then returned.
    public ListItem getListItem(int pListItemKey) {
        ListItem rListItem =new ListItem();

        return rListItem;
    }
    // Removing a List from the database, the function takes a int key to the ListObject entry to be deleted, finds it and then removes it.
    public void removeListItem(int pListItemKey) {

    }

}
