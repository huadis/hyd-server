package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.resp.*;
import cn.wuhan.hyd.sports.service.IHydYktService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
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
 * 功能说明： 青少年技能培训 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@RestController
@RequestMapping("/api/ykt")
@Api(tags = "青少年技能培训")
public class HydYktController {

    @Resource
    private IHydYktService yktService;

    @ApiOperation("从订单表刷新青少年技能培训结果表")
    @AnonymousGetMapping("/refresh")
    public Response<Boolean> refresh() {
        yktService.syncResultData();
        return Response.ok(true);
    }

    @ApiOperation("各区机构数量统计")
    @AnonymousGetMapping("/districtStat")
    public Response<List<YktDistrictStatResp>> districtStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderYktDistrictStat> districtStats = yktService.listDistrict(year);
        List<YktDistrictStatResp> result = districtStats.stream().map(source -> {
            YktDistrictStatResp target = new YktDistrictStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("性别统计")
    @AnonymousGetMapping("/userSexStat")
    public Response<List<YktUserSexStatResp>> userSexStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderYktUserSexStat> userSexStats = yktService.listUserSex(year);
        List<YktUserSexStatResp> result = userSexStats.stream().map(source -> {
            YktUserSexStatResp target = new YktUserSexStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("热门项目机构数量统计")
    @AnonymousGetMapping("/projectStat")
    public Response<List<YktProjectStatResp>> projectStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderYktProjectStat> projectStats = yktService.listProject(year);
        List<YktProjectStatResp> result = projectStats.stream().map(source -> {
            YktProjectStatResp target = new YktProjectStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("课程热度排行")
    @AnonymousGetMapping("/courseStat")
    public Response<List<YktCourseStatResp>> courseStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderYktCourseStat> courseStats = yktService.courseTop5(year);
        List<YktCourseStatResp> result = courseStats.stream().map(source -> {
            YktCourseStatResp target = new YktCourseStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("年龄分布")
    @AnonymousGetMapping("/userAgeStat")
    public Response<List<YktUserAgeStatResp>> userAgeStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderYktUserAgeStat> userAgeStats = yktService.listUserAge(year);
        List<YktUserAgeStatResp> result = userAgeStats.stream().map(source -> {
            YktUserAgeStatResp target = new YktUserAgeStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("培训场馆销售统计")
    @AnonymousGetMapping("/stadiumStat")
    public Response<List<YktStadiumStatResp>> stadiumStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderYktStadiumStat> stadiumStats = yktService.stadiumTop10(year);
        List<YktStadiumStatResp> result = stadiumStats.stream().map(source -> {
            YktStadiumStatResp target = new YktStadiumStatResp();
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
