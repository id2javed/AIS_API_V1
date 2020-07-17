package com.app.main.encryption;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/*
* Developer    : Pallavi Dhage
* Created Date : 2019-07-26
* Updated Date : 
* Description  : Encryption And Decryption Master
*  */
public class EncryptionData {
public static String encryptId(String id) {
	String encryptId = null;
	id = id.replace("pvt", "+");
	id = id.replace("pam", "/");
	String data2[] = id.split(":");
	String iv = data2[0];
	byte[] encryptedByteData = hexStringToByteArray(data2[1]);

    Decoder decoder=Base64.getDecoder();
	String keyString = data2[2];
	try {
		try {
			IvParameterSpec iv1 = new IvParameterSpec(decoder.decode(iv));
			Key k = new SecretKeySpec(decoder.decode(keyString), "AES");

			encryptId = AESCrypt.decrypt3(Base64.getEncoder().encode(encryptedByteData), k, iv1);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			// System.out.println("Error in encription " + e);
		}
	} catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
			| BadPaddingException | InvalidAlgorithmParameterException e) {
		// System.out.println("Error in encription2 " + e);
	}
	
	return encryptId;
	
}

public static byte[] hexStringToByteArray(String s) {

	int len = s.length();
	byte[] data = new byte[len / 2];

	for (int i = 0; i < len; i += 2) {
		data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
	}
	return data;
}


	public static String[] getparamencryption(String encryptkey) {

		String[] spltstr = null;
		try {
			encryptkey = encryptkey.replace("pvt", "+");
			try {

				encryptkey = AESCrypt.decrypt(encryptkey);
			} catch (Exception e) {

			}

			spltstr = encryptkey.split("\\$\\$");
		} catch (Exception e) {
		}
		return spltstr;
	}

}
