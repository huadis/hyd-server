package cn.wuhan.hyd.framework.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 功能说明: 异步线程池配置 <br>
 * 开发人员: @author huadi<br>
 * 开发时间: 2020年02月14日<br>
 */
// 启用 异步调用
@EnableAsync
@Configuration
public class ExecutorConfig {

    /**
     * 获取日志对象，构造函数传入当前类，查找日志方便定位
     */
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Value("${executor.corePool:10}")
    private Integer corePool;
    @Value("${executor.maxPool:100}")
    private Integer maxPool;
    @Value("${executor.keepAliveTime:10}")
    private Integer keepAliveTime;
    @Value("${executor.queue:10}")
    private Integer queue;


    @Bean("executor")
    public Executor executor() {
        log.info("start async Executor");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 配置核心线程数 线程池创建时候初始化的线程数
        executor.setCorePoolSize(corePool);
        // 配置最大线程数 线程池最大的线程数，只有在缓冲队列满了之后才会申请超过核心线程数的线程
        executor.setMaxPoolSize(maxPool);
        // 允许线程空闲时间（单位：默认为秒）当超过了核心线程之外的线程在空闲时间到达之后会被销毁
        executor.setKeepAliveSeconds(keepAliveTime);
        // 配置队列大小 用来缓冲执行任务的队列
        executor.setQueueCapacity(queue);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-executor-");

        // 设置拒绝策略
        /*executor.setRejectedExecutionHandler((r, e) -> {
            // .....
        });*/

        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
