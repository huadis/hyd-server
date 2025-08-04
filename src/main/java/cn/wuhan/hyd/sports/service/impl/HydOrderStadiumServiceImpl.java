package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOrderStadium;
import cn.wuhan.hyd.sports.repository.HydOrderStadiumRepository;
import cn.wuhan.hyd.sports.service.IHydOrderStadiumService;
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
public class HydOrderStadiumServiceImpl implements IHydOrderStadiumService {

    @Resource
    private HydOrderStadiumRepository hydOrderStadiumRepository;

    @Override
    public PageResult<HydOrderStadium> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydOrderStadium> queryAll() {
        return hydOrderStadiumRepository.findAll();
    }

    @Override
    @Transactional
    public HydOrderStadium save(HydOrderStadium hydOrderStadium) {
        return hydOrderStadiumRepository.save(hydOrderStadium);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydOrderStadiumRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydOrderStadium update(HydOrderStadium hydOrderStadium) {
        if (hydOrderStadium.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydOrderStadiumRepository.save(hydOrderStadium);
    }

    @Override
    public HydOrderStadium findById(Long id) {
        return hydOrderStadiumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 场馆消费券订单金额Top5
     *
     * @param orderStadiums 场馆消费券订单金额Top5 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOrderStadium> orderStadiums) {
        // 验证参数
        if (orderStadiums == null || orderStadiums.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (orderStadiums.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydOrderStadium> savedList = hydOrderStadiumRepository.saveAll(orderStadiums);
        return savedList.size();
    }
}
