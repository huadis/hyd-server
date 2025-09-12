package cn.wuhan.hyd.sports.service.impl;

import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelSportsOrgDistrictStat;
import cn.wuhan.hyd.sports.repository.HydExcelSportsOrgDistrictStatRepo;
import cn.wuhan.hyd.sports.service.IHydExcelSportsOrgDistrictStatService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 体育组织-区属统计服务实现类
 * 功能说明：实现体育组织区属统计相关的业务逻辑 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@Service
public class HydExcelSportsOrgDistrictStatServiceImpl implements IHydExcelSportsOrgDistrictStatService {

    @Resource
    private HydExcelSportsOrgDistrictStatRepo districtStatRepo;

    @Override
    public PageResult<HydExcelSportsOrgDistrictStat> queryAll(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdTime");
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<HydExcelSportsOrgDistrictStat> pageResult = districtStatRepo.findAll(pageable);
        PageResult<HydExcelSportsOrgDistrictStat> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());

        return result;
    }

    @Override
    public List<HydExcelSportsOrgDistrictStat> queryAll() {
        return districtStatRepo.findAll();
    }

    @Override
    public HydExcelSportsOrgDistrictStat findById(Long id) {
        Optional<HydExcelSportsOrgDistrictStat> optional = districtStatRepo.findById(id);
        return optional.orElse(null);
    }

    @Override
    public HydExcelSportsOrgDistrictStat save(HydExcelSportsOrgDistrictStat districtStat) {
        return districtStatRepo.save(districtStat);
    }

    @Override
    public HydExcelSportsOrgDistrictStat update(HydExcelSportsOrgDistrictStat districtStat) {
        return districtStatRepo.save(districtStat);
    }

    @Override
    public void deleteById(Long id) {
        districtStatRepo.deleteById(id);
    }
}
