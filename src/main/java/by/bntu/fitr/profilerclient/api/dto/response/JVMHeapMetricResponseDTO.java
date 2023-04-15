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
public class JVMHeapMetricResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "metricType")
    private String metricType;

    @JsonProperty(value = "availableHeap")
    private Long availableHeap;

    @JsonProperty(value = "usedHeap")
    private Long usedHeap;

    @JsonProperty(value = "maxHeap")
    private Long maxHeap;

    @JsonProperty(value = "freeMemory")
    private Long freeMemory;
    @JsonProperty(value = "startedDate")
    private LocalDateTime startedDate;

    @JsonProperty(value = "endedDate")
    private LocalDateTime endedDate;

    public JVMHeapMetricResponseDTO(Long id, String metricType, Long availableHeap, Long usedHeap, Long maxHeap, Long freeMemory, LocalDateTime startedDate, LocalDateTime endedDate) {
        this.id = id;
        this.metricType = metricType;
        this.availableHeap = availableHeap;
        this.usedHeap = usedHeap;
        this.maxHeap = maxHeap;
        this.freeMemory = freeMemory;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }
}
