package by.bntu.fitr.profilerclient.api.uti;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class PasswordEncoder {

    public String encodeWithMD5(final String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("md5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        byte[] passwordInBytes = md5.digest(password.getBytes());
        StringBuilder passwordBuilder = new StringBuilder();

        for (byte b : passwordInBytes) {
            passwordBuilder.append(String.format("%02X", b));
        }

        return passwordBuilder.toString();
    }
}
