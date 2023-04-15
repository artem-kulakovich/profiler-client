package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.DiskMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiskMetricRepository extends JpaRepository<DiskMetricEntity, Long> {
}
