package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.HydCouponAmount;
import cn.wuhan.hyd.sports.service.IHydCouponAmountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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
    private IHydCouponAmountService hydCouponAmountService;

    @ApiOperation("概览")
    @AnonymousGetMapping("/overview")
    public Response<HydCouponAmount> overview(@ApiParam(value = "年份") @RequestParam String year) {
        // HydCouponAmount
        return Response.ok(hydCouponAmountService.findLatestCouponAmount());
    }

}
