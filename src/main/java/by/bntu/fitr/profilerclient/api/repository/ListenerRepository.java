package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ListenerRepository extends JpaRepository<ListenerEntity, Long> {

    @Query(value = "SELECT *FROM listener WHERE name = :name",
            nativeQuery = true)
    Optional<ListenerEntity> findByName(@Param(value = "name") String name);

    List<ListenerEntity> findAllByEnabled(Integer enabled);
}
