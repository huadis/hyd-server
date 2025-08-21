package cn.wuhan.hyd.framework.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

/**
 * 功能说明： 全局 Controller 访问日志切面 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月21日 <br>
 */
@Aspect
@Component
public class WebLogAspect {


    /**
     * 获取日志对象，构造函数传入当前类，查找日志方便定位
     */
    private static final Logger log = LoggerFactory.getLogger(WebLogAspect.class);

    // 记录请求开始时间
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();

    // 定义切点：匹配所有 Controller 中的方法
    @Pointcut("execution(public * cn.wuhan.hyd.*.controller..*.*(..))")
    public void webLog() {
    }

    // 方法执行前记录请求信息
    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        // 获取请求上下文
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();

            // 打印请求信息
            log.info("========================================== 开始请求 ==========================================");
            log.info("RequestTime    :  {}", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            log.info("RequestURL     :  {}", request.getRequestURL().toString());
            log.info("RequestMethod  :  {}", request.getMethod());
            log.info("RequestIP      :  {}", request.getRemoteAddr());
            log.info("RequestParam   :  {}", Arrays.toString(joinPoint.getArgs()));
        }
    }

    // 方法执行后记录响应信息
    @AfterReturning(returning = "result", pointcut = "webLog()")
    public void doAfterReturning(Object result) {
        // 打印响应信息
        log.info("Response Result : {}", result);
        log.info("Total toast     : {} ms", System.currentTimeMillis() - startTime.get());
        log.info("========================================== 结束请求 ==========================================\n");

        // 清除 ThreadLocal，避免内存泄漏
        startTime.remove();
    }
}
