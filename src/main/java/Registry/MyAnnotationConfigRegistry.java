package Registry;

/**
 * Configuration register
 */
public interface MyAnnotationConfigRegistry {
    /**
     * Register one or more annotated classes to be processed.
     * <p>Calls to {@code register} are idempotent; adding the same
     * annotated class more than once has no additional effect.
     * @param annotatedClasses one or more annotated classes,
     * e.g. {@link Configuration @Configuration} classes
     */
    void register(Class<?>... annotatedClasses);

    /**
     * Perform a scan within the specified base packages.
     * @param basePackages the packages to check for annotated classes
     */
    void scan(String... basePackages);
}
