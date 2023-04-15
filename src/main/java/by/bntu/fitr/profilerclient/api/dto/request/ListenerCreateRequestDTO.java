package by.bntu.fitr.profilerclient.api.dto.request;

import by.bntu.fitr.profilerclient.api.dto.response.ListenerSettingsResponseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ListenerCreateRequestDTO {
    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "topicName")
    private String topicName;

    @JsonProperty(value = "type")
    private String type;
}
