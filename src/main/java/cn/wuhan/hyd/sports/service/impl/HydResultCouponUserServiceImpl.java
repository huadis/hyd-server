package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultCouponUser;
import cn.wuhan.hyd.sports.repository.HydResultCouponUserRepository;
import cn.wuhan.hyd.sports.service.IHydResultCouponUserService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育消费卷-券用户分析 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultCouponUserServiceImpl implements IHydResultCouponUserService {

    @Resource
    private HydResultCouponUserRepository hydResultCouponUserRepository;

    @Override
    public PageResult<HydResultCouponUser> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultCouponUser> queryAll() {
        return hydResultCouponUserRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultCouponUser save(HydResultCouponUser hydResultCouponUser) {
        return hydResultCouponUserRepository.save(hydResultCouponUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultCouponUserRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultCouponUser update(HydResultCouponUser hydResultCouponUser) {
        if (hydResultCouponUser.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultCouponUserRepository.save(hydResultCouponUser);
    }

    @Override
    public HydResultCouponUser findById(Long id) {
        return hydResultCouponUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 券用户分析
     *
     * @param couponUsers 券用户分析 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultCouponUser> couponUsers) {
        // 验证参数
        if (couponUsers == null || couponUsers.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (couponUsers.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultCouponUser> savedList = hydResultCouponUserRepository.saveAll(couponUsers);
        return savedList.size();
    }
}
