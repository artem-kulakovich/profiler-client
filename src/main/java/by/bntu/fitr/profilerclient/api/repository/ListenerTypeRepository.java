package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.ListenerTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListenerTypeRepository extends JpaRepository<ListenerTypeEntity, Long> {

    Optional<ListenerTypeEntity> findByName(String name);
}
