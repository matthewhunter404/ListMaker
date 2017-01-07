package matt.listmaker;

/**
 * Created by MattsDesktop on 04/01/2017.
 */
public class ListItem {
    private String itemText; //The items text
    private int linkID; //this links this list item to the unique ID of a list
    private int uniqueID; // A unique ID to ensure that this Item is unique, this is the main ID handle the program will have on each Item.

    public ListItem() {
        itemText="";
        linkID=0;
        uniqueID=0;
    }

    public ListItem(int pUniqueID,String pText, int pkeyID) {
        uniqueID=pUniqueID;
        itemText=pText;
        linkID=pkeyID;
    }

    public void setItemText(String pText) {
        itemText=pText;
    }

    public String getItemText(){
        return itemText;
    }

    public void setItemLinkID(int pLinkID) {
        linkID=pLinkID;
    }

    public int getItemLinkID(){
        return linkID;
    }

    public void setItemUniqueID(int pUniqueID) {
        uniqueID=pUniqueID;
    }

    public int getItemUniqueID(){
        return uniqueID;
    }

}
