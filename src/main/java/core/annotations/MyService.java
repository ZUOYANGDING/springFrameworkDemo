package core.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyComponent
public @interface MyService {
    String name() default "";
}
