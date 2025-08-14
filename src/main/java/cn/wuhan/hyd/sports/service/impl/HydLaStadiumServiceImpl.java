package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydLaStadium;
import cn.wuhan.hyd.sports.repository.HydLaStadiumRepo;
import cn.wuhan.hyd.sports.service.IHydLaStadiumService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydLaStadiumServiceImpl implements IHydLaStadiumService {

    private final HydLaStadiumRepo hydLaStadiumRepo;

    @Override
    public PageResult<HydLaStadium> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydLaStadium> queryAll() {
        return hydLaStadiumRepo.findAll();
    }

    @Override
    public HydLaStadium findById(Integer id) {
        return hydLaStadiumRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("校外培训机构不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydLaStadium save(HydLaStadium hydLaStadium) {
        return hydLaStadiumRepo.save(hydLaStadium);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydLaStadium> hydLaStadiums) {
        List<HydLaStadium> saved = hydLaStadiumRepo.saveAll(hydLaStadiums);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        hydLaStadiumRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydLaStadium update(HydLaStadium hydLaStadium) {
        // 校验是否存在
        findById(hydLaStadium.getId());
        return hydLaStadiumRepo.save(hydLaStadium);
    }

    @Override
    public Map<String, Object> orderStat() {
        // 如需实现统计功能，可在此补充
        throw new UnsupportedOperationException("统计功能待实现");
    }
}
