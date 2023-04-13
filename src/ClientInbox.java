import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementation of an inbox that will be used by users
 */
class ClientInbox implements Inbox {
    LinkedList<Activity> inbox;

    /**
     * Constructor for an inbox, creates a list to keep track of
     * activities in the inbox
     */
    public ClientInbox() {
        inbox = new LinkedList<Activity>();
    }

    /**
     * Method to add a new activity to the inbox
     * @param activity - item to be added to the inbox
     * @return boolean - true if the activity is successfully added, false if not
     */
    @Override
    public boolean receive(Activity activity) {
        try {
            inbox.add(activity);
            return(true);
        } catch(Exception e) {
            return(false);
        }
    }

    /**
     * Remove the oldest item from the inbox, in order to be read
     * @return Activity - the oldest activity in the inbox
     */
    @Override
    public Activity readNext() {
        try {
            Activity first = inbox.getFirst(); // Fetch oldest item
            inbox.removeFirst(); // Remove it
            return(first);
        } catch(NoSuchElementException e) { // If there's nothing in the inbox
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Return the amount of items in the inbox
     * @return int - amount of items in inbox
     */
    @Override
    public int getCount() {
        return(inbox.size());
    }
}