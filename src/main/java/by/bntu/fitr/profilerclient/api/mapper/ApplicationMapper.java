package by.bntu.fitr.profilerclient.api.mapper;


import by.bntu.fitr.profilerclient.api.dto.request.*;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerResponseDTO;

import by.bntu.fitr.profilerclient.api.dto.response.ListenerSettingsResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerThreadResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerTypeResponseDTO;
import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerSettingsEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerTypeEntity;
import org.springframework.stereotype.Component;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ApplicationMapper {

    public ListenerResponseDTO mapToListenerResponseDTO(ListenerEntity listenerEntity) {
        if (listenerEntity == null) {
            return null;
        }
        return new ListenerResponseDTO(
                listenerEntity.getId(),
                listenerEntity.getName(),
                listenerEntity.getTopicName(),
                listenerEntity.getEnabled(),
                listenerEntity.getCreateAt(),
                mapToListenerSettingsResponseDTO(listenerEntity.getListenerSettings()),
                mapToListenerThreadsResponseDTO(listenerEntity.getListenerThreads()),
                mapToListenerTypeResponseDTO(listenerEntity.getListenerType())
        );
    }

    public ListenerEntity mapToListenerEntity(ListenerCreateRequestDTO listenerCreateRequestDTO) {
        return new ListenerEntity(
                listenerCreateRequestDTO.getName(),
                listenerCreateRequestDTO.getTopicName()
        );
    }

    public List<ListenerResponseDTO> mapToListenersResponseDTO(List<ListenerEntity> listeners) {
        if (listeners == null) {
            return null;
        }
        return listeners.stream().map(this::mapToListenerResponseDTO).collect(Collectors.toList());
    }

    public ListenerSettingsResponseDTO mapToListenerSettingsResponseDTO(ListenerSettingsEntity listenerSettingsEntity) {
        if (listenerSettingsEntity == null) {
            return null;
        }

        return new ListenerSettingsResponseDTO(listenerSettingsEntity.getId(),
                listenerSettingsEntity.getName(),
                listenerSettingsEntity.getBootstrapServer(),
                listenerSettingsEntity.getKeyDeserializer(),
                listenerSettingsEntity.getValueDeserializer(),
                listenerSettingsEntity.getSessionTimeout(),
                listenerSettingsEntity.getHeartBeatInterval(),
                listenerSettingsEntity.getGroupId(),
                listenerSettingsEntity.getCreateAt());
    }

    public ListenerSettingsEntity mapToListenerSettingsEntity(ListenerSettingsCreateRequestDTO listenerSettingsCreateRequestDTO) {
        if (listenerSettingsCreateRequestDTO == null) {
            return null;
        }

        return new ListenerSettingsEntity(
                listenerSettingsCreateRequestDTO.getName(),
                listenerSettingsCreateRequestDTO.getBootstrapServer(),
                listenerSettingsCreateRequestDTO.getKeyDeserializer(),
                listenerSettingsCreateRequestDTO.getValueDeserializer(),
                listenerSettingsCreateRequestDTO.getSessionTimeout(),
                listenerSettingsCreateRequestDTO.getHeartBeatInterval(),
                listenerSettingsCreateRequestDTO.getGroupId()
        );
    }

    public List<ListenerSettingsResponseDTO> mapToListenersSettingsResponseDTO(List<ListenerSettingsEntity> listenersSettingsEntity) {
        if (listenersSettingsEntity == null) {
            return null;
        }

        return listenersSettingsEntity.stream().map(this::mapToListenerSettingsResponseDTO).collect(Collectors.toList());
    }

    public ListenerThreadResponseDTO mapToListenerThreadResponseDTO(ListenerThreadEntity listenerThread) {
        if (listenerThread == null) {
            return null;
        }

        return new ListenerThreadResponseDTO(
                listenerThread.getId(),
                listenerThread.getName(),
                listenerThread.getEnabled(),
                listenerThread.getCreateAt()
        );
    }

    public List<ListenerThreadResponseDTO> mapToListenerThreadsResponseDTO(List<ListenerThreadEntity> listenerThreads) {
        if (listenerThreads == null) {
            return null;
        }

        return listenerThreads.stream().map(this::mapToListenerThreadResponseDTO).collect(Collectors.toList());
    }

    public ListenerThreadEntity mapToListenerThreadEntity(ListenerThreadCreateRequestDTO listenerThreadCreateRequestDTO) {
        return new ListenerThreadEntity(
                listenerThreadCreateRequestDTO.getName()
        );
    }

    public ListenerTypeEntity mapToListenerTypeEntity(ListenerTypeCreateRequestDTO listenerTypeCreateRequestDTO) {
        return new ListenerTypeEntity(
                listenerTypeCreateRequestDTO.getName()
        );
    }

    public ListenerTypeResponseDTO mapToListenerTypeResponseDTO(ListenerTypeEntity listenerType) {
        if (listenerType == null) {
            return null;
        }
        return new ListenerTypeResponseDTO(
                listenerType.getId(),
                listenerType.getName(),
                listenerType.getCreateAt()
        );
    }

    public List<ListenerTypeResponseDTO> mapToListenerTypesResponseDTO(List<ListenerTypeEntity> listenerTypes) {
        if (listenerTypes == null) {
            return null;
        }

        return listenerTypes.stream().map(this::mapToListenerTypeResponseDTO).collect(Collectors.toList());
    }
}
