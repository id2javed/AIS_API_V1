package com.app.main.encryption;



import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
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

public class AESCrypt 
{
    private static final String ALGORITHM = "AES";
	//private static final String ALGORITHM = "SHA-256";
    private static final String KEY = "3Hbfh997adMkEJ78";
    
    public static String encrypt(String value) throws Exception
    {
    	Encoder encoder=Base64.getEncoder();
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte [] encryptedByteValue = cipher.doFinal(value.getBytes("utf-8"));
        String encryptedValue64 = encoder.encodeToString(encryptedByteValue);
        return encryptedValue64;
               
    }
    
    public static String decrypt(String value) throws Exception
    {
    	Decoder decoder=Base64.getDecoder();
        Key key = generateKey();
        Cipher cipher = Cipher.getInstance(AESCrypt.ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        
        byte [] decryptedValue64 = decoder.decode(value);
        byte [] decryptedByteValue = cipher.doFinal(decryptedValue64);
        String decryptedValue = new String(decryptedByteValue,"utf-8");
        return decryptedValue;
                
    }
    
    private static Key generateKey() throws Exception 
    {
        Key key = new SecretKeySpec(AESCrypt.KEY.getBytes(),AESCrypt.ALGORITHM);
        return key;
    }

	public static final String decrypt3(final byte[] bs,final Key key, final IvParameterSpec iv) throws InvalidKeyException,
    NoSuchAlgorithmException, NoSuchPaddingException,
    IllegalBlockSizeException, BadPaddingException, IOException, InvalidAlgorithmParameterException {
    //	System.out.println("encrypted decrypt3  "+bs);
//byte[] encodedBytes = Base64.getEncoder().encode(encrypted.getBytes());
          Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
          cipher.init(Cipher.DECRYPT_MODE, key,iv);
          //byte [] raw = new BASE64Decoder().decodeBuffer(encrypted);
         byte[] raw = Base64.getDecoder().decode(bs);
          byte[] stringBytes = cipher.doFinal(raw);
          String clearText = new String(stringBytes, "UTF8");
          return clearText;
    }
}