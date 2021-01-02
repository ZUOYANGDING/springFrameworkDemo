package Registry;

import core.BeanDefinition;

import java.util.Properties;
import java.util.Set;

/**
 * Bean definition registry interface
 */
public interface MyBeanDefinitionRegistry {
    /**
     * Register a new BeanDefinition into Bean Registry form
     */
    void registerBeanDefinition(String beanName, BeanDefinition beanDefinition);

    /**
     * get bean definition from the registry form
     * @param beanName
     */
    BeanDefinition getBeanDefinition(String beanName);

    /**
     * check if bean exist in registry form
     * @param beanName
     * @return
     */
    Boolean checkBeanDefinition(String beanName);

    /**
     * get all bean names from registry form
     * @return
     */
    Set<String> getAllBeanDefinition();

    /**
     * set up bean config
     * @param properties
     */
    void registryProperties(Properties properties);
}
