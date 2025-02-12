package ru.moskalev.sandbox.spring_ripper.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InjectRandomInt {
    int min() default 0;
    int max() ;
}
