package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacilityInspect;
import cn.wuhan.hyd.sports.repository.HydFacilityInspectRepository;
import cn.wuhan.hyd.sports.service.IHydFacilityInspectService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育基础设施-巡检动态 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydFacilityInspectServiceImpl implements IHydFacilityInspectService {

    @Resource
    private HydFacilityInspectRepository hydFacilityInspectRepository;

    @Override
    public PageResult<HydFacilityInspect> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydFacilityInspect> queryAll() {
        return hydFacilityInspectRepository.findAll();
    }

    @Override
    @Transactional
    public HydFacilityInspect save(HydFacilityInspect hydFacilityInspect) {
        return hydFacilityInspectRepository.save(hydFacilityInspect);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydFacilityInspectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydFacilityInspect update(HydFacilityInspect hydFacilityInspect) {
        if (hydFacilityInspect.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydFacilityInspectRepository.save(hydFacilityInspect);
    }

    @Override
    public HydFacilityInspect findById(Long id) {
        return hydFacilityInspectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 巡检动态
     *
     * @param facilityInspects 巡检动态 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydFacilityInspect> facilityInspects) {
        // 验证参数
        if (facilityInspects == null || facilityInspects.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityInspects.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydFacilityInspect> savedList = hydFacilityInspectRepository.saveAll(facilityInspects);
        return savedList.size();
    }
}
