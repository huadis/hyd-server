package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月11日 <br>
 */
public interface IHydExcelIndustryService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryCoreIndicators> queryAllIndustryCoreIndicators(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryCoreIndicators> queryAllIndustryCoreIndicators();

    /**
     * 新增数据
     *
     * @param industryCoreIndicators 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryCoreIndicators save(HydExcelIndustryCoreIndicators industryCoreIndicators);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryCoreIndicatorsById(Long id);

    /**
     * 更新数据
     *
     * @param industryCoreIndicators 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryCoreIndicators updateIndustryCoreIndicators(HydExcelIndustryCoreIndicators industryCoreIndicators);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryCoreIndicators findIndustryCoreIndicatorsById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryEmployeeCount> queryAllIndustryEmployeeCount(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryEmployeeCount> queryAllIndustryEmployeeCount();

    /**
     * 新增数据
     *
     * @param industryEmployeeCount 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryEmployeeCount save(HydExcelIndustryEmployeeCount industryEmployeeCount);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryEmployeeCountById(Long id);

    /**
     * 更新数据
     *
     * @param industryEmployeeCount 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryEmployeeCount updateIndustryEmployeeCount(HydExcelIndustryEmployeeCount industryEmployeeCount);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryEmployeeCount findIndustryEmployeeCountById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryEntityCountRatio> queryAllIndustryEntityCountRatio(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryEntityCountRatio> queryAllIndustryEntityCountRatio();

    /**
     * 新增数据
     *
     * @param industryEntityCountRatio 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryEntityCountRatio save(HydExcelIndustryEntityCountRatio industryEntityCountRatio);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryEntityCountRatioById(Long id);

    /**
     * 更新数据
     *
     * @param industryEntityCountRatio 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryEntityCountRatio updateIndustryEntityCountRatio(HydExcelIndustryEntityCountRatio industryEntityCountRatio);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryEntityCountRatio findIndustryEntityCountRatioById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryGoodsPurchaseRate> queryAllIndustryGoodsPurchaseRate(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryGoodsPurchaseRate> queryAllIndustryGoodsPurchaseRate();

    /**
     * 新增数据
     *
     * @param industryGoodsPurchaseRate 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryGoodsPurchaseRate save(HydExcelIndustryGoodsPurchaseRate industryGoodsPurchaseRate);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryGoodsPurchaseRateById(Long id);

    /**
     * 更新数据
     *
     * @param industryGoodsPurchaseRate 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryGoodsPurchaseRate updateIndustryGoodsPurchaseRate(HydExcelIndustryGoodsPurchaseRate industryGoodsPurchaseRate);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryGoodsPurchaseRate findIndustryGoodsPurchaseRateById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryGrowthValueTrend> queryAllIndustryGrowthValueTrend(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryGrowthValueTrend> queryAllIndustryGrowthValueTrend();

    /**
     * 新增数据
     *
     * @param industryGrowthValueTrend 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryGrowthValueTrend save(HydExcelIndustryGrowthValueTrend industryGrowthValueTrend);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryGrowthValueTrendById(Long id);

    /**
     * 更新数据
     *
     * @param industryGrowthValueTrend 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryGrowthValueTrend updateIndustryGrowthValueTrend(HydExcelIndustryGrowthValueTrend industryGrowthValueTrend);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryGrowthValueTrend findIndustryGrowthValueTrendById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryScaleTrend> queryAllIndustryScaleTrend(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryScaleTrend> queryAllIndustryScaleTrend();

    /**
     * 新增数据
     *
     * @param industryScaleTrend 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryScaleTrend save(HydExcelIndustryScaleTrend industryScaleTrend);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryScaleTrendById(Long id);

    /**
     * 更新数据
     *
     * @param industryScaleTrend 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryScaleTrend updateIndustryScaleTrend(HydExcelIndustryScaleTrend industryScaleTrend);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryScaleTrend findIndustryScaleTrendById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydExcelIndustryTrainingParticipationRate> queryAllIndustryTrainingParticipationRate(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydExcelIndustryTrainingParticipationRate> queryAllIndustryTrainingParticipationRate();

    /**
     * 新增数据
     *
     * @param industryTrainingParticipationRate 实体对象
     * @return 保存后的实体对象
     */
    HydExcelIndustryTrainingParticipationRate save(HydExcelIndustryTrainingParticipationRate industryTrainingParticipationRate);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteIndustryTrainingParticipationRateById(Long id);

    /**
     * 更新数据
     *
     * @param industryTrainingParticipationRate 实体对象
     * @return 更新后的实体对象
     */
    HydExcelIndustryTrainingParticipationRate updateIndustryTrainingParticipationRate(HydExcelIndustryTrainingParticipationRate industryTrainingParticipationRate);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydExcelIndustryTrainingParticipationRate findIndustryTrainingParticipationRateById(Long id);


    boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData);

    /**
     * 总览
     *
     * @return
     */
    HydExcelIndustryCoreIndicators overview(String year);

    /**
     * 体育产业总规模
     *
     * @return
     */
    List<Map<String, Object>> industryScaleTrendStat(String year);

    /**
     * 体育产业市场主体数量
     *
     * @return
     */
    List<Map<String, Object>> industryEntityCountRatioStat(String year);

    /**
     * 体育产业总增速和增加值
     *
     * @return
     */
    List<Map<String, Object>> industryGrowthValueTrendStat(String year);

    /**
     * 居民体育用品购买率
     *
     * @return
     */
    List<Map<String, Object>> industryGoodsPurchaseRateStat(String year);

    /**
     * 体育产业从业人员数量
     *
     * @return
     */
    List<Map<String, Object>> industryEmployeeCountStat(String year);

    /**
     * 居民体育培训项目参与率
     *
     * @return
     */
    List<Map<String, Object>> industryTrainingParticipationRateStat(String year);
}
