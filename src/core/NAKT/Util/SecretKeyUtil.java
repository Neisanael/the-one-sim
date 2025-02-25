package core.NAKT.Util;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

public class SecretKeyUtil {

    // Generate a random secret key (Base64 format)
    public static String generateSecretKey(int keySize) {
        try {
            byte[] key = new byte[keySize];
            SecureRandom random = new SecureRandom();
            random.nextBytes(key);
            return Base64.getEncoder().encodeToString(key);
        } catch (Exception e) {
            throw new RuntimeException("Error generating secret key", e);
        }
    }

    // Generate a random secret key using KeyGenerator (for AES, HMAC, etc.)
    public static String generateSecretKeyHMAC() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA1"); // Change to HmacSHA256 if needed
            SecretKey secretKey = keyGen.generateKey();
            return Base64.getEncoder().encodeToString(secretKey.getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("HMAC algorithm not found", e);
        }
    }
}
