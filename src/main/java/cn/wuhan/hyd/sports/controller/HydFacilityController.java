package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydFacilityDistrict;
import cn.wuhan.hyd.sports.domain.HydFacilityDistrictMonth;
import cn.wuhan.hyd.sports.domain.HydFacilityInspect;
import cn.wuhan.hyd.sports.domain.HydFacilityYear;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育基础设施 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@RestController
@RequestMapping("/api/facility")
@Api(tags = "体育基础设施")
public class HydFacilityController {

    @Resource
    private IHydFacilityYearService hydFacilityYearService;
    @Resource
    private IHydFacilityService hydFacilityService;
    @Resource
    private IHydFacilityInspectService hydFacilityInspectService;
    @Resource
    private IHydFacilityDistrictService hydFacilityDistrictService;
    @Autowired
    private IHydFacilityDistrictMonthService hydFacilityDistrictMonthService;

    @ApiOperation("健身点位")
    @AnonymousGetMapping("/fitnessOverview")
    public Response<HydFacilityYear> FitnessOverview(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityYear
        return Response.ok(hydFacilityYearService.findLatestFacilityYear());
    }

    @ApiOperation("体育设施全貌")
    @AnonymousGetMapping("/facilityOverview")
    public Response<List<Map<String, Object>>> facilityOverview(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacility
        return Response.ok(hydFacilityService.facility());
    }

    @ApiOperation("各区分布")
    @AnonymousGetMapping("/districtDistribution")
    public Response<List<HydFacilityDistrict>> districtDistribution(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityDistrictMonth
        return Response.ok(hydFacilityDistrictService.queryAll());
    }

    @ApiOperation("巡检维修动态")
    @AnonymousGetMapping("/inspect")
    public Response<PageResult<HydFacilityInspect>> inspect(@ApiParam(value = "年份") @RequestParam String year, @RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        // HydFacilityInspect
        return Response.ok(hydFacilityInspectService.queryAll(page, size));
    }

    @ApiOperation("本年度巡检维修详细数据")
    @AnonymousGetMapping("/inspectMaintenance")
    public Response<List<HydFacilityDistrictMonth>> inspectMaintenance(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityDistrict
        // HydFacilityDistrictMonth
        return Response.ok(hydFacilityDistrictMonthService.queryAll());
    }
}
