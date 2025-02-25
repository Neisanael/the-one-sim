package core.NAKT.Util;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class EncryptionUtil {

    // KØ num = KH K(w) (num)
    // KH is a keyed pseudo-random function (approximated by HMAC-MD5 or HMAC-SHA1)
    public static String generateKeyRoot(String topic, int numAttrPublisher){
        //  K(w) = KH rk(KDC) (w)
        // authorization key for the topic w
        // rk(KDC) denotes the secret key of the KDC
        String keyTopic = hashHmacSha1(SecretKeyUtil.generateSecretKey(64), topic);
        return hashHmacSha1(keyTopic, numAttrPublisher);
    }

    private static String hashHmacSha1(String key, String message) {
        return hashHmacSha1Internal(key, message.getBytes(StandardCharsets.UTF_8));
    }

    private static String hashHmacSha1(String key, int message) {
        return hashHmacSha1Internal(key, String.valueOf(message).getBytes(StandardCharsets.UTF_8));
    }

    private static String hashHmacSha1Internal(String key, byte[] messageBytes) {
        try {
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(secretKey);
            byte[] hash = mac.doFinal(messageBytes);

            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error while calculating HMAC-SHA1", e);
        }
    }

    public static String hashSha1(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array to hexadecimal format
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-1 algorithm not found", e);
        }
    }
}
