package factory;

public interface BeanFactory {
    /**
     * get bean object by name
     * @param beanName
     * @return
     */
    Object getBean(String beanName);

    /**
     * do getBean process
     * @param beanName
     * @return
     */
    Object doGetBean(String beanName);

    /**
     * create bean by name
     * @param beanName
     * @return
     */
    Object createBean(String beanName);

    /**
     * do create bean process
     * @param beanName
     * @return
     */
    Object doCreateBean(String beanName);
}
