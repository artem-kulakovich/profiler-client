package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.GarbageCollectorMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GarbageCollectorMetricRepository extends JpaRepository<GarbageCollectorMetricEntity, Long> {
}
