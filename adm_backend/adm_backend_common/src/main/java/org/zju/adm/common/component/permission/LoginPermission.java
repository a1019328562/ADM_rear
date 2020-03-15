package org.zju.adm.common.component.permission;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ClassName: AdminPermission
 * Description: 用户登录验证类
 * Created by tiamo on 15/3/2020 3:55 下午
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginPermission {
    String produceType() default "http";
}