package by.bntu.fitr.profilerclient.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequestDTO {
    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "password")
    private String password;

    @JsonProperty(value = "repeatedPassword")
    private String repeatedPassword;
}
