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
@Table(name = "jvm_heap_metric", schema = "public")
public class JVMHeapMetricEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "jvm_heap_metric_id_seq")
    @SequenceGenerator(name = "jvm_heap_metric_id_seq", sequenceName = "jvm_heap_metric_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "metric_type")
    private String metricType;

    @Column(name = "available_heap")
    private Long availableHeap;

    @Column(name = "used_heap")
    private Long usedHeap;

    @Column(name = "max_heap")
    private Long maxHeap;

    @Column(name = "free_memory")
    private Long freeMemory;
    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "ended_date")
    private LocalDateTime endedDate;
}
