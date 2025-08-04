package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacility;
import cn.wuhan.hyd.sports.repository.HydFacilityRepository;
import cn.wuhan.hyd.sports.service.IHydFacilityService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育基础设施-设施全貌 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydFacilityServiceImpl implements IHydFacilityService {

    @Resource
    private HydFacilityRepository hydFacilityRepository;

    @Override
    public PageResult<HydFacility> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydFacility> queryAll() {
        return hydFacilityRepository.findAll();
    }

    @Override
    @Transactional
    public HydFacility save(HydFacility hydFacility) {
        return hydFacilityRepository.save(hydFacility);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydFacilityRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydFacility update(HydFacility hydFacility) {
        if (hydFacility.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydFacilityRepository.save(hydFacility);
    }

    @Override
    public HydFacility findById(Long id) {
        return hydFacilityRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 设施全貌
     *
     * @param facilities 设施全貌 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydFacility> facilities) {
        // 验证参数
        if (facilities == null || facilities.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilities.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydFacility> savedList = hydFacilityRepository.saveAll(facilities);
        return savedList.size();
    }
}
