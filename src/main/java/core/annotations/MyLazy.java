package core.annotations;

import java.lang.annotation.*;

/**
 * for Bean lazy load
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@MyComponent
public @interface MyLazy {
}
