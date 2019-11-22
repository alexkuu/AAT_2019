package service;

import javax.management.openmbean.InvalidKeyException;

public class UserFactory {
    private static final String USERS_FILE = "users.properties";
    private static PropReader pReader = new PropReader(USERS_FILE);

    public static User getUser(String role) {
        role = role.toLowerCase();
        if (getUserAttribute(role, true) == null) {
            throw new InvalidKeyException("User with role [" + role + "] not found.");
        }
        return new User(getUserAttribute(role, true), getUserAttribute(role, false));
    }

    private static String getUserAttribute(String role, boolean returnName) {
        if (role == null || role.trim().length() == 0) {
            return null;
        }
        if (returnName) {
            return pReader.readProperty(role + ".name");
        }
        return pReader.readProperty(role + ".password");
    }
}
