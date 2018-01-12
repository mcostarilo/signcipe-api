package com.st.signservice.security.jwt.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

@Service
public class TemporalLoginServiceImpl implements TemporalLoginService {

	private SecretKeySpec generateSecretKey(String myKey) {
		SecretKeySpec result = null;
		MessageDigest sha = null;
		byte[] key;
		try {
			key = myKey.getBytes("UTF-8");
			sha = MessageDigest.getInstance("SHA-1");
			key = sha.digest(key);
			key = Arrays.copyOf(key, 16);
			result = new SecretKeySpec(key, "AES");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String passwordCipher(String str, String secret, int mode) {
		String result = null;
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(mode, generateSecretKey(secret));
			result = new String(cipher.doFinal(str.getBytes()));
		} catch (Exception e) {
			System.out.println("Error: " + e.toString());
		}
		return result;
	}
	
	private String getSeed() {
		DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date date = new Date();
		String secret = sourceFormat.format(date);
		return secret;
	}
	
	@Override
	public boolean checkPasswod(String userName, String passwordBase64) {
		boolean result = false;
		try {
			String password = new String(Base64.getDecoder().decode(passwordBase64));
			String decodedPassword = passwordCipher(password, getSeed(), Cipher.DECRYPT_MODE);
			if (userName != null && userName.equals(decodedPassword)) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String generatePassword(String userName) {
		String encodedPassword = passwordCipher(userName, getSeed(), Cipher.ENCRYPT_MODE);
		return Base64.getEncoder().encodeToString(encodedPassword.getBytes());
	}

}
