/**
 * An activity for deleting a post
 */
class DeleteActivity extends GenericActivity {

    /**
     * Constructor for a custom message
     * @param sender - user sending the activity
     * @param receiver - user intended to receive the activity
     * @param content - message to be given with the activity
     */
    DeleteActivity(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    /**
     * Constructor with the default message
     * @param sender - user sending the activity
     * @param receiver - user intended to recieve the activity
     */
    DeleteActivity(Person sender, Person receiver) {
        super(sender, receiver, sender + "has deleted a post!");
    }
}

/**
 * An activity for sending a simple message to another user
 */
class CreateActivity extends GenericActivity {
    /**
     * Constructor for a message
     * @param sender - user sending the activity
     * @param receiver - user intended to receive the activity
     * @param content - message to be given with the activity
     */
    CreateActivity(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }
}

/**
 * An activity for unfollowing another user that you are currently following
 */
class UnfollowActivity extends GenericActivity {

    /**
     * Constructor for a custom message
     * @param sender - user sending the activity
     * @param receiver - user intended to receive the activity
     * @param content - message to be given with the activity
     */
    UnfollowActivity(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    /**
     * Constructor with the default message
     * @param sender - user sending the activity
     * @param receiver - user intended to recieve the activity
     */
    UnfollowActivity(Person sender, Person receiver) {
        super(sender, receiver, sender + " has stopped following you!");
    }

    /**
     * Functionality for getting removed from the following list when the activity is sent
     * @return boolean - true if opening is successful, false if it isn't
     */
    @Override
    public boolean send() {
        if(sender.getFollowing().contains(receiver)) {
            sender.getFollowing().remove(receiver);
            return super.send();
        }
        else {
            System.out.println("You're already following " + receiver + "!");
            return(false);
        }
    }

    /**
     * Functionality for getting removed from the followers list when the activity is read
     * @return boolean - true if opening is successful, false if it isn't
     */
    public boolean open() {
        receiver.getFollowers().remove(sender);
        return(super.open());
    }
}

/**
 * An activity for following another user
 */
class FollowActivity extends GenericActivity {

    /**
     * Constructor for a custom message
     * @param sender - user sending the activity
     * @param receiver - user intended to receive the activity
     * @param content - message to be given with the activity
     */
    FollowActivity(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    /**
     * Constructor with the default message
     * @param sender - user sending the activity
     * @param receiver - user intended to recieve the activity
     */
    FollowActivity(Person sender, Person receiver) {
        super(sender, receiver, sender + " has started following you!");
    }

    /**
     * Functionality for getting added to the following list when the activity is sent
     * @return boolean - true if opening is successful, false if it isn't
     */
    @Override
    public boolean send() {
        if(sender.getFollowing().contains(receiver)) { // If you're already following
            System.out.println("You're already following " + receiver + "!");
            return(false);
        }
        else {
            sender.getFollowing().add(receiver);
            return super.send();
        }
    }

    /**
     * Functionality for getting added to the followers list when the activity is read
     * @return boolean - true if opening is successful, false if it isn't
     */
    @Override
    public boolean open() {
        receiver.getFollowers().add(sender);
        return(super.open());
    }
}

/**
 * An activity for liking a user's profile
 */
class LikeActivity extends GenericActivity {

    /**
     * Constructor for a custom message
     * @param sender - user sending the activity
     * @param receiver - user intended to receive the activity
     * @param content - message to be given with the activity
     */
    LikeActivity(Person sender, Person receiver, String content) {
        super(sender, receiver, content);
    }

    /**
     * Constructor with the default message
     * @param sender - user sending the activity
     * @param receiver - user intended to recieve the activity
     */
    LikeActivity(Person sender, Person receiver) {
        super(sender, receiver, sender + " has liked your profile!");
    }

    /**
     * Functionality for getting added to the liked list when the activity is read
     * @return boolean - true if opening is successful, false if it isn't
     */
    @Override
    public boolean open() {
        receiver.getLiked().add(sender);
        return(super.open());
    }
}

/**
 * Abstract class, used as a base for all the other activities.
 * It implements all the functionalities of an Activity and acts
 * as the default code that specific times of activity will also need.
 */
public abstract class GenericActivity implements Activity{
    Person sender;
    Person receiver;
    StreamObject stream;

    /**
     * Default constructor for activities
     * @param sender - who is sending the activity
     * @param receiver - who is the intended receiver of the activity
     * @param content - the message attached to the activity
     */
    GenericActivity(Person sender, Person receiver, String content) {
        this.sender = sender;
        this.receiver = receiver;
        this.stream = new StreamObject(content);
    }

    /**
     * Getter for the sender
     * @return Person - the sender of the activity
     */
    @Override
    public Person getSender() {
        return sender;
    }

    /**
     * Getter for the receiver
     * @return Person - the intended receiver of the activity
     */
    @Override
    public Person getReceiver() {
        return receiver;
    }

    /**
     * Getter for the message
     * @return SteamObject - the message of the activity
     */
    @Override
    public StreamObject getStream() {
        return stream;
    }

    /**
     * Logic for sending a plain activity with just a message
     * @return boolean - true if the message is successfully sent, false if it isn't
     */
    @Override
    public boolean send() {
        try {
            receiver.getInbox().receive(this); // Get inbox to receive this activity
            return(true);
        } catch(Exception e) {
            return(false);
        }
    }

    /**
     * Logic for opening a plain activity from an inbox
     * @return boolean - true if the message is successfully opened, false if it isn't
     */
    @Override
    public boolean open() {
        try {
            System.out.println(sender + ": " + stream.getContent());
            return(true);
        } catch(Exception e) {
            return(false);
        }
    }
}