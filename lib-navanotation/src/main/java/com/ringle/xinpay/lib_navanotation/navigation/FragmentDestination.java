package com.ringle.xinpay.lib_navanotation.navigation;

import com.sun.istack.internal.NotNull;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * create by 岑胜德
 * on 2020/1/6
 * 说明:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface FragmentDestination {
    @NotNull String path();

    @NotNull boolean needLogin() default false;

    @NotNull boolean asStarter() default false;
}
