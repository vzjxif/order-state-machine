package top.zxx.order.statemachine.core.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface OrderProcessor {

    String[] state() default {};

    String[] bizCode() default {};

    String[] sceneId() default {};

    String event();
}
