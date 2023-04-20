package utils;

import java.util.UUID;

public class TestUtils {

    public static String generateRandomString(int length) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid.substring(0, length);
    }

    public boolean containsIgnoreCaseAndSpace(String actual, String expected) {
        if (actual == null) {
            return false;
        }
        String expectedNormalized = expected.replaceAll("\\s", "");
        String actualNormalized = actual.replaceAll("\\s", "");
        return actualNormalized.toLowerCase().contains(expectedNormalized.toLowerCase());
    }
}
