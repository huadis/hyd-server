package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultOrderStadium;
import cn.wuhan.hyd.sports.repository.HydResultOrderStadiumRepository;
import cn.wuhan.hyd.sports.service.IHydResultOrderStadiumService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 体育消费卷-场馆消费券订单金额Top5 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultOrderStadiumServiceImpl implements IHydResultOrderStadiumService {

    @Resource
    private HydResultOrderStadiumRepository hydResultOrderStadiumRepository;

    @Override
    public PageResult<HydResultOrderStadium> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultOrderStadium> queryAll() {
        return hydResultOrderStadiumRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultOrderStadium save(HydResultOrderStadium hydResultOrderStadium) {
        return hydResultOrderStadiumRepository.save(hydResultOrderStadium);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultOrderStadiumRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultOrderStadium update(HydResultOrderStadium hydResultOrderStadium) {
        if (hydResultOrderStadium.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultOrderStadiumRepository.save(hydResultOrderStadium);
    }

    @Override
    public HydResultOrderStadium findById(Long id) {
        return hydResultOrderStadiumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 场馆消费券订单金额Top5
     *
     * @param orderStadiums 场馆消费券订单金额Top5 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultOrderStadium> orderStadiums) {
        // 验证参数
        if (orderStadiums == null || orderStadiums.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orderStadiums.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultOrderStadium> savedList = hydResultOrderStadiumRepository.saveAll(orderStadiums);
        return savedList.size();
    }
}
