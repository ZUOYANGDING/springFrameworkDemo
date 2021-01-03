package context;

import Registry.MyBeanDefinitionRegistry;
import core.BeanDefinition;
import factory.MyDefaultListableBeanFactory;

import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class MyAbstractApplicationContext implements MyBeanDefinitionRegistry {

    /**
     * bean factory for bean manage
     */
    public final MyDefaultListableBeanFactory defaultListableBeanFactory;

    public MyAbstractApplicationContext() {
        this.defaultListableBeanFactory = new MyDefaultListableBeanFactory();
    }

    public void refresh() {
        // get bean factory
        MyDefaultListableBeanFactory beanFactory = obtainFreshBeanFactory();
        // create non-lazy load singleton bean
        finishBeanFactoryInitialization(beanFactory);
    }

    private MyDefaultListableBeanFactory obtainFreshBeanFactory() {
        return this.defaultListableBeanFactory;
    }

    /**
     * start bean injection
     * @param beanFactory
     */
    private void finishBeanFactoryInitialization(MyDefaultListableBeanFactory beanFactory) {
        beanFactory.preInstantiateSingletons();
    }

    /**
     * get bean from bean factory
     * @param beanName
     * @return
     */
    public Object getBean(String beanName) {
        return this.defaultListableBeanFactory.getBean(beanName);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.defaultListableBeanFactory.beanDefinitionMap.get(beanName);
    }

    @Override
    public Boolean checkBeanDefinition(String beanName) {
        return this.defaultListableBeanFactory.beanDefinitionMap.containsKey(beanName);
    }

    @Override
    public Set<String> getAllBeanDefinition() {
        return this.defaultListableBeanFactory.beanDefinitionMap.keySet();
    }

    @Override
    public void registryProperties(Properties properties) {
        this.defaultListableBeanFactory.registerProperties(properties);
    }
}
