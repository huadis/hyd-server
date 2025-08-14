package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponStadiumTop;
import cn.wuhan.hyd.sports.repository.HydResultCouponStadiumTopRepository;
import cn.wuhan.hyd.sports.service.IHydResultCouponStadiumTopService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定/体育消费卷-消费券场馆预订Top 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponStadiumTopServiceImpl implements IHydResultCouponStadiumTopService {

    @Resource
    private HydResultCouponStadiumTopRepository hydResultCouponStadiumTopRepository;

    @Override
    public PageResult<HydResultCouponStadiumTop> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultCouponStadiumTop> queryAll() {
        return hydResultCouponStadiumTopRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponStadiumTop save(HydResultCouponStadiumTop hydResultCouponStadiumTop) {
        return hydResultCouponStadiumTopRepository.save(hydResultCouponStadiumTop);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultCouponStadiumTopRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponStadiumTop update(HydResultCouponStadiumTop hydResultCouponStadiumTop) {
        if (hydResultCouponStadiumTop.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultCouponStadiumTopRepository.save(hydResultCouponStadiumTop);
    }

    @Override
    public HydResultCouponStadiumTop findById(Long id) {
        return hydResultCouponStadiumTopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> stadiumTop5() {
        return hydResultCouponStadiumTopRepository.stadiumTop5();
    }

    /**
     * 批量保存 消费券场馆预订Top
     *
     * @param couponStadiumTops 消费券场馆预订Top 数据列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponStadiumTop> couponStadiumTops) {
        // 验证参数
        if (couponStadiumTops == null || couponStadiumTops.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponStadiumTops.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultCouponStadiumTop> savedList = hydResultCouponStadiumTopRepository.saveAll(couponStadiumTops);
        return savedList.size();
    }
}

