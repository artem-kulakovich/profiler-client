package by.bntu.fitr.profilerclient.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class StatusCreateRequestDTO {
    @JsonProperty(value = "name")
    private String name;

    public StatusCreateRequestDTO(String name) {
        this.name = name;
    }
}
