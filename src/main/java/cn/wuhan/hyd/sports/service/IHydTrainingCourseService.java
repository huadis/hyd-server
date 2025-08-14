package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydTrainingCourse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 功能说明： 培训课程表 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
public interface IHydTrainingCourseService {

    /**
     * 分页查询
     *
     * @param pageable 分页参数
     * @return 实体对象列表
     */
    PageResult<HydTrainingCourse> queryAll(Pageable pageable);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydTrainingCourse> queryAll();


    /**
     * 新增数据
     *
     * @param hydTrainingCourse 实体对象
     * @return 保存后的实体对象
     */
    HydTrainingCourse save(HydTrainingCourse hydTrainingCourse);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteById(String id);

    /**
     * 更新数据
     *
     * @param hydTrainingCourse 实体对象
     * @return 更新后的实体对象
     */
    HydTrainingCourse update(HydTrainingCourse hydTrainingCourse);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydTrainingCourse findById(String id);

    int batchSave(List<HydTrainingCourse> courses);
}
