package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingActivityItemStadium;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 培训活动场馆支持的项目表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydTrainingActivityItemStadiumService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydTrainingActivityItemStadium> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydTrainingActivityItemStadium> queryAll();

    /**
     * 新增数据
     *
     * @param hydTrainingActivityItemStadium 实体对象
     * @return 保存后的实体对象
     */
    HydTrainingActivityItemStadium save(HydTrainingActivityItemStadium hydTrainingActivityItemStadium);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 更新数据
     *
     * @param hydTrainingActivityItemStadium 实体对象
     * @return 更新后的实体对象
     */
    HydTrainingActivityItemStadium update(HydTrainingActivityItemStadium hydTrainingActivityItemStadium);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydTrainingActivityItemStadium findById(String id);

    int batchSave(List<HydTrainingActivityItemStadium> items);
}
