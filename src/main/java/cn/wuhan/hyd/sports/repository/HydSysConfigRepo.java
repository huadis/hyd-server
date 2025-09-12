package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydSysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 功能说明： 系统配置 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年09月11日 <br>
 */
@Repository
public interface HydSysConfigRepo extends JpaRepository<HydSysConfig, String> {
}
