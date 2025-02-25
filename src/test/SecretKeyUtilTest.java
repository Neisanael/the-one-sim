package test;

import core.NAKT.Util.SecretKeyUtil;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import java.util.Base64;

public class SecretKeyUtilTest extends TestCase {

    private SecretKeyUtil secretKeyUtil;

    @Before
    public void setUp() throws Exception {
        secretKeyUtil = new SecretKeyUtil();
    }

    @Test
    public void testSecretKeyLength() {
        int keySize = 32;
        String secretKey = secretKeyUtil.generateSecretKey(keySize);
        byte[] decodedKey = Base64.getDecoder().decode(secretKey);
        assertEquals(keySize, decodedKey.length);
    }

    @Test
    public void testSecretKeyNotNull() {
        String secretKey = secretKeyUtil.generateSecretKey(32);
        assertNotNull(secretKey);
        assertFalse(secretKey.isEmpty());
    }
}
