package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.framework.utils.UUIDUtil;
import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;
import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrictHistory;
import cn.wuhan.hyd.sports.repository.HydResultStadiumDistrictHistoryRepo;
import cn.wuhan.hyd.sports.repository.HydResultStadiumDistrictRepo;
import cn.wuhan.hyd.sports.req.HydResultStadiumDistrictReq;
import cn.wuhan.hyd.sports.service.IHydResultStadiumDistrictService;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能说明： 场馆预定-在线场馆各区情况 服务实现 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@Service
public class HydResultStadiumDistrictServiceImpl extends HydBaseServiceImpl implements IHydResultStadiumDistrictService {

    private final Logger logger = LoggerFactory.getLogger(IHydResultStadiumDistrictService.class);

    @Resource
    private HydResultStadiumDistrictRepo stadiumDistrictRepo;
    @Resource
    private HydResultStadiumDistrictHistoryRepo stadiumDistrictHistoryRepo;
    @Resource
    private IHydSysConfigService configService;

    @Override
    public PageResult<HydResultStadiumDistrict> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultStadiumDistrict> pageResult = stadiumDistrictRepo.findAll(pageable);
        PageResult<HydResultStadiumDistrict> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultStadiumDistrict> queryAll() {
        return stadiumDistrictRepo.findAll();
    }

    @Override
    @Transactional
    public HydResultStadiumDistrict save(HydResultStadiumDistrict hydResultStadiumDistrict) {
        return stadiumDistrictRepo.save(hydResultStadiumDistrict);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        stadiumDistrictRepo.deleteById(id);
    }

    @Override
    @Transactional
    public HydResultStadiumDistrict update(HydResultStadiumDistrict stadiumDistrict) {
        if (stadiumDistrict.getId() == null) {
            throw new IllegalArgumentException("更新操作必须提供ID");
        }
        // 先校验数据是否存在
        findById(stadiumDistrict.getId());
        return stadiumDistrictRepo.save(stadiumDistrict);
    }

    @Override
    public HydResultStadiumDistrict findById(Long id) {
        return stadiumDistrictRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("未找到ID为" + id + "的记录"));
    }

    /**
     * 统计各区场馆数量
     *
     * @return 包含区场馆统计数据列表，包含区名称及场馆数量
     */
    @Override
    public List<HydResultStadiumDistrict> countStadiumDistrict(String year) {
        return stadiumDistrictRepo.countStadiumDistrict(year);
    }

    /**
     * 批量保存 在线场馆各区情况
     *
     * @param stadiumDistricts 在线场馆各区情况 列表
     * @return 保存成功的记录数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int batchSave(List<HydResultStadiumDistrictReq> stadiumDistricts) {
        // 验证参数
        if (stadiumDistricts == null || stadiumDistricts.isEmpty()) {
            throw new IllegalArgumentException("导入的数据列表不能为空");
        }
        String batchNo = UUIDUtil.getBatchNo();
        List<HydResultStadiumDistrictHistory> historyList = convert(logger, stadiumDistricts, HydResultStadiumDistrictHistory.class, batchNo);
        try {
            int querySaveCount = 0;
            boolean refresh = !configService.notRefresh("场馆预定");
            // 是否冻结，不允许更新查询表
            if (refresh) {
                List<HydResultStadiumDistrict> queryList = computeQueryList(stadiumDistricts, batchNo);
                // 4. 清空查询表：日志记录操作意图，便于问题追溯
                logger.info("【批量保存】开始清空HydResultStadiumDistrict表，批次号：{}", batchNo);
                stadiumDistrictRepo.deleteByNotBatchNo(batchNo, DateUtil.getPreviousDayYear());

                // 5. 保存查询表：统一时间统计工具，日志包含批次号和数据量
                querySaveCount = saveAndLog(
                        logger,
                        queryList,
                        stadiumDistrictRepo::saveAll,
                        "HydResultStadiumDistrict",
                        batchNo
                );
            }

            // 6. 保存历史表：复用时间统计逻辑，避免代码冗余
            int historySaveCount = saveAndLog(
                    logger,
                    historyList,
                    stadiumDistrictHistoryRepo::saveAll,
                    "HydResultStadiumDistrictHistory",
                    batchNo
            );

            // 7. 校验保存结果：根据 refresh 状态区分校验逻辑，避免数据不一致
            checkSaveData(stadiumDistricts, refresh, querySaveCount, historySaveCount, batchNo);

            logger.info("【批量保存】批次数据同步完成，批次号：{}，共保存{}条数据", batchNo, querySaveCount);
            return historySaveCount;
        } catch (Exception e) {
            // 8. 异常处理：补充上下文信息，便于定位问题；抛出异常触发事务回滚
            logger.error("【批量保存】批次数据同步失败，批次号：{}，原数据量：{}，异常信息：",
                    batchNo, stadiumDistricts.size(), e);
            throw new RuntimeException(String.format("【批量保存】批次%s同步失败", batchNo), e);
        }
    }

    private static List<HydResultStadiumDistrict> computeQueryList(List<HydResultStadiumDistrictReq> stadiumDistricts, String batchNo) {
        List<HydResultStadiumDistrict> queryList = new ArrayList<>();
        for (HydResultStadiumDistrictReq stadiumDistrict : stadiumDistricts) {
            String couponStadiumNum = stadiumDistrict.getCouponStadiumNum();
            String socialStadiumNum = stadiumDistrict.getSocialStadiumNum();
            String publicStadiumNum = stadiumDistrict.getPublicStadiumNum();
            int stadiumNumInt = Integer.parseInt(couponStadiumNum) + Integer.parseInt(socialStadiumNum) + Integer.parseInt(publicStadiumNum);
            HydResultStadiumDistrict district = new HydResultStadiumDistrict();
            district.setDistrict(stadiumDistrict.getDistrict());
            district.setDistrictName(stadiumDistrict.getDistrictName());
            district.setStadiumNum(stadiumNumInt + "");
            district.setBatchNo(batchNo);
            district.setStatisticalYear(DateUtil.getPreviousDayYear());
            queryList.add(district);
        }
        return queryList;
    }
}
