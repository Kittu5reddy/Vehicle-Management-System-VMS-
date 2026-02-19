package com.vms.security;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    // Hash password before saving to DB
    public static String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt(12));
    }

    // Verify password during login
    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}
