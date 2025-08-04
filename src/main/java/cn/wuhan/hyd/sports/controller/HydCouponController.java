package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 功能说明：体育消费卷  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月03日 <br>
 */
@RestController
@RequestMapping("/api/coupon")
@Api(tags = "体育消费券")
public class HydCouponController {

    @ApiOperation("概览")
    @AnonymousGetMapping("/overview")
    public Response<Map<String, Object>> overview(@ApiParam(value = "年份") @RequestParam String year) {
        // HydCouponAmount
        return null;
    }

    @ApiOperation("健身点位")
    @AnonymousGetMapping("/overview")
    public Response<Map<String, Object>> xxx(@ApiParam(value = "年份") @RequestParam String year) {
        // HydCouponAmount
        return null;
    }

}
