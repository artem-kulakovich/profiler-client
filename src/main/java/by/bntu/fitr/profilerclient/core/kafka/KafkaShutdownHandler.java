package by.bntu.fitr.profilerclient.core.kafka;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class KafkaShutdownHandler {

    public synchronized void killKafkaConsumerThread(List<String> threadsListenersName) {
        Set<Thread> runningThreads = Thread.getAllStackTraces().keySet();

        for (Thread runningThread : runningThreads) {
            for (String threadListenersName : threadsListenersName) {
                if (runningThread.getName().equals(threadListenersName)) {
                    runningThread.interrupt();
                }
            }

        }

    }
}
