package matt.listmaker;

/**
 * Created by MattsDesktop on 04/01/2017.
 */
public class ListItem {
    private String itemText; //The items text
    private int keyID; //this links this list item to the unique ID of a list


    public ListItem() {
        itemText="";
        keyID=0;
    }

    public ListItem(String pText, int pkeyID) {
        itemText=pText;
        keyID=pkeyID;
    }

    public void setItemText(String pText) {
        itemText=pText;
    }

    public String getItemText(){
        return itemText;
    }

    public void setItemKeyID(int pkeyID) {
        keyID=pkeyID;
    }

    public int getItemKeyID(){
        return keyID;
    }

}
