package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydStadiumMap;
import cn.wuhan.hyd.sports.repository.HydStadiumMapRepository;
import cn.wuhan.hyd.sports.service.IHydStadiumMapService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 驾驶舱-电子地图 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydStadiumMapServiceImpl implements IHydStadiumMapService {

    @Resource
    private HydStadiumMapRepository hydStadiumMapRepository;

    @Override
    public PageResult<HydStadiumMap> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydStadiumMap> queryAll() {
        return hydStadiumMapRepository.findAll();
    }

    @Override
    @Transactional
    public HydStadiumMap save(HydStadiumMap hydStadiumMap) {
        return hydStadiumMapRepository.save(hydStadiumMap);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydStadiumMapRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydStadiumMap update(HydStadiumMap hydStadiumMap) {
        if (hydStadiumMap.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydStadiumMapRepository.save(hydStadiumMap);
    }

    @Override
    public HydStadiumMap findById(Long id) {
        return hydStadiumMapRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 电子地图
     *
     * @param stadiumMaps 电子地图 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydStadiumMap> stadiumMaps) {
        // 验证参数
        if (stadiumMaps == null || stadiumMaps.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumMaps.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydStadiumMap> savedList = hydStadiumMapRepository.saveAll(stadiumMaps);
        return savedList.size();
    }
}
