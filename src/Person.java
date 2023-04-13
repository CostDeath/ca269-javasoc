import java.util.LinkedList;

/**
 * Person is the class for a user of the JavaSoc application
 */
class Person {
    private final String username;
    private String bio;
    private final LinkedList<Person> followers;
    private final LinkedList<Person> following;
    private final LinkedList<Person> liked;
    private final ClientInbox inbox;
    private final ClientOutbox outbox;

    /**
     * Creates a new user with the provided information
     * @param username - the identifier for the user, this cannot be changed
     * @param bio - a description of the user, this can be changed
     */
    Person(String username, String bio) {
        this.username = username;
        this.bio = bio;
        // Create the unique lists for this user
        followers = new LinkedList<Person>();
        following = new LinkedList<Person>();
        liked = new LinkedList<Person>();
        inbox = new ClientInbox();
        outbox = new ClientOutbox();
    }

    /**
     * Add a newly made activity to the user's outbox. This will not get immediately sent.
     * @param activity - the activity to be added to the outbox
     * @return boolean - true if the method succeeds, false if it fails
     */
    public boolean draft(Activity activity) {
        try {
            outbox.send(activity);
            return(true);
        }
        catch(Exception e) {
            return(false);
        }
    }

    /**
     * Sends the next item in the outbox (in an order of FIFO)
     * @return boolean - true if the method succeeds, false if it fails
     */
    public boolean sendLatest() {
        try {
            outbox.deliverNext().send();
            return(true);
        }
        catch(Exception e) {
            return(false);
        }
    }

    /**
     * Reads the next item in the inbox (in an order of FIFO)
     * @return boolean - true if the method succeeds, false if it fails
     */
    public boolean readLatest() {
        try {
            inbox.readNext().open();
            return(true);
        }
        catch(Exception e) {
            return(false);
        }
    }

    /**
     * Getter for the user's username
     * @return String - the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Getter for the user's bio
     * @return String - the bio
     */
    public String getBio() {
        return bio;
    }

    /**
     * Getter for the user's followers list
     * @return LinkedList - list of Person objects that follow the user
     */
    public LinkedList<Person> getFollowers() {
        return followers;
    }

    /**
     * Getter for the user's following list
     * @return LinkedList - list of Person objects that the user follows
     */
    public LinkedList<Person> getFollowing() {
        return following;
    }

    /**
     * Getter for the user's liked list
     * @return LinkedList - list of Person objects that liked the user's profile
     */
    public LinkedList<Person> getLiked() {
        return liked;
    }

    /**
     * Getter for the user's inbox
     * @return Inbox - the user's inbox
     */
    public Inbox getInbox() {
        return inbox;
    }

    /**
     * Getter for the user's outbox
     * @return Inbox - the user's outbox
     */
    public Outbox getOutbox() {
        return outbox;
    }

    /**
     * Setter for the user's bio
     * @param bio - the user's new intended bio
     */
    public void setBio(String bio) {
        this.bio = bio;
    }

    /**
     * Method defining how the user should be displayed as a string
     * @return String - the user's username
     */
    @Override
    public String toString() {
        return username;
    }
}