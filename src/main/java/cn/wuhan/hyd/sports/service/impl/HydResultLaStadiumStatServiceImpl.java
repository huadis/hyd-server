package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumDistrict;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportName;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportNameTop;
import cn.wuhan.hyd.sports.repository.HydResultLaStadiumDistrictRepo;
import cn.wuhan.hyd.sports.repository.HydResultLaStadiumSportNameRepo;
import cn.wuhan.hyd.sports.repository.HydResultLaStadiumSportNameTopRepo;
import cn.wuhan.hyd.sports.service.IHydOriginStadiumItemService;
import cn.wuhan.hyd.sports.service.IHydOriginStadiumService;
import cn.wuhan.hyd.sports.service.IHydResultLaStadiumStatService;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 功能说明： 校外培训机构-场馆统计统一服务实现（含各区数量、项目类型、项目类型占比TOP10） <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月27日 <br>
 */
@Service
public class HydResultLaStadiumStatServiceImpl implements IHydResultLaStadiumStatService {

    // 注入对应Repository（数据访问层）
    @Resource
    private HydResultLaStadiumDistrictRepo districtRepo;
    @Resource
    private HydResultLaStadiumSportNameRepo sportNameRepo;
    @Resource
    private HydResultLaStadiumSportNameTopRepo sportNameTopRepo;
    @Resource
    private IHydOriginStadiumService stadiumService;

    @Resource
    private IHydOriginStadiumItemService stadiumItemService;

    // ========================== 数据同步方法（参考模板syncResultData逻辑） ==========================
    public void syncResultData() {
        syncStadiumDistricts();
        syncSportName();
        syncSportNameTop();
    }

    // ========================== 1. 各区场馆数量统计（HydResultLaStadiumDistrict）实现 ==========================
    @Override
    public PageResult<HydResultLaStadiumDistrict> queryAllDistrictStat(int page, int size) {
        // 构建分页参数（Spring Data JPA分页从0开始，直接使用page参数）
        Pageable pageable = PageRequest.of(page, size);
        // 调用Repository分页查询
        Page<HydResultLaStadiumDistrict> pageData = districtRepo.findAll(pageable);

        // 封装为自定义PageResult返回
        PageResult<HydResultLaStadiumDistrict> result = new PageResult<>();
        result.setTotalElements(pageData.getTotalElements()); // 总条数
        result.setContent(pageData.getContent()); // 当前页数据列表
        return result;
    }

    @Override
    public List<HydResultLaStadiumDistrict> queryAllDistrictStat() {
        // 调用Repository查询所有数据
        return districtRepo.findAll();
    }

    @Override
    public HydResultLaStadiumDistrict saveDistrictStat(HydResultLaStadiumDistrict districtStat) {
        // 调用Repository保存（JPA save()：无ID则新增，有ID则更新，此处为新增场景，实体无需提前设置ID）
        return districtRepo.save(districtStat);
    }

    @Override
    public HydResultLaStadiumDistrict updateDistrictStat(HydResultLaStadiumDistrict districtStat) {
        // 校验主键是否存在（避免更新不存在的数据）
        if (districtStat.getId() == null) {
            throw new IllegalArgumentException("更新操作需传入主键ID（districtStat.id不能为空）");
        }
        // 调用Repository保存（实体含ID，触发更新逻辑）
        return districtRepo.save(districtStat);
    }

    @Override
    public void deleteDistrictStatById(Long id) {
        // 调用Repository根据ID删除
        districtRepo.deleteById(id);
    }

    @Override
    public HydResultLaStadiumDistrict getDistrictStatById(Long id) {
        // 调用Repository根据ID查询，Optional处理空值（不存在则返回null）
        Optional<HydResultLaStadiumDistrict> optional = districtRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 2. 项目类型统计（HydResultLaStadiumSportName）实现 ==========================
    @Override
    public PageResult<HydResultLaStadiumSportName> queryAllSportNameStat(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultLaStadiumSportName> pageData = sportNameRepo.findAll(pageable);

        PageResult<HydResultLaStadiumSportName> result = new PageResult<>();
        result.setTotalElements(pageData.getTotalElements());
        result.setContent(pageData.getContent());
        return result;
    }

    @Override
    public List<HydResultLaStadiumSportName> queryAllSportNameStat() {
        return sportNameRepo.findAll();
    }

    @Override
    public HydResultLaStadiumSportName saveSportNameStat(HydResultLaStadiumSportName sportNameStat) {
        return sportNameRepo.save(sportNameStat);
    }

    @Override
    public HydResultLaStadiumSportName updateSportNameStat(HydResultLaStadiumSportName sportNameStat) {
        if (sportNameStat.getId() == null) {
            throw new IllegalArgumentException("更新操作需传入主键ID（sportNameStat.id不能为空）");
        }
        return sportNameRepo.save(sportNameStat);
    }

    @Override
    public void deleteSportNameStatById(Long id) {
        sportNameRepo.deleteById(id);
    }

    @Override
    public HydResultLaStadiumSportName getSportNameStatById(Long id) {
        Optional<HydResultLaStadiumSportName> optional = sportNameRepo.findById(id);
        return optional.orElse(null);
    }


    // ========================== 3. 项目类型占比TOP10统计（HydResultLaStadiumSportNameTop）实现 ==========================
    @Override
    public PageResult<HydResultLaStadiumSportNameTop> queryAllSportNameTopStat(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydResultLaStadiumSportNameTop> pageData = sportNameTopRepo.findAll(pageable);

        PageResult<HydResultLaStadiumSportNameTop> result = new PageResult<>();
        result.setTotalElements(pageData.getTotalElements());
        result.setContent(pageData.getContent());
        return result;
    }

    @Override
    public List<HydResultLaStadiumSportNameTop> queryAllSportNameTopStat() {
        return sportNameTopRepo.findAll();
    }

    @Override
    public HydResultLaStadiumSportNameTop saveSportNameTopStat(HydResultLaStadiumSportNameTop sportNameTopStat) {
        return sportNameTopRepo.save(sportNameTopStat);
    }

    @Override
    public HydResultLaStadiumSportNameTop updateSportNameTopStat(HydResultLaStadiumSportNameTop sportNameTopStat) {
        if (sportNameTopStat.getId() == null) {
            throw new IllegalArgumentException("更新操作需传入主键ID（sportNameTopStat.id不能为空）");
        }
        return sportNameTopRepo.save(sportNameTopStat);
    }

    @Override
    public void deleteSportNameTopStatById(Long id) {
        sportNameTopRepo.deleteById(id);
    }

    @Override
    public HydResultLaStadiumSportNameTop getSportNameTopStatById(Long id) {
        Optional<HydResultLaStadiumSportNameTop> optional = sportNameTopRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public List<Map<String, Object>> stadiumCountByDistrict(String year) {
        return districtRepo.stadiumCountByDistrict(year);
    }

    @Override
    public List<Map<String, Object>> itemCountTop10BySportName(String year) {
        return sportNameTopRepo.itemCountTop10BySportName(year);
    }

    @Override
    public List<Map<String, Object>> itemCountBySportName(String year) {
        return sportNameRepo.itemCountBySportName(year);
    }

    private void syncStadiumDistricts() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = stadiumService.stadiumCountByDistrict("");
        List<HydResultLaStadiumDistrict> stadiumDistricts = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydResultLaStadiumDistrict e = new HydResultLaStadiumDistrict();
            e.setDistrictName(MapUtils.getString(map, "districtName"));
            e.setStadiumNum(MapUtils.getLong(map, "stadiumNum"));
            stadiumDistricts.add(e);
        });

        // 3. 清空旧数据并保存新数据
        districtRepo.deleteAll();
        districtRepo.saveAll(stadiumDistricts);
    }

    private void syncSportName() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = stadiumItemService.itemCountTop10BySportName("");
        List<HydResultLaStadiumSportNameTop> laStadiumSportNameTops = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydResultLaStadiumSportNameTop e = new HydResultLaStadiumSportNameTop();
            e.setSportName(MapUtils.getString(map, "sportName"));
            e.setNum(MapUtils.getLong(map, "num"));
            laStadiumSportNameTops.add(e);
        });

        // 3. 清空旧数据并保存新数据
        sportNameTopRepo.deleteAll();
        sportNameTopRepo.saveAll(laStadiumSportNameTops);
    }

    private void syncSportNameTop() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = stadiumItemService.itemCountBySportName("");
        List<HydResultLaStadiumSportName> laStadiumSportNames = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydResultLaStadiumSportName e = new HydResultLaStadiumSportName();
            e.setSportName(MapUtils.getString(map, "sportName"));
            e.setNum(MapUtils.getLong(map, "num"));
            laStadiumSportNames.add(e);
        });

        // 3. 清空旧数据并保存新数据
        sportNameRepo.deleteAll();
        sportNameRepo.saveAll(laStadiumSportNames);
    }
}
