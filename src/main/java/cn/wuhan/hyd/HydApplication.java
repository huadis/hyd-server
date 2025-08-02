package cn.wuhan.hyd;

import cn.wuhan.hyd.framework.utils.SpringBeanHolder;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能说明： 体育局大屏项目 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月02日 <br>
 */
@Slf4j
@RestController
@Api(hidden = true)
@SpringBootApplication
@EnableTransactionManagement
public class HydApplication {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(HydApplication.class);
        // 监控应用的PID，启动时可指定PID路径：--spring.pid.file=/home/sports/app.pid
        // 或者在 application.yml 添加文件路径，方便 kill，kill `cat /home/sports/app.pid`
        springApplication.addListeners(new ApplicationPidFileWriter());
        ConfigurableApplicationContext context = springApplication.run(args);
        String port = context.getEnvironment().getProperty("server.port");
        log.info("____________________________________________");
        log.info("Local: http://localhost:{}                 |", port);
        log.info("Swagger: http://localhost:{}/doc.html      |", port);
        log.info("---------------------------------------------");
    }

    @Bean
    public SpringBeanHolder springContextHolder() {
        return new SpringBeanHolder();
    }

}
