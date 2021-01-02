package scanner;

import Registry.MyBeanDefinitionRegistry;
import core.BeanDefinition;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;
import java.util.Set;

public class MyClassPathBeanDefinitionScanner {
    /**
     * bean registration form
     */
    private final MyBeanDefinitionRegistry beanDefinitionRegistry;

    /**
     * scan all classes under base packages
     */
    private final static Set<String> basePackageClassName = new HashSet<>();

    public MyClassPathBeanDefinitionScanner(MyBeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void scan(String[] basePackages) {
        // start scan packages
        doScan(basePackages);
    }

    /**
     * get all configuration files and put
     * @param basePackages
     */
    private void doScan(String[] basePackages) {
        // set of source components
        Set<BeanDefinition> candidates = new HashSet<>();
        // scan properties file
        Properties properties = findAllConfigurationFile();
        beanDefinitionRegistry.registryProperties(properties);
        // scan all candidate source file
        findCandidateComponents(basePackages, candidates);
        // store all components into registration form
        // call the registerBeanDefinition() in MyDefaultListableBeanFactory
        candidates.forEach(beanDefinition -> {
            beanDefinitionRegistry.registerBeanDefinition(beanDefinition.getBeanName(), beanDefinition);
        });
    }

    /**
     * scan properties file with GBK text format
     * @return
     */
    private Properties findAllConfigurationFile() {
        Properties properties = new Properties();
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("application.properties");
            if (!Objects.isNull(is)) {
                // load properties file contains Chinese text format
                InputStreamReader inputStreamReader = new InputStreamReader(is, "GBK");
                properties.load(inputStreamReader);
            }
            // load active properties file, such as properties file separated into dev and server, decided with is active
            if (properties.containsKey(IocConstant.active) && !Objects.isNull(properties.get(IocConstant.active))) {
                InputStream is_1 = this.getClass().getClassLoader().getResourceAsStream("application-" +
                        properties.get(IocConstant.active) + ".properties");
                if (!Objects.isNull(is_1)) {
                    InputStreamReader inputStreamReader = new InputStreamReader(is_1, "GBK");
                    properties.load(inputStreamReader);
                }
            }
            if (properties.containsKey(IocConstant.include) && !Objects.isNull(properties.get(IocConstant.include))) {
                InputStreamReader is_1 = this.getClass().getClassLoader().getResourceAsStream("application-" +
                        properties.get(IOcConstant.include) + ".properties");
                if (!Objects.isNull(is_1)) {
                    InputStreamReader inputStreamReader = new InputStreamReader(is_1, "GBK");
                    properties.load(inputStreamReader);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    /**
     * load candidates source file
     * @param basePackages
     * @param candidates
     */
    private void findCandidateComponents(String[] basePackages, Set<BeanDefinition> candidates) {
        for (String basePackage : basePackages) {
            loadClass(basePackage);
        }
    }

    /**
     * load candidates source file
     * @param basePackage
     */
    private void loadClass(String basePackage) {
        URL url = this.getClass().getClassLoader().getResource(basePackage.replaceAll("\\.", File.separator));
        File file = new File(url.getFile());
        if (file.exists() && file.isDirectory()) {
            File[] files = file.listFiles();
            for (File subFile : files) {
                if (subFile.isDirectory()) {
                    loadClass(basePackage + File.separator + subFile.getName());
                } else {
                    if (subFile.getName().endsWith(".class")) {
                        String beanReferenceName = basePackage.replace(File.separator, ".")
                                + "."
                                + subFile.getName().replaceAll(".class", "");
                        System.out.println("Read Class File " + beanReferenceName);
                        basePackageClassName.add(beanReferenceName);
                    }
                }
            }
        } else {
            throw new RuntimeException("Did not find the necessary file.......");
        }
    }

    public static String toLowerCaseIndex(String name) {
        if (StringUtils.isNotEmpty(name)) {
            return name.substring(0, 1).toLowerCase() + name.substring(1, name.length());
        }
        return name;
    }
}
