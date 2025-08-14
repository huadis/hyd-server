package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydStadiumItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 场馆培训项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydStadiumItemService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydStadiumItem> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydStadiumItem> queryAll();


    /**
     * 新增数据
     *
     * @param hydStadiumItem 实体对象
     * @return 保存后的实体对象
     */
    HydStadiumItem save(HydStadiumItem hydStadiumItem);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 更新数据
     *
     * @param hydStadiumItem 实体对象
     * @return 更新后的实体对象
     */
    HydStadiumItem update(HydStadiumItem hydStadiumItem);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydStadiumItem findById(String id);

    int batchSave(List<HydStadiumItem> items);
}
