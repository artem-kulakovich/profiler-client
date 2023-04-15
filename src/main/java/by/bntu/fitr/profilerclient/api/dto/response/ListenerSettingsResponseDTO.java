package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ListenerSettingsResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "bootstrapServer")
    private String bootstrapServer;

    @JsonProperty(value = "keyDeserializer")
    private String keyDeserializer;

    @JsonProperty(value = "valueDeserializer")
    private String valueDeserializer;

    @JsonProperty(value = "sessionTimeout")
    private Integer sessionTimeout;

    @JsonProperty(value = "heartBeatInterval")
    private Integer heartBeatInterval;

    @JsonProperty(value = "groupId")
    private String groupId;

    @JsonProperty(value = "createAt")
    private LocalDateTime createAt;

    public ListenerSettingsResponseDTO(Long id, String name, String bootstrapServer, String keyDeserializer, String valueDeserializer, Integer sessionTimeout, Integer heartBeatInterval, String groupId, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.bootstrapServer = bootstrapServer;
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
        this.sessionTimeout = sessionTimeout;
        this.heartBeatInterval = heartBeatInterval;
        this.groupId = groupId;
        this.createAt = createAt;
    }
}
