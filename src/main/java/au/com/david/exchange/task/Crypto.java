package au.com.david.exchange.task;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Crypto helper methods.
 * 
 * @author howed
 */
public class Crypto {
	private static final byte[] KEY = "A5#*Cv8-hT%C@l)H".getBytes();

	/**
	 * Needed so that users can encrypt their passwords.
	 * 
	 * @param args
	 *            The password to be encrypted.
	 */
	public static void main(String[] args) {
		System.out.println(args[0]);
		System.out.println(encrypt(args[0]));
	}

	public static String encrypt(String password) {
		try {
			Key key = new SecretKeySpec(KEY, 0, 16, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, key);
			String encryptedString = Base64.encodeBase64String(cipher
					.doFinal(password.getBytes()));
			return encryptedString;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected static String decrypt(String password) {
		try {
			Key key = new SecretKeySpec(KEY, 0, 16, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, key);
			String decryptedString = new String(cipher.doFinal(Base64
					.decodeBase64(password)));
			return decryptedString;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
