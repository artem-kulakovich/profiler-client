package by.bntu.fitr.profilerclient.api.mapper;

import by.bntu.fitr.profilerclient.api.dto.response.*;
import by.bntu.fitr.profilerclient.api.entity.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MetricMapper {

    public TimedMetricResponseDTO mapToTimedMetricResponseDTO(TimedMetricEntity timedMetric) {
        return new TimedMetricResponseDTO(
                timedMetric.getId(),
                timedMetric.getMetricType(),
                timedMetric.getExecutionTime(),
                timedMetric.getExecutionTimeStart(),
                timedMetric.getExecutionTimeEnd(),
                timedMetric.getEndedDate(),
                timedMetric.getStartedDate(),
                timedMetric.getMethodName(),
                timedMetric.getClassName()
        );
    }

    public List<TimedMetricResponseDTO> mapToTimedMetricsResponseDTO(List<TimedMetricEntity> timedMetrics) {
        return timedMetrics.stream().map(this::mapToTimedMetricResponseDTO).collect(Collectors.toList());
    }

    public JVMHeapMetricResponseDTO mapToJVMHeapMetricResponseDTO(JVMHeapMetricEntity jvmHeapMetricEntity) {
        return new JVMHeapMetricResponseDTO(
                jvmHeapMetricEntity.getId(),
                jvmHeapMetricEntity.getMetricType(),
                jvmHeapMetricEntity.getAvailableHeap(),
                jvmHeapMetricEntity.getUsedHeap(),
                jvmHeapMetricEntity.getMaxHeap(),
                jvmHeapMetricEntity.getFreeMemory(),
                jvmHeapMetricEntity.getStartedDate(),
                jvmHeapMetricEntity.getEndedDate()
        );
    }

    public List<JVMHeapMetricResponseDTO> mapToJVMHeapMetricsResponseDTO(List<JVMHeapMetricEntity> jvmHeapMetrics) {
        return jvmHeapMetrics.stream().map(this::mapToJVMHeapMetricResponseDTO).collect(Collectors.toList());
    }

    public DiskMetricResponseDTO mapToDiskMetricResponseDTO(DiskMetricEntity diskMetric) {
        return new DiskMetricResponseDTO(
                diskMetric.getId(),
                diskMetric.getMetricType(),
                diskMetric.getDiskName(),
                diskMetric.getDiskPath(),
                diskMetric.getTotalSpace(),
                diskMetric.getUsedSpace(),
                diskMetric.getFreeSpace(),
                diskMetric.getStartedDate(),
                diskMetric.getEndedDate()
        );
    }

    public List<DiskMetricResponseDTO> mapToDiskMetricsResponseDTO(List<DiskMetricEntity> diskMetrics) {
        return diskMetrics.stream().map(this::mapToDiskMetricResponseDTO).collect(Collectors.toList());
    }

    public GarbageCollectorMetricResponseDTO mapToGarbageCollectorMetricResponseDTO(GarbageCollectorMetricEntity garbageCollectorMetric) {
        return new GarbageCollectorMetricResponseDTO(
                garbageCollectorMetric.getId(),
                garbageCollectorMetric.getMetricType(),
                garbageCollectorMetric.getTotalExecutionTime(),
                garbageCollectorMetric.getTotalGarbageCount(),
                garbageCollectorMetric.getStartedDate(),
                garbageCollectorMetric.getEndedDate()
        );
    }

    public List<GarbageCollectorMetricResponseDTO> mapToGarbageCollectorMetricsResponseDTO(List<GarbageCollectorMetricEntity> garbageCollectorMetrics) {
        return garbageCollectorMetrics.stream().map(this::mapToGarbageCollectorMetricResponseDTO).collect(Collectors.toList());
    }
}
