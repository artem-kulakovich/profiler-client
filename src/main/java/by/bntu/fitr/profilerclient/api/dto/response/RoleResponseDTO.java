package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class RoleResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "createAt")
    private LocalDateTime createAt;

    @JsonProperty(value = "permissions")
    private List<PermissionResponseDTO> permissions;

    public RoleResponseDTO(Long id, String name, LocalDateTime createAt, List<PermissionResponseDTO> permissions) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
        this.permissions = permissions;
    }
}
