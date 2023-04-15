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
public class DiskMetricResponseDTO {
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "metricType")
    private String metricType;

    @JsonProperty(value = "diskName")
    private String diskName;

    @JsonProperty(value = "diskPath")
    private String diskPath;

    @JsonProperty(value = "totalSpace")
    private Long totalSpace;

    @JsonProperty(value = "usedSpace")
    private Long usedSpace;

    @JsonProperty(value = "freeSpace")
    private Long freeSpace;

    @JsonProperty(value = "startedDate")
    private LocalDateTime startedDate;

    @JsonProperty(value = "endedDate")
    private LocalDateTime endedDate;

    public DiskMetricResponseDTO(Long id, String metricType, String diskName, String diskPath, Long totalSpace, Long usedSpace, Long freeSpace, LocalDateTime startedDate, LocalDateTime endedDate) {
        this.id = id;
        this.metricType = metricType;
        this.diskName = diskName;
        this.diskPath = diskPath;
        this.totalSpace = totalSpace;
        this.usedSpace = usedSpace;
        this.freeSpace = freeSpace;
        this.startedDate = startedDate;
        this.endedDate = endedDate;
    }
}
