package by.bntu.fitr.profilerclient.api.service;

import by.bntu.fitr.profilerclient.api.dto.request.TimedMetricBetweenDatesRequestDTO;
import by.bntu.fitr.profilerclient.api.entity.*;

import java.time.LocalDateTime;
import java.util.List;

public interface MetricService {

    List<TimedMetricEntity> getAllTimedMetric();

    void saveTimedMetrics(final String timedMetricStr);

    List<TimedMetricEntity> getTimedMetricsBetweenCertainTime(TimedMetricBetweenDatesRequestDTO timedMetricBetweenDatesRequestDTO);

    List<TimedMetricEntity> getTimedMetricsWhereExecutionTimeGreaterThanEqual(Long ms);

    List<TimedMetricEntity> getTimedMetricsWhereExecutionTimeLessThanEqual(Long ms);

    List<TimedMetricEntity> getTimedMetricsWhereExecutionTimeBetween(Long from, Long to);

    void saveJVMMetric(final String jvmMetricStr);

    void saveJVMHeapMetric(final JVMHeapMetricEntity jvmHeapMetricEntity);

    void saveGarbageCollectorMetric(final GarbageCollectorMetricEntity garbageCollectorMetric);

    void saveDiskMetric(final DiskMetricEntity diskMetric);

    List<GarbageCollectorMetricEntity> getAllGarbageCollectorMetrics();

    List<JVMHeapMetricEntity> getAllJVMHeapMetrics();

    List<DiskMetricEntity> getAllDisksMetrics();
}
