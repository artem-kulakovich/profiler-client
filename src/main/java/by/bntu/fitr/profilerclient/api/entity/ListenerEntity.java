package by.bntu.fitr.profilerclient.api.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "listener", schema = "public")
public class ListenerEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listener_id_seq")
    @SequenceGenerator(name = "listener_id_seq", sequenceName = "listener_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "topic_name")
    private String topicName;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "enabled")
    private Integer enabled;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "listener_settings_id")
    private ListenerSettingsEntity listenerSettings;

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.ALL}, mappedBy = "listenerEntity")
    private List<ListenerThreadEntity> listenerThreads;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "listener_type_id")
    private ListenerTypeEntity listenerType;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST}, mappedBy = "listener")
    private List<UserEntity> users;

    public ListenerEntity(String name, String topicName) {
        this.name = name;
        this.topicName = topicName;
    }

    public void addListenerThread(ListenerThreadEntity listenerThreadEntity) {
        if (listenerThreads == null) {
            listenerThreads = new ArrayList<>();
        }

        listenerThreads.add(listenerThreadEntity);
    }

    public ListenerEntity(String name, String topicName, ListenerSettingsEntity listenerSettings, List<ListenerThreadEntity> listenerThreads, ListenerTypeEntity listenerType) {
        this.name = name;
        this.topicName = topicName;
        this.listenerSettings = listenerSettings;
        this.listenerThreads = listenerThreads;
        this.listenerType = listenerType;
    }
}
