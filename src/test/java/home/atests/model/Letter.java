package home.atests.model;

public class Letter {
    private final String message;
    private final String recipient;

    public Letter(String message, String recipient) {
        this.message = message;
        this.recipient = recipient;
    }

    public String getMessage() {
        return message;
    }

    public String getRecipient() {
        return recipient;
    }
}
