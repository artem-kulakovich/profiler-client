package by.bntu.fitr.profilerclient.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TimedMetricResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "metricType")
    private String metricType;

    @JsonProperty(value = "executionTime")
    private Long executionTime;

    @JsonProperty(value = "executionTimeStart")
    private Long executionTimeStart;

    @JsonProperty(value = "executionTimeEnd")
    private Long executionTimeEnd;

    @JsonProperty(value = "endedDate")
    private LocalDateTime endedDate;

    @JsonProperty(value = "startedDate")
    private LocalDateTime startedDate;

    @JsonProperty(value = "methodName")
    private String methodName;

    @JsonProperty(value = "className")
    private String className;

    public TimedMetricResponseDTO(Long id, String metricType, Long executionTime, Long executionTimeStart, Long executionTimeEnd, LocalDateTime endedDate, LocalDateTime startedDate, String methodName, String className) {
        this.id = id;
        this.metricType = metricType;
        this.executionTime = executionTime;
        this.executionTimeStart = executionTimeStart;
        this.executionTimeEnd = executionTimeEnd;
        this.endedDate = endedDate;
        this.startedDate = startedDate;
        this.methodName = methodName;
        this.className = className;
    }
}
