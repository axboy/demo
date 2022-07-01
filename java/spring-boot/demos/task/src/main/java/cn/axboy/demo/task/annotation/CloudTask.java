package cn.axboy.demo.task.annotation;

import java.lang.annotation.*;

//@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface CloudTask {

    String key() default "";

    String tag() default "";
}
