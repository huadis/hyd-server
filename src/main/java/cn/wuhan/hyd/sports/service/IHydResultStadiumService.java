package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultStadium;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 功能说明： 驾驶舱-在线场馆数量 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultStadiumService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydResultStadium> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultStadium> queryAll();


    /**
     * 新增数据
     *
     * @param hydResultStadium 实体对象
     * @return 保存后的实体对象
     */
    HydResultStadium save(HydResultStadium hydResultStadium);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultStadium 实体对象
     * @return 更新后的实体对象
     */
    HydResultStadium update(HydResultStadium hydResultStadium);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultStadium findById(Long id);

    int batchSave(List<HydResultStadium> stadiums);
}
