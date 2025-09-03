package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.MapUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 首页数据 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@RestController
@RequestMapping("/api/home")
@Api(tags = "首页数据")
public class HomeController {

    @Resource
    private IHydResultLaStadiumStatService laStadiumStatService;
    @Resource
    private IHydResultStadiumDistrictService stadiumDistrictService;
    @Resource
    private IHydExcelPublicEventsService publicEventsService;
    @Resource
    private IHydExcelSportsOrgService sportsOrgService;

    @ApiOperation("数据总览")
    @AnonymousGetMapping("/dataOverview")
    public Response<Map<String, Integer>> dataOverview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<Map<String, Object>> list1 = laStadiumStatService.stadiumCountByDistrict(year);
        List<HydResultStadiumDistrict> list2 = stadiumDistrictService.countStadiumDistrict(year);
        List<Map<String, Object>> list3 = publicEventsService.districtCountByYear(year);
        // 体育组织
        List<Map<String, Object>> list4 = sportsOrgService.districtCountByYear(year);
        Integer tenantCount = 0;
        for (Map<String, Object> map : list1) {
            tenantCount += MapUtils.getInteger(map, "stadiumNum", 0);
        }
        int stadiumCount = 0;
        for (HydResultStadiumDistrict district : list2) {
            stadiumCount += Integer.parseInt(district.getStadiumNum());
        }
        Integer eventCount = 0;
        for (Map<String, Object> map : list3) {
            eventCount += MapUtils.getInteger(map, "num", 0);
        }
        Integer orgCount = 0;
        for (Map<String, Object> map : list4) {
            orgCount += MapUtils.getInteger(map, "districtNum", 0);
        }
        Map<String, Integer> result = new HashMap<>();
        result.put("tenantCount", tenantCount);
        result.put("stadiumCount", stadiumCount);
        result.put("eventCount", eventCount);
        result.put("orgCount", orgCount);
        return Response.ok(result);
    }


}
