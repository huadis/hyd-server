package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOrderMonth;
import cn.wuhan.hyd.sports.repository.HydOrderMonthRepository;
import cn.wuhan.hyd.sports.service.IHydOrderMonthService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育消费卷-订单趋势 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydOrderMonthServiceImpl implements IHydOrderMonthService {

    @Resource
    private HydOrderMonthRepository hydOrderMonthRepository;

    @Override
    public PageResult<HydOrderMonth> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydOrderMonth> queryAll() {
        return hydOrderMonthRepository.findAll();
    }

    @Override
    @Transactional
    public HydOrderMonth save(HydOrderMonth hydOrderMonth) {
        return hydOrderMonthRepository.save(hydOrderMonth);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydOrderMonthRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydOrderMonth update(HydOrderMonth hydOrderMonth) {
        if (hydOrderMonth.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydOrderMonthRepository.save(hydOrderMonth);
    }

    @Override
    public HydOrderMonth findById(Long id) {
        return hydOrderMonthRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 订单趋势
     *
     * @param orderMonths 订单趋势 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOrderMonth> orderMonths) {
        // 验证参数
        if (orderMonths == null || orderMonths.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orderMonths.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydOrderMonth> savedList = hydOrderMonthRepository.saveAll(orderMonths);
        return savedList.size();
    }
}
