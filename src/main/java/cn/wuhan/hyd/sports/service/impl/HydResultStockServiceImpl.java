package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStock;
import cn.wuhan.hyd.sports.repository.HydResultStockRepository;
import cn.wuhan.hyd.sports.service.IHydResultStockService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育消费卷-消费券领用券 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStockServiceImpl implements IHydResultStockService {

    @Resource
    private HydResultStockRepository hydResultStockRepository;

    @Override
    public PageResult<HydResultStock> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultStock> queryAll() {
        return hydResultStockRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultStock save(HydResultStock hydResultStock) {
        return hydResultStockRepository.save(hydResultStock);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultStockRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStock update(HydResultStock hydResultStock) {
        if (hydResultStock.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultStockRepository.save(hydResultStock);
    }

    @Override
    public HydResultStock findById(Long id) {
        return hydResultStockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 消费券领用券
     *
     * @param stocks 消费券领用券 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStock> stocks) {
        // 验证参数
        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stocks.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultStock> savedList = hydResultStockRepository.saveAll(stocks);
        return savedList.size();
    }
}
