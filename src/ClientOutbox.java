import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Implementation of an outbox that will be used by users
 */
class ClientOutbox implements Outbox {
    LinkedList<Activity> outbox;

    /**
     * Constructor for an outbox, creates a list to keep track of
     * activities in the outbox
     */
    public ClientOutbox() {
        outbox = new LinkedList<Activity>();
    }

    /**
     * Method to add a new activity to the outbox
     * @param activity - item to be added to the outbox
     * @return boolean - true if the activity is successfully added, false if not
     */
    @Override
    public boolean send(Activity activity) {
        try {
            outbox.add(activity);
            return(true);
        } catch(Exception e) {
            return(false);
        }
    }

    /**
     * Remove the oldest item from the outbox, in order to send it to an inbox
     * @return Activity - the oldest activity in the outbox
     */
    @Override
    public Activity deliverNext() {
        try {
            Activity first = outbox.getFirst(); // Fetch the oldest item
            outbox.removeFirst(); // Remove it
            return(first);
        } catch(NoSuchElementException e) { // If there's nothing in the outbox
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Return the amount of items in the outbox
     * @return int - amount of items in outbox
     */
    @Override
    public int getCount() {
        return(outbox.size());
    }
}