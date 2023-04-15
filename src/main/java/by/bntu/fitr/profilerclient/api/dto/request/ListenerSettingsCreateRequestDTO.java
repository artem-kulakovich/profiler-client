package by.bntu.fitr.profilerclient.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class ListenerSettingsCreateRequestDTO {
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
}
