package matt.listmaker;

import java.util.ArrayList;

/**
 * Created by MattsDesktop on 03/01/2017.
 */
public class ListObject {
    private String listName; //The lists name
    private int uniqueID; // A unique ID to ensure that this List is unique, this is the main ID handle the program will have on the object as list names don't need to be unique.
    private ArrayList<ListItem> listItemsArray;

    public ListObject(){
        listName="";
        uniqueID=0;
        listItemsArray=new ArrayList();
    }

    public ListObject(String pName, int pUniqueID, ArrayList pArrayList){
        listName=pName;
        uniqueID=pUniqueID;
        listItemsArray=pArrayList;
    }

    public String getListObjectName(){
        return listName;
    }
    public void setListObjectName(String pListObjectName){
        listName=pListObjectName;
    }
    public int getUniqueID(){
        return uniqueID;
    }

    public void setUniqueID(int pUniqueID){
        uniqueID=pUniqueID;
    }

    public ArrayList getlistItemsArray(){
        return listItemsArray;
    }

    public void setlistItemsArray(ArrayList pArrayList){
        listItemsArray=pArrayList;
    }
}
