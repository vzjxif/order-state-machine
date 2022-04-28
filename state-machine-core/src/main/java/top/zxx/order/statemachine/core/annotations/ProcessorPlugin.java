package top.zxx.order.statemachine.core.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface ProcessorPlugin {

    String[] state() default {};

    String event();

    String[] bizCode() default {};

    String[] sinceId() default {};
}
