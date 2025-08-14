package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import cn.wuhan.hyd.sports.repository.HydResultCouponAmountRepository;
import cn.wuhan.hyd.sports.service.IHydResultCouponAmountService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育消费卷-消费券总金额 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponAmountServiceImpl implements IHydResultCouponAmountService {

    @Resource
    private HydResultCouponAmountRepository hydResultCouponAmountRepository;

    @Override
    public PageResult<HydResultCouponAmount> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultCouponAmount> queryAll() {
        return hydResultCouponAmountRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponAmount save(HydResultCouponAmount hydResultCouponAmount) {
        return hydResultCouponAmountRepository.save(hydResultCouponAmount);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultCouponAmountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponAmount update(HydResultCouponAmount hydResultCouponAmount) {
        if (hydResultCouponAmount.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultCouponAmountRepository.save(hydResultCouponAmount);
    }

    @Override
    public HydResultCouponAmount findById(Long id) {
        return hydResultCouponAmountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 消费券总金额
     *
     * @param couponAmounts 消费券总金额 数据列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponAmount> couponAmounts) {
        // 验证参数
        if (couponAmounts == null || couponAmounts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponAmounts.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultCouponAmount> savedList = hydResultCouponAmountRepository.saveAll(couponAmounts);
        return savedList.size();
    }

    @Override
    public HydResultCouponAmount findLatestCouponAmount() {
        return hydResultCouponAmountRepository.findLatestCouponAmount();
    }
}
