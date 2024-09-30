package PACKAGE_NAME;

class Comment {
    private final String username;
    private final String message;

    public Comment(String username, String message) {
        this.username = username;
        this.message = message;
    }

    @Override
    public String toString() {
        return username + ": " + message;
    }
}
