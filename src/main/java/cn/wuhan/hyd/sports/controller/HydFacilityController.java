package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.resp.*;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private IHydResultStadiumMapPointService mapPointService;
    @Resource
    private IHydResultFacilityInspectService hydFacilityInspectService;
    @Resource
    private IHydResultFacilityDistrictService hydFacilityDistrictService;
    @Autowired
    private IHydResultFacilityDistrictMonthService hydFacilityDistrictMonthService;

    @ApiOperation("健身点位")
    @AnonymousGetMapping("/fitnessOverview")
    public Response<FacilityFitnessOverviewResp> fitnessOverview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        HydResultFacilityYear source = hydFacilityYearService.fitnessOverview(year);
        FacilityFitnessOverviewResp target = new FacilityFitnessOverviewResp();
        if (source != null) {
            BeanUtils.copyProperties(source, target);
            return Response.ok(target);
        } else {
            return Response.fail("未找到对应年份的数据");
        }
    }

    @ApiOperation("体育设施全貌")
    @AnonymousGetMapping("/facilityOverview")
    public Response<List<FacilityOverviewResp>> facilityOverview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultFacility> facilities = hydFacilityService.facilityOverview(year);
        List<FacilityOverviewResp> result = facilities.stream().map(source -> {
            FacilityOverviewResp target = new FacilityOverviewResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("设施类型名称列表")
    @AnonymousGetMapping("/allTypeName")
    public Response<List<String>> allTypeName(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydFacilityService.allTypeName(year));
    }

    @ApiOperation("各区分布")
    @AnonymousGetMapping("/districtDistribution")
    public Response<List<FacilityDistrictResp>> districtDistribution(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year,
            @ApiParam(value = "设施类型名称（如2025）", required = true)
            @NotBlank(message = "设施类型名称不能为空")
            @RequestParam String typeName) {
        // HydFacilityDistrictMonth
        List<HydResultFacilityDistrict> facilityDistricts = hydFacilityDistrictService.districtDistribution(year, typeName);
        List<FacilityDistrictResp> result = facilityDistricts.stream().map(source -> {
            FacilityDistrictResp target = new FacilityDistrictResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("基础设施点位地图")
    @AnonymousGetMapping("/mapPoint")
    public Response<List<FacilityMapPoint>> mapPoint(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultStadiumMapPoint> stadiumMapPoints = mapPointService.queryAll();
        List<FacilityMapPoint> result = stadiumMapPoints.stream().map(source -> {
            FacilityMapPoint target = new FacilityMapPoint();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("巡检维修动态")
    @AnonymousGetMapping("/inspect")
    public Response<PageResult<FacilityInspectResp>> inspect(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PageResult<HydResultFacilityInspect> inspectPageResult = hydFacilityInspectService.queryAll(year, page, size);
        List<HydResultFacilityInspect> content = inspectPageResult.getContent();
        List<FacilityInspectResp> newContents = content.stream().map(source -> {
            FacilityInspectResp target = new FacilityInspectResp();
            BeanUtils.copyProperties(source, target);
            return target;
        }).collect(Collectors.toList());
        PageResult<FacilityInspectResp> pageResult = new PageResult<>();
        pageResult.setTotalElements(inspectPageResult.getTotalElements());
        pageResult.setContent(newContents);
        return Response.ok(pageResult);
    }

    @ApiOperation("本年度巡检维修详细数据")
    @AnonymousGetMapping("/inspectMaintenance")
    public Response<List<FacilityDistrictMonthResp>> inspectMaintenance(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultFacilityDistrictMonth> districtMonths = hydFacilityDistrictMonthService.inspectMaintenance(year);
        List<FacilityDistrictMonthResp> result = districtMonths.stream().map(source -> {
            FacilityDistrictMonthResp target = new FacilityDistrictMonthResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }


}
