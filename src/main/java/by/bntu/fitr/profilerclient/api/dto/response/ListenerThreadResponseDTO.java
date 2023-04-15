package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ListenerThreadResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "enabled")
    private Integer enabled;

    @JsonProperty(value = "createAt")
    private LocalDateTime createAt;

    public ListenerThreadResponseDTO(Long id, String name, Integer enabled, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.createAt = createAt;
    }
}
