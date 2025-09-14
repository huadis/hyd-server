package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.DateUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumDistrict;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportName;
import cn.wuhan.hyd.sports.domain.HydResultLaStadiumSportNameTop;
import cn.wuhan.hyd.sports.repository.*;
import cn.wuhan.hyd.sports.service.IHydResultLaStadiumStatService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

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
    private HydOriginLaStadiumHistoryRepo laStadiumHistoryRepo;
    @Resource
    private HydOriginTrainingActivityItemHistoryRepo trainingActivityItemHistoryRepo;

    // ========================== 数据同步方法（参考模板syncResultData逻辑） ==========================
    @Transactional(rollbackFor = Exception.class)
    public void syncResultData() {
        syncStadiumDistricts();
        syncSportName();
        syncSportNameTop();
    }

    // ========================== 1. 各区场馆数量统计（HydResultLaStadiumDistrict）实现 ==========================
    @Override
    public PageResult<HydResultLaStadiumDistrict> queryAllDistrictStat(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
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
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
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
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
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

    @Transactional(rollbackFor = Exception.class)
    public void syncStadiumDistricts() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = laStadiumHistoryRepo.stadiumCountByDistrict();
        List<HydResultLaStadiumDistrict> stadiumDistricts = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydResultLaStadiumDistrict e = new HydResultLaStadiumDistrict();
            e.setDistrictName(MapUtils.getString(map, "districtName"));
            e.setStadiumNum(MapUtils.getLong(map, "stadiumNum"));
            e.setStatisticalYear(DateUtil.getPreviousDayYear());
            stadiumDistricts.add(e);
        });
        if (CollectionUtils.isNotEmpty(stadiumDistricts)) {
            // 3. 清空旧数据并保存新数据
            districtRepo.deleteByStatisticalYear(DateUtil.getPreviousDayYear());
            districtRepo.saveAll(stadiumDistricts);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void syncSportName() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = trainingActivityItemHistoryRepo.itemCountTop10BySportName();
        List<HydResultLaStadiumSportNameTop> laStadiumSportNameTops = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydResultLaStadiumSportNameTop e = new HydResultLaStadiumSportNameTop();
            e.setSportName(MapUtils.getString(map, "sportName"));
            e.setNum(MapUtils.getLong(map, "num"));
            e.setStatisticalYear(DateUtil.getPreviousDayYear());
            laStadiumSportNameTops.add(e);
        });
        if (CollectionUtils.isNotEmpty(laStadiumSportNameTops)) {
            // 3. 清空旧数据并保存新数据
            sportNameTopRepo.deleteByStatisticalYear(DateUtil.getPreviousDayYear());
            sportNameTopRepo.saveAll(laStadiumSportNameTops);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void syncSportNameTop() {
        // 1. 从原始数据仓库查询统计结果
        List<Map<String, Object>> list = trainingActivityItemHistoryRepo.itemCountBySportName();
        List<HydResultLaStadiumSportName> laStadiumSportNames = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        countOtherStat(list).forEach(map -> {
            HydResultLaStadiumSportName e = new HydResultLaStadiumSportName();
            e.setSportName(MapUtils.getString(map, "sportName"));
            e.setNum(MapUtils.getLong(map, "num"));
            e.setStatisticalYear(DateUtil.getPreviousDayYear());
            laStadiumSportNames.add(e);
        });
        if (CollectionUtils.isNotEmpty(laStadiumSportNames)) {
            // 3. 清空旧数据并保存新数据
            sportNameRepo.deleteByStatisticalYear(DateUtil.getPreviousDayYear());
            sportNameRepo.saveAll(laStadiumSportNames);
        }
    }

    private List<Map<String, Object>> countOtherStat(List<Map<String, Object>> allSportStats) {
        if (CollectionUtils.isEmpty(allSportStats)) {
            return new ArrayList<>();  // 无数据时返回空列表
        }

        // 2. 第二步：按数量降序排序（数量相同按 sportName 升序）
        List<Map<String, Object>> sortedStats = allSportStats.stream()
                .sorted((map1, map2) -> {
                    // 提取两个 map 中的数量和名称（假设 map 的 key 为 "sportName" 和 "num"）
                    int num1 = MapUtils.getInteger(map1, "num");
                    int num2 = MapUtils.getInteger(map2, "num");
                    String name1 = map1.get("sportName").toString();
                    String name2 = map2.get("sportName").toString();

                    // 先按数量降序
                    if (num1 != num2) {
                        return Integer.compare(num2, num1); // 降序用 num2 - num1
                    }
                    // 数量相同则按名称升序
                    return name1.compareTo(name2);
                })
                .collect(Collectors.toList());

        // 3. 第三步：筛选 Top5 和剩余数据，聚合 “其他”
        List<Map<String, Object>> finalResult = new ArrayList<>();
        int totalSize = sortedStats.size();

        // 3.1 截取 Top5（若总数量不足 5，取全部）
        int top5EndIndex = Math.min(totalSize, 5);
        for (int i = 0; i < top5EndIndex; i++) {
            Map<String, Object> statMap = sortedStats.get(i);
            // 转换为结果 Map（保持 key 为 "sportName" 和 "num"，值类型适配 Object）
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("sportName", statMap.get("sportName"));
            resultMap.put("num", statMap.get("num"));
            finalResult.add(resultMap);
        }

        // 3.2 若有第 6 条及之后的数据，聚合为 “其他”
        if (totalSize > 5) {
            // 计算 “其他” 的总数量（累加第 6 条到最后一条的 num）
            int othersTotalNum = sortedStats.subList(5, totalSize).stream()
                    .mapToInt(map -> MapUtils.getInteger(map, "num"))
                    .sum();
            // 添加 “其他” 条目
            Map<String, Object> othersMap = new HashMap<>();
            othersMap.put("sportName", "其他");
            othersMap.put("num", othersTotalNum);
            finalResult.add(othersMap);
        }

        // 4. 确保 “其他” 始终排在最后
        finalResult.sort((map1, map2) -> {
            String name1 = map1.get("sportName").toString();
            String name2 = map2.get("sportName").toString();

            // 标记 “其他” 为 1，Top5 为 0，按 0→1 排序
            int flag1 = "其他".equals(name1) ? 1 : 0;
            int flag2 = "其他".equals(name2) ? 1 : 0;
            if (flag1 != flag2) {
                return Integer.compare(flag1, flag2);
            }

            // Top5 内部按数量降序
            BigInteger num1 = (BigInteger) map1.get("num");
            BigInteger num2 = (BigInteger) map2.get("num");
            // 注意：降序排列，所以是 num2.compareTo(num1)
            return num2.compareTo(num1);
        });
        return finalResult;
    }
}
