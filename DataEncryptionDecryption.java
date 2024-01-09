import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DataEncryptionDecryption {

    public static SecretKey generateKey() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256);
        return keyGenerator.generateKey();
    }

    public static byte[] encryptData(String data, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data.getBytes());
    }

    public static String decryptData(byte[] encryptedData, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decrypted = cipher.doFinal(encryptedData);
        return new String(decrypted);
    }

    public static void main(String[] args) throws Exception {
        SecretKey key = generateKey();
        String originalData = "Sensitive Information";
        byte[] encryptedData = encryptData(originalData, key);
        String decryptedData = decryptData(encryptedData, key);

        System.out.println("Original Data: " + originalData);
        System.out.println("Encrypted Data: " + new String(encryptedData));
        System.out.println("Decrypted Data: " + decryptedData);
    }
}
