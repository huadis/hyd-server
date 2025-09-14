package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydSysConfig;
import cn.wuhan.hyd.sports.repository.HydSysConfigRepo;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 系统配置 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年09月13日 <br>
 */
@Service
public class HydSysConfigServiceImpl implements IHydSysConfigService {

    @Resource
    private HydSysConfigRepo sysConfigRepo;

    private final Logger logger = LoggerFactory.getLogger(HydSysConfigServiceImpl.class);

    @Override
    public List<HydSysConfig> queryAll() {
        return sysConfigRepo.findAll();
    }

    @Override
    public PageResult<HydSysConfig> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydSysConfig> pageResult = sysConfigRepo.findAll(pageable);
        PageResult<HydSysConfig> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public HydSysConfig findById(Long id) {
        return sysConfigRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("系统配置不存在，ID：" + id));
    }

    @Override
    public HydSysConfig findByKey(String name) {
        return sysConfigRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("系统配置不存在，Name：" + name));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Long id) {
        sysConfigRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydSysConfig update(HydSysConfig config) {
        if (config.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(config.getId());
        return sysConfigRepo.save(config);
    }

    @Override
    public boolean notRefresh(String name) {
        return sysConfigRepo.findByName(name)
                .map(config -> {
                    String value = config.getValue();
                    if ("true".equalsIgnoreCase(value)) {
                        return true;
                    } else if ("false".equalsIgnoreCase(value)) {
                        return false;
                    } else {
                        logger.warn("配置项 {} 的值 {} 不是有效的布尔值，默认返回 false", name, value);
                        return false;
                    }
                })
                .orElse(false);
    }
}
