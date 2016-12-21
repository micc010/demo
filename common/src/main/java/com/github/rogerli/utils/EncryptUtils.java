package com.github.rogerli.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

/**
 * @author Roger
 * @description
 */
public class EncryptUtils {

	/**
	 * Returns MD5-tarkistussumman merkkijonosta.
	 * 
	 * @param s merkkijono, josta tarkistussumma lasketaan
	 * @return MD5 summa 32 heksadesimaalin muodostamana merkkijonona
	 */
	public static String md5Encoding(String s) {
		if (s == null) {
			return null;
		}
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("MD5");
		} catch (java.security.NoSuchAlgorithmException ex) {
			throw new AssertionError(ex);
		}
		byte[] res = new byte[0];
		try {
			res = md.digest(s.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
		return dumpBytes(res);
	}

	/**
	 * Returns heksadesimaalit tavutaulukosta merkkijonona.
	 * 
	 * @param bytes taulukko, jonka tavuista heksadesimaalit otetaan
	 * @return merkkijono, jossa jokaista taulukon tavua vastaa kaksi
	 *         heksadesimaalia.
	 */
	private static String dumpBytes(byte[] bytes) {
		int size = bytes.length;
		StringBuffer sb = new StringBuffer(size * 2);
		String s;
		for (int i = 0; i < size; ++i) {
			s = Integer.toHexString(bytes[i]);
			if (s.length() == 8) { // -128 <= bytes[i] < 0
				sb.append(s.substring(6));
			} else if (s.length() == 2) { // 16 <= bytes[i] < 128
				sb.append(s);
			} else {
				sb.append("0" + s); // 0 <= bytes[i] < 16
			}
		}
		return sb.toString();
	}

}
