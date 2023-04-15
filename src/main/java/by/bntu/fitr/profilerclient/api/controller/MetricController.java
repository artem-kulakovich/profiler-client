package by.bntu.fitr.profilerclient.api.controller;

import by.bntu.fitr.profilerclient.api.dto.request.TimedMetricBetweenDatesRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.response.*;
import by.bntu.fitr.profilerclient.api.entity.*;
import by.bntu.fitr.profilerclient.api.mapper.MetricMapper;
import by.bntu.fitr.profilerclient.api.service.MetricService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/metrics")
public class MetricController {
    private final MetricService metricService;
    private final MetricMapper metricMapper;

    public MetricController(final MetricService metricService,
                            final MetricMapper metricMapper) {
        this.metricService = metricService;
        this.metricMapper = metricMapper;
    }

    @GetMapping(value = "/timed-metrics")
    public ResponseEntity<List<TimedMetricResponseDTO>> getAllTimedMetrics() {
        List<TimedMetricEntity> timedMetrics = metricService.getAllTimedMetric();
        return new ResponseEntity<>(metricMapper.mapToTimedMetricsResponseDTO(timedMetrics), HttpStatus.OK);
    }

    @PostMapping(value = "/timed-metrics/between-dates")
    public ResponseEntity<List<TimedMetricResponseDTO>> getTimedMetricsBetweenDates(@RequestBody TimedMetricBetweenDatesRequestDTO timedMetricBetweenDatesRequestDTO) {
        List<TimedMetricEntity> timedMetrics = metricService.getTimedMetricsBetweenCertainTime(timedMetricBetweenDatesRequestDTO);
        return new ResponseEntity<>(metricMapper.mapToTimedMetricsResponseDTO(timedMetrics), HttpStatus.OK);
    }

    @GetMapping(value = "/timed-metrics/execution-time/less")
    public ResponseEntity<List<TimedMetricResponseDTO>> getTimedMetricsWhereExecutionTimeLessThan(@RequestParam(value = "executionTime") Long executionTime) {
        List<TimedMetricEntity> timedMetrics = metricService.getTimedMetricsWhereExecutionTimeLessThanEqual(executionTime);
        return new ResponseEntity<>(metricMapper.mapToTimedMetricsResponseDTO(timedMetrics), HttpStatus.OK);
    }

    @GetMapping(value = "/timed-metrics/execution-time/greater")
    public ResponseEntity<List<TimedMetricResponseDTO>> getTimedMetricsWhereExecutionTimeGreaterThan(@RequestParam(value = "executionTime") Long executionTime) {
        List<TimedMetricEntity> timedMetrics = metricService.getTimedMetricsWhereExecutionTimeGreaterThanEqual(executionTime);
        return new ResponseEntity<>(metricMapper.mapToTimedMetricsResponseDTO(timedMetrics), HttpStatus.OK);
    }

    @GetMapping(value = "/timed-metrics/execution-time/between")
    public ResponseEntity<List<TimedMetricResponseDTO>> getTimedMetricsWhereExecutionTimeBetween(@RequestParam(value = "from") Long from,
                                                                                                 @RequestParam(value = "to") Long to) {
        List<TimedMetricEntity> timedMetrics = metricService.getTimedMetricsWhereExecutionTimeBetween(from, to);
        return new ResponseEntity<>(metricMapper.mapToTimedMetricsResponseDTO(timedMetrics), HttpStatus.OK);
    }

    @GetMapping(value = "/jvm-heap-metrics")
    public ResponseEntity<List<JVMHeapMetricResponseDTO>> getAllJVMHeapMetrics() {
        List<JVMHeapMetricEntity> jvmHeapMetrics = metricService.getAllJVMHeapMetrics();
        return new ResponseEntity<>(metricMapper.mapToJVMHeapMetricsResponseDTO(jvmHeapMetrics), HttpStatus.OK);
    }

    @GetMapping(value = "/disk-metrics")
    public ResponseEntity<List<DiskMetricResponseDTO>> getAllDisksMetrics() {
        List<DiskMetricEntity> diskMetrics = metricService.getAllDisksMetrics();
        return new ResponseEntity<>(metricMapper.mapToDiskMetricsResponseDTO(diskMetrics), HttpStatus.OK);
    }

    @GetMapping(value = "/garbage-collector-metrics")
    public ResponseEntity<List<GarbageCollectorMetricResponseDTO>> getAllGarbageCollectorMetrics() {
        List<GarbageCollectorMetricEntity> garbageCollectorMetrics = metricService.getAllGarbageCollectorMetrics();
        return new ResponseEntity<>(metricMapper.mapToGarbageCollectorMetricsResponseDTO(garbageCollectorMetrics), HttpStatus.OK);
    }
}
