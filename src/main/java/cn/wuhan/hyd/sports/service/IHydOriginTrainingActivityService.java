package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOriginTrainingActivity;
import cn.wuhan.hyd.sports.req.HydOriginTrainingActivityReq;

import java.util.List;

/**
 * 功能说明： 培训活动表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydOriginTrainingActivityService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydOriginTrainingActivity> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydOriginTrainingActivity> queryAll();


    /**
     * 新增数据
     *
     * @param hydOriginTrainingActivity 实体对象
     * @return 保存后的实体对象
     */
    HydOriginTrainingActivity save(HydOriginTrainingActivity hydOriginTrainingActivity);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);  // 主键是String类型

    /**
     * 更新数据
     *
     * @param hydOriginTrainingActivity 实体对象
     * @return 更新后的实体对象
     */
    HydOriginTrainingActivity update(HydOriginTrainingActivity hydOriginTrainingActivity);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydOriginTrainingActivity findById(String id);  // 主键是String类型

    int batchSave(List<HydOriginTrainingActivityReq> activities);
}
