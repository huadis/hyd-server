package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydStock;
import cn.wuhan.hyd.sports.repository.HydStockRepository;
import cn.wuhan.hyd.sports.service.IHydStockService;
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
public class HydStockServiceImpl implements IHydStockService {

    @Resource
    private HydStockRepository hydStockRepository;

    @Override
    public PageResult<HydStock> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydStock> queryAll() {
        return hydStockRepository.findAll();
    }

    @Override
    @Transactional
    public HydStock save(HydStock hydStock) {
        return hydStockRepository.save(hydStock);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydStockRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydStock update(HydStock hydStock) {
        if (hydStock.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydStockRepository.save(hydStock);
    }

    @Override
    public HydStock findById(Long id) {
        return hydStockRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 消费券领用券
     *
     * @param stocks 消费券领用券 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydStock> stocks) {
        // 验证参数
        if (stocks == null || stocks.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stocks.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydStock> savedList = hydStockRepository.saveAll(stocks);
        return savedList.size();
    }
}
