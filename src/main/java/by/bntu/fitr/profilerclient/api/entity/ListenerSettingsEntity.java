package by.bntu.fitr.profilerclient.api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "listener_settings", schema = "public")
public class ListenerSettingsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listener_settings_id_seq")
    @SequenceGenerator(name = "listener_settings_id_seq", sequenceName = "listener_settings_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "bootstrap_server")
    private String bootstrapServer;

    @Column(name = "key_deserializer")
    private String keyDeserializer;

    @Column(name = "value_deserializer")
    private String valueDeserializer;

    @Column(name = "session_timeout")
    private Integer sessionTimeout;

    @Column(name = "heart_beat_interval")
    private Integer heartBeatInterval;

    @Column(name = "group_id")
    private String groupId;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    public ListenerSettingsEntity(String name, String bootstrapServer, String keyDeserializer, String valueDeserializer, Integer sessionTimeout, Integer heartBeatInterval, String groupId) {
        this.name = name;
        this.bootstrapServer = bootstrapServer;
        this.keyDeserializer = keyDeserializer;
        this.valueDeserializer = valueDeserializer;
        this.sessionTimeout = sessionTimeout;
        this.heartBeatInterval = heartBeatInterval;
        this.groupId = groupId;
    }
}
