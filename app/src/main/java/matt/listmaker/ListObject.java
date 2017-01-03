package matt.listmaker;

import java.util.ArrayList;

/**
 * Created by MattsDesktop on 03/01/2017.
 */
public class ListObject {
    private String listName; //The lists name
    private int UniqueID; // A unique ID to ensure that this List is unique, this is the main ID handle the program will have on the object as list names don't need to be unique.
    private ArrayList<ListItem> listItemsArray;

    public ListObject(){
        listName="";
        UniqueID=0;
        listItemsArray=new ArrayList();
    }
    public String getListObjectName(){
        return listName;
    }
    public void setListObjectName(String pListObjectName){
        listName=pListObjectName;
    }
    public int getUniqueID(){
        return UniqueID;
    }

    public void setUniqueID(int pUniqueID){
        UniqueID=pUniqueID;
    }
}
