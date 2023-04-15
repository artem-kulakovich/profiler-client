package by.bntu.fitr.profilerclient.core.job;

import by.bntu.fitr.profilerclient.api.entity.ListenerEntity;
import by.bntu.fitr.profilerclient.api.entity.ListenerThreadEntity;
import by.bntu.fitr.profilerclient.api.service.ListenerService;
import by.bntu.fitr.profilerclient.api.service.MetricService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class KafkaConsumerControllerJob {
    private final ListenerService listenerService;

    public KafkaConsumerControllerJob(final ListenerService listenerService) {
        this.listenerService = listenerService;
    }

    @Scheduled(fixedDelay = 120000L)
    public void renewDeadThreads() {
        List<ListenerEntity> listeners = listenerService.getAllRunningListeners();
        System.out.println(listeners.size());

        if (!listeners.isEmpty()) {
            Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
            for (ListenerEntity listener : listeners) {
                int countOfRunningThreads = 0;
                for (ListenerThreadEntity listenerThread : listener.getListenerThreads()) {
                    for (Thread thread : threadSet) {
                        if (listenerThread.getName().equals(thread.getName())) {
                            countOfRunningThreads++;
                            break;
                        }
                    }
                }

                if (countOfRunningThreads != listener.getListenerThreads().size()) {

                }
            }
        }
        /*
        System.out.println("size: " + threadSet.size());
        System.out.println();
        for (Thread thread : threadSet) {
            System.out.println(thread.getName());

        }

         */
    }
}
