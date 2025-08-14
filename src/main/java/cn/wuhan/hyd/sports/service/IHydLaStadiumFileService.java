package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydLaStadiumFile;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构附件表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydLaStadiumFileService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydLaStadiumFile> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydLaStadiumFile> queryAll();


    /**
     * 新增数据
     *
     * @param hydLaStadiumFile 实体对象
     * @return 保存后的实体对象
     */
    HydLaStadiumFile save(HydLaStadiumFile hydLaStadiumFile);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(Integer id);

    /**
     * 更新数据
     *
     * @param hydLaStadiumFile 实体对象
     * @return 更新后的实体对象
     */
    HydLaStadiumFile update(HydLaStadiumFile hydLaStadiumFile);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydLaStadiumFile findById(Integer id);

    Map<String, Object> orderStat();

    int batchSave(List<HydLaStadiumFile> files);
}
