package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacilityDistrictMonth;
import cn.wuhan.hyd.sports.repository.HydFacilityDistrictMonthRepository;
import cn.wuhan.hyd.sports.service.IHydFacilityDistrictMonthService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育基础设施-设施各区月数据 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydFacilityDistrictMonthServiceImpl implements IHydFacilityDistrictMonthService {

    @Resource
    private HydFacilityDistrictMonthRepository hydFacilityDistrictMonthRepository;

    @Override
    public PageResult<HydFacilityDistrictMonth> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydFacilityDistrictMonth> queryAll() {
        return hydFacilityDistrictMonthRepository.findAll();
    }

    @Override
    @Transactional
    public HydFacilityDistrictMonth save(HydFacilityDistrictMonth hydFacilityDistrictMonth) {
        return hydFacilityDistrictMonthRepository.save(hydFacilityDistrictMonth);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydFacilityDistrictMonthRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydFacilityDistrictMonth update(HydFacilityDistrictMonth hydFacilityDistrictMonth) {
        if (hydFacilityDistrictMonth.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydFacilityDistrictMonthRepository.save(hydFacilityDistrictMonth);
    }

    @Override
    public HydFacilityDistrictMonth findById(Long id) {
        return hydFacilityDistrictMonthRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施各区月数据
     *
     * @param facilityDistrictMonths 设施各区月数据 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydFacilityDistrictMonth> facilityDistrictMonths) {
        // 验证参数
        if (facilityDistrictMonths == null || facilityDistrictMonths.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityDistrictMonths.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydFacilityDistrictMonth> savedList = hydFacilityDistrictMonthRepository.saveAll(facilityDistrictMonths);
        return savedList.size();
    }
}
