package service;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.LeafPropertyLoader;

import javax.management.openmbean.InvalidKeyException;
import java.io.IOException;
import java.util.Properties;

public class UserFactory {
    private static final String USERS_FILE = "/users.properties";

    public static User getUser(String role) {
        if (getUserAttribute(role, true) == null) {
            throw new InvalidKeyException("User with role [" + role + "] not found.");
        }
        return new User(getUserAttribute(role, true), getUserAttribute(role, false));
    }

    private static String getUserAttribute(String role, boolean returnName) {
        if (role == null || role.trim().length() == 0) {
            return null;
        }
        Properties props = new Properties();
        try {
            props.load(LeafPropertyLoader.class.getResourceAsStream(USERS_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (returnName) {
            return props.getProperty(role + ".name");
        }
        return props.getProperty(role + ".password");
    }
}
