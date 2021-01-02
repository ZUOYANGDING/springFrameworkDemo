package context;

import Registry.MyBeanDefinitionRegistry;
import core.BeanDefinition;
import factory.MyDefaultListableBeanFactory;

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
        // create lazy load singleton bean
        finishBeanFactoryInitialization(beanFactory);
    }

    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinition);
    }
}
