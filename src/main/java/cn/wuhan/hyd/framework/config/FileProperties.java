package cn.wuhan.hyd.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年07月13日 <br>
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file")
public class FileProperties {
    /** 文件大小限制 */
    private Long maxSize;

    /** 头像大小限制 */
    private Long avatarMaxSize;

    private LocalPath mac;

    private LocalPath linux;

    private LocalPath windows;

    public LocalPath getPath(){
        String os = System.getProperty("os.name");
        if(os.toLowerCase().startsWith("win")) {
            return windows;
        } else if(os.toLowerCase().startsWith("mac")){
            return mac;
        }
        return linux;
    }

    @Data
    public static class LocalPath{

        private String path;

        private String avatar;
    }
}
