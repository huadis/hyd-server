package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOriginStadiumItem;

import java.util.List;

/**
 * 功能说明： 场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydOriginStadiumItemService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydOriginStadiumItem> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydOriginStadiumItem> queryAll();


    /**
     * 新增数据
     *
     * @param hydOriginStadiumItem 实体对象
     * @return 保存后的实体对象
     */
    HydOriginStadiumItem save(HydOriginStadiumItem hydOriginStadiumItem);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 更新数据
     *
     * @param hydOriginStadiumItem 实体对象
     * @return 更新后的实体对象
     */
    HydOriginStadiumItem update(HydOriginStadiumItem hydOriginStadiumItem);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydOriginStadiumItem findById(String id);

    int batchSave(List<HydOriginStadiumItem> items);
}
