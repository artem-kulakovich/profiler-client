package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.JVMHeapMetricEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JVMHeapMetricRepository extends JpaRepository<JVMHeapMetricEntity, Long> {
}
