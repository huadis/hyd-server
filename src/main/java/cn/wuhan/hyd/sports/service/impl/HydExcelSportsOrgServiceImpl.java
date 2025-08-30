package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelSportsOrg;
import cn.wuhan.hyd.sports.domain.HydExcelSportsOrgDistrictStat;
import cn.wuhan.hyd.sports.repository.HydExcelSportsOrgDistrictStatRepo;
import cn.wuhan.hyd.sports.repository.HydExcelSportsOrgRepo;
import cn.wuhan.hyd.sports.service.IHydExcelSportsOrgService;
import org.apache.commons.collections.CollectionUtils;
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
 * 体育组织服务实现类
 * 功能说明：实现体育组织相关的业务逻辑 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Service
public class HydExcelSportsOrgServiceImpl implements IHydExcelSportsOrgService {

    @Resource
    private HydExcelSportsOrgRepo sportsOrgRepo;
    @Resource
    private HydExcelSportsOrgDistrictStatRepo sportsOrgDistrictStatRepo;

    @Override
    public void syncResultData() {
        List<Map<String, Object>> list = sportsOrgRepo.districtStatCount();
        List<HydExcelSportsOrgDistrictStat> districtStats = new ArrayList<>();

        // 2. 映射原始数据到统计实体
        list.forEach(map -> {
            HydExcelSportsOrgDistrictStat e = new HydExcelSportsOrgDistrictStat();
            e.setDistrictName(MapUtils.getString(map, "districtName"));
            e.setDistrictNum(MapUtils.getLong(map, "districtNum"));
            districtStats.add(e);
        });

        // 3. 清空旧数据并保存新数据
        sportsOrgDistrictStatRepo.deleteAll();
        sportsOrgDistrictStatRepo.saveAll(districtStats);
    }

    @Override
    public PageResult<HydExcelSportsOrg> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelSportsOrg> pageResult = sportsOrgRepo.findAll(pageable);

        PageResult<HydExcelSportsOrg> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());

        return result;
    }

    @Override
    public List<HydExcelSportsOrg> queryAll() {
        return sportsOrgRepo.findAll();
    }

    @Override
    public HydExcelSportsOrg findById(Long id) {
        Optional<HydExcelSportsOrg> optional = sportsOrgRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public HydExcelSportsOrg save(HydExcelSportsOrg sportsOrganization) {
        return sportsOrgRepo.save(sportsOrganization);
    }

    @Override
    public List<HydExcelSportsOrg> saveBatch(List<HydExcelSportsOrg> sportsOrganizations) {
        return sportsOrgRepo.saveAll(sportsOrganizations);
    }

    @Override
    public HydExcelSportsOrg update(HydExcelSportsOrg sportsOrganization) {
        return sportsOrgRepo.save(sportsOrganization);
    }

    @Override
    public void deleteById(Long id) {
        sportsOrgRepo.deleteById(id);
    }

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelSportsOrg> sportsOrganizations = new ArrayList<>();
        sheetMapData.forEach((name, list) -> {
            if (CollectionUtils.isNotEmpty(list)) {
                // 批量保存
                list.forEach(m -> {
                    sportsOrganizations.add(MapUtil.map2Object(HydExcelSportsOrg.class, m));
                });
                saveBatch(sportsOrganizations);
            }
        });
        return true;
    }

    @Override
    public List<Map<String, Object>> districtCountByYear(String year) {
        return sportsOrgDistrictStatRepo.districtCountByYear(year);
    }
}
