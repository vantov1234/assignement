package utils.enums;

public enum RepoErrorMessages {

    NOT_FOUND("Not Found", "https://docs.github.com/rest"),
    BAD_CREDENTIALS("Bad credentials", "https://docs.github.com/rest"),
    WRONG_HEADER("Unsupported 'Accept' header: 'text/html; charset=utf'. Must accept 'application/json'.", "https://docs.github.com/v3/media");

    RepoErrorMessages(String message, String docUrl) {
        this.message = message;
        this.docUrl = docUrl;
    }

    public String getMessage() {
        return message;
    }

    public String getDocUrl() {
        return docUrl;
    }

    private final String message;
    private final String docUrl;
}
