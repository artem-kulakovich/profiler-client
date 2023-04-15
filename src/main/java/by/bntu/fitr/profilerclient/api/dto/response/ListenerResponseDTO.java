package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class ListenerResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "topicName")
    private String topicName;

    @JsonProperty(value = "enabled")
    private Integer enabled;

    @JsonProperty(value = "createAt")
    private LocalDateTime createAt;

    @JsonProperty(value = "listenerSettings")
    private ListenerSettingsResponseDTO listenerSettingsResponseDTO;

    @JsonProperty(value = "listenerThreads")
    private List<ListenerThreadResponseDTO> listenerThreads;

    @JsonProperty(value = "listenerType")
    private ListenerTypeResponseDTO listenerTypeResponseDTO;

    public ListenerResponseDTO(Long id, String name, String topicName, Integer enabled, LocalDateTime createAt, ListenerSettingsResponseDTO listenerSettingsResponseDTO, List<ListenerThreadResponseDTO> listenerThreads, ListenerTypeResponseDTO listenerTypeResponseDTO) {
        this.id = id;
        this.name = name;
        this.topicName = topicName;
        this.enabled = enabled;
        this.createAt = createAt;
        this.listenerSettingsResponseDTO = listenerSettingsResponseDTO;
        this.listenerThreads = listenerThreads;
        this.listenerTypeResponseDTO = listenerTypeResponseDTO;
    }
}
