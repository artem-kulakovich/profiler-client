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
@Table(name = "listener_type", schema = "public")
public class ListenerTypeEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "listener_type_id_seq")
    @SequenceGenerator(name = "listener_type_id_seq", sequenceName = "listener_type_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "create_at")
    private LocalDateTime createAt;

    public ListenerTypeEntity(String name) {
        this.name = name;
    }
}
