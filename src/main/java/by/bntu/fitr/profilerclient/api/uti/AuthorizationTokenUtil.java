package by.bntu.fitr.profilerclient.api.uti;

import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.model.Base64Token;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class AuthorizationTokenUtil {
    public String encodeBase64(String msg) {
        return Base64.getEncoder().encodeToString(msg.getBytes());
    }

    public String decodeBase64(String msg) {
        return new String(Base64.getDecoder().decode(msg));
    }

    public boolean isTokenValid(String msg) {
        return decodeBase64(msg).split(":").length == 2;
    }

    public Base64Token getTokenParts(String authToken) {
        String decodedToken = decodeBase64(authToken);
        String[] tokenParts = decodedToken.split(":");

        if (tokenParts.length != 2) {
            throw new CommonException("Permission denied");
        }

        Base64Token base64Token = new Base64Token();
        base64Token.setUserName(tokenParts[0]);
        base64Token.setPassword(tokenParts[1]);

        return base64Token;
    }
}
