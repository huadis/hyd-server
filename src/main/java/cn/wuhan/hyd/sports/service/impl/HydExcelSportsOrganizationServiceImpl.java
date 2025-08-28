package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.MapUtil;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelSportsOrganization;
import cn.wuhan.hyd.sports.repository.HydExcelSportsOrganizationRepo;
import cn.wuhan.hyd.sports.service.IHydExcelSportsOrganizationService;
import org.apache.commons.collections.CollectionUtils;
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
public class HydExcelSportsOrganizationServiceImpl implements IHydExcelSportsOrganizationService {

    @Resource
    private HydExcelSportsOrganizationRepo sportsOrganizationRepo;

    @Override
    public PageResult<HydExcelSportsOrganization> queryAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<HydExcelSportsOrganization> pageResult = sportsOrganizationRepo.findAll(pageable);

        PageResult<HydExcelSportsOrganization> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());

        return result;
    }

    @Override
    public List<HydExcelSportsOrganization> queryAll() {
        return sportsOrganizationRepo.findAll();
    }

    @Override
    public HydExcelSportsOrganization findById(Long id) {
        Optional<HydExcelSportsOrganization> optional = sportsOrganizationRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public HydExcelSportsOrganization save(HydExcelSportsOrganization sportsOrganization) {
        return sportsOrganizationRepo.save(sportsOrganization);
    }

    @Override
    public List<HydExcelSportsOrganization> saveBatch(List<HydExcelSportsOrganization> sportsOrganizations) {
        return sportsOrganizationRepo.saveAll(sportsOrganizations);
    }

    @Override
    public HydExcelSportsOrganization update(HydExcelSportsOrganization sportsOrganization) {
        return sportsOrganizationRepo.save(sportsOrganization);
    }

    @Override
    public void deleteById(Long id) {
        sportsOrganizationRepo.deleteById(id);
    }

    @Override
    public boolean importExcel(Map<String, List<Map<String, Object>>> sheetMapData) {
        List<HydExcelSportsOrganization> sportsOrganizations = new ArrayList<>();
        sheetMapData.forEach((name, list) -> {
            if (CollectionUtils.isNotEmpty(list)) {
                // 批量保存
                list.forEach(m -> {
                    sportsOrganizations.add(MapUtil.map2Object(HydExcelSportsOrganization.class, m));
                });
                saveBatch(sportsOrganizations);
            }
        });
        return true;
    }
}
