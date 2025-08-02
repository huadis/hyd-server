package cn.wuhan.hyd.framework.annotation;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月13日 <br>
 */
public @interface Query {
    String propName() default "";
    Type type() default Type.EQUAL;

    /**
     * 连接查询的属性名，如User类中的dept
     */
    String joinName() default "";

    /**
     * 默认左连接
     */
    Join join() default Join.LEFT;

    /**
     * 多字段模糊搜索，仅支持String类型字段，多个用逗号隔开, 如@Query(blurry = "email,username")
     */
    String blurry() default "";

    enum Type {
        EQUAL
        , GREATER_THAN
        , LESS_THAN
        , INNER_LIKE
        , LEFT_LIKE
        , RIGHT_LIKE
        , LESS_THAN_NQ
        , IN
        , NOT_IN
        ,NOT_EQUAL
        ,BETWEEN
        ,NOT_NULL
        ,IS_NULL,
        FIND_IN_SET
    }

    enum Join {
        LEFT, RIGHT, INNER
    }
}
