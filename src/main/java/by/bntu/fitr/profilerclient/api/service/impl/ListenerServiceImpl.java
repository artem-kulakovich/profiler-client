package by.bntu.fitr.profilerclient.api.service.impl;

import by.bntu.fitr.profilerclient.api.dto.request.ListenerCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerSettingsCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerThreadCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.dto.request.ListenerTypeCreateRequestDTO;
import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerSettingsEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerTypeEntity;
import by.bntu.fitr.profilerclient.api.exception.AlreadyExistsException;
import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.exception.NotFoundException;
import by.bntu.fitr.profilerclient.api.mapper.ApplicationMapper;
import by.bntu.fitr.profilerclient.api.repository.ListenerRepository;
import by.bntu.fitr.profilerclient.api.repository.ListenerSettingsRepository;
import by.bntu.fitr.profilerclient.api.repository.ListenerThreadRepository;
import by.bntu.fitr.profilerclient.api.repository.ListenerTypeRepository;
import by.bntu.fitr.profilerclient.api.service.ListenerService;
import by.bntu.fitr.profilerclient.core.constant.CommonConstant;
import by.bntu.fitr.profilerclient.core.hanndler.BeanDefinitionHandler;
import by.bntu.fitr.profilerclient.core.kafka.KafkaJVMMetricConsumer;
import by.bntu.fitr.profilerclient.core.kafka.KafkaShutdownHandler;
import by.bntu.fitr.profilerclient.core.kafka.KafkaTimedMetricConsumer;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ListenerServiceImpl implements ListenerService {
    private final ListenerRepository listenerRepository;
    private final ListenerSettingsRepository listenerSettingsRepository;
    private final ApplicationMapper applicationMapper;
    private final BeanDefinitionHandler beanDefinitionHandler;
    private final ListenerThreadRepository listenerThreadRepository;
    private final ListenerTypeRepository listenerTypeRepository;
    private final KafkaShutdownHandler kafkaShutdownHandler;

    @Autowired
    public ListenerServiceImpl(final ListenerRepository listenerRepository,
                               final ListenerSettingsRepository listenerSettingsRepository,
                               final ApplicationMapper applicationMapper,
                               final BeanDefinitionHandler beanDefinitionHandler,
                               final ListenerThreadRepository listenerThreadRepository,
                               final ListenerTypeRepository listenerTypeRepository,
                               final KafkaShutdownHandler kafkaShutdownHandler) {
        this.listenerRepository = listenerRepository;
        this.listenerSettingsRepository = listenerSettingsRepository;
        this.applicationMapper = applicationMapper;
        this.beanDefinitionHandler = beanDefinitionHandler;
        this.listenerThreadRepository = listenerThreadRepository;
        this.listenerTypeRepository = listenerTypeRepository;
        this.kafkaShutdownHandler = kafkaShutdownHandler;
    }

    @Transactional
    @Override
    public ListenerEntity createListener(final ListenerCreateRequestDTO listenerCreateRequestDTO) {
        if (listenerRepository.findByName(listenerCreateRequestDTO.getName()).isPresent()) {
            throw new AlreadyExistsException("listener with name: " + listenerCreateRequestDTO.getName() + " already exists");
        }

        ListenerTypeEntity listenerTypeEntity = getOrCreateListenerType(new ListenerTypeCreateRequestDTO(listenerCreateRequestDTO.getType()));

        ListenerEntity listenerEntity = applicationMapper.mapToListenerEntity(listenerCreateRequestDTO);
        listenerEntity.setListenerType(listenerTypeEntity);
        listenerEntity.setEnabled(0);
        listenerEntity.setCreateAt(LocalDateTime.now());
        return listenerRepository.save(listenerEntity);
    }

    @Override
    public ListenerSettingsEntity createListenerSettings(final ListenerSettingsCreateRequestDTO listenerSettingsCreateRequestDTO) {
        if (listenerSettingsRepository.findByName(listenerSettingsCreateRequestDTO.getName()).isPresent()) {
            throw new AlreadyExistsException("listener settings with name: " + listenerSettingsCreateRequestDTO.getName() + " already exists");
        }

        ListenerSettingsEntity listenerSettingsEntity = applicationMapper.mapToListenerSettingsEntity(listenerSettingsCreateRequestDTO);
        listenerSettingsEntity.setCreateAt(LocalDateTime.now());
        return listenerSettingsRepository.save(listenerSettingsEntity);
    }

    @Override
    public void bindSettingsToListener(final Long listenerId, final Long settingsId) {
        ListenerSettingsEntity listenerSettings = getListenerSettingsByIdOrThrowException(settingsId);
        ListenerEntity listener = getListenerByIdOrThrowException(listenerId);

        listener.setListenerSettings(listenerSettings);
        listenerRepository.save(listener);
    }

    @Override
    public void unbindSettingsFromListener(final Long listenerId, final Long settingsId) {
        ListenerEntity listener = getListenerByIdOrThrowException(listenerId);

        if (listener.getListenerSettings() == null) {
            throw new CommonException("Listener settings already unbinded");
        }

        if (listener.getEnabled() == 1) {
            throw new CommonException("Deactivate listener before unbinding settings");
        }

        if (!listener.getListenerSettings().getId().equals(settingsId)) {
            throw new CommonException("Couldnt unbind settings because id different");
        }
        listener.setListenerSettings(null);
        listenerRepository.save(listener);
    }

    @Override
    public List<ListenerEntity> getAllListeners() {
        return listenerRepository.findAll();
    }

    @Override
    public List<ListenerSettingsEntity> getAllListenersSettings() {
        return listenerSettingsRepository.findAll();
    }

    @Transactional
    @Override
    public void activateListener(final Long id) {
        ListenerEntity listenerEntity = getListenerByIdOrThrowException(id);

        if (listenerEntity.getEnabled() == 1) {
            throw new CommonException("Listener already activated with id: " + id);
        }

        if (listenerEntity.getListenerSettings() == null) {
            throw new CommonException("Please specify listener settings");
        }

        if (listenerEntity.getListenerThreads() == null || listenerEntity.getListenerThreads().isEmpty()) {
            throw new CommonException("Please specify listener thread");
        }


        listenerEntity.setEnabled(1);

        List<String> threadsName = new ArrayList<>();

        for (ListenerThreadEntity listenerThread : listenerEntity.getListenerThreads()) {
            listenerThread.setEnabled(1);
            threadsName.add(listenerThread.getName());
        }

        listenerRepository.save(listenerEntity);

        beanDefinitionHandler.registerTheSameTypeOfBeans(threadsName,
                getConsumerClassAccordingMetricType(listenerEntity.getListenerType().getName()),
                CommonConstant.SINGLETON_SCOPE);

        for (String threadName : threadsName) {
            if (listenerEntity.getListenerType().getName().equals(CommonConstant.TIMED_METRIC_TYPE_NAME)) {
                KafkaTimedMetricConsumer kafkaTimedMetricConsumer = beanDefinitionHandler.getBeanByName(threadName, KafkaTimedMetricConsumer.class);
                kafkaTimedMetricConsumer.setListenerEntity(listenerEntity);
                kafkaTimedMetricConsumer.setUniqueThreadName(threadName);
                kafkaTimedMetricConsumer.start();
            } else if (listenerEntity.getListenerType().getName().equals(CommonConstant.JVM_HEAP_METRIC_TYPE)) {
                KafkaJVMMetricConsumer kafkaJVMMetricConsumer = beanDefinitionHandler.getBeanByName(threadName, KafkaJVMMetricConsumer.class);
                kafkaJVMMetricConsumer.setListenerEntity(listenerEntity);
                kafkaJVMMetricConsumer.setUniqueThreadName(threadName);
                kafkaJVMMetricConsumer.start();
            }
        }
    }

    @Transactional
    @Override
    public void deactivateListener(final Long id) {
        ListenerEntity listenerEntity = getListenerByIdOrThrowException(id);

        if (listenerEntity.getEnabled() == 0) {
            throw new CommonException("Listener already deactivated with id: " + id);
        }

        listenerEntity.setEnabled(0);

        for (ListenerThreadEntity listenerThread : listenerEntity.getListenerThreads()) {
            listenerThread.setEnabled(0);
        }

        listenerRepository.save(listenerEntity);

        stopListenerTreads(listenerEntity.getListenerThreads());
        // TODO: 12.04.2023   stop kafka logic
    }

    @Transactional
    @Override
    public void deleteListener(final Long id) {
        ListenerEntity listenerEntity = getListenerByIdOrThrowException(id);

        if (listenerEntity.getEnabled() == 1) {
            throw new CommonException("Deactivate listener before deleting with id: " + id);
        }

        //stopListenerTreads(listenerEntity.getListenerThreads());
        listenerRepository.delete(listenerEntity);
    }

    @Override
    public void deleteSettings(final Long id) {
        ListenerSettingsEntity listenerSettings = getListenerSettingsByIdOrThrowException(id);

        if (listenerSettingsRepository.findBindedListenerId(id).isPresent()) {
            throw new CommonException("this property bind to listener: " + id);
        }

        listenerSettingsRepository.delete(listenerSettings);
    }

    @Override
    public List<ListenerThreadEntity> getAllListenerThreads() {
        return listenerThreadRepository.findAll();
    }

    @Override
    public ListenerThreadEntity createListenerThread(final ListenerThreadCreateRequestDTO listenerThreadCreateRequestDTO) {
        if (listenerThreadRepository.findByName(listenerThreadCreateRequestDTO.getName()).isPresent()) {
            throw new AlreadyExistsException("Listener with name: " + listenerThreadCreateRequestDTO.getName() + " already exists");
        }

        ListenerThreadEntity listenerThread = applicationMapper.mapToListenerThreadEntity(listenerThreadCreateRequestDTO);
        listenerThread.setCreateAt(LocalDateTime.now());
        listenerThread.setEnabled(0);
        return listenerThreadRepository.save(listenerThread);
    }

    @Override
    public void bindThreadToListener(final Long listenerId, final Long threadId) {
        ListenerEntity listenerEntity = getListenerByIdOrThrowException(listenerId);
        ListenerThreadEntity listenerThreadEntity = getListenerThreadByIdOrThrowException(threadId);

        if (listenerEntity.getListenerThreads() != null && listenerEntity.getListenerThreads().size() > 4) {
            throw new CommonException("Listener thread should be less then 6");
        }

        List<Long> threadIds = listenerEntity.getListenerThreads().stream().map(ListenerThreadEntity::getId).toList();

        for (Long currentThreadId : threadIds) {
            if (currentThreadId.equals(threadId)) {
                throw new CommonException("Thread already binded");
            }
        }

        listenerThreadEntity.setListenerEntity(listenerEntity);
        listenerEntity.addListenerThread(listenerThreadEntity);
        listenerRepository.save(listenerEntity);
    }

    @Override
    public void unbindThreadFromListener(final Long listenerId, final Long threadId) {
        ListenerThreadEntity listenerThread = getListenerThreadByIdOrThrowException(threadId);
        ListenerEntity listener = getListenerByIdOrThrowException(listenerId);

        if (listener.getListenerThreads() == null || listener.getListenerThreads().isEmpty()) {
            throw new CommonException("Listener doesnt have threads");
        }

        List<Long> threadIds = listener.getListenerThreads().stream().map(ListenerThreadEntity::getId).toList();

        for (Long currentThreadId : threadIds) {
            if (currentThreadId.equals(threadId)) {
                listenerThread.setListenerEntity(null);
                listener.getListenerThreads().remove(listenerThread);
                listenerRepository.save(listener);
                return;
            }
        }

        throw new CommonException("Couldnt unbind thead because id different");
    }

    @Override
    public ListenerTypeEntity getOrCreateListenerType(final ListenerTypeCreateRequestDTO listenerTypeCreateRequestDTO) {
        Optional<ListenerTypeEntity> optionalListenerTypeEntity = listenerTypeRepository.findByName(listenerTypeCreateRequestDTO.getName());
        if (optionalListenerTypeEntity.isPresent()) {
            return optionalListenerTypeEntity.get();
        }

        ListenerTypeEntity listenerTypeEntity = applicationMapper.mapToListenerTypeEntity(listenerTypeCreateRequestDTO);
        listenerTypeEntity.setCreateAt(LocalDateTime.now());
        return listenerTypeRepository.save(listenerTypeEntity);
    }

    @Override
    public List<ListenerTypeEntity> getAllListenerTypes() {
        return listenerTypeRepository.findAll();
    }

    @Override
    public void updateListenerEntity(ListenerEntity listenerEntity) {
        listenerRepository.save(listenerEntity);
    }

    @Override
    public List<ListenerEntity> getAllRunningListeners() {
        return listenerRepository.findAllByEnabled(1);
    }

    private ListenerEntity getListenerByIdOrThrowException(final Long id) {
        return listenerRepository.findById(id).orElseThrow(() -> new NotFoundException("Listener not found with id: " + id));
    }

    private ListenerSettingsEntity getListenerSettingsByIdOrThrowException(final Long id) {
        return listenerSettingsRepository.findById(id).orElseThrow(() -> new NotFoundException("Listener settings not found with id: " + id));
    }

    private ListenerThreadEntity getListenerThreadByIdOrThrowException(final Long id) {
        return listenerThreadRepository.findById(id).orElseThrow(() -> new NotFoundException("Listener thread not found with id: " + id));
    }

    private void stopListenerTreads(List<ListenerThreadEntity> listenerThreads) {
        List<String> threadsName = listenerThreads
                .stream()
                .map(ListenerThreadEntity::getName)
                .toList();

        kafkaShutdownHandler.killKafkaConsumerThread(threadsName);
        beanDefinitionHandler.removeBeans(threadsName);
    }

    private Class getConsumerClassAccordingMetricType(String metricType) {
        if (metricType.equals(CommonConstant.TIMED_METRIC_TYPE_NAME)) {
            return KafkaTimedMetricConsumer.class;
        } else if (metricType.equals(CommonConstant.JVM_HEAP_METRIC_TYPE)) {
            return KafkaJVMMetricConsumer.class;
        }
        return null;
    }

}
