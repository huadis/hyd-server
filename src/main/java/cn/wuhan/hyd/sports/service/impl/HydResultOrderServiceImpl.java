package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultOrder;
import cn.wuhan.hyd.sports.repository.HydResultOrderRepository;
import cn.wuhan.hyd.sports.service.IHydResultOrderService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定-订单数量 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderServiceImpl implements IHydResultOrderService {

    @Resource
    private HydResultOrderRepository hydResultOrderRepository;

    @Override
    public PageResult<HydResultOrder> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultOrder> queryAll() {
        return hydResultOrderRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultOrder save(HydResultOrder hydResultOrder) {
        return hydResultOrderRepository.save(hydResultOrder);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultOrderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrder update(HydResultOrder hydResultOrder) {
        if (hydResultOrder.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultOrderRepository.save(hydResultOrder);
    }

    @Override
    public HydResultOrder findById(Long id) {
        return hydResultOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public Map<String, Object> orderStat() {
        Map<String, Object> result = new HashMap<>();
        result.put("sumOrderNum", hydResultOrderRepository.sumOrderNum());
        result.put("sumOrderAmount", hydResultOrderRepository.sumOrderAmount());
        result.put("monthOrderStat", hydResultOrderRepository.orderStat());
        return result;
    }

    /**
     * 批量保存 订单数量
     *
     * @param orders 订单数量 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrder> orders) {
        // 验证参数
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orders.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultOrder> savedList = hydResultOrderRepository.saveAll(orders);
        return savedList.size();
    }
}
