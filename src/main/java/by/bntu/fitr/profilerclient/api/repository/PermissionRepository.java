package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.PermissionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermissionRepository extends JpaRepository<PermissionEntity, Long> {

    Optional<PermissionEntity> findByName(String name);
}
