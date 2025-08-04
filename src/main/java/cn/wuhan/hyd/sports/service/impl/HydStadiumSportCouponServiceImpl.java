package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydStadiumSportCoupon;
import cn.wuhan.hyd.sports.repository.HydStadiumSportCouponRepository;
import cn.wuhan.hyd.sports.service.IHydStadiumSportCouponService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 场馆预定/体育消费卷-运动项目分布用券数占比 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydStadiumSportCouponServiceImpl implements IHydStadiumSportCouponService {

    @Resource
    private HydStadiumSportCouponRepository hydStadiumSportCouponRepository;

    @Override
    public PageResult<HydStadiumSportCoupon> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydStadiumSportCoupon> queryAll() {
        return hydStadiumSportCouponRepository.findAll();
    }

    @Override
    @Transactional
    public HydStadiumSportCoupon save(HydStadiumSportCoupon hydStadiumSportCoupon) {
        return hydStadiumSportCouponRepository.save(hydStadiumSportCoupon);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydStadiumSportCouponRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydStadiumSportCoupon update(HydStadiumSportCoupon hydStadiumSportCoupon) {
        if (hydStadiumSportCoupon.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydStadiumSportCouponRepository.save(hydStadiumSportCoupon);
    }

    @Override
    public HydStadiumSportCoupon findById(Long id) {
        return hydStadiumSportCouponRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 运动项目分布用券数占比
     *
     * @param stadiumSportCoupons 运动项目分布用券数占比 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydStadiumSportCoupon> stadiumSportCoupons) {
        // 验证参数
        if (stadiumSportCoupons == null || stadiumSportCoupons.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumSportCoupons.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydStadiumSportCoupon> savedList = hydStadiumSportCouponRepository.saveAll(stadiumSportCoupons);
        return savedList.size();
    }
}
