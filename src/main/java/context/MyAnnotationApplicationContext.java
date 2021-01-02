package context;

import scanner.MyClassPathBeanDefinitionScanner;

public class MyAnnotationApplicationContext extends MyAbstractApplicationContext{
    private final MyClassPathBeanDefinitionScanner myClassPathBeanDefinitionScanner;

    public MyAnnotationApplicationContext(MyClassPathBeanDefinitionScanner myClassPathBeanDefinitionScanner) {
        this.myClassPathBeanDefinitionScanner = new MyClassPathBeanDefinitionScanner(this);
    }

    public MyAnnotationApplicationContext(String ...basePackage) {
        // container init by call constructor in MyAbstractApplicationContext
        this();
        // scan all classes belong to base packages
        scan(basePackage);
        // inject bean
        refresh();
    }

    @Override
    public void register(Class<?>... annotatedClasses) {

    }

    @Override
    public void scan(String... basePackages) {
        this.myClassPathBeanDefinitionScanner.scan(basePackages);
    }
}
