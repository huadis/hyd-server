package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingActivityItem;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 培训活动支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydTrainingActivityItemService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydTrainingActivityItem> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydTrainingActivityItem> queryAll();


    /**
     * 新增数据
     *
     * @param hydTrainingActivityItem 实体对象
     * @return 保存后的实体对象
     */
    HydTrainingActivityItem save(HydTrainingActivityItem hydTrainingActivityItem);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);  // 主键为String类型

    /**
     * 更新数据
     *
     * @param hydTrainingActivityItem 实体对象
     * @return 更新后的实体对象
     */
    HydTrainingActivityItem update(HydTrainingActivityItem hydTrainingActivityItem);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydTrainingActivityItem findById(String id);  // 主键为String类型

    int batchSave(List<HydTrainingActivityItem> items);
}
