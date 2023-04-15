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
public class GarbageCollectorMetricResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "metricType")
    private String metricType;

    @JsonProperty(value = "totalExecutionTime")
    private Long totalExecutionTime;

    @JsonProperty(value = "totalGarbageCount")
    private Long totalGarbageCount;

    @JsonProperty(value = "startedDate")
    private LocalDateTime startedDate;

    @JsonProperty(value = "endedDate")
    private LocalDateTime endedDate;

    public GarbageCollectorMetricResponseDTO(Long id, String metricType, Long totalExecutionTime, Long totalGarbageCount, LocalDateTime startedDate, LocalDateTime endedDate) {
        this.id = id;
        this.metricType = metricType;
        this.totalExecutionTime = totalExecutionTime;
        this.totalGarbageCount = totalGarbageCount;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }
}
