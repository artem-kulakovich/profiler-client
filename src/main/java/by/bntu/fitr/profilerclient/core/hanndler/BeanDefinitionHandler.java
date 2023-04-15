package by.bntu.fitr.profilerclient.core.hanndler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeanDefinitionHandler {
    private final ApplicationContext applicationContext;
    private final ConfigurableListableBeanFactory configurableListableBeanFactory;

    @Autowired
    public BeanDefinitionHandler(ApplicationContext applicationContext, ConfigurableListableBeanFactory configurableListableBeanFactory) {
        this.applicationContext = applicationContext;
        this.configurableListableBeanFactory = configurableListableBeanFactory;
    }

    public synchronized void registerTheSameTypeOfBeans(List<String> beansName, Class clazz, String scope) {
        BeanDefinitionRegistry registry = ((BeanDefinitionRegistry) configurableListableBeanFactory);

        for (int i = 0; i < beansName.size(); i++) {
            GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
            beanDefinition.setBeanClass(clazz);
            beanDefinition.setLazyInit(false);
            beanDefinition.setAbstract(false);
            beanDefinition.setAutowireCandidate(true);
            beanDefinition.setScope(scope);

            registry.registerBeanDefinition(beansName.get(i), beanDefinition);
        }

    }

    public <T> T getBeanByName(String beanName, Class<T> clazz) {
        return applicationContext.getBean(beanName, clazz);
    }

    public synchronized void removeBeans(List<String> beansName) {
        BeanDefinitionRegistry registry = ((BeanDefinitionRegistry) configurableListableBeanFactory);

        for (String beanName : beansName) {
            registry.removeBeanDefinition(beanName);
        }
    }
}
