package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultFacilityYear;
import cn.wuhan.hyd.sports.repository.HydResultFacilityYearRepository;
import cn.wuhan.hyd.sports.service.IHydResultFacilityYearService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育基础设施-健身点位年数据 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityYearServiceImpl implements IHydResultFacilityYearService {

    @Resource
    private HydResultFacilityYearRepository hydResultFacilityYearRepository;

    @Override
    public PageResult<HydResultFacilityYear> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultFacilityYear> queryAll() {
        return hydResultFacilityYearRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityYear save(HydResultFacilityYear hydResultFacilityYear) {
        return hydResultFacilityYearRepository.save(hydResultFacilityYear);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultFacilityYearRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityYear update(HydResultFacilityYear hydResultFacilityYear) {
        if (hydResultFacilityYear.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultFacilityYearRepository.save(hydResultFacilityYear);
    }

    @Override
    public HydResultFacilityYear findById(Long id) {
        return hydResultFacilityYearRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 健身点位年数据
     *
     * @param facilityYears 健身点位年数据 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityYear> facilityYears) {
        // 验证参数
        if (facilityYears == null || facilityYears.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityYears.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultFacilityYear> savedList = hydResultFacilityYearRepository.saveAll(facilityYears);
        return savedList.size();
    }

    @Override
    public HydResultFacilityYear findLatestFacilityYear() {
        return hydResultFacilityYearRepository.findLatestFacilityYear();
    }
}
