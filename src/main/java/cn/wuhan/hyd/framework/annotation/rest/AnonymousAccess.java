package cn.wuhan.hyd.framework.annotation.rest;

import java.lang.annotation.*;

/**
 * 功能说明：  用于标记匿名访问方法 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月12日 <br>
 */
@Inherited
@Documented
@Target({ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnonymousAccess {

}
