package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultFacilityInspect;
import cn.wuhan.hyd.sports.repository.HydResultFacilityInspectRepository;
import cn.wuhan.hyd.sports.service.IHydResultFacilityInspectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
public class HydResultFacilityInspectServiceImpl implements IHydResultFacilityInspectService {

    @Resource
    private HydResultFacilityInspectRepository hydResultFacilityInspectRepository;

    @Override
    public PageResult<HydResultFacilityInspect> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultFacilityInspect> pageResult = hydResultFacilityInspectRepository.findAll(pageable);
        PageResult<HydResultFacilityInspect> pageResult1 = new PageResult<>();
        pageResult1.setTotalElements(pageResult.getTotalElements());
        pageResult1.setContent(pageResult.getContent());
        return pageResult1;
    }

    @Override
    public List<HydResultFacilityInspect> queryAll() {
        return hydResultFacilityInspectRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityInspect save(HydResultFacilityInspect hydResultFacilityInspect) {
        return hydResultFacilityInspectRepository.save(hydResultFacilityInspect);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultFacilityInspectRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityInspect update(HydResultFacilityInspect hydResultFacilityInspect) {
        if (hydResultFacilityInspect.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultFacilityInspectRepository.save(hydResultFacilityInspect);
    }

    @Override
    public HydResultFacilityInspect findById(Long id) {
        return hydResultFacilityInspectRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 巡检动态
     *
     * @param facilityInspects 巡检动态 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityInspect> facilityInspects) {
        // 验证参数
        if (facilityInspects == null || facilityInspects.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (facilityInspects.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultFacilityInspect> savedList = hydResultFacilityInspectRepository.saveAll(facilityInspects);
        return savedList.size();
    }
}
