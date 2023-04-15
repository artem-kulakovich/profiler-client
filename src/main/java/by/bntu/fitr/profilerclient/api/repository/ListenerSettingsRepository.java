package by.bntu.fitr.profilerclient.api.repository;

import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerSettingsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ListenerSettingsRepository extends JpaRepository<ListenerSettingsEntity, Long> {

    @Query(value = "SELECT *FROM listener_settings WHERE name = :name",
            nativeQuery = true)
    Optional<ListenerSettingsEntity> findByName(@Param(value = "name") String name);

    @Query(value = "SELECT *FROM listener WHERE listener_settings_id = :id",
            nativeQuery = true)
    Optional<Long> findBindedListenerId(@Param(value = "id") Long settingsId);
}
