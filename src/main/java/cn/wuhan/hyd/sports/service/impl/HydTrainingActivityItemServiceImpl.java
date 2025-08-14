package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingActivityItem;
import cn.wuhan.hyd.sports.repository.HydTrainingActivityItemRepo;
import cn.wuhan.hyd.sports.service.IHydTrainingActivityItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 功能说明： 培训活动支持的项目表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydTrainingActivityItemServiceImpl implements IHydTrainingActivityItemService {

    private final HydTrainingActivityItemRepo hydTrainingActivityItemRepo;

    @Override
    public PageResult<HydTrainingActivityItem> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydTrainingActivityItem> queryAll() {
        return hydTrainingActivityItemRepo.findAll();
    }

    @Override
    public HydTrainingActivityItem findById(String id) {
        return hydTrainingActivityItemRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("培训活动支持的项目不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingActivityItem save(HydTrainingActivityItem hydTrainingActivityItem) {
        return hydTrainingActivityItemRepo.save(hydTrainingActivityItem);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydTrainingActivityItem> hydTrainingActivityItems) {
        List<HydTrainingActivityItem> saved = hydTrainingActivityItemRepo.saveAll(hydTrainingActivityItems);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        hydTrainingActivityItemRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydTrainingActivityItem update(HydTrainingActivityItem hydTrainingActivityItem) {
        // 先校验数据是否存在
        findById(hydTrainingActivityItem.getId());
        return hydTrainingActivityItemRepo.save(hydTrainingActivityItem);
    }
}
