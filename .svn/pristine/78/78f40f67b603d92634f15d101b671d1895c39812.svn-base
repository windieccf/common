package com.nus.adqs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nus.adqs.constant.ConstantOperator;


@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Filterable {
	String jpaColumn() default "";
	String operator() default ConstantOperator.EQUAL;
	String[] multiColumns() default {};
}
