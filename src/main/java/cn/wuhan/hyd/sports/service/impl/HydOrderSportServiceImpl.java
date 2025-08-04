package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOrderSport;
import cn.wuhan.hyd.sports.repository.HydOrderSportRepository;
import cn.wuhan.hyd.sports.service.IHydOrderSportService;
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
public class HydOrderSportServiceImpl implements IHydOrderSportService {

    @Resource
    private HydOrderSportRepository hydOrderSportRepository;

    @Override
    public PageResult<HydOrderSport> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydOrderSport> queryAll() {
        return hydOrderSportRepository.findAll();
    }

    @Override
    @Transactional
    public HydOrderSport save(HydOrderSport hydOrderSport) {
        return hydOrderSportRepository.save(hydOrderSport);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydOrderSportRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydOrderSport update(HydOrderSport hydOrderSport) {
        if (hydOrderSport.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydOrderSportRepository.save(hydOrderSport);
    }

    @Override
    public HydOrderSport findById(Long id) {
        return hydOrderSportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> projectTop5() {
        return hydOrderSportRepository.projectTop5();
    }

    /**
     * 批量保存 项目消费券订单金额Top5
     *
     * @param orderSports 项目消费券订单金额Top5 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOrderSport> orderSports) {
        // 验证参数
        if (orderSports == null || orderSports.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orderSports.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydOrderSport> savedList = hydOrderSportRepository.saveAll(orderSports);
        return savedList.size();
    }
}
