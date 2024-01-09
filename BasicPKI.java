import java.security.*;
import javax.crypto.Cipher;

public class BasicPKI {

    public static KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator generator = KeyPairGenerator.getInstance("RSA");
        generator.initialize(2048, new SecureRandom());
        return generator.generateKeyPair();
    }

    public static byte[] encrypt(String plainText, PublicKey publicKey) throws Exception {
        Cipher encryptCipher = Cipher.getInstance("RSA");
        encryptCipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return encryptCipher.doFinal(plainText.getBytes());
    }

    public static String decrypt(byte[] cipherText, PrivateKey privateKey) throws Exception {
        Cipher decryptCipher = Cipher.getInstance("RSA");
        decryptCipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(decryptCipher.doFinal(cipherText));
    }

    public static void main(String[] args) throws Exception {
        KeyPair keyPair = generateKeyPair();
        String originalMessage = "Confidential Message";
        byte[] encryptedMessage = encrypt(originalMessage, keyPair.getPublic());
        String decryptedMessage = decrypt(encryptedMessage, keyPair.getPrivate());

        System.out.println("Original: " + originalMessage);
        System.out.println("Encrypted: " + new String(encryptedMessage));
        System.out.println("Decrypted: " + decryptedMessage);
    }
}
