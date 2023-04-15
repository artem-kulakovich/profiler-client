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
@Table(name = "garbage_collector_metric", schema = "public")
public class GarbageCollectorMetricEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "garbage_collector_metric_id_seq")
    @SequenceGenerator(name = "garbage_collector_metric_id_seq", sequenceName = "garbage_collector_metric_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "metric_type")
    private String metricType;

    @Column(name = "execution_time")
    private Long totalExecutionTime;

    @Column(name = "count_of_garbage")
    private Long totalGarbageCount;

    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "ended_date")
    private LocalDateTime endedDate;
}
