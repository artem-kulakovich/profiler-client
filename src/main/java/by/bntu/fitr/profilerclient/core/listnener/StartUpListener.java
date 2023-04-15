package by.bntu.fitr.profilerclient.core.listnener;

import by.bntu.fitr.profilerclient.api.mapper.ApplicationMapper;
import by.bntu.fitr.profilerclient.core.kafka.KafkaTimedMetricConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartUpListener {
    /*
    private final ApplicationContextWrapper applicationContextWrapper;
    private final ApplicationContext applicationContext;
    private final ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Autowired
    public StartUpListener(ApplicationContextWrapper applicationContextWrapper,
                           ApplicationContext applicationContext,
                           ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.applicationContextWrapper = applicationContextWrapper;
        this.applicationContext = applicationContext;
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadOnStartUp() {
        KafkaTimedMetricConsumer kafkaTimedMetricConsumer1 = applicationContext.getBean("kafkaTimedMetricConsumer10", KafkaTimedMetricConsumer.class);
        KafkaTimedMetricConsumer kafkaTimedMetricConsumer2 = applicationContext.getBean("kafkaTimedMetricConsumer", KafkaTimedMetricConsumer.class);
        System.out.println(configurableListableBeanFactory);
        System.out.println(kafkaTimedMetricConsumer1.getName());
        System.out.println(kafkaTimedMetricConsumer2.getName());
        BeanDefinitionRegistry registry = ((BeanDefinitionRegistry )configurableListableBeanFactory);


        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(KafkaTimedMetricConsumer.class);
        beanDefinition.setLazyInit(false);
        beanDefinition.setAbstract(false);
        beanDefinition.setAutowireCandidate(true);
        beanDefinition.setScope("singleton");

        registry.registerBeanDefinition("kafkaTimedMetricConsumer11",beanDefinition);
        KafkaTimedMetricConsumer kafkaTimedMetricConsume11 = applicationContext.getBean("kafkaTimedMetricConsumer11", KafkaTimedMetricConsumer.class);

        System.out.println(kafkaTimedMetricConsume11);




    }

     */
}
