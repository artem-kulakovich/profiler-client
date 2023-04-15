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
@Table(name = "disk_metric", schema = "public")
public class DiskMetricEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disk_metric_id_seq")
    @SequenceGenerator(name = "disk_metric_id_seq", sequenceName = "disk_metric_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "metric_type")
    private String metricType;

    @Column(name = "disk_name")
    private String diskName;

    @Column(name = "disk_path")
    private String diskPath;

    @Column(name = "total_space")
    private Long totalSpace;

    @Column(name = "used_space")
    private Long usedSpace;

    @Column(name = "free_space")
    private Long freeSpace;

    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "ended_date")
    private LocalDateTime endedDate;
}
