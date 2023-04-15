package by.bntu.fitr.profilerclient.api.repository;


import by.bntu.fitr.profilerclient.api.entity.TimedMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TimedMetricRepository extends JpaRepository<TimedMetricEntity, Long> {

    List<TimedMetricEntity> findByStartedDateGreaterThanEqualAndEndedDateLessThanEqual(LocalDateTime from, LocalDateTime to);

    List<TimedMetricEntity> findByExecutionTimeGreaterThanEqual(Long executionTime);

    List<TimedMetricEntity> findByExecutionTimeLessThanEqual(Long executionTime);

    List<TimedMetricEntity> findByExecutionTimeBetween(Long from, Long to);
}
