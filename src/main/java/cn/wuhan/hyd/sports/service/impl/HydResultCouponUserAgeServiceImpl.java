package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponUserAge;
import cn.wuhan.hyd.sports.repository.HydResultCouponUserAgeRepository;
import cn.wuhan.hyd.sports.service.IHydResultCouponUserAgeService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育消费卷-券用户年龄分布 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponUserAgeServiceImpl implements IHydResultCouponUserAgeService {

    @Resource
    private HydResultCouponUserAgeRepository hydResultCouponUserAgeRepository;

    @Override
    public PageResult<HydResultCouponUserAge> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultCouponUserAge> queryAll() {
        return hydResultCouponUserAgeRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponUserAge save(HydResultCouponUserAge hydResultCouponUserAge) {
        return hydResultCouponUserAgeRepository.save(hydResultCouponUserAge);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultCouponUserAgeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponUserAge update(HydResultCouponUserAge hydResultCouponUserAge) {
        if (hydResultCouponUserAge.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultCouponUserAgeRepository.save(hydResultCouponUserAge);
    }

    @Override
    public HydResultCouponUserAge findById(Long id) {
        return hydResultCouponUserAgeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 券用户年龄分布
     *
     * @param couponUserAges 券用户年龄分布 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponUserAge> couponUserAges) {
        // 验证参数
        if (couponUserAges == null || couponUserAges.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponUserAges.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultCouponUserAge> savedList = hydResultCouponUserAgeRepository.saveAll(couponUserAges);
        return savedList.size();
    }
}
