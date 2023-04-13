/**
 * Stream Object is used to convey the content of an activity
 */
class StreamObject {
    private final String content;

    /**
     * Constructor for a StreamObject
     * @param content - the content of the message to be sent, this cannot be changed
     */
    StreamObject(String content) {
        this.content = content;
    }

    /**
     * Getter for the message content
     * @return String - the message content
     */
    public String getContent() {
        return content;
    }
}