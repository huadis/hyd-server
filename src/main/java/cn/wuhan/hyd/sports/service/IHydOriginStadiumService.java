package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOriginStadium;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumHistory;
import cn.wuhan.hyd.sports.req.HydOriginStadiumReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 培训场馆表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydOriginStadiumService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydOriginStadiumHistory> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydOriginStadiumHistory> queryAll();


    /**
     * 新增数据
     *
     * @param hydOriginStadium 实体对象
     * @return 保存后的实体对象
     */
    HydOriginStadiumHistory save(HydOriginStadiumHistory hydOriginStadium);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 更新数据
     *
     * @param hydOriginStadium 实体对象
     * @return 更新后的实体对象
     */
    HydOriginStadiumHistory update(HydOriginStadiumHistory hydOriginStadium);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydOriginStadiumHistory findById(String id);

    int batchSave(List<HydOriginStadiumReq> items);

    List<Map<String, Object>> stadiumCountByDistrict(String year);
}
