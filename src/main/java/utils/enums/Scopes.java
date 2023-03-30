package utils.enums;

public enum Scopes {

    PUBLIC_REPO("public_repo"),
    DELETE_REPO("delete_repo");

    Scopes(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }

    private final String scope;
}
