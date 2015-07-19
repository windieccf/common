package com.nus.adqs.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nus.adqs.constant.ConstantWeb;

@Target(value={ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Parameter {
	String formElement() default ConstantWeb.FORM_TEXT;
	String bindingName() default "";
}
