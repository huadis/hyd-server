package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultOrderSport;
import cn.wuhan.hyd.sports.repository.HydResultOrderSportRepository;
import cn.wuhan.hyd.sports.service.IHydResultOrderSportService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-项目消费券订单金额Top5 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderSportServiceImpl implements IHydResultOrderSportService {

    @Resource
    private HydResultOrderSportRepository hydResultOrderSportRepository;

    @Override
    public PageResult<HydResultOrderSport> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultOrderSport> queryAll() {
        return hydResultOrderSportRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultOrderSport save(HydResultOrderSport hydResultOrderSport) {
        return hydResultOrderSportRepository.save(hydResultOrderSport);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultOrderSportRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrderSport update(HydResultOrderSport hydResultOrderSport) {
        if (hydResultOrderSport.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultOrderSportRepository.save(hydResultOrderSport);
    }

    @Override
    public HydResultOrderSport findById(Long id) {
        return hydResultOrderSportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> projectTop5() {
        return hydResultOrderSportRepository.projectTop5();
    }

    /**
     * 批量保存 项目消费券订单金额Top5
     *
     * @param orderSports 项目消费券订单金额Top5 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrderSport> orderSports) {
        // 验证参数
        if (orderSports == null || orderSports.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orderSports.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultOrderSport> savedList = hydResultOrderSportRepository.saveAll(orderSports);
        return savedList.size();
    }
}
