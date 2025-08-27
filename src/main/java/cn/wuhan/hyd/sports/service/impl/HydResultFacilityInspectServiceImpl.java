package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultFacilityInspect;
import cn.wuhan.hyd.sports.domain.HydResultFacilityInspectHistory;
import cn.wuhan.hyd.sports.repository.HydResultFacilityInspectHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultFacilityInspectRepo;
import cn.wuhan.hyd.sports.req.HydResultFacilityInspectReq;
import cn.wuhan.hyd.sports.service.IHydResultFacilityInspectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 功能说明： 体育基础设施-巡检动态 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultFacilityInspectServiceImpl extends HydBaseServiceImpl implements IHydResultFacilityInspectService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultFacilityInspectService.class);

    @Resource
    private HydResultFacilityInspectRepo facilityInspectRepo;
    @Resource
    private HydResultFacilityInspectHistoryRepo facilityInspectHistoryRepo;

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    @Override
    public PageResult<HydResultFacilityInspect> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultFacilityInspect> pageResult = facilityInspectRepo.findAll(pageable);
        PageResult<HydResultFacilityInspect> pageResult1 = new PageResult<>();
        pageResult1.setTotalElements(pageResult.getTotalElements());
        pageResult1.setContent(pageResult.getContent());
        return pageResult1;
    }

    @Override
    public PageResult<HydResultFacilityInspect> queryAll(String year, int page, int size) {
        int yearInt = Integer.parseInt(year);
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        // 2. 构建查询条件（Specification）
        Specification<HydResultFacilityInspect> spec = (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            LocalDateTime startOf = LocalDateTime.of(yearInt, 1, 1, 0, 0, 0);
            Date startDate = Date.from(startOf.atZone(ZoneId.systemDefault()).toInstant());
            LocalDateTime endOf = LocalDateTime.of(yearInt, 12, 31, 23, 59, 59, 999000000);
            Date endDate = Date.from(endOf.atZone(ZoneId.systemDefault()).toInstant());

            predicates.add(cb.between(root.get("createdTime"), startDate, endDate));

            // 将所有条件组合起来
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        Page<HydResultFacilityInspect> pageResult = facilityInspectRepo.findAll(spec, pageable);
        PageResult<HydResultFacilityInspect> pageResult1 = new PageResult<>();
        pageResult1.setTotalElements(pageResult.getTotalElements());
        pageResult1.setContent(pageResult.getContent());
        return pageResult1;
    }

    @Override
    public List<HydResultFacilityInspect> queryAll() {
        return facilityInspectRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultFacilityInspect save(HydResultFacilityInspect hydResultFacilityInspect) {
        return facilityInspectRepo.save(hydResultFacilityInspect);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        facilityInspectRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultFacilityInspect update(HydResultFacilityInspect facilityInspect) {
        if (facilityInspect.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(facilityInspect.getId());
        return facilityInspectRepo.save(facilityInspect);
    }

    @Override
    public HydResultFacilityInspect findById(Long id) {
        return facilityInspectRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 批量保存 巡检动态
     *
     * @param facilityInspects 巡检动态 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultFacilityInspectReq> facilityInspects) {
        // 验证参数
        if (facilityInspects == null || facilityInspects.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityInspect> queryList = convert(logger, facilityInspects, HydResultFacilityInspect.class, batchNo);
        // 数据转换：Stream流+异常封装, 提前转换失败直接终止
        List<HydResultFacilityInspectHistory> historyList = convert(logger, facilityInspects, HydResultFacilityInspectHistory.class, batchNo);
        try {
            // 4. 清空查询表：日志记录操作意图，便于问题追溯
            logger.info("【批量保存】开始清空HydResultFacilityInspect表，批次号：{}", batchNo);
            facilityInspectRepo.deleteAll();

            // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
            int querySaveCount = saveAndLog(
                    logger,
                    queryList,
                    facilityInspectRepo::saveAll,
                    "HydResultFacilityInspect",
                    batchNo
            );

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    facilityInspectHistoryRepo::saveAll,
                    "HydResultFacilityInspectHistory",
                    batchNo
            );

            // 7. 校验保存结果：确保双表保存数量一致，避免数据不一致
            if (querySaveCount != historySaveCount || querySaveCount != facilityInspects.size()) {
                throw new RuntimeException(
                        String.format("【批量保存】数据保存数量不一致，批次号：%s，原数据量：%d，查询表保存量：%d，历史表保存量：%d",
                                batchNo, facilityInspects.size(), querySaveCount, historySaveCount)
                );
            }

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return querySaveCount; // 返回实际保存数量，而非固定100，更具业务意义

        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, facilityInspects.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }
}
