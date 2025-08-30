package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelSportsOrgDistrictStat;

import java.util.List;

/**
 * 体育组织-区属统计服务接口
 * 功能说明：定义体育组织区属统计相关的业务逻辑 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
public interface IHydExcelSportsOrgDistrictStatService {

    /**
     * 分页查询统计数据
     *
     * @param page 页码
     * @param size 每页条数
     * @return 分页结果
     */
    PageResult<HydExcelSportsOrgDistrictStat> queryAll(int page, int size);

    /**
     * 查询所有统计数据
     *
     * @return 统计数据列表
     */
    List<HydExcelSportsOrgDistrictStat> queryAll();

    /**
     * 根据ID查询统计数据
     *
     * @param id 主键ID
     * @return 统计数据实体
     */
    HydExcelSportsOrgDistrictStat findById(Long id);

    /**
     * 保存统计数据
     *
     * @param districtStat 统计数据实体
     * @return 保存后的实体
     */
    HydExcelSportsOrgDistrictStat save(HydExcelSportsOrgDistrictStat districtStat);

    /**
     * 更新统计数据
     *
     * @param districtStat 统计数据实体
     * @return 更新后的实体
     */
    HydExcelSportsOrgDistrictStat update(HydExcelSportsOrgDistrictStat districtStat);

    /**
     * 根据ID删除统计数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);
}
