package core;

import lombok.Data;

@Data
public class BeanDefinition {
    /**
     * Id of Bean
     */
    private String beanName;

    /**
     * reflect of Bean
     */
    private Class<?> beanClass;

    /**
     * reference name of Bean
     */
    private String beanReferenceName;

    /**
     * abstract or not, default is false
     */
    private boolean isAbstract = false;

    /**
     * lazy load or not, default is false
     */
    private boolean isLazy = false;

    /**
     * bean's scope, default is singleton
     */
    private String scope = "singleton";

    public BeanDefinition(String beanName, Class<?> beanClass, String beanReferenceName) {
        this.beanName = beanName;
        this.beanClass = beanClass;
        this.beanReferenceName = beanReferenceName;
    }

    public BeanDefinition(String beanName, Class<?> beanClass, String beanReferenceName, boolean isAbstract) {
        this.beanName = beanName;
        this.beanClass = beanClass;
        this.beanReferenceName = beanReferenceName;
        this.isAbstract = isAbstract;
    }

    public BeanDefinition(String beanName, Class<?> beanClass, String beanReferenceName, boolean isAbstract, boolean isLazy) {
        this.beanName = beanName;
        this.beanClass = beanClass;
        this.beanReferenceName = beanReferenceName;
        this.isAbstract = isAbstract;
        this.isLazy = isLazy;
    }
}
