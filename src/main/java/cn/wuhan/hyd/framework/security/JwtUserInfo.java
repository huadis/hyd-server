package cn.wuhan.hyd.framework.security;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月02日 <br>
 */
public interface JwtUserInfo {

    Long getId();

    String getUsername();

    String getPassword();

    boolean getEnabled();
}
