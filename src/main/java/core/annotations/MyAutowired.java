package core.annotations;

import java.lang.annotation.*;

/**
 * annotation for Bean configuration and dependencies injection
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Documented
@MyComponent
public @interface MyAutowired {
}
