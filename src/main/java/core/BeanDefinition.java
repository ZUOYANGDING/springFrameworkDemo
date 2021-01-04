package core;

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

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(Class<?> beanClass) {
        this.beanClass = beanClass;
    }

    public String getBeanReferenceName() {
        return beanReferenceName;
    }

    public void setBeanReferenceName(String beanReferenceName) {
        this.beanReferenceName = beanReferenceName;
    }

    public boolean isAbstract() {
        return isAbstract;
    }

    public void setAbstract(boolean anAbstract) {
        isAbstract = anAbstract;
    }

    public boolean isLazy() {
        return isLazy;
    }

    public void setLazy(boolean lazy) {
        isLazy = lazy;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
