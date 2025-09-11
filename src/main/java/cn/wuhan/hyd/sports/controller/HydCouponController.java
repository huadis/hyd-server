package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.HydResultCouponAmount;
import cn.wuhan.hyd.sports.domain.HydResultCouponUserAge;
import cn.wuhan.hyd.sports.domain.HydResultOrderMonth;
import cn.wuhan.hyd.sports.domain.HydResultStock;
import cn.wuhan.hyd.sports.resp.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 功能说明：体育消费卷  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@RestController
@RequestMapping("/api/coupon")
@Api(tags = "体育消费券")
public class HydCouponController {

    @Resource
    private IHydResultCouponAmountService couponAmountService;
    @Resource
    private IHydResultStockService stockService;
    @Resource
    private IHydResultOrderService orderService;
    @Resource
    private IHydResultOrderSportService orderSportService;
    @Resource
    private IHydResultOrderStadiumService orderStadiumService;
    @Resource
    private IHydResultOrderMonthService orderMonthService;
    @Resource
    private IHydResultCouponUserService couponUserService;
    @Resource
    private IHydResultCouponUserAgeService couponUserAgeService;

    @ApiOperation("概览")
    @AnonymousGetMapping("/overview")
    public Response<CouponAmountOverviewResp> overview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        HydResultCouponAmount source = couponAmountService.findLatestCouponAmount(year);
        CouponAmountOverviewResp target = new CouponAmountOverviewResp();
        if (source != null) {
            BeanUtils.copyProperties(source, target);
            return Response.ok(target);
        } else {
            return Response.fail("未找到对应年份的数据");
        }
    }

    @ApiOperation("卷名列表")
    @AnonymousGetMapping("/allGroupName")
    public Response<List<String>> allGroupName(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(stockService.allGroupName(year));
    }

    @ApiOperation("消费卷领卷用卷统计")
    @AnonymousGetMapping("/stockGetAndUseStat")
    public Response<List<StockGetAndUseStatResp>> stockGetAndUseStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year,
            @ApiParam(value = "卷名", required = true)
            @NotBlank(message = "卷名不能为空") @RequestParam String groupName) {
        List<HydResultStock> stocks = stockService.queryAll(year, groupName);
        List<StockGetAndUseStatResp> result = stocks.stream().map(source -> {
            StockGetAndUseStatResp target = new StockGetAndUseStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        return Response.ok(result);
    }

    @ApiOperation("消费卷用卷订单统计")
    @AnonymousGetMapping("/stockUseOrderStat")
    public Response<List<StockUseOrderStatResp>> stockUseOrderStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year,
            @ApiParam(value = "卷名", required = true)
            @NotBlank(message = "卷名不能为空") @RequestParam String groupName) {
        List<HydResultStock> stocks = stockService.queryAll(year, groupName);
        List<StockUseOrderStatResp> result = stocks.stream().map(source -> {
            StockUseOrderStatResp target = new StockUseOrderStatResp();
            if (source != null) {
                BeanUtils.copyProperties(source, target);
                return target;
            } else {
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
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

    @ApiOperation("订单总数统计")
    @AnonymousGetMapping("/orderOverview")
    public Response<OrderOverviewResp> orderOverview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        Map<String, Object> map = orderService.overview(year);
        OrderOverviewResp resp = new OrderOverviewResp();
        resp.setOrderNum(MapUtils.getString(map, "orderNum"));
        resp.setOrderAmount(MapUtils.getString(map, "orderAmount"));
        resp.setCouponAmount(MapUtils.getString(map, "couponAmount"));
        return Response.ok(resp);
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

    @ApiOperation("用户性别统计")
    @AnonymousGetMapping("/userSex")
    public Response<CouponUserSexStatResp> userSex(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        Map<String, Object> map = couponUserService.latestCouponUser(year);
        CouponUserSexStatResp resp = new CouponUserSexStatResp();
        resp.setMaleNum(MapUtils.getString(map, "maleNum"));
        resp.setFemaleNum(MapUtils.getString(map, "femaleNum"));
        return Response.ok(resp);
    }

    @ApiOperation("用户领卷用卷统计")
    @AnonymousGetMapping("/useCouponGetAndUseStat")
    public Response<CouponGetAndUseStatResp> useCouponGetAndUseStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        Map<String, Object> map = couponUserService.latestCouponUser(year);
        CouponGetAndUseStatResp resp = new CouponGetAndUseStatResp();
        resp.setReceiveCouponNum(MapUtils.getString(map, "receiveCouponNum"));
        resp.setUseCouponNum(MapUtils.getString(map, "useCouponNum"));
        return Response.ok(resp);
    }

    @ApiOperation("用户年龄统计")
    @AnonymousGetMapping("/userAge")
    public Response<CouponUserAgeStatResp> userAge(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        HydResultCouponUserAge source = couponUserAgeService.latestCouponUserAge(year);
        CouponUserAgeStatResp target = new CouponUserAgeStatResp();
        if (source != null) {
            BeanUtils.copyProperties(source, target);
            return Response.ok(target);
        } else {
            return Response.fail("未找到对应年份的数据");
        }
    }

}
