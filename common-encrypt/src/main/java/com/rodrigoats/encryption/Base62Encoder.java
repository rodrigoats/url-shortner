package com.rodrigoats.encryption;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Base62Encoder {

  private static final String BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
  private static final int BASE = BASE62.length();

  public static String encode(String value) {
    var generatedHash = generateHash(value);
    return encode(new BigInteger(generatedHash, 16));
  }

  private static String generateHash(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5"); // Can use SHA-256 for stronger hashing
      byte[] hashBytes = md.digest(input.getBytes(StandardCharsets.UTF_8));

      // Convert bytes to hexadecimal string
      StringBuilder hexString = new StringBuilder();
      for (byte b : hashBytes) {
        hexString.append(String.format("%02x", b)); // Convert byte to hex
      }
      return hexString.toString(); // Full MD5 hash (32 characters)
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
  }

  private static String encode(BigInteger num) {
    StringBuilder sb = new StringBuilder();
    while (num.compareTo(BigInteger.ZERO) > 0) {
      int remainder = num.mod(BigInteger.valueOf(BASE)).intValue();
      sb.append(BASE62.charAt(remainder));
      num = num.divide(BigInteger.valueOf(BASE));
    }
    return sb.reverse().toString();
  }
}
