package matt.listmaker;

/**
 * Created by MattsDesktop on 23/02/2017.
 */

public interface ItemTouchHelperAdapter {

    void onItemMove(int fromPosition, int toPosition);

    void onItemDismiss(int position);
}
