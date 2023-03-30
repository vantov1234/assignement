package utils.enums;

public enum StatusCodes {

    CREATED(201);

    StatusCodes(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private final int statusCode;
}
