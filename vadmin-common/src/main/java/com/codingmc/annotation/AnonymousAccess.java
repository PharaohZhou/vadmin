package com.codingmc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记"匿名访问方法"
 *
 * @ClassName AnonymousAccess
 * @Description: TODO
 * @Author zhou
 * @Date 2020/5/12
 * @Version V1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
