package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ListenerTypeResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "createAt")
    private LocalDateTime createAt;


    public ListenerTypeResponseDTO(Long id, String name, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.createAt = createAt;
    }
}
