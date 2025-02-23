package com.example.encryption;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class EncryptUtil
{
  private static SecretKey key;

  static {
    try {
      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
      keyGenerator.init(128);
      key = keyGenerator.generateKey();
    } catch (Exception e) {
      throw new RuntimeException("Error initializing encryption key", e);
    }
  }

  public static String encrypt(String data) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.ENCRYPT_MODE, key);
    return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
  }

  public static String decrypt(String encryptedData) throws Exception {
    Cipher cipher = Cipher.getInstance("AES");
    cipher.init(Cipher.DECRYPT_MODE, key);
    return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedData)));
  }
}
