package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStadiumMapPointHistory;
import cn.wuhan.hyd.sports.req.HydResultStadiumMapPointReq;

import java.util.List;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月21日 <br>
 */
public interface IHydResultStadiumMapPointService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultStadiumMapPointHistory> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultStadiumMapPointHistory> queryAll();


    /**
     * 新增数据
     *
     * @param stadiumMapPoint 实体对象
     * @return 保存后的实体对象
     */
    HydResultStadiumMapPointHistory save(HydResultStadiumMapPointHistory stadiumMapPoint);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param stadiumMapPoint 实体对象
     * @return 更新后的实体对象
     */
    HydResultStadiumMapPointHistory update(HydResultStadiumMapPointHistory stadiumMapPoint);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultStadiumMapPointHistory findById(Long id);

    int batchSave(List<HydResultStadiumMapPointReq> stadiumMapPoints);
}
