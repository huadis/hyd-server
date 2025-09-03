package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.HydOriginLaStadium;
import cn.wuhan.hyd.sports.service.IHydOriginLaStadiumService;
import cn.wuhan.hyd.sports.service.IHydResultLaStadiumStatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@RestController
@RequestMapping("/api/laStadium")
@Api(tags = "校外培训机构")
public class HydLaStadiumController {

    @Resource
    private IHydOriginLaStadiumService laStadiumService;

    @Resource
    private IHydResultLaStadiumStatService laStadiumStatService;

    @ApiOperation("手动刷新结果表")
    @AnonymousGetMapping("/refresh")
    public Response<Boolean> refresh() {
        laStadiumStatService.syncResultData();
        return Response.ok(true);
    }

    /**
     * 所有场馆信息
     */
    @ApiOperation("所有场馆信息")
    @AnonymousGetMapping("/stadiums")
    public Response<List<HydOriginLaStadium>> stadiums() {
        return Response.ok(laStadiumService.queryAll());
    }

    /**
     * 所有培训机构信息
     */
    @ApiOperation("所有培训机构信息")
    @AnonymousGetMapping("/laStadiums")
    public Response<List<HydOriginLaStadium>> laStadiums() {
        return Response.ok(laStadiumService.queryAll());
    }

    /**
     * 各区场馆数量统计
     */
    @ApiOperation("各区场馆数量统计")
    @AnonymousGetMapping("/districtStadiumStat")
    public Response<List<Map<String, Object>>> districtStadiumStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(laStadiumStatService.stadiumCountByDistrict(year));
    }

    /**
     * 项目类型占比TOP10
     */
    @ApiOperation("项目类型占比TOP10")
    @AnonymousGetMapping("/sportNameTop10")
    public Response<List<Map<String, Object>>> sportNameTop10(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(laStadiumStatService.itemCountTop10BySportName(year));
    }

    /**
     * 项目类型占比Top5和其他
     */
    @ApiOperation("项目类型占比Top5和其他")
    @AnonymousGetMapping("/sportNameTop5AndOther")
    public Response<List<Map<String, Object>>> sportNameTop5AndOther(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(laStadiumStatService.itemCountBySportName(year));
    }
}
