package utils.enums;

public enum AcceptHeaders {

    GITHUB("application/vnd.github+json"),
    TEXT("text/html; charset=utf-8");

    AcceptHeaders(String acceptHeader) {
        this.acceptHeader = acceptHeader;
    }

    public String getAcceptHeader() {
        return acceptHeader;
    }

    private final String acceptHeader;
}
