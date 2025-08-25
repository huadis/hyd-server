package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydYktService;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月24日 <br>
 */
@Service
public class HydYktServiceImpl implements IHydYktService {

    @Resource
    private HydResultOrderYktCourseStatRepo yktCourseStatRepo;
    @Resource
    private HydResultOrderYktDistrictStatRepo yktDistrictStatRepo;
    @Resource
    private HydResultOrderYktProjectStatRepo yktProjectStatRepo;
    @Resource
    private HydResultOrderYktStadiumStatRepo yktStadiumStatRepo;
    @Resource
    private HydResultOrderYktUserAgeStatRepo yktUserAgeStatRepo;
    @Resource
    private HydResultOrderYktUserSexStatRepo yktUserSexStatRepo;
    @Resource
    private HydOriginOrderRepo originOrderRepo;

    public void syncResultData() {
        syncDistrict();
        syncGender();
        syncProject();
        syncCourse();
        syncUserAgeStat();
        syncStadium();
    }

    @Override
    public PageResult<HydResultOrderYktDistrictStat> queryAllDistrict(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderYktDistrictStat> pageResult = yktDistrictStatRepo.findAll(pageable);
        PageResult<HydResultOrderYktDistrictStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderYktDistrictStat> queryAllDistrict() {
        return yktDistrictStatRepo.findAll();
    }

    @Override
    public HydResultOrderYktDistrictStat save(HydResultOrderYktDistrictStat districtStat) {
        return yktDistrictStatRepo.save(districtStat);
    }

    @Override
    public void deleteDistrictById(Long id) {
        yktDistrictStatRepo.deleteById(id);
    }

    @Override
    public HydResultOrderYktDistrictStat update(HydResultOrderYktDistrictStat districtStat) {
        return yktDistrictStatRepo.save(districtStat);
    }

    @Override
    public HydResultOrderYktDistrictStat findDistrictById(Long id) {
        return yktDistrictStatRepo.findById(id).orElse(null);
    }

    @Override
    public PageResult<HydResultOrderYktCourseStat> queryAllCourse(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderYktCourseStat> pageResult = yktCourseStatRepo.findAll(pageable);
        PageResult<HydResultOrderYktCourseStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderYktCourseStat> queryAllCourse() {
        return yktCourseStatRepo.findAll();
    }

    @Override
    public HydResultOrderYktCourseStat save(HydResultOrderYktCourseStat courseStat) {
        return yktCourseStatRepo.save(courseStat);
    }

    @Override
    public void deleteCourseById(Long id) {
        yktCourseStatRepo.deleteById(id);
    }

    @Override
    public HydResultOrderYktCourseStat update(HydResultOrderYktCourseStat courseStat) {
        return yktCourseStatRepo.save(courseStat);
    }

    @Override
    public HydResultOrderYktCourseStat findCourseById(Long id) {
        return yktCourseStatRepo.findById(id).orElse(null);
    }

    @Override
    public PageResult<HydResultOrderYktProjectStat> queryAllProject(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderYktProjectStat> pageResult = yktProjectStatRepo.findAll(pageable);
        PageResult<HydResultOrderYktProjectStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderYktProjectStat> queryAllProject() {
        return yktProjectStatRepo.findAll();
    }

    @Override
    public HydResultOrderYktProjectStat save(HydResultOrderYktProjectStat projectStat) {
        return yktProjectStatRepo.save(projectStat);
    }

    @Override
    public void deleteProjectById(Long id) {
        yktProjectStatRepo.deleteById(id);
    }

    @Override
    public HydResultOrderYktProjectStat update(HydResultOrderYktProjectStat projectStat) {
        return yktProjectStatRepo.save(projectStat);
    }

    @Override
    public HydResultOrderYktProjectStat findProjectById(Long id) {
        return yktProjectStatRepo.findById(id).orElse(null);
    }

    @Override
    public PageResult<HydResultOrderYktStadiumStat> queryAllStadium(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderYktStadiumStat> pageResult = yktStadiumStatRepo.findAll(pageable);
        PageResult<HydResultOrderYktStadiumStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderYktStadiumStat> queryAllStadium() {
        return yktStadiumStatRepo.findAll();
    }

    @Override
    public HydResultOrderYktStadiumStat save(HydResultOrderYktStadiumStat projectStat) {
        return yktStadiumStatRepo.save(projectStat);
    }

    @Override
    public void deleteStadiumById(Long id) {
        yktStadiumStatRepo.deleteById(id);
    }

    @Override
    public HydResultOrderYktStadiumStat update(HydResultOrderYktStadiumStat projectStat) {
        return yktStadiumStatRepo.save(projectStat);
    }

    @Override
    public HydResultOrderYktStadiumStat findStadiumById(Long id) {
        return yktStadiumStatRepo.findById(id).orElse(null);
    }

    @Override
    public PageResult<HydResultOrderYktUserAgeStat> queryAllUserAge(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderYktUserAgeStat> pageResult = yktUserAgeStatRepo.findAll(pageable);
        PageResult<HydResultOrderYktUserAgeStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderYktUserAgeStat> queryAllUserAge() {
        return yktUserAgeStatRepo.findAll();
    }

    @Override
    public HydResultOrderYktUserAgeStat save(HydResultOrderYktUserAgeStat userAgeStat) {
        return yktUserAgeStatRepo.save(userAgeStat);
    }

    @Override
    public void deleteUserAgeById(Long id) {
        yktUserAgeStatRepo.deleteById(id);
    }

    @Override
    public HydResultOrderYktUserAgeStat update(HydResultOrderYktUserAgeStat userAgeStat) {
        return yktUserAgeStatRepo.save(userAgeStat);
    }

    @Override
    public HydResultOrderYktUserAgeStat findUserAgeById(Long id) {
        return yktUserAgeStatRepo.findById(id).orElse(null);
    }

    @Override
    public PageResult<HydResultOrderYktUserSexStat> queryAllUserSex(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultOrderYktUserSexStat> pageResult = yktUserSexStatRepo.findAll(pageable);
        PageResult<HydResultOrderYktUserSexStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultOrderYktUserSexStat> queryAllUserSex() {
        return yktUserSexStatRepo.findAll();
    }

    @Override
    public HydResultOrderYktUserSexStat save(HydResultOrderYktUserSexStat userSexStat) {
        return yktUserSexStatRepo.save(userSexStat);
    }

    @Override
    public void deleteUserSexById(Long id) {
        yktUserSexStatRepo.deleteById(id);
    }

    @Override
    public HydResultOrderYktUserSexStat update(HydResultOrderYktUserSexStat userSexStat) {
        return yktUserSexStatRepo.save(userSexStat);
    }

    @Override
    public HydResultOrderYktUserSexStat findUserSexById(Long id) {
        return yktUserSexStatRepo.findById(id).orElse(null);
    }

    @Override
    public List<HydResultOrderYktDistrictStat> listDistrict(String year) {
        return yktDistrictStatRepo.listDistrict(year);
    }

    @Override
    public List<HydResultOrderYktUserSexStat> listUserSex(String year) {
        return yktUserSexStatRepo.listUserSex(year);
    }

    @Override
    public List<HydResultOrderYktProjectStat> listProject(String year) {
        return yktProjectStatRepo.listProject(year);
    }

    @Override
    public List<HydResultOrderYktCourseStat> courseTop5(String year) {
        return yktCourseStatRepo.courseTop5(year);
    }

    @Override
    public List<HydResultOrderYktStadiumStat> stadiumTop10(String year) {
        return yktStadiumStatRepo.stadiumTop10(year);
    }

    @Override
    public List<HydResultOrderYktUserAgeStat> listUserAge(String year) {
        return yktUserAgeStatRepo.listUserAge(year);
    }

    public void syncDistrict() {
        List<Map<String, Object>> list = originOrderRepo.districtStatCount();
        List<HydResultOrderYktDistrictStat> districtStats = new ArrayList<>();
        list.forEach(map -> {
            HydResultOrderYktDistrictStat e = new HydResultOrderYktDistrictStat();
            e.setDistrict(MapUtils.getString(map, "district"));
            e.setNum(MapUtils.getLong(map, "num"));
            districtStats.add(e);
        });
        yktDistrictStatRepo.deleteAll();
        yktDistrictStatRepo.saveAll(districtStats);
    }

    public void syncGender() {
        List<Map<String, Object>> list = originOrderRepo.genderStatCount();
        List<HydResultOrderYktUserSexStat> userSexStats = new ArrayList<>();
        list.forEach(map -> {
            HydResultOrderYktUserSexStat e = new HydResultOrderYktUserSexStat();
            e.setGender(MapUtils.getString(map, "gender"));
            e.setNum(MapUtils.getLong(map, "num"));
            userSexStats.add(e);
        });
        yktUserSexStatRepo.deleteAll();
        yktUserSexStatRepo.saveAll(userSexStats);
    }

    public void syncProject() {
        List<Map<String, Object>> list = originOrderRepo.projectStatCount();
        List<HydResultOrderYktProjectStat> projectStats = new ArrayList<>();
        list.forEach(map -> {
            HydResultOrderYktProjectStat e = new HydResultOrderYktProjectStat();
            e.setProject(MapUtils.getString(map, "project"));
            e.setNum(MapUtils.getLong(map, "num"));
            projectStats.add(e);
        });
        yktProjectStatRepo.deleteAll();
        yktProjectStatRepo.saveAll(projectStats);
    }

    public void syncCourse() {
        List<Map<String, Object>> list = originOrderRepo.courseStatCount();
        List<HydResultOrderYktCourseStat> courseStats = new ArrayList<>();
        list.forEach(map -> {
            HydResultOrderYktCourseStat e = new HydResultOrderYktCourseStat();
            e.setCourse(MapUtils.getString(map, "course"));
            e.setNum(MapUtils.getLong(map, "num"));
            courseStats.add(e);
        });
        yktCourseStatRepo.deleteAll();
        yktCourseStatRepo.saveAll(courseStats);
    }

    public void syncUserAgeStat() {
        List<Map<String, Object>> list = originOrderRepo.userAgeStatCount();
        List<HydResultOrderYktUserAgeStat> userAgeStat = new ArrayList<>();
        list.forEach(map -> {
            HydResultOrderYktUserAgeStat e = new HydResultOrderYktUserAgeStat();
            e.setAgeGroup(MapUtils.getString(map, "ageGroup"));
            e.setNum(MapUtils.getLong(map, "num"));
            userAgeStat.add(e);
        });
        yktUserAgeStatRepo.deleteAll();
        yktUserAgeStatRepo.saveAll(userAgeStat);
    }

    public void syncStadium() {
        List<Map<String, Object>> list = originOrderRepo.stadiumStatCount();
        List<HydResultOrderYktStadiumStat> stadiumStats = new ArrayList<>();
        list.forEach(map -> {
            HydResultOrderYktStadiumStat e = new HydResultOrderYktStadiumStat();
            e.setStadium(MapUtils.getString(map, "stadium"));
            e.setNum(MapUtils.getLong(map, "num"));
            stadiumStats.add(e);
        });
        yktStadiumStatRepo.deleteAll();
        yktStadiumStatRepo.saveAll(stadiumStats);
    }


}
