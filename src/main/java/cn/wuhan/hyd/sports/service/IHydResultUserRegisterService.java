package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultUserRegister;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 驾驶舱-用户注册趋势 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
public interface IHydResultUserRegisterService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydResultUserRegister> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultUserRegister> queryAll();

    /**
     * 新增数据
     *
     * @param hydResultUserRegister 实体对象
     * @return 保存后的实体对象
     */
    HydResultUserRegister save(HydResultUserRegister hydResultUserRegister);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Long id);

    /**
     * 更新数据
     *
     * @param hydResultUserRegister 实体对象
     * @return 更新后的实体对象
     */
    HydResultUserRegister update(HydResultUserRegister hydResultUserRegister);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultUserRegister findById(Long id);

    List<Map<String, Object>> countStadiumUserGrowthStat();

    int batchSave(List<HydResultUserRegister> userRegisters);
}
