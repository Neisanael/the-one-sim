package core.NAKT;

public class KeyAuthorization <T>{
    private T authorizationKey;

    public KeyAuthorization(T authorizationKey) {
        this.authorizationKey = authorizationKey;
    }

    public T getAuthorizationKey() {
        return authorizationKey;
    }

    public void setAuthorizationKey(T authorizationKey) {
        this.authorizationKey = authorizationKey;
    }
}
