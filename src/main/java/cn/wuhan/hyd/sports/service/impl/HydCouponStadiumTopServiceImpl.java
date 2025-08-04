package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydCouponStadiumTop;
import cn.wuhan.hyd.sports.repository.HydCouponStadiumTopRepository;
import cn.wuhan.hyd.sports.service.IHydCouponStadiumTopService;
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
public class HydCouponStadiumTopServiceImpl implements IHydCouponStadiumTopService {

    @Resource
    private HydCouponStadiumTopRepository hydCouponStadiumTopRepository;

    @Override
    public PageResult<HydCouponStadiumTop> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydCouponStadiumTop> queryAll() {
        return hydCouponStadiumTopRepository.findAll();
    }

    @Override
    @Transactional
    public HydCouponStadiumTop save(HydCouponStadiumTop hydCouponStadiumTop) {
        return hydCouponStadiumTopRepository.save(hydCouponStadiumTop);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydCouponStadiumTopRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydCouponStadiumTop update(HydCouponStadiumTop hydCouponStadiumTop) {
        if (hydCouponStadiumTop.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydCouponStadiumTopRepository.save(hydCouponStadiumTop);
    }

    @Override
    public HydCouponStadiumTop findById(Long id) {
        return hydCouponStadiumTopRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    @Override
    public List<Map<String, Object>> stadiumTop5() {
        return hydCouponStadiumTopRepository.stadiumTop5();
    }

    /**
     * 批量保存 消费券场馆预订Top
     *
     * @param couponStadiumTops 消费券场馆预订Top 数据列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydCouponStadiumTop> couponStadiumTops) {
        // 验证参数
        if (couponStadiumTops == null || couponStadiumTops.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponStadiumTops.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydCouponStadiumTop> savedList = hydCouponStadiumTopRepository.saveAll(couponStadiumTops);
        return savedList.size();
    }
}

