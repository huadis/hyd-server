package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserRepurchase;
import cn.wuhan.hyd.sports.req.HydResultUserRepurchaseReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-用户复购分析 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultUserRepurchaseService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultUserRepurchase> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultUserRepurchase> queryAll();

    /**
     * 新增数据
     *
     * @param hydResultUserRepurchase 实体对象
     * @return 保存后的实体对象
     */
    HydResultUserRepurchase save(HydResultUserRepurchase hydResultUserRepurchase);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultUserRepurchase 实体对象
     * @return 更新后的实体对象
     */
    HydResultUserRepurchase update(HydResultUserRepurchase hydResultUserRepurchase);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultUserRepurchase findById(Long id);

    List<Map<String, Object>> countStadiumUserRepurchaseStat(String year);

    int batchSave(List<HydResultUserRepurchaseReq> userRepurchases);
}
