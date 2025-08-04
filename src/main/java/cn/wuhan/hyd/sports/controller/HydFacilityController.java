package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.service.IHydFacilityYearService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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

    @ApiOperation("健身点位")
    @AnonymousGetMapping("/overview")
    public Response<Map<String, Object>> overview(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityYear
        return null;
    }

    @ApiOperation("体育设施全貌")
    @AnonymousGetMapping("/facility")
    public Response<Map<String, Object>> facility(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacility
        return null;
    }

    @ApiOperation("各区分布")
    @AnonymousGetMapping("/districtMonth")
    public Response<Map<String, Object>> district(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityDistrictMonth
        return null;
    }

    @ApiOperation("巡检维修动态")
    @AnonymousGetMapping("/districtStat")
    public Response<Map<String, Object>> xxx(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityInspect
        return null;
    }

    @ApiOperation("本年度巡检维修详细数据")
    @AnonymousGetMapping("/districtStat")
    public Response<Map<String, Object>> yyy(@ApiParam(value = "年份") @RequestParam String year) {
        // HydFacilityDistrict
        return null;
    }
}
