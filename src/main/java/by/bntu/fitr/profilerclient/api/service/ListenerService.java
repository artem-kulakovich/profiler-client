package by.bntu.fitr.profilerclient.api.service;

import by.bntu.fitr.profilerclient.api.dto.request.ListenerCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerSettingsCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerThreadCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerTypeCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerSettingsEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerTypeEntity;

import java.util.List;

public interface ListenerService {

    ListenerEntity createListener(final ListenerCreateRequestDTO listenerCreateRequestDTO);

    ListenerSettingsEntity createListenerSettings(final ListenerSettingsCreateRequestDTO listenerSettingsCreateRequestDTO);

    void bindSettingsToListener(final Long listenerId, final Long settingsId);

    void unbindSettingsFromListener(final Long listenerId, final Long settingsId);

    List<ListenerEntity> getAllListeners();

    List<ListenerSettingsEntity> getAllListenersSettings();

    void activateListener(final Long id);

    void deactivateListener(final Long id);

    void deleteListener(final Long id);

    void deleteSettings(final Long id);

    List<ListenerThreadEntity> getAllListenerThreads();

    ListenerThreadEntity createListenerThread(final ListenerThreadCreateRequestDTO listenerThreadCreateRequestDTO);

    void bindThreadToListener(final Long listenerId, final Long threadId);

    void unbindThreadFromListener(final Long listenerId, final Long threadId);

    ListenerTypeEntity getOrCreateListenerType(final ListenerTypeCreateRequestDTO listenerTypeCreateRequestDTO);

    List<ListenerTypeEntity> getAllListenerTypes();

    void updateListenerEntity(final ListenerEntity listenerEntity);

    List<ListenerEntity> getAllRunningListeners();
}
