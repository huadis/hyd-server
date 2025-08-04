package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOrder;
import cn.wuhan.hyd.sports.repository.HydOrderRepository;
import cn.wuhan.hyd.sports.service.IHydOrderService;
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
public class HydOrderServiceImpl implements IHydOrderService {

    @Resource
    private HydOrderRepository hydOrderRepository;

    @Override
    public PageResult<HydOrder> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydOrder> queryAll() {
        return hydOrderRepository.findAll();
    }

    @Override
    @Transactional
    public HydOrder save(HydOrder hydOrder) {
        return hydOrderRepository.save(hydOrder);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydOrderRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydOrder update(HydOrder hydOrder) {
        if (hydOrder.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydOrderRepository.save(hydOrder);
    }

    @Override
    public HydOrder findById(Long id) {
        return hydOrderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public Map<String, Object> orderStat() {
        Map<String, Object> result = new HashMap<>();
        result.put("sumOrderNum", hydOrderRepository.sumOrderNum());
        result.put("sumOrderAmount", hydOrderRepository.sumOrderAmount());
        result.put("monthOrderStat", hydOrderRepository.orderStat());
        return result;
    }

    /**
     * 批量保存 订单数量
     *
     * @param orders 订单数量 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOrder> orders) {
        // 验证参数
        if (orders == null || orders.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orders.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydOrder> savedList = hydOrderRepository.saveAll(orders);
        return savedList.size();
    }
}
