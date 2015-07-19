package com.nus.adqs.annotation.security;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.nus.adqs.enumeration.EnumPermission;

@Target(value={ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Accessable {
	String[] privilegeType() default {""};
	EnumPermission[] permissionType() default {EnumPermission.READ};
}
