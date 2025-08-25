package cn.wuhan.hyd.sports.service;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;

import java.util.List;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
public interface IHydYktService {

    void syncResultData();

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderYktDistrictStat> queryAllDistrict(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderYktDistrictStat> queryAllDistrict();


    /**
     * 新增数据
     *
     * @param districtStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderYktDistrictStat save(HydResultOrderYktDistrictStat districtStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteDistrictById(Long id);

    /**
     * 更新数据
     *
     * @param districtStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderYktDistrictStat update(HydResultOrderYktDistrictStat districtStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderYktDistrictStat findDistrictById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderYktCourseStat> queryAllCourse(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderYktCourseStat> queryAllCourse();


    /**
     * 新增数据
     *
     * @param courseStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderYktCourseStat save(HydResultOrderYktCourseStat courseStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteCourseById(Long id);

    /**
     * 更新数据
     *
     * @param courseStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderYktCourseStat update(HydResultOrderYktCourseStat courseStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderYktCourseStat findCourseById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderYktProjectStat> queryAllProject(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderYktProjectStat> queryAllProject();


    /**
     * 新增数据
     *
     * @param projectStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderYktProjectStat save(HydResultOrderYktProjectStat projectStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteProjectById(Long id);

    /**
     * 更新数据
     *
     * @param projectStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderYktProjectStat update(HydResultOrderYktProjectStat projectStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderYktProjectStat findProjectById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderYktStadiumStat> queryAllStadium(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderYktStadiumStat> queryAllStadium();


    /**
     * 新增数据
     *
     * @param projectStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderYktStadiumStat save(HydResultOrderYktStadiumStat projectStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteStadiumById(Long id);

    /**
     * 更新数据
     *
     * @param projectStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderYktStadiumStat update(HydResultOrderYktStadiumStat projectStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderYktStadiumStat findStadiumById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderYktUserAgeStat> queryAllUserAge(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderYktUserAgeStat> queryAllUserAge();


    /**
     * 新增数据
     *
     * @param projectStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderYktUserAgeStat save(HydResultOrderYktUserAgeStat projectStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteUserAgeById(Long id);

    /**
     * 更新数据
     *
     * @param projectStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderYktUserAgeStat update(HydResultOrderYktUserAgeStat projectStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderYktUserAgeStat findUserAgeById(Long id);

    /**
     * 分页查询
     *
     * @param page 页数
     * @param size 每页条数
     * @return 实体对象列表
     */
    PageResult<HydResultOrderYktUserSexStat> queryAllUserSex(int page, int size);

    /**
     * 查询全部
     *
     * @return 实体对象列表
     */
    List<HydResultOrderYktUserSexStat> queryAllUserSex();


    /**
     * 新增数据
     *
     * @param projectStat 实体对象
     * @return 保存后的实体对象
     */
    HydResultOrderYktUserSexStat save(HydResultOrderYktUserSexStat projectStat);

    /**
     * 根据ID删除数据
     *
     * @param id 主键ID
     */
    void deleteUserSexById(Long id);

    /**
     * 更新数据
     *
     * @param projectStat 实体对象
     * @return 更新后的实体对象
     */
    HydResultOrderYktUserSexStat update(HydResultOrderYktUserSexStat projectStat);

    /**
     * 根据ID查询数据
     *
     * @param id 主键ID
     * @return 实体对象
     */
    HydResultOrderYktUserSexStat findUserSexById(Long id);

    List<HydResultOrderYktDistrictStat> listDistrict(String year);

    List<HydResultOrderYktUserSexStat> listUserSex(String year);

    List<HydResultOrderYktProjectStat> listProject(String year);

    List<HydResultOrderYktCourseStat> courseTop5(String year);

    List<HydResultOrderYktStadiumStat> stadiumTop10(String year);

    List<HydResultOrderYktUserAgeStat> listUserAge(String year);
}
