package core.NAKT.Util;

import core.Message;
import core.NAKT.KeyAuthorization;
import core.NAKT.KeyEncryption;

public class PropertyEncryptionUtil {
    public static String processKeyEncryption(Message m) {
        Object property = m.getProperty("keyEncryption");
        if (property instanceof KeyEncryption<?> keyEncryption) {
            return (String) keyEncryption.getEncryptionKey(); // Cast to String
        } else {
            throw new ClassCastException("The 'keyEncryption' property is not of type KeyEncryption");
        }
    }

    public static void processKeyAuthorization(Message m){
        System.out.println(((KeyAuthorization<?>) m.getProperty("keyAuthorization")).getAuthorizationKey());
    }
}


