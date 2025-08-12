package cn.wuhan.hyd.framework.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelField {

    String name() default "";
}
