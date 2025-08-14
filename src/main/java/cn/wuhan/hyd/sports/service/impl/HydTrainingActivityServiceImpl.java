package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingActivity;
import cn.wuhan.hyd.sports.repository.HydTrainingActivityRepo;
import cn.wuhan.hyd.sports.service.IHydTrainingActivityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能说明： 培训活动表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydTrainingActivityServiceImpl implements IHydTrainingActivityService {

    private final HydTrainingActivityRepo hydTrainingActivityRepository;

    @Override
    public PageResult<HydTrainingActivity> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydTrainingActivity> queryAll() {
        return hydTrainingActivityRepository.findAll();
    }

    @Override
    public HydTrainingActivity findById(String id) {
        return hydTrainingActivityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("培训活动不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingActivity save(HydTrainingActivity hydTrainingActivity) {
        return hydTrainingActivityRepository.save(hydTrainingActivity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydTrainingActivity> hydTrainingActivities) {
        List<HydTrainingActivity> saved = hydTrainingActivityRepository.saveAll(hydTrainingActivities);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        hydTrainingActivityRepository.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingActivity update(HydTrainingActivity hydTrainingActivity) {
        // 先校验是否存在
        findById(hydTrainingActivity.getId());
        return hydTrainingActivityRepository.save(hydTrainingActivity);
    }
}
