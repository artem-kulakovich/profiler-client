package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "userName")
    private String userName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "createAt")
    private LocalDateTime createAt;

    @JsonProperty(value = "role")
    private RoleResponseDTO role;

    public UserResponseDTO(Long id, String userName, String email, LocalDateTime createAt, RoleResponseDTO role) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createAt = createAt;
        this.role = role;
    }
}
