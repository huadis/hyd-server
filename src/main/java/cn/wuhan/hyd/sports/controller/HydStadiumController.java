package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 场馆预定 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@RestController
@RequestMapping("/api/stadium")
@Api(tags = "场馆预定")
public class HydStadiumController {

    @Resource
    private IHydResultStadiumDistrictService hydStadiumDistrictService;
    @Resource
    private IHydResultCouponStadiumTopService hydCouponStadiumTopService;
    @Resource
    private IHydResultOrderSportService hydOrderSportService;
    @Resource
    private IHydResultOrderService hydOrderService;
    @Resource
    private IHydResultUserChannelService hydUserChannelService;
    @Resource
    private IHydResultUserAgeService hydUserAgeService;
    @Resource
    private IHydResultUserSexService hydUserSexService;
    @Resource
    private IHydResultUserRepurchaseService hydUserRepurchaseService;
    @Resource
    private IHydResultUserRegisterService hydUserRegisterService;

    /**
     * 各区场馆数量统计数据
     * 柱状图
     */
    @ApiOperation("各区场馆数量统计数据")
    @AnonymousGetMapping("/districtStat")
    public Response<Map<String, Object>> countStadiumDistrict(@ApiParam(value = "年份") @RequestParam String year) {
        Map<String, Object> result = new HashMap<>();
        result.put("stadiumDistrict", hydStadiumDistrictService.countStadiumDistrict());
        result.put("projectTop5", hydOrderSportService.projectTop5());
        result.put("stadiumTop5", hydCouponStadiumTopService.stadiumTop5());
        result.put("orderStat", hydOrderService.orderStat());
        return Response.ok(result);
    }

    /**
     * 用户
     * 饼图
     */
    @ApiOperation("用户")
    @AnonymousGetMapping("/userStat")
    public Response<Map<String, Object>> userStat(@ApiParam(value = "年份") @RequestParam String year) {
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", hydUserChannelService.countStadiumUserChannelStat());
        result.put("userSex", hydUserSexService.countStadiumUserSexStat());
        result.put("userAge", hydUserAgeService.countStadiumUserAgeStat());
        return Response.ok(result);
    }

    /**
     * 复购率
     * 饼图
     */
    @ApiOperation("复购率")
    @AnonymousGetMapping("/repurchase")
    public Response<List<Map<String, Object>>> repurchase(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydUserRepurchaseService.countStadiumUserRepurchaseStat());
    }

    /**
     * 新增用户
     * 柱状图
     */
    @ApiOperation("新增用户")
    @AnonymousGetMapping("/userGrowth")
    public Response<List<Map<String, Object>>> userGrowth(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydUserRegisterService.countStadiumUserGrowthStat());
    }
}
