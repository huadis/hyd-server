package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydOriginLaStadiumFile;
import cn.wuhan.hyd.sports.domain.HydOriginLaStadiumFileHistory;
import cn.wuhan.hyd.sports.req.HydOriginLaStadiumFileReq;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构附件表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydOriginLaStadiumFileService {

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydOriginLaStadiumFileHistory> queryAll(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydOriginLaStadiumFileHistory> queryAll();


    /**
     * 新增数据
     *
     * @param hydOriginLaStadiumFile 实体对象
     * @return 保存后的实体对象
     */
    HydOriginLaStadiumFileHistory save(HydOriginLaStadiumFileHistory hydOriginLaStadiumFile);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Integer id);

    /**
     * 更新数据
     *
     * @param hydOriginLaStadiumFile 实体对象
     * @return 更新后的实体对象
     */
    HydOriginLaStadiumFileHistory update(HydOriginLaStadiumFileHistory hydOriginLaStadiumFile);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydOriginLaStadiumFileHistory findById(Integer id);

    int batchSave(List<HydOriginLaStadiumFileReq> files);
}
