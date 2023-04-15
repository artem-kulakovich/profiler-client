package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListenerThreadRepository extends JpaRepository<ListenerThreadEntity, Long> {

    Optional<ListenerThreadEntity> findByName(String name);
}
