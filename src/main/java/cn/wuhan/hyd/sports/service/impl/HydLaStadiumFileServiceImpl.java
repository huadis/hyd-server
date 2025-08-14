package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydLaStadiumFile;
import cn.wuhan.hyd.sports.repository.HydLaStadiumFileRepo;
import cn.wuhan.hyd.sports.service.IHydLaStadiumFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构附件表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
@RequiredArgsConstructor
public class HydLaStadiumFileServiceImpl implements IHydLaStadiumFileService {

    private final HydLaStadiumFileRepo hydLaStadiumFileRepo;

    @Override
    public PageResult<HydLaStadiumFile> queryAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<HydLaStadiumFile> queryAll() {
        return hydLaStadiumFileRepo.findAll();
    }

    @Override
    public HydLaStadiumFile findById(Integer id) {
        return hydLaStadiumFileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("校外培训机构附件不存在，ID：" + id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydLaStadiumFile save(HydLaStadiumFile hydLaStadiumFile) {
        // 自动填充创建/更新时间（若需基于当前时间戳）
        if (hydLaStadiumFile.getCreateTime() == null) {
            hydLaStadiumFile.setCreateTime((int) (System.currentTimeMillis() / 1000));
        }
        hydLaStadiumFile.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return hydLaStadiumFileRepo.save(hydLaStadiumFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydLaStadiumFile> hydLaStadiumFiles) {
        int currentTime = (int) (System.currentTimeMillis() / 1000);
        hydLaStadiumFiles.forEach(file -> {
            if (file.getCreateTime() == null) {
                file.setCreateTime(currentTime);
            }
            file.setUpdateTime(currentTime);
        });
        List<HydLaStadiumFile> saved = hydLaStadiumFileRepo.saveAll(hydLaStadiumFiles);
        return saved.size();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        hydLaStadiumFileRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydLaStadiumFile update(HydLaStadiumFile hydLaStadiumFile) {
        // 校验附件是否存在
        findById(hydLaStadiumFile.getId());
        // 更新时间戳
        hydLaStadiumFile.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return hydLaStadiumFileRepo.save(hydLaStadiumFile);
    }

    @Override
    public Map<String, Object> orderStat() {
        // 如需实现统计功能，可在此补充（如附件数量、关联场馆统计等）
        throw new UnsupportedOperationException("统计功能待实现");
    }
}
