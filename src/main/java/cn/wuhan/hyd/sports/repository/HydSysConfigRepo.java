package cn.wuhan.hyd.sports.repository;

import cn.wuhan.hyd.sports.domain.HydSysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 功能说明： 系统配置 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年09月11日 <br>
 */
@Repository
public interface HydSysConfigRepo extends JpaRepository<HydSysConfig, Long> {

    /**
     * 根据key查询配置记录
     * @param key 配置键
     * @return 配置记录（包含value），若不存在则返回空Optional
     */
    Optional<HydSysConfig> findByKey(String key);

    /**
     * 检查指定key是否存在
     * @param key 配置键
     * @return 存在返回true，否则返回false
     */
    boolean existsByKey(String key);
}
