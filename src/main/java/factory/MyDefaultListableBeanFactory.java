package factory;

import core.BeanDefinition;
import core.BeanPostProcessor;
import core.annotations.MyAutowired;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static scanner.MyClassPathBeanDefinitionScanner.toLowerCaseIndex;

public class MyDefaultListableBeanFactory implements BeanFactory{
    /**
     * for first level cache, store initialized bean
     * bean from singletonObjects can used directly
     */
    private final Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    /**
     * for second level cache
     * solve loop dependency, bean in earlySingletonObjects has already point to a piece of memory
     */
    private final Map<String, Object> earlySingletonObjects = new HashMap<>(16);

    /**
     * for 3rd level cache
     * solve loop dependency, bean in singletonFactories is singleton bean Factory object
     *
     */
    private final Map<String, Object> singletonFactories = new HashMap<>(16);

    /**
     * collection of bean's info data, from scanner's scanning result
     */
    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(128);

    /**
     * store bean post processors
     */
    private Set<BeanPostProcessor> postProcessors = new HashSet<>();

    /**
     * add bean's info data into definition map
     * @param beanName
     * @param beanDefinition
     */
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    private Properties properties = null;

    public void registerProperties(Properties properties) {
        this.properties = properties;
    }

    /**
     * init all beans which are not abstract and not lazy loading
     */
    public void preInstantiateSingletons() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        beanNames.forEach(beanName -> {
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (!Objects.isNull(beanDefinition) && !beanDefinition.isAbstract() && !beanDefinition.isLazy()) {
                getBean(beanName);
            }
        });
    }

    @Override
    public Object getBean(String beanName) {
        return this.doGetBean(beanName);
    }

    @Override
    public Object doGetBean(String beanName) {
        if (singletonObjects.containsKey(beanName)) {
            return this.singletonObjects.get(beanName);
        }
        return this.createBean(beanName);
    }

    @Override
    public Object createBean(String beanName) {
        return this.createBean(beanName);
    }

    @Override
    public Object doCreateBean(String beanName) {
        synchronized (this.getClass()) {
            // check if this bean is in creating process
            if (earlySingletonObjects.containsKey(beanName) || singletonFactories.containsKey(beanName)) {
                throw new RuntimeException("IOC has dependency loop:     " + beanName);
            }
            // put the bean into 3rd cache, mark it is in creating process
            singletonFactories.put(beanName, "");
            // load the bean name and bean info data into definition map
            BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
            if (Objects.isNull(beanDefinition)) {
                return null;
            }

            // create bean
            Object beanObject = null;
            try {
                // reflect the class of the object of the bean
                beanObject = beanDefinition.getBeanClass().newInstance();
                if (beanObject instanceof BeanPostProcessor) {
                    postProcessors.add((BeanPostProcessor) beanObject);
                }
                // store bean into 2nd cache, mark it in creating process but without properties filled
                earlySingletonObjects.put(beanName, beanObject);
                // remove the bean from 3rd cache
                singletonFactories.remove(beanName);
                // fill dependency properties (fields) of bean
                beanObject = populateBean(beanObject);
                // do post bean processes
                initializeBeanPostProcess(beanName, beanObject);
                // store bean into 1st cache
                singletonObjects.put(beanName, beanObject);
                // remove the bean from 2nd cache
                earlySingletonObjects.remove(beanName);
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
            return beanObject;
        }

    }

    private Object populateBean(Object beanObject) throws IllegalAccessException {
        // get fields declared in the class of bean
        Field[] fields = beanObject.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // check if the field need dependent injection
            if (field.isAnnotationPresent(MyAutowired.class)) {
                String simpleName= toLowerCaseIndex(field.getType().getSimpleName());
                // check if the lower case simpleName is in bean's info data map
                Set<String> beanDefinitionNames = beanDefinitionMap.keySet();
                if (!beanDefinitionNames.contains(simpleName)) {
                    simpleName = toLowerCaseIndex(field.getName());
                    if (!beanDefinitionNames.contains(simpleName)) {
                        // both field name and field type are not in bean's info data map
                        throw new RuntimeException("Cannot find property " + field.getType() + " for bean " + beanObject);
                    }
                }
                // check if the dependent field in 3 caches (can be use directly)
                // fill the dependency field to target bean
                if (singletonObjects.containsKey(simpleName)) {
                    field.set(beanObject, singletonObjects.get(simpleName));
                } else if (earlySingletonObjects.containsKey(simpleName)) {
                    field.set(beanObject, earlySingletonObjects.get(simpleName));
                } else {
                    // if not in 1st and 2nd cache, create the dependency field first
                    field.set(beanObject, getBean(simpleName));
                }
            }
        }
        return beanObject;
    }

    /**
     * finish process of post bean init
     * @param beanName
     * @param beanObject
     */
    private void initializeBeanPostProcess(String beanName, Object beanObject) {
        for (BeanPostProcessor beanPostProcessor : postProcessors) {
            beanObject = beanPostProcessor.postProcessBeforeInit(beanName, beanObject);
        }
        System.out.println("Do the init process of bean:   " + beanName);
        for (BeanPostProcessor beanPostProcessor : postProcessors) {
            beanObject = beanPostProcessor.postProcessAfterInit(beanName, beanObject);
        }
    }
}
