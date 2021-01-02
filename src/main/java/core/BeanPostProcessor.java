package core;



public interface BeanPostProcessor {
    /**
     * bean process before init
     * @param bean
     * @param beanName
     * @return
     */
    public Object postProcessBeforeInit(String beanName, Object bean);

    /**
     * bean process after init
     * @param bean
     * @param beanName
     * @return
     */
    public Object postProcessAfterInit(String beanName, Object bean);
}
