package core.NAKT;

public class KeyEncryption<T> {
    private T encryptionKey;

    public KeyEncryption(T encryptionKey) {
        this.encryptionKey = encryptionKey;
    }

    public T getEncryptionKey() {
        return encryptionKey;
    }

    public void setEncryptionKey(T encryptionKey) {
        this.encryptionKey = encryptionKey;
    }
}
