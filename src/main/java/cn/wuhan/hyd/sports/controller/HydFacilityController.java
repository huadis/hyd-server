package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

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
    private IHydResultFacilityYearService hydFacilityYearService;
    @Resource
    private IHydResultFacilityService hydFacilityService;
    @Resource
    private IHydResultFacilityInspectService hydFacilityInspectService;
    @Resource
    private IHydResultFacilityDistrictService hydFacilityDistrictService;
    @Autowired
    private IHydResultFacilityDistrictMonthService hydFacilityDistrictMonthService;

    @ApiOperation("健身点位")
    @AnonymousGetMapping("/fitnessOverview")
    public Response<HydResultFacilityYear> fitnessOverview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydFacilityYearService.fitnessOverview(year));
    }

    @ApiOperation("体育设施全貌")
    @AnonymousGetMapping("/facilityOverview")
    public Response<List<HydResultFacility>> facilityOverview(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacility
        return Response.ok(hydFacilityService.facility());
    }

    @ApiOperation("各区分布")
    @AnonymousGetMapping("/districtDistribution")
    public Response<List<HydResultFacilityDistrict>> districtDistribution(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityDistrictMonth
        return Response.ok(hydFacilityDistrictService.queryAll());
    }

    @ApiOperation("巡检维修动态")
    @AnonymousGetMapping("/inspect")
    public Response<PageResult<HydResultFacilityInspect>> inspect(@ApiParam(value = "年份") @RequestParam String year, @RequestParam(defaultValue = "0") int page,
                                                                  @RequestParam(defaultValue = "10") int size) {
        // HydFacilityInspect
        return Response.ok(hydFacilityInspectService.queryAll(page, size));
    }

    @ApiOperation("本年度巡检维修详细数据")
    @AnonymousGetMapping("/inspectMaintenance")
    public Response<List<HydResultFacilityDistrictMonth>> inspectMaintenance(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityDistrict
        // HydFacilityDistrictMonth
        return Response.ok(hydFacilityDistrictMonthService.queryAll());
    }
}
