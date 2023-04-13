/**
 * Client for a user, each client has a user for
 * which it operates
 */
public class ClientApp implements App {
    private final Person user;

    /**
     * Constructor for a client
     * @param user - user using the client
     */
    ClientApp(Person user) {
        this.user = user;
    }

    /**
     * Retrieve the user's inbox
     * @return Inbox - the user's inbox
     */
    @Override
    public Inbox getInbox() {
        return user.getInbox();
    }

    /**
     * Retrieve the user's outbox
     * @return Outbox - the user's outbox
     */
    @Override
    public Outbox getOutbox() {
        return user.getOutbox();
    }

    /**
     * A demonstration of the functionalities of the client
     * @return String - confirmation that the demo worked
     */
    @Override
    public String demo() {
        // Person 1 is the user using the client
        Person userTwo = new Person("vedder", "philly student");

        // Test bios
        System.out.println("\tChecking Bios Test!");
        System.out.println("user 1's bio: " + user.getBio());

        // Create Messages
        System.out.println("\n\tSending Messages Test!");
        user.draft(new CreateActivity(user, userTwo, "hi ved!"));
        user.sendLatest();

        userTwo.readLatest();

        // Delete Messages
        System.out.println("\n\tDeleting Messages Test!");
        userTwo.draft(new DeleteActivity(userTwo, user));
        userTwo.sendLatest();

        user.readLatest();

        // Following function
        System.out.println("\n\tFollowing Users Test!");
        user.draft(new FollowActivity(user, userTwo));
        user.sendLatest();
        System.out.println("user 1's following: " + user.getFollowing());
        System.out.println("user 2's followers: " + userTwo.getFollowers());

        userTwo.readLatest();
        System.out.println("user 2's followers: " + userTwo.getFollowers());

        // Unfollowing function
        System.out.println("\n\tUnfollowing Users Test!");
        user.draft(new UnfollowActivity(user, userTwo));
        user.sendLatest();
        System.out.println("user 1's following: " + user.getFollowing());
        System.out.println("user 2's followers: " + userTwo.getFollowers());

        userTwo.readLatest();
        System.out.println("user 2's followers: " + userTwo.getFollowers());

        // Liking function
        System.out.println("\n\tLiking Users Test!");
        user.draft(new LikeActivity(user, userTwo));
        user.sendLatest();
        userTwo.readLatest();
        System.out.println("user 2's likes: " + userTwo.getLiked());

        // Custom Messages function
        System.out.println("\n\tCustom Messages Test!");
        userTwo.draft(new LikeActivity(userTwo, user, "liked ur profile bozo"));
        userTwo.sendLatest();
        user.readLatest();
        System.out.println("user 1's likes: " + user.getLiked());

        // Inbox & Outbox Stacking
        System.out.println("\n\tInbox & Outbox Stacking Test!");
        user.draft(new CreateActivity(user, userTwo, "1"));
        user.draft(new CreateActivity(user, userTwo, "2"));
        user.draft(new CreateActivity(user, userTwo, "3"));

        user.sendLatest();
        user.sendLatest();
        user.sendLatest();

        userTwo.readLatest();
        userTwo.readLatest();
        userTwo.readLatest();

        return("\n\nDemo concluded!");
    }

    public static void main(String[] args) {
        Person user = new Person("cost", "2nd year comsci");
        ClientApp app = new ClientApp(user);
        System.out.println(app.demo());
    }
}
