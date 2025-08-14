package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOrder;
import cn.wuhan.hyd.sports.repository.HydOrderRepo;
import cn.wuhan.hyd.sports.service.IHydOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 订单表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydOrderServiceImpl implements IHydOrderService {

    private final HydOrderRepo hydOrderRepo;

    @Override
    public PageResult<HydOrder> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydOrder> queryAll() {
        return hydOrderRepo.findAll();
    }

    @Override
    public HydOrder findById(String id) {
        return hydOrderRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("订单不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOrder save(HydOrder hydOrder) {
        return hydOrderRepo.save(hydOrder);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOrder> hydOrders) {
        List<HydOrder> saved = hydOrderRepo.saveAll(hydOrders);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        hydOrderRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOrder update(HydOrder hydOrder) {
        // 校验订单是否存在
        findById(hydOrder.getId());
        return hydOrderRepo.save(hydOrder);
    }

    @Override
    public Map<String, Object> orderStat() {
        // 若需实现订单统计逻辑，可在此补充（如基于JPA查询或原生SQL统计订单金额、数量等）
        throw new UnsupportedOperationException("订单统计功能待实现");
    }
}
