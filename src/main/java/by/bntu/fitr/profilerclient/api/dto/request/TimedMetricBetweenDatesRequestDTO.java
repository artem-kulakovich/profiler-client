package by.bntu.fitr.profilerclient.api.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class TimedMetricBetweenDatesRequestDTO {
    @JsonProperty(value = "from")
    private LocalDateTime from;

    @JsonProperty(value = "to")
    private LocalDateTime to;

    @JsonProperty(value = "methodName")
    private String methodName;

    @JsonProperty(value = "className")
    private String className;
}
