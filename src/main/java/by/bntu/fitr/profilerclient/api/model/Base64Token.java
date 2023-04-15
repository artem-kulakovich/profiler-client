package by.bntu.fitr.profilerclient.api.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class Base64Token {
    private String userName;
    private String password;
}
