package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydExcelInstructorService;
import cn.wuhan.hyd.sports.service.IHydResultInstructorService;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 功能说明： 社会体育指导员统计统一服务实现（含级别、概览、各区、项目、项目TOP15、性别统计） <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
@Service
public class HydResultInstructorServiceImpl extends HydBaseServiceImpl implements IHydResultInstructorService {

    @Resource
    private HydResultInstructorLevelRepo instructorLevelRepo;
    @Resource
    private HydResultInstructorOverviewRepo instructorOverviewRepo;
    @Resource
    private HydResultInstructorRegionRepo instructorRegionRepo;
    @Resource
    private HydResultInstructorServiceProjectRepo instructorServiceProjectRepo;
    @Resource
    private HydResultInstructorServiceProjectTopRepo instructorServiceProjectTopRepo;
    @Resource
    private HydResultInstructorUserSexRepo instructorUserSexRepo;
    @Resource
    private IHydExcelInstructorService instructorService;

    // ========================== 数据同步方法（参考模板syncResultData逻辑） ==========================
    public void syncResultData() {
        syncLevel();
        syncOverview();
        syncRegion();
        syncServiceProject();
        syncServiceProjectTop();
        syncUserSex();
    }

    // ========================== 1. 社会体育指导员-级别统计（HydResultInstructorLevel）实现 ==========================
    @Override
    public PageResult<HydResultInstructorLevel> queryAllLevel(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultInstructorLevel> pageResult = instructorLevelRepo.findAll(pageable);
        PageResult<HydResultInstructorLevel> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultInstructorLevel> queryAllLevel() {
        return instructorLevelRepo.findAll();
    }

    @Override
    public HydResultInstructorLevel save(HydResultInstructorLevel levelStat) {
        return instructorLevelRepo.save(levelStat);
    }

    @Override
    public void deleteLevelById(Long id) {
        instructorLevelRepo.deleteById(id);
    }

    @Override
    public HydResultInstructorLevel update(HydResultInstructorLevel levelStat) {
        return instructorLevelRepo.save(levelStat);
    }

    @Override
    public HydResultInstructorLevel findLevelById(Long id) {
        Optional<HydResultInstructorLevel> optional = instructorLevelRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 2. 社会体育指导员-概览（HydResultInstructorOverview）实现 ==========================
    @Override
    public PageResult<HydResultInstructorOverview> queryAllOverview(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultInstructorOverview> pageResult = instructorOverviewRepo.findAll(pageable);
        PageResult<HydResultInstructorOverview> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultInstructorOverview> queryAllOverview() {
        return instructorOverviewRepo.findAll();
    }

    @Override
    public HydResultInstructorOverview save(HydResultInstructorOverview overviewStat) {
        return instructorOverviewRepo.save(overviewStat);
    }

    @Override
    public void deleteOverviewById(Long id) {
        instructorOverviewRepo.deleteById(id);
    }

    @Override
    public HydResultInstructorOverview update(HydResultInstructorOverview overviewStat) {
        return instructorOverviewRepo.save(overviewStat);
    }

    @Override
    public HydResultInstructorOverview findOverviewById(Long id) {
        Optional<HydResultInstructorOverview> optional = instructorOverviewRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 3. 社会体育指导员-各区指导人员统计（HydResultInstructorRegion）实现 ==========================
    @Override
    public PageResult<HydResultInstructorRegion> queryAllRegion(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultInstructorRegion> pageResult = instructorRegionRepo.findAll(pageable);
        PageResult<HydResultInstructorRegion> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultInstructorRegion> queryAllRegion() {
        return instructorRegionRepo.findAll();
    }

    @Override
    public HydResultInstructorRegion save(HydResultInstructorRegion regionStat) {
        return instructorRegionRepo.save(regionStat);
    }

    @Override
    public void deleteRegionById(Long id) {
        instructorRegionRepo.deleteById(id);
    }

    @Override
    public HydResultInstructorRegion update(HydResultInstructorRegion regionStat) {
        return instructorRegionRepo.save(regionStat);
    }

    @Override
    public HydResultInstructorRegion findRegionById(Long id) {
        Optional<HydResultInstructorRegion> optional = instructorRegionRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 4. 社会体育指导员-项目统计（HydResultInstructorServiceProject）实现 ==========================
    @Override
    public PageResult<HydResultInstructorServiceProject> queryAllServiceProject(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultInstructorServiceProject> pageResult = instructorServiceProjectRepo.findAll(pageable);
        PageResult<HydResultInstructorServiceProject> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultInstructorServiceProject> queryAllServiceProject() {
        return instructorServiceProjectRepo.findAll();
    }

    @Override
    public HydResultInstructorServiceProject save(HydResultInstructorServiceProject serviceProjectStat) {
        return instructorServiceProjectRepo.save(serviceProjectStat);
    }

    @Override
    public void deleteServiceProjectById(Long id) {
        instructorServiceProjectRepo.deleteById(id);
    }

    @Override
    public HydResultInstructorServiceProject update(HydResultInstructorServiceProject serviceProjectStat) {
        return instructorServiceProjectRepo.save(serviceProjectStat);
    }

    @Override
    public HydResultInstructorServiceProject findServiceProjectById(Long id) {
        Optional<HydResultInstructorServiceProject> optional = instructorServiceProjectRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 5. 社会体育指导员-指导项目统计TOP15（HydResultInstructorServiceProjectTop）实现 ==========================
    @Override
    public PageResult<HydResultInstructorServiceProjectTop> queryAllServiceProjectTop(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultInstructorServiceProjectTop> pageResult = instructorServiceProjectTopRepo.findAll(pageable);
        PageResult<HydResultInstructorServiceProjectTop> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultInstructorServiceProjectTop> queryAllServiceProjectTop() {
        return instructorServiceProjectTopRepo.findAll();
    }

    @Override
    public HydResultInstructorServiceProjectTop save(HydResultInstructorServiceProjectTop serviceProjectTopStat) {
        return instructorServiceProjectTopRepo.save(serviceProjectTopStat);
    }

    @Override
    public void deleteServiceProjectTopById(Long id) {
        instructorServiceProjectTopRepo.deleteById(id);
    }

    @Override
    public HydResultInstructorServiceProjectTop update(HydResultInstructorServiceProjectTop serviceProjectTopStat) {
        return instructorServiceProjectTopRepo.save(serviceProjectTopStat);
    }

    @Override
    public HydResultInstructorServiceProjectTop findServiceProjectTopById(Long id) {
        Optional<HydResultInstructorServiceProjectTop> optional = instructorServiceProjectTopRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 6. 社会体育指导员-性别统计（HydResultInstructorUserSex）实现 ==========================
    @Override
    public PageResult<HydResultInstructorUserSex> queryAllUserSex(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydResultInstructorUserSex> pageResult = instructorUserSexRepo.findAll(pageable);
        PageResult<HydResultInstructorUserSex> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return result;
    }

    @Override
    public List<HydResultInstructorUserSex> queryAllUserSex() {
        return instructorUserSexRepo.findAll();
    }

    @Override
    public HydResultInstructorUserSex save(HydResultInstructorUserSex userSexStat) {
        return instructorUserSexRepo.save(userSexStat);
    }

    @Override
    public void deleteUserSexById(Long id) {
        instructorUserSexRepo.deleteById(id);
    }

    @Override
    public HydResultInstructorUserSex update(HydResultInstructorUserSex userSexStat) {
        return instructorUserSexRepo.save(userSexStat);
    }

    @Override
    public HydResultInstructorUserSex findUserSexById(Long id) {
        Optional<HydResultInstructorUserSex> optional = instructorUserSexRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Map<String, Object>> serviceProjectTop15(String year) {
        return instructorServiceProjectTopRepo.serviceProjectTop15(year);
    }

    @Override
    public List<Map<String, Object>> genderStat(String year) {
        return instructorUserSexRepo.genderStat(year);
    }

    @Override
    public List<Map<String, Object>> levelStat(String year) {
        return instructorLevelRepo.levelStat(year);
    }

    @Override
    public List<Map<String, Object>> regionInstructorStat(String year) {
        return instructorRegionRepo.regionInstructorStat(year);
    }

    @Override
    public Map<String, Object> overview(String year) {
        return instructorOverviewRepo.overview(year);
    }

    @Override
    public List<Map<String, Object>> serviceProject(String year) {
        return instructorServiceProjectRepo.serviceProject(year);
    }

    /**
     * 同步级别统计数据（参考模板syncDistrict逻辑）
     */
    private void syncLevel() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = instructorService.levelStat();
        List<HydResultInstructorLevel> levelStats = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydResultInstructorLevel e = new HydResultInstructorLevel();
            e.setLevel(MapUtils.getString(map, "level"));
            e.setPersonCount(MapUtils.getLong(map, "personCount"));
            e.setBatchNo(MapUtils.getString(map, "batchNo"));
            levelStats.add(e);
        });

        // 3. 清空旧数据并保存新数据
        instructorLevelRepo.deleteAll();
        instructorLevelRepo.saveAll(levelStats);
    }

    /**
     * 同步概览统计数据
     */
    private void syncOverview() {
        Map<String, Object> map = instructorService.overview();
        HydResultInstructorOverview e = new HydResultInstructorOverview();
        e.setTotal(MapUtils.getLong(map, "total")); // 全市人数
        e.setNewCount(MapUtils.getLong(map, "newCount")); // 今年新增
        e.setSexRatio(MapUtils.getString(map, "男:女")); // 男女比例

        instructorOverviewRepo.deleteAll();
        instructorOverviewRepo.save(e);
    }

    /**
     * 同步各区指导人员统计数据
     */
    private void syncRegion() {
        List<Map<String, Object>> list = instructorService.regionInstructorStat();
        List<HydResultInstructorRegion> regionStats = new ArrayList<>();

        list.forEach(map -> {
            HydResultInstructorRegion e = new HydResultInstructorRegion();
            e.setRegion(MapUtils.getString(map, "region")); // 地区名称
            e.setInstructorCount(MapUtils.getLong(map, "instructorCount")); // 人员数量
            regionStats.add(e);
        });

        instructorRegionRepo.deleteAll();
        instructorRegionRepo.saveAll(regionStats);
    }

    /**
     * 同步项目统计数据
     */
    private void syncServiceProject() {
        List<Map<String, Object>> list = instructorService.serviceProject();
        List<HydResultInstructorServiceProject> projectStats = new ArrayList<>();
        list.forEach(map -> {
            HydResultInstructorServiceProject e = new HydResultInstructorServiceProject();
            e.setServiceProject(MapUtils.getString(map, "serviceProject")); // 服务项目ID
            e.setTotalPersonCount(MapUtils.getLong(map, "totalPersonCount")); // 总人数
            e.setNewPersonCount(MapUtils.getLong(map, "newPersonCount")); // 新增人数
            projectStats.add(e);
        });

        instructorServiceProjectRepo.deleteAll();
        instructorServiceProjectRepo.saveAll(projectStats);
    }

    /**
     * 同步项目统计TOP15数据
     */
    private void syncServiceProjectTop() {
        List<Map<String, Object>> list = instructorService.serviceProjectTop15();
        List<HydResultInstructorServiceProjectTop> projectTopStats = new ArrayList<>();

        list.forEach(map -> {
            HydResultInstructorServiceProjectTop e = new HydResultInstructorServiceProjectTop();
            e.setServiceProject(MapUtils.getString(map, "serviceProject")); // 服务项目ID
            e.setPersonCount(MapUtils.getLong(map, "personCount")); // 指导人数
            e.setProportion(getBigDecimalFromMap(map, "proportion"));
            projectTopStats.add(e);
        });

        instructorServiceProjectTopRepo.deleteAll();
        instructorServiceProjectTopRepo.saveAll(projectTopStats);
    }

    /**
     * 性别统计
     */
    private void syncUserSex() {
        List<Map<String, Object>> list = instructorService.genderStat();
        List<HydResultInstructorUserSex> userSexes = new ArrayList<>();

        list.forEach(map -> {
            HydResultInstructorUserSex e = new HydResultInstructorUserSex();
            e.setGender(MapUtils.getString(map, "gender"));
            e.setPersonCount(MapUtils.getLong(map, "personCount"));
            e.setProportion(getBigDecimalFromMap(map, "proportion"));
            userSexes.add(e);
        });

        instructorUserSexRepo.deleteAll();
        instructorUserSexRepo.saveAll(userSexes);
    }


}
