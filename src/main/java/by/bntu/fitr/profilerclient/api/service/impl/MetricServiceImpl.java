package by.bntu.fitr.profilerclient.api.service.impl;

import by.bntu.fitr.profilerclient.api.dto.request.TimedMetricBetweenDatesRequestDTO;
import by.bntu.fitr.profilerclient.api.entity.*;
import by.bntu.fitr.profilerclient.api.repository.*;
import by.bntu.fitr.profilerclient.api.service.MetricService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MetricServiceImpl implements MetricService {
    private final TimedMetricRepository timedMetricRepository;
    private final JVMHeapMetricRepository jvmHeapMetricRepository;
    private final GarbageCollectorMetricRepository garbageCollectorMetricRepository;
    private final DiskMetricRepository diskMetricRepository;

    public MetricServiceImpl(final TimedMetricRepository timedMetricRepository,
                             final JVMHeapMetricRepository jvmHeapMetricRepository,
                             final GarbageCollectorMetricRepository garbageCollectorMetricRepository,
                             final DiskMetricRepository diskMetricRepository) {
        this.timedMetricRepository = timedMetricRepository;
        this.jvmHeapMetricRepository = jvmHeapMetricRepository;
        this.garbageCollectorMetricRepository = garbageCollectorMetricRepository;
        this.diskMetricRepository = diskMetricRepository;
    }

    @Override
    public List<TimedMetricEntity> getAllTimedMetric() {
        return timedMetricRepository.findAll();
    }

    @Override
    public void saveTimedMetrics(String timedMetricStr) {
        String[] values = timedMetricStr.split(",");
        TimedMetricEntity timedMetric = new TimedMetricEntity();

        timedMetric.setMetricType(values[0]);
        timedMetric.setClassName(values[1]);
        timedMetric.setMethodName(values[2]);

        timedMetric.setExecutionTime(Long.valueOf(values[3]));
        timedMetric.setExecutionTimeStart(Long.valueOf(values[4]));
        timedMetric.setExecutionTimeEnd(Long.valueOf(values[5]));

        timedMetric.setStartedDate(LocalDateTime.parse(values[6]));
        timedMetric.setEndedDate(LocalDateTime.parse(values[7]));

        timedMetricRepository.save(timedMetric);
    }

    @Override
    public List<TimedMetricEntity> getTimedMetricsBetweenCertainTime(final TimedMetricBetweenDatesRequestDTO timedMetricBetweenDatesRequestDTO) {
        return timedMetricRepository.findByMethodNameAndClassNameAndStartedDateGreaterThanEqualAndEndedDateLessThanEqual(timedMetricBetweenDatesRequestDTO.getMethodName(),
                timedMetricBetweenDatesRequestDTO.getClassName(),
                timedMetricBetweenDatesRequestDTO.getFrom(),
                timedMetricBetweenDatesRequestDTO.getTo());
    }

    @Override
    public List<TimedMetricEntity> getTimedMetricsWhereExecutionTimeGreaterThanEqual(Long ms) {
        return timedMetricRepository.findByExecutionTimeGreaterThanEqual(ms);
    }

    @Override
    public List<TimedMetricEntity> getTimedMetricsWhereExecutionTimeLessThanEqual(Long ms) {
        return timedMetricRepository.findByExecutionTimeLessThanEqual(ms);
    }

    @Override
    public List<TimedMetricEntity> getTimedMetricsWhereExecutionTimeBetween(Long from, Long to) {
        return timedMetricRepository.findByExecutionTimeBetween(from, to);
    }

    @Override
    public void saveJVMMetric(final String jvmMetricStr) {
        String[] subMetrics = jvmMetricStr.substring(1, jvmMetricStr.length() - 1).split("\\],\\[");


        String[] jvmHeapMetricValues = subMetrics[0].split(",");

        JVMHeapMetricEntity jvmHeapMetricEntity = new JVMHeapMetricEntity();

        jvmHeapMetricEntity.setMetricType(jvmHeapMetricValues[0]);
        jvmHeapMetricEntity.setUsedHeap(Long.valueOf(jvmHeapMetricValues[1]));
        jvmHeapMetricEntity.setFreeMemory(Long.valueOf(jvmHeapMetricValues[2]));
        jvmHeapMetricEntity.setAvailableHeap(Long.valueOf(jvmHeapMetricValues[3]));
        jvmHeapMetricEntity.setMaxHeap(Long.valueOf(jvmHeapMetricValues[4]));
        jvmHeapMetricEntity.setStartedDate(LocalDateTime.parse(jvmHeapMetricValues[5]));
        jvmHeapMetricEntity.setEndedDate(LocalDateTime.parse(jvmHeapMetricValues[6]));


        String[] garbageCollectorMetricValues = subMetrics[1].split(",");

        GarbageCollectorMetricEntity garbageCollectorMetricEntity = new GarbageCollectorMetricEntity();

        garbageCollectorMetricEntity.setMetricType(garbageCollectorMetricValues[0]);
        garbageCollectorMetricEntity.setTotalGarbageCount(Long.valueOf(garbageCollectorMetricValues[1]));
        garbageCollectorMetricEntity.setTotalExecutionTime(Long.valueOf(garbageCollectorMetricValues[2]));
        garbageCollectorMetricEntity.setStartedDate(LocalDateTime.parse(garbageCollectorMetricValues[3]));
        garbageCollectorMetricEntity.setEndedDate(LocalDateTime.parse(garbageCollectorMetricValues[4]));

        String diskMetric = subMetrics[2].substring(2, subMetrics[2].length() - 2);
        String[] disksMetric = diskMetric.split("\\},\\{");

        for (String currentDiskMetric : disksMetric) {
            String[] diskMetricValues = currentDiskMetric.split(",");
            DiskMetricEntity diskMetricEntity = new DiskMetricEntity();
            diskMetricEntity.setMetricType(diskMetricValues[0]);
            diskMetricEntity.setDiskName(diskMetricValues[1]);
            diskMetricEntity.setDiskPath(diskMetricValues[2]);
            diskMetricEntity.setUsedSpace(Long.valueOf(diskMetricValues[3]));
            diskMetricEntity.setTotalSpace(Long.valueOf(diskMetricValues[4]));
            diskMetricEntity.setFreeSpace(Long.valueOf(diskMetricValues[5]));
            diskMetricEntity.setStartedDate(LocalDateTime.parse(diskMetricValues[6]));
            diskMetricEntity.setEndedDate(LocalDateTime.parse(diskMetricValues[7]));
            saveDiskMetric(diskMetricEntity);
        }

        saveJVMHeapMetric(jvmHeapMetricEntity);
        saveGarbageCollectorMetric(garbageCollectorMetricEntity);

    }

    @Override
    public void saveJVMHeapMetric(final JVMHeapMetricEntity jvmHeapMetricEntity) {
        jvmHeapMetricRepository.save(jvmHeapMetricEntity);
    }

    @Override
    public void saveGarbageCollectorMetric(final GarbageCollectorMetricEntity garbageCollectorMetric) {
        garbageCollectorMetricRepository.save(garbageCollectorMetric);
    }

    @Override
    public void saveDiskMetric(final DiskMetricEntity diskMetric) {
        diskMetricRepository.save(diskMetric);
    }

    @Override
    public List<GarbageCollectorMetricEntity> getAllGarbageCollectorMetrics() {
        return garbageCollectorMetricRepository.findAll();
    }

    @Override
    public List<JVMHeapMetricEntity> getAllJVMHeapMetrics() {
        return jvmHeapMetricRepository.findAll();
    }

    @Override
    public List<DiskMetricEntity> getAllDisksMetrics() {
        return diskMetricRepository.findAll();
    }
}

