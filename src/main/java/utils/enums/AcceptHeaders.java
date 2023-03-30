package utils.enums;

public enum AcceptHeaders {

    GITHUB("application/vnd.github+json");

    AcceptHeaders(String acceptHeader) {
        this.acceptHeader = acceptHeader;
    }

    public String getAcceptHeader() {
        return acceptHeader;
    }

    private final String acceptHeader;
}
