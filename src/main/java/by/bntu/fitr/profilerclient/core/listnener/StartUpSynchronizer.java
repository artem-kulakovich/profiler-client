package by.bntu.fitr.profilerclient.core.listnener;

import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import by.bntu.fitr.profilerclient.api.repository.ListenerRepository;
import by.bntu.fitr.profilerclient.api.service.ListenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StartUpSynchronizer {
    private final ListenerService listenerService;

    @Autowired
    public StartUpSynchronizer(ListenerService listenerService) {
        this.listenerService = listenerService;
    }


    @EventListener(ApplicationReadyEvent.class)
    public void loadOnStartUp() {
        List<ListenerEntity> listeners = listenerService.getAllListeners();

        for (ListenerEntity listener : listeners) {
            if (listener.getEnabled() == 1) {
                listener.setEnabled(0);

                for (ListenerThreadEntity listenerThread : listener.getListenerThreads()) {
                    if (listenerThread.getEnabled() == 1) {
                        listenerThread.setEnabled(0);
                    }
                }
                listenerService.updateListenerEntity(listener);
            }
        }
    }
}
