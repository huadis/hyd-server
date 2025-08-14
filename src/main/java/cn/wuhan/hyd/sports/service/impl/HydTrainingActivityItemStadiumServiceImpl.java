package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingActivityItemStadium;
import cn.wuhan.hyd.sports.repository.HydTrainingActivityItemStadiumRepo;
import cn.wuhan.hyd.sports.service.IHydTrainingActivityItemStadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能说明： 培训活动场馆支持的项目表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydTrainingActivityItemStadiumServiceImpl implements IHydTrainingActivityItemStadiumService {

    private final HydTrainingActivityItemStadiumRepo hydTrainingActivityItemStadiumRepo;

    @Override
    public PageResult<HydTrainingActivityItemStadium> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydTrainingActivityItemStadium> queryAll() {
        return hydTrainingActivityItemStadiumRepo.findAll();
    }

    @Override
    public HydTrainingActivityItemStadium findById(String id) {
        return hydTrainingActivityItemStadiumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("培训活动场馆支持的项目不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingActivityItemStadium save(HydTrainingActivityItemStadium hydTrainingActivityItemStadium) {
        return hydTrainingActivityItemStadiumRepo.save(hydTrainingActivityItemStadium);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydTrainingActivityItemStadium> hydTrainingActivityItemStadiums) {
        List<HydTrainingActivityItemStadium> saved = hydTrainingActivityItemStadiumRepo.saveAll(hydTrainingActivityItemStadiums);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        hydTrainingActivityItemStadiumRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingActivityItemStadium update(HydTrainingActivityItemStadium hydTrainingActivityItemStadium) {
        // 校验数据是否存在
        findById(hydTrainingActivityItemStadium.getId());
        return hydTrainingActivityItemStadiumRepo.save(hydTrainingActivityItemStadium);
    }
}
