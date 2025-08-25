package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.HydOriginStadium;
import cn.wuhan.hyd.sports.domain.HydResultOrderMonth;
import cn.wuhan.hyd.sports.domain.HydResultStadiumDistrict;
import cn.wuhan.hyd.sports.resp.OrderMonthTrendResp;
import cn.wuhan.hyd.sports.resp.OrderSportResp;
import cn.wuhan.hyd.sports.resp.OrderStadiumResp;
import cn.wuhan.hyd.sports.resp.StadiumDistrictCountResp;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

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
    private IHydResultUserChannelService hydUserChannelService;
    @Resource
    private IHydResultUserAgeService hydUserAgeService;
    @Resource
    private IHydResultUserSexService hydUserSexService;
    @Resource
    private IHydResultUserRepurchaseService hydUserRepurchaseService;
    @Resource
    private IHydResultUserRegisterService hydUserRegisterService;
    @Resource
    private IHydResultOrderSportService orderSportService;
    @Resource
    private IHydResultOrderStadiumService orderStadiumService;
    @Resource
    private IHydOriginStadiumService stadiumService;

    @Resource
    private IHydResultOrderMonthService orderMonthService;

    /**
     * 所有场馆信息
     */
    @ApiOperation("所有场馆信息")
    @AnonymousGetMapping("/stadiums")
    public Response<List<HydOriginStadium>> stadiums() {
        return Response.ok(stadiumService.queryAll());
    }

    /**
     * 各区场馆数量统计数据
     * 柱状图
     */
    @ApiOperation("各区场馆数量统计数据")
    @AnonymousGetMapping("/districtStat")
    public Response<List<StadiumDistrictCountResp>> countStadiumDistrict(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultStadiumDistrict> list = hydStadiumDistrictService.countStadiumDistrict(year);
        List<StadiumDistrictCountResp> result = new ArrayList<>();
        for (HydResultStadiumDistrict source : list) {
            StadiumDistrictCountResp target = new StadiumDistrictCountResp();
            BeanUtils.copyProperties(source, target);
            result.add(target);
        }
        return Response.ok(result);
    }

    @ApiOperation("项目消费卷订单金额TOP5")
    @AnonymousGetMapping("/orderStadiumStat")
    public Response<List<OrderSportResp>> orderStadiumStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<Map<String, Object>> list = orderSportService.projectTop5(year);
        List<OrderSportResp> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            OrderSportResp resp = new OrderSportResp();
            resp.setSportName(MapUtils.getString(map, "sportName"));
            resp.setOrderAmount(MapUtils.getString(map, "orderAmount"));
            result.add(resp);
        }
        return Response.ok(result);
    }

    @ApiOperation("场馆消费卷订单金额TOP5")
    @AnonymousGetMapping("/orderSportStat")
    public Response<List<OrderStadiumResp>> orderSportStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<Map<String, Object>> list = orderStadiumService.stadiumTop5(year);
        List<OrderStadiumResp> result = new ArrayList<>();
        for (Map<String, Object> map : list) {
            OrderStadiumResp resp = new OrderStadiumResp();
            resp.setStadiumName(MapUtils.getString(map, "stadiumName"));
            resp.setOrderAmount(MapUtils.getString(map, "orderAmount"));
            result.add(resp);
        }
        return Response.ok(result);
    }

    @ApiOperation("订单趋势统计")
    @AnonymousGetMapping("/orderTrend")
    public Response<List<OrderMonthTrendResp>> orderTrend(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        List<HydResultOrderMonth> list = orderMonthService.list(year);
        List<OrderMonthTrendResp> result = list.stream().map(source -> {
            OrderMonthTrendResp target = new OrderMonthTrendResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    /**
     * 用户
     * 饼图
     */
    @ApiOperation("用户")
    @AnonymousGetMapping("/userStat")
    public Response<Map<String, Object>> userStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        Map<String, Object> result = new HashMap<>();
        result.put("userInfo", hydUserChannelService.countStadiumUserChannelStat(year));
        result.put("userSex", hydUserSexService.countStadiumUserSexStat(year));
        result.put("userAge", hydUserAgeService.countStadiumUserAgeStat(year));
        return Response.ok(result);
    }

    /**
     * 复购率
     * 饼图
     */
    @ApiOperation("复购率")
    @AnonymousGetMapping("/repurchase")
    public Response<List<Map<String, Object>>> repurchase(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydUserRepurchaseService.countStadiumUserRepurchaseStat(year));
    }

    /**
     * 新增用户
     * 柱状图
     */
    @ApiOperation("新增用户")
    @AnonymousGetMapping("/userGrowth")
    public Response<List<Map<String, Object>>> userGrowth(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydUserRegisterService.countStadiumUserGrowthStat(year));
    }
}
