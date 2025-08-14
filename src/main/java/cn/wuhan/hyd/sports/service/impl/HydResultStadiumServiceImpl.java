package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStadium;
import cn.wuhan.hyd.sports.repository.HydResultStadiumRepository;
import cn.wuhan.hyd.sports.service.IHydResultStadiumService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 场馆预定-在线场馆数量 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStadiumServiceImpl implements IHydResultStadiumService {

    @Resource
    private HydResultStadiumRepository hydResultStadiumRepository;

    @Override
    public PageResult<HydResultStadium> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydResultStadium> queryAll() {
        return hydResultStadiumRepository.findAll();
    }

    @Override
    @Transactional
    public HydResultStadium save(HydResultStadium hydResultStadium) {
        return hydResultStadiumRepository.save(hydResultStadium);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        hydResultStadiumRepository.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadium update(HydResultStadium hydResultStadium) {
        if (hydResultStadium.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        return hydResultStadiumRepository.save(hydResultStadium);
    }

    @Override
    public HydResultStadium findById(Long id) {
        return hydResultStadiumRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 在线场馆数量
     *
     * @param stadiums 在线场馆数量 列表
     * @return 保存成功的记录数
     */
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadium> stadiums) {
        // 验证参数
        if (stadiums == null || stadiums.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiums.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }

        // 批量保存
        List<HydResultStadium> savedList = hydResultStadiumRepository.saveAll(stadiums);
        return savedList.size();
    }
}
