package matt.listmaker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

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
    private static final String KEY_OBJECT_ID = "id";
    private static final String KEY_NAME = "name";

    // ListMakerItem Table Columns names
    private static final String KEY_ITEM_ID = "id";
    private static final String KEY_ITEM_LIST_ID = "listId";
    private static final String KEY_TEXT = "text";

    //______________________________________________________________________________________________
    public ListDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create ListMakerObjects
        //TODO: Implement "IF NOT EXISTS" clause
        String CREATE_LIST_TABLE = "CREATE TABLE " + TABLE_LIST_OBJECTS + "("
                + KEY_OBJECT_ID  + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT" +")";
        db.execSQL(CREATE_LIST_TABLE);
        //Create CommentTable
        String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_LIST_ITEMS + "("+ KEY_ITEM_ID+ " INTEGER PRIMARY KEY,"
                + KEY_ITEM_LIST_ID + " INT,"+ KEY_TEXT + " TEXT" +")";
        db.execSQL(CREATE_ITEM_TABLE);
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
    // Adding new List, the function takes a ListObject Object and creates the appropriate Database entries.
    public void addListObject(ListObject pListObject) {
        SQLiteDatabase db = this.getWritableDatabase();
        //if the uniqueID in pListObject is -1 that means that the listObject hasn't been given a unique ID yet, in which case that field is left blank when inserting as this will make
        //Sqllite automatically create a new unique number.
        if(pListObject.getUniqueID()==-1) {
            db.execSQL("INSERT INTO " + TABLE_LIST_OBJECTS + "(" +KEY_NAME + ") VALUES('" + pListObject.getListObjectName() + "')");
        }
        else {
            db.execSQL("INSERT INTO " + TABLE_LIST_OBJECTS + "(" + KEY_OBJECT_ID + "," + KEY_NAME + ") VALUES(" + pListObject.getUniqueID() + ", '" + pListObject.getListObjectName() + "')");
        }
    }
    // Returns a ListObject from the database, the function takes a int key to the ListObject entry, gets the fields from it and builds the ListObject Object, which is then returned.
    public ListObject getListObject(int pListObjectKey) {
        ListObject rListObject =new ListObject();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor objectCursor = db.query(TABLE_LIST_OBJECTS, new String[]{KEY_OBJECT_ID,KEY_NAME}, KEY_OBJECT_ID + "=?", new String[]{Integer.toString(pListObjectKey)},null,null,null);
        rListObject.setUniqueID(pListObjectKey); //alternately rListObject.setUniqueID(cursor.getInt(cursor.getColumnIndex(KEY_LIST_ID))); could be used
        rListObject.setListObjectName(objectCursor.getString(objectCursor.getColumnIndex(KEY_NAME)));//"getColumnIndex" is more programming cycles than hardcodng in values, but hopefully will make the code my dynamic and bug-resistant.
        Cursor itemCursor = db.query(TABLE_LIST_ITEMS, new String[]{KEY_ITEM_ID,KEY_ITEM_LIST_ID,KEY_TEXT}, KEY_ITEM_LIST_ID + "=?", new String[]{Integer.toString(pListObjectKey)},null,null,null);
        while(itemCursor.moveToNext())
        {
            rListObject.addListItem(makeListItem(itemCursor.getInt(itemCursor.getColumnIndex(KEY_ITEM_ID)), itemCursor.getString(itemCursor.getColumnIndex(KEY_TEXT)),pListObjectKey));
        }
        return rListObject;
    }
    // Removing a List from the database, the function takes a int key to the ListObject entry to be deleted, finds it and then removes it.
    public void removeListObject(int pListObjectKey) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_LIST_OBJECTS + " WHERE " + KEY_OBJECT_ID + "=" + pListObjectKey);

    }
    //This function gets all the ListObjects in the TABLE_LIST_OBJECTS table and then returns them in a List of ListObjects
    //This should probably reuse the getListObject function, but that seems a bit inefficient in terms ofr gettign the writable database and setting up cursors
    public List<ListObject> getAllListObjects(){
        List<ListObject> rTempListObjects = new ArrayList<ListObject>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor objectCursor = db.rawQuery("select * from "+TABLE_LIST_OBJECTS,null);
        while(objectCursor.moveToNext())
        {
            ArrayList<ListItem> dummyItemsArray= new ArrayList<ListItem>();
            ListObject rListObject =new ListObject(objectCursor.getString(objectCursor.getColumnIndex(KEY_NAME)),objectCursor.getInt(objectCursor.getColumnIndex(KEY_ITEM_ID)),dummyItemsArray);
            rTempListObjects.add(rListObject);
        }
        return rTempListObjects;
    }
    // Adding new List, the function takes a ListObject Object and creates the appropriate Database entries.
    public void addListItem(ListItem pListItem) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("INSERT INTO "+ TABLE_LIST_ITEMS +"("+ KEY_ITEM_ID+","+ KEY_ITEM_LIST_ID +","+ KEY_NAME+") VALUES("+pListItem.getItemUniqueID()+","+pListItem.getItemLinkID()+",'"+pListItem.getItemText()+"')");
    }
    // Returns a ListObject from the database, the function takes a int key to the ListObject entry, gets the fields from it and builds the ListObject Object, which is then returned.
    public ListItem getListItem(int pItemKey) {
        //ListItem rListItem =new ListItem();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABLE_LIST_ITEMS, new String[]{KEY_ITEM_ID,KEY_ITEM_LIST_ID,KEY_TEXT}, KEY_ITEM_ID+ "=?", new String[]{Integer.toString(pItemKey)},null,null,null);
        //rListItem.setItemUniqueID(pItemKey);
        //rListItem.setItemLinkID(cursor.getInt(cursor.getColumnIndex(KEY_ITEM_LIST_ID)));
        //rListItem.setItemText(cursor.getString(cursor.getColumnIndex(KEY_TEXT)));
        return makeListItem(pItemKey, cursor.getString(cursor.getColumnIndex(KEY_TEXT)),cursor.getInt(cursor.getColumnIndex(KEY_ITEM_LIST_ID)));
    }
    // Removing a List from the database, the function takes a int key to the ListObject entry to be deleted, finds it and then removes it.
    public void removeListItem(int pItemKey) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_LIST_ITEMS + " WHERE " + KEY_ITEM_ID + "=" + pItemKey);
    }

   //This function puts together a ListItem from a set a variables, and is used in both getListItem and getListObject
   public ListItem makeListItem(int pUniqueID,String pItemText, int pLinkID) {
       ListItem rListItem =new ListItem(pUniqueID,pItemText,pLinkID);
       return rListItem;
   }
    //This function is intended for internal use in the DBhelper class, called to return a List of items that belong a ListObject.
    public List<ListItem> getListItemArray(int pLinkID,SQLiteDatabase pDB){
        List<ListItem> rTempListItems = new ArrayList<ListItem>();
        Cursor itemCursor = pDB.query(TABLE_LIST_ITEMS, new String[]{KEY_ITEM_ID,KEY_ITEM_LIST_ID,KEY_TEXT}, KEY_ITEM_LIST_ID + "=?", new String[]{Integer.toString(pLinkID)},null,null,null);
        while(itemCursor.moveToNext())
        {
            rTempListItems.add(makeListItem(itemCursor.getInt(itemCursor.getColumnIndex(KEY_ITEM_ID)), itemCursor.getString(itemCursor.getColumnIndex(KEY_TEXT)),pLinkID));
        }
        return rTempListItems;
    }
}
