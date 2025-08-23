package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserAge;
import cn.wuhan.hyd.sports.req.HydResultUserAgeReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-用户年龄分布 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultUserAgeService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultUserAge> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultUserAge> queryAll();

    /**
     * 新增数据
     *
     * @param hydResultUserAge 实体对象
     * @return 保存后的实体对象
     */
    HydResultUserAge save(HydResultUserAge hydResultUserAge);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultUserAge 实体对象
     * @return 更新后的实体对象
     */
    HydResultUserAge update(HydResultUserAge hydResultUserAge);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultUserAge findById(Long id);

    List<Map<String, Object>> countStadiumUserAgeStat();

    int batchSave(List<HydResultUserAgeReq> userAges);
}
