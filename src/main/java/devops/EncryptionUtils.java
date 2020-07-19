package devops;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

/**
 * Utility Functions for Encryption and Decryption
 */
public class EncryptionUtils {

    private static String SYMMETRIC_KEY_ALGORITHM = "AES";
    private static int SYMMETRIC_KEY_LENGTH = 256;
    private static String KEY_FILE = "/opt/bdmp/secure/dev-int-symmetric.key";

    public static void main(String args[]) {
        //Test
        String key = generateSymmetricKey();
        assert "hello".equals(decrypt(encrypt("hello")));

        //encrypt
        String toEncrypt = "ClojureRox15!";
        System.out.println("encrypted - " + encrypt(toEncrypt));

        //Decrypt
        String toDecrypt = "3654e66cb9621a87a71b10696cc37863";
        System.out.println("decrypted - " + decrypt(toDecrypt));
    }

    /**
     * Generates a Symmetric Key using the default algorithm: AES
     *
     * @return encoded bytes for the symmetric key in Hexadecimal
     */
    public static String generateSymmetricKey() {
        try {
            KeyGenerator kg = KeyGenerator.getInstance(SYMMETRIC_KEY_ALGORITHM);
            kg.init(SYMMETRIC_KEY_LENGTH);
            return encode2Hex(KeyGenerator.getInstance(SYMMETRIC_KEY_ALGORITHM).generateKey().getEncoded());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encrypts input data using provided symmetric key
     *
     * @param data data to be encrypted
     * @return encrypted data in Hexadecimal
     */
    public static String encrypt(String data) {
        try {
            String key = loadKeyFromFile();
            Cipher c = Cipher.getInstance(SYMMETRIC_KEY_ALGORITHM);
            SecretKeySpec k = new SecretKeySpec(decodeFromHex(key), SYMMETRIC_KEY_ALGORITHM);
            c.init(Cipher.ENCRYPT_MODE, k);
            return String.valueOf(encode2Hex(c.doFinal(data.getBytes())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Decrypts encrypted bytes with the provided symmetric key
     *
     * @param encrypted hexadecimal encoded encrypted data
     * @return decrypted data
     */
    public static String decrypt(String encrypted) {
        try {
            String key = "a863740c2d269f628c678f8298261929"; //loadKeyFromFile();
            Cipher c = Cipher.getInstance(SYMMETRIC_KEY_ALGORITHM);
            SecretKeySpec k = new SecretKeySpec(decodeFromHex(key), SYMMETRIC_KEY_ALGORITHM);
            c.init(Cipher.DECRYPT_MODE, k);
            return new String(c.doFinal(decodeFromHex(encrypted)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String loadKeyFromFile() {
        try {
            return new String(Files.readAllBytes(Paths.get(KEY_FILE)), StandardCharsets.UTF_8)
                    .replaceAll("(\\r|\\n)", "");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private EncryptionUtils() {
    }

    public static String encode2Hex(byte[] bytes) {
        return DatatypeConverter.printHexBinary(bytes).toLowerCase();
    }

    public static byte[] decodeFromHex(String hexString) {
        return DatatypeConverter.parseHexBinary(hexString);
    }
}
