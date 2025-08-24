package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydOriginLaStadiumFile;
import cn.wuhan.hyd.sports.domain.HydOriginLaStadiumFileHistory;
import cn.wuhan.hyd.sports.repository.HydOriginLaStadiumFileHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydOriginLaStadiumFileRepo;
import cn.wuhan.hyd.sports.req.HydOriginLaStadiumFileReq;
import cn.wuhan.hyd.sports.service.IHydOriginLaStadiumFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构附件表业务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@Service
public class HydOriginLaStadiumFileServiceImpl extends HydBaseServiceImpl implements IHydOriginLaStadiumFileService {

    private final Logger logger = LoggerFactory.getLogger(IHydOriginLaStadiumFileService.class);

    @Resource
    private HydOriginLaStadiumFileRepo laStadiumFileRepo;

    @Resource
    private HydOriginLaStadiumFileHistoryRepo laStadiumFileHistoryRepo;

    @Override
    public PageResult<HydOriginLaStadiumFile> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydOriginLaStadiumFile> pageResult = laStadiumFileRepo.findAll(pageable);
        PageResult<HydOriginLaStadiumFile> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydOriginLaStadiumFile> queryAll() {
        return laStadiumFileRepo.findAll();
    }

    @Override
    public HydOriginLaStadiumFile findById(Integer id) {
        return laStadiumFileRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("校外培训机构附件不存在，ID：" + id));
    }

    @Override
    public Map<String, Object> orderStat() {
        return null;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginLaStadiumFile save(HydOriginLaStadiumFile hydOriginLaStadiumFile) {
        // 自动填充创建/更新时间（若需基于当前时间戳）
        if (hydOriginLaStadiumFile.getCreateTime() == null) {
            hydOriginLaStadiumFile.setCreateTime((int) (System.currentTimeMillis() / 1000));
        }
        hydOriginLaStadiumFile.setUpdateTime((int) (System.currentTimeMillis() / 1000));
        return laStadiumFileRepo.save(hydOriginLaStadiumFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(Integer id) {
        laStadiumFileRepo.deleteById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HydOriginLaStadiumFile update(HydOriginLaStadiumFile laStadiumFile) {
        if (laStadiumFile.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 校验附件是否存在
        findById(laStadiumFile.getId());
        return laStadiumFileRepo.save(laStadiumFile);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydOriginLaStadiumFileReq> stadiumFiles) {
        // 验证参数
        if (stadiumFiles == null || stadiumFiles.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }

        // 限制批量导入的最大数量，防止过大数据量导致内存溢出
        if (stadiumFiles.size() > 1000) {
            throw new IllegalArgumentException("单次导入最大支持1000条数据");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginLaStadiumFile> queryList = convert(logger, stadiumFiles, HydOriginLaStadiumFile.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydOriginLaStadiumFileHistory> historyList = convert(logger, stadiumFiles, HydOriginLaStadiumFileHistory.class, batchNo);

        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydOriginLaStadiumFile表，批次号：{}", batchNo);
            laStadiumFileRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    laStadiumFileRepo::saveAll,
                    "HydOriginLaStadiumFile",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    laStadiumFileHistoryRepo::saveAll,
                    "HydOriginLaStadiumFileHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != stadiumFiles.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, stadiumFiles.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumFiles.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
