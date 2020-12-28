package core.annotations;

import java.lang.annotation.*;

/**
 * for Bean scan, used for classloader, and then for bean injection
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyComponent {
    String name() default "";
}
