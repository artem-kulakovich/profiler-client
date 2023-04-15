package by.bntu.fitr.profilerclient.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
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
@Table(name = "timed_metric", schema = "public")
public class TimedMetricEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timed_metric_id_seq")
    @SequenceGenerator(name = "timed_metric_id_seq", sequenceName = "timed_metric_id_seq", allocationSize = 1)
    @Column(name = "id")
    private Long id;

    @Column(name = "metric_type")
    private String metricType;

    @Column(name = "execution_time")
    private Long executionTime;

    @Column(name = "execution_time_start")
    private Long executionTimeStart;

    @Column(name = "execution_time_end")
    private Long executionTimeEnd;

    @Column(name = "ended_date")
    private LocalDateTime endedDate;

    @Column(name = "started_date")
    private LocalDateTime startedDate;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "class_name")
    private String className;

}
