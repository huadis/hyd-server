package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydCouponUserAge;
import cn.wuhan.hyd.sports.repository.HydCouponUserAgeRepository;
import cn.wuhan.hyd.sports.service.IHydCouponUserAgeService;
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
public class HydCouponUserAgeServiceImpl implements IHydCouponUserAgeService {

    @Resource
    private HydCouponUserAgeRepository hydCouponUserAgeRepository;

    @Override
    public PageResult<HydCouponUserAge> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydCouponUserAge> queryAll() {
        return hydCouponUserAgeRepository.findAll();
    }

    @Override
    @Transactional
    public HydCouponUserAge save(HydCouponUserAge hydCouponUserAge) {
        return hydCouponUserAgeRepository.save(hydCouponUserAge);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydCouponUserAgeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydCouponUserAge update(HydCouponUserAge hydCouponUserAge) {
        if (hydCouponUserAge.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydCouponUserAgeRepository.save(hydCouponUserAge);
    }

    @Override
    public HydCouponUserAge findById(Long id) {
        return hydCouponUserAgeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 券用户年龄分布
     *
     * @param couponUserAges 券用户年龄分布 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydCouponUserAge> couponUserAges) {
        // 验证参数
        if (couponUserAges == null || couponUserAges.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponUserAges.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydCouponUserAge> savedList = hydCouponUserAgeRepository.saveAll(couponUserAges);
        return savedList.size();
    }
}
