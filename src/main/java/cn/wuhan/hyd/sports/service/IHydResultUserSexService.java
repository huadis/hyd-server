package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserSex;
import cn.wuhan.hyd.sports.req.HydResultUserSexReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-用户性别分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultUserSexService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultUserSex> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultUserSex> queryAll();

    /**
     * 新增数据
     *
     * @param hydResultUserSex 实体对象
     * @return 保存后的实体对象
     */
    HydResultUserSex save(HydResultUserSex hydResultUserSex);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultUserSex 实体对象
     * @return 更新后的实体对象
     */
    HydResultUserSex update(HydResultUserSex hydResultUserSex);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultUserSex findById(Long id);

    List<Map<String, Object>> countStadiumUserSexStat();

    int batchSave(List<HydResultUserSexReq> userSexes);
}
