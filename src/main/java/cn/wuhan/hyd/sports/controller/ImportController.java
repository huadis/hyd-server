package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月04日 <br>
 */
@RestController
@RequestMapping("/api/batchImport")
@Api(tags = "数据导入")
public class ImportController {

    @Resource
    private IHydCouponAmountService hydCouponAmountService;
    @Resource
    private IHydCouponStadiumTopService hydCouponStadiumTopService;
    @Resource
    private IHydCouponUserAgeService hydCouponUserAgeService;
    @Resource
    private IHydCouponUserService hydCouponUserService;
    @Resource
    private IHydFacilityDistrictMonthService hydFacilityDistrictMonthService;
    @Resource
    private IHydFacilityDistrictService hydFacilityDistrictService;
    @Resource
    private IHydFacilityInspectService hydFacilityInspectService;
    @Resource
    private IHydFacilityService hydFacilityService;
    @Resource
    private IHydFacilityYearService hydFacilityYearService;
    @Resource
    private IHydOrderMonthService hydOrderMonthService;
    @Resource
    private IHydOrderService hydOrderService;
    @Resource
    private IHydOrderSportService hydOrderSportService;
    @Resource
    private IHydOrderStadiumService hydOrderStadiumService;
    @Resource
    private IHydStadiumDistrictService hydStadiumDistrictService;
    @Resource
    private IHydStadiumMapService hydStadiumMapService;
    @Resource
    private IHydStadiumService hydStadiumService;
    @Resource
    private IHydStadiumSportCouponService hydStadiumSportCouponService;
    @Resource
    private IHydStockService hydStockService;
    @Resource
    private IHydUserAgeService hydUserAgeService;
    @Resource
    private IHydUserChannelService hydUserChannelService;
    @Resource
    private IHydUserRegisterService hydUserRegisterService;
    @Resource
    private IHydUserRepurchaseService hydUserRepurchaseService;
    @Resource
    private IHydUserSexService hydUserSexService;

    @ApiOperation("消费券总金额-批量导入")
    @AnonymousPostMapping("/couponAmount")
    public Response<Boolean> couponAmountImport(@RequestBody List<HydCouponAmount> couponAmounts) {
        try {
            return Response.ok(hydCouponAmountService.batchSave(couponAmounts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("消费券场馆预订Top-批量导入")
    @AnonymousPostMapping("/couponStadiumTop")
    public Response<Boolean> couponStadiumTopImport(@RequestBody List<HydCouponStadiumTop> couponStadiumTops) {
        try {
            return Response.ok(hydCouponStadiumTopService.batchSave(couponStadiumTops) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("券用户年龄分布-批量导入")
    @AnonymousPostMapping("/couponUserAge")
    public Response<Boolean> couponUserAgeImport(@RequestBody List<HydCouponUserAge> couponUserAges) {
        try {
            return Response.ok(hydCouponUserAgeService.batchSave(couponUserAges) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("券用户分析-批量导入")
    @AnonymousPostMapping("/couponUser")
    public Response<Boolean> couponUserImport(@RequestBody List<HydCouponUser> couponUsers) {
        try {
            return Response.ok(hydCouponUserService.batchSave(couponUsers) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("设施各区月数据-批量导入")
    @AnonymousPostMapping("/facilityDistrictMonth")
    public Response<Boolean> facilityDistrictMonthImport(@RequestBody List<HydFacilityDistrictMonth> facilityDistrictMonths) {
        try {
            return Response.ok(hydFacilityDistrictMonthService.batchSave(facilityDistrictMonths) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("设施各区分布-批量导入")
    @AnonymousPostMapping("/facilityDistrict")
    public Response<Boolean> facilityDistrictImport(@RequestBody List<HydFacilityDistrict> facilityDistricts) {
        try {
            return Response.ok(hydFacilityDistrictService.batchSave(facilityDistricts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("巡检动态-批量导入")
    @AnonymousPostMapping("/facilityInspect")
    public Response<Boolean> facilityInspectImport(@RequestBody List<HydFacilityInspect> facilityInspects) {
        try {
            return Response.ok(hydFacilityInspectService.batchSave(facilityInspects) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("设施全貌-批量导入")
    @AnonymousPostMapping("/facility")
    public Response<Boolean> facilityImport(@RequestBody List<HydFacility> facilities) {
        try {
            return Response.ok(hydFacilityService.batchSave(facilities) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("健身点位年数据-批量导入")
    @AnonymousPostMapping("/facilityYear")
    public Response<Boolean> facilityYearImport(@RequestBody List<HydFacilityYear> facilityYears) {
        try {
            return Response.ok(hydFacilityYearService.batchSave(facilityYears) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("订单趋势-批量导入")
    @AnonymousPostMapping("/orderMonth")
    public Response<Boolean> orderMonthImport(@RequestBody List<HydOrderMonth> orderMonths) {
        try {
            return Response.ok(hydOrderMonthService.batchSave(orderMonths) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("订单数量-批量导入")
    @AnonymousPostMapping("/order")
    public Response<Boolean> orderImport(@RequestBody List<HydOrder> orders) {
        try {
            return Response.ok(hydOrderService.batchSave(orders) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("项目消费券订单金额Top5-批量导入")
    @AnonymousPostMapping("/orderSport")
    public Response<Boolean> orderSportImport(@RequestBody List<HydOrderSport> orderSports) {
        try {
            return Response.ok(hydOrderSportService.batchSave(orderSports) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆消费券订单金额Top5-批量导入")
    @AnonymousPostMapping("/orderStadium")
    public Response<Boolean> orderStadiumImport(@RequestBody List<HydOrderStadium> orderStadiums) {
        try {
            return Response.ok(hydOrderStadiumService.batchSave(orderStadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("在线场馆各区情况-批量导入")
    @AnonymousPostMapping("/stadiumDistrict")
    public Response<Boolean> stadiumDistrictImport(@RequestBody List<HydStadiumDistrict> stadiumDistricts) {
        try {
            return Response.ok(hydStadiumDistrictService.batchSave(stadiumDistricts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("电子地图-批量导入")
    @AnonymousPostMapping("/stadiumMap")
    public Response<Boolean> stadiumMapImport(@RequestBody List<HydStadiumMap> stadiumMaps) {
        try {
            return Response.ok(hydStadiumMapService.batchSave(stadiumMaps) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("在线场馆数量-批量导入")
    @AnonymousPostMapping("/stadium")
    public Response<Boolean> stadiumImport(@RequestBody List<HydStadium> stadiums) {
        try {
            return Response.ok(hydStadiumService.batchSave(stadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("运动项目分布用券数占比-批量导入")
    @AnonymousPostMapping("/stadiumSportCoupon")
    public Response<Boolean> stadiumSportCouponImport(@RequestBody List<HydStadiumSportCoupon> stadiumSportCoupons) {
        try {
            return Response.ok(hydStadiumSportCouponService.batchSave(stadiumSportCoupons) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("消费券领用券-批量导入")
    @AnonymousPostMapping("/stock")
    public Response<Boolean> stockImport(@RequestBody List<HydStock> stocks) {
        try {
            return Response.ok(hydStockService.batchSave(stocks) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("年龄占比-批量导入")
    @AnonymousPostMapping("/userAge")
    public Response<Boolean> userAgeImport(@RequestBody List<HydUserAge> userAges) {
        try {
            return Response.ok(hydUserAgeService.batchSave(userAges) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("用户来源渠道-批量导入")
    @AnonymousPostMapping("/userChannel")
    public Response<Boolean> userChannelImport(@RequestBody List<HydUserChannel> userChannels) {
        try {
            return Response.ok(hydUserChannelService.batchSave(userChannels) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("每月新增用户-批量导入")
    @AnonymousPostMapping("/userRegister")
    public Response<Boolean> userRegisterImport(@RequestBody List<HydUserRegister> userRegisters) {
        try {
            return Response.ok(hydUserRegisterService.batchSave(userRegisters) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("复购率-批量导入")
    @AnonymousPostMapping("/userRepurchase")
    public Response<Boolean> userRepurchaseImport(@RequestBody List<HydUserRepurchase> userRepurchases) {
        try {
            return Response.ok(hydUserRepurchaseService.batchSave(userRepurchases) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("男女占比-批量导入")
    @AnonymousPostMapping("/userSex")
    public Response<Boolean> userSexImport(@RequestBody List<HydUserSex> userSexes) {
        try {
            return Response.ok(hydUserSexService.batchSave(userSexes) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }
}
