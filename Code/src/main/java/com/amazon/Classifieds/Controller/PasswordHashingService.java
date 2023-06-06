package com.amazon.Classifieds.Controller;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class PasswordHashingService {
	
	public static String hashPassword(String password) {
		try {
			// Hash the Password
			MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
			byte[] hash = messageDigest.digest(password.getBytes(StandardCharsets.UTF_8));
			password = Base64.getEncoder().encodeToString(hash);	
			return password;
		}catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		return null;
	}
}
