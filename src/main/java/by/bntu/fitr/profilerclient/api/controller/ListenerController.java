package by.bntu.fitr.profilerclient.api.controller;

import by.bntu.fitr.profilerclient.api.dto.request.ListenerCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerSettingsCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerThreadCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerTypeCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerSettingsResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerThreadResponseDTO;
import by.bntu.fitr.profilerclient.api.dto.response.ListenerTypeResponseDTO;
import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerSettingsEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerTypeEntity;
import by.bntu.fitr.profilerclient.api.mapper.ApplicationMapper;
import by.bntu.fitr.profilerclient.api.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/listeners")
public class ListenerController {
    private final ApplicationMapper applicationMapper;
    private final ListenerService listenerService;

    @Autowired
    public ListenerController(final ApplicationMapper applicationMapper,
                              final ListenerService listenerService) {
        this.applicationMapper = applicationMapper;
        this.listenerService = listenerService;
    }

    @PostMapping(value = "")
    public ResponseEntity<ListenerResponseDTO> createListener(@RequestBody final ListenerCreateRequestDTO listenerCreateRequestDTO) {
        ListenerEntity listener = listenerService.createListener(listenerCreateRequestDTO);
        return new ResponseEntity<>(applicationMapper.mapToListenerResponseDTO(listener), HttpStatus.CREATED);
    }

    @PostMapping(value = "/settings")
    public ResponseEntity<ListenerSettingsResponseDTO> createListenerSettings(@RequestBody final ListenerSettingsCreateRequestDTO listenerSettingsCreateRequestDTO) {
        ListenerSettingsEntity listenerSettings = listenerService.createListenerSettings(listenerSettingsCreateRequestDTO);
        return new ResponseEntity<>(applicationMapper.mapToListenerSettingsResponseDTO(listenerSettings), HttpStatus.OK);
    }

    @PostMapping(value = "/{listenerId}/settings/{settingsId}/bind")
    public ResponseEntity<Void> bindSettingsToListener(@PathVariable(value = "listenerId") Long listenerId,
                                                       @PathVariable(value = "settingsId") Long settingsId) {
        listenerService.bindSettingsToListener(listenerId, settingsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{listenerId}/settings/{settingsId}/unbind")
    public ResponseEntity<Void> unbindSettingsFromListener(@PathVariable(value = "listenerId") Long listenerId,
                                                           @PathVariable(value = "settingsId") Long settingsId) {
        listenerService.unbindSettingsFromListener(listenerId, settingsId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "")
    public ResponseEntity<List<ListenerResponseDTO>> getAllListeners() {
        List<ListenerEntity> listeners = listenerService.getAllListeners();
        return new ResponseEntity<>(applicationMapper.mapToListenersResponseDTO(listeners), HttpStatus.OK);
    }

    @GetMapping(value = "/settings")
    public ResponseEntity<List<ListenerSettingsResponseDTO>> getAllListenerSettings() {
        List<ListenerSettingsEntity> listenersSettings = listenerService.getAllListenersSettings();
        return new ResponseEntity<>(applicationMapper.mapToListenersSettingsResponseDTO(listenersSettings), HttpStatus.OK);
    }

    @PostMapping(value = "/activate/{id}")
    public ResponseEntity<Void> activateListener(@PathVariable("id") Long id) {
        listenerService.activateListener(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/deactivate/{id}")
    public ResponseEntity<Void> deactivateListener(@PathVariable("id") Long id) {
        listenerService.deactivateListener(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteListener(@PathVariable("id") Long id) {
        listenerService.deleteListener(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/settings/{id}")
    public ResponseEntity<Void> deleteSettings(@PathVariable(value = "id") Long id) {
        listenerService.deleteSettings(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/threads")
    public ResponseEntity<List<ListenerThreadResponseDTO>> getAllThreads() {
        List<ListenerThreadEntity> listenerThreads = listenerService.getAllListenerThreads();
        return new ResponseEntity<>(applicationMapper.mapToListenerThreadsResponseDTO(listenerThreads), HttpStatus.OK);
    }

    @PostMapping(value = "/threads")
    public ResponseEntity<ListenerThreadResponseDTO> createListenerThread(@RequestBody ListenerThreadCreateRequestDTO listenerThreadCreateRequestDTO) {
        ListenerThreadEntity listenerThread = listenerService.createListenerThread(listenerThreadCreateRequestDTO);
        return new ResponseEntity<>(applicationMapper.mapToListenerThreadResponseDTO(listenerThread), HttpStatus.OK);
    }


    @PostMapping(value = "/{listenerId}/thread/{threadId}/bind")
    public ResponseEntity<Void> bindListenerToThread(@PathVariable(value = "listenerId") Long listenerId,
                                                     @PathVariable(value = "threadId") Long threadId) {
        listenerService.bindThreadToListener(listenerId, threadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/{listenerId}/thread/{threadId}/unbind")
    public ResponseEntity<Void> unbindListenerFromThread(@PathVariable(value = "listenerId") Long listenerId,
                                                         @PathVariable(value = "threadId") Long threadId) {

        listenerService.unbindThreadFromListener(listenerId, threadId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/types")
    public ResponseEntity<ListenerTypeResponseDTO> createListenerType(@RequestBody ListenerTypeCreateRequestDTO listenerTypeCreateRequestDTO) {
        ListenerTypeEntity listenerTypeEntity = listenerService.getOrCreateListenerType(listenerTypeCreateRequestDTO);
        return new ResponseEntity<>(applicationMapper.mapToListenerTypeResponseDTO(listenerTypeEntity), HttpStatus.OK);
    }

    @GetMapping(value = "/types")
    public ResponseEntity<List<ListenerTypeResponseDTO>> getAllListenerTypes() {
        List<ListenerTypeEntity> listenerTypes = listenerService.getAllListenerTypes();
        return new ResponseEntity<>(applicationMapper.mapToListenerTypesResponseDTO(listenerTypes), HttpStatus.OK);
    }


}
