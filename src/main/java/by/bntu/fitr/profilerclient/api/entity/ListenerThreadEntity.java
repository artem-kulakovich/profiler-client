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
@Table(name = "listener_thread", schema = "public")
public class ListenerThreadEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listener_thread_id_seq")
    @SequenceGenerator(name = "listener_thread_id_seq", sequenceName = "listener_thread_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "enabled")
    private Integer enabled;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
    @JoinColumn(name = "listener_id")
    public ListenerEntity listenerEntity;

    public ListenerThreadEntity(String name) {
        this.name = name;
    }
}
