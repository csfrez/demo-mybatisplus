package com.csfrez.datalimit.annotation;

import java.lang.annotation.*;

@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface DataScope {
    String type() default "";

    DataColumn[] value() default {};

    boolean ignore() default false;
}
