package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.req.*;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 功能说明： 数据导入 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月04日 <br>
 */
@RestController
@RequestMapping("/api/batchImport")
@Api(tags = "数据导入")
public class ImportController {

    @Resource
    private IHydResultCouponAmountService couponAmountService;
    @Resource
    private IHydResultCouponStadiumTopService couponStadiumTopService;
    @Resource
    private IHydResultStadiumMapPointService stadiumMapPointService;
    @Resource
    private IHydResultCouponUserAgeService couponUserAgeService;
    @Resource
    private IHydResultCouponUserService couponUserService;
    @Resource
    private IHydResultFacilityDistrictMonthService facilityDistrictMonthService;
    @Resource
    private IHydResultFacilityDistrictService facilityDistrictService;
    @Resource
    private IHydResultFacilityInspectService facilityInspectService;
    @Resource
    private IHydResultFacilityService facilityService;
    @Resource
    private IHydResultFacilityYearService facilityYearService;
    @Resource
    private IHydResultOrderMonthService orderMonthService;
    @Resource
    private IHydResultOrderService orderService;
    @Resource
    private IHydResultOrderSportService orderSportService;
    @Resource
    private IHydResultOrderStadiumService orderStadiumService;
    @Resource
    private IHydResultStadiumDistrictService stadiumDistrictService;
    @Resource
    private IHydResultStadiumMapService stadiumMapService;
    @Resource
    private IHydResultStadiumService stadiumService;
    @Resource
    private IHydResultStadiumSportCouponService stadiumSportCouponService;
    @Resource
    private IHydResultStockService stockService;
    @Resource
    private IHydResultUserAgeService userAgeService;
    @Resource
    private IHydResultUserChannelService userChannelService;
    @Resource
    private IHydResultUserRegisterService userRegisterService;
    @Resource
    private IHydResultUserRepurchaseService userRepurchaseService;
    @Resource
    private IHydResultUserSexService userSexService;
    @Resource
    private IHydOriginLaStadiumFileService laStadiumFileService;
    @Resource
    private IHydOriginLaStadiumService laStadiumService;
    @Resource
    private IHydOriginOrderService originOrderService;
    @Resource
    private IHydOriginStadiumItemService stadiumItemService;
    @Resource
    private IHydOriginStadiumService originStadiumService;
    @Resource
    private IHydOriginTenantService tenantService;
    @Resource
    private IHydOriginTrainingActivityItemService trainingActivityItemService;
    @Resource
    private IHydOriginTrainingActivityItemStadiumService trainingActivityItemStadiumService;
    @Resource
    private IHydOriginTrainingActivityService trainingActivityService;
    @Resource
    private IHydOriginTrainingCourseService trainingCourseService;

    private final Logger logger = LoggerFactory.getLogger(ImportController.class);

    // ------------------------------------- 体育基础设施 -------------------------------------

    @ApiOperation(value = "体育基础设施-健身点位年数据-批量导入")
    @AnonymousPostMapping("/facilityYear")
    public Response<Boolean> facilityYearImport(@RequestBody List<HydResultFacilityYearReq> facilityYears) {
        logger.info("体育基础设施-健身点位年数据-批量导入, 条数 : {}", facilityYears.size());
        try {
            return Response.ok(facilityYearService.batchSave(facilityYears) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育基础设施-设施全貌-批量导入")
    @AnonymousPostMapping("/facility")
    public Response<Boolean> facilityImport(@RequestBody List<HydResultFacilityReq> facilities) {
        logger.info("体育基础设施-设施全貌-批量导入, 条数 : {}", facilities.size());
        try {
            return Response.ok(facilityService.batchSave(facilities) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育基础设施-设施各区分布-批量导入")
    @AnonymousPostMapping("/facilityDistrict")
    public Response<Boolean> facilityDistrictImport(@RequestBody List<HydResultFacilityDistrictReq> facilityDistricts) {
        logger.info("体育基础设施-设施各区分布-批量导入, 条数 : {}", facilityDistricts.size());
        try {
            return Response.ok(facilityDistrictService.batchSave(facilityDistricts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-电子地图点位信息-批量导入")
    @AnonymousPostMapping("/stadiumMapPointImport")
    public Response<Boolean> stadiumMapPointImport(@RequestBody List<HydResultStadiumMapPoint> stadiumMapPoints) {
        logger.info("结果表-电子地图点位信息-批量导入, 条数 : {}", stadiumMapPoints.size());
        try {
            return Response.ok(stadiumMapPointService.batchSave(stadiumMapPoints) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }


    @ApiOperation("结果表-电子地图-批量导入")
    @AnonymousPostMapping("/stadiumMap")
    public Response<Boolean> stadiumMapImport(@RequestBody List<HydResultStadiumMap> stadiumMaps) {
        logger.info("结果表-电子地图-批量导入, 条数 : {}", stadiumMaps.size());
        try {
            return Response.ok(stadiumMapService.batchSave(stadiumMaps) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育基础设施-巡检维修动态-批量导入")
    @AnonymousPostMapping("/facilityInspect")
    public Response<Boolean> facilityInspectImport(@RequestBody List<HydResultFacilityInspectReq> facilityInspects) {
        logger.info("体育基础设施-巡检维修动态-批量导入, 条数 : {}", facilityInspects.size());
        try {
            return Response.ok(facilityInspectService.batchSave(facilityInspects) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育基础设施-设施各区月数据-批量导入")
    @AnonymousPostMapping("/facilityDistrictMonth")
    public Response<Boolean> facilityDistrictMonthImport(@RequestBody List<HydResultFacilityDistrictMonthReq> facilityDistrictMonths) {
        logger.info("体育基础设施-设施各区月数据-批量导入, 条数 : {}", facilityDistrictMonths.size());
        try {
            return Response.ok(facilityDistrictMonthService.batchSave(facilityDistrictMonths) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    // ------------------------------------- 体育消费卷 -------------------------------------

    @ApiOperation(value = "体育消费卷-消费券总金额-批量导入")
    @AnonymousPostMapping("/couponAmount")
    public Response<Boolean> couponAmountImport(@RequestBody List<HydResultCouponAmountReq> couponAmounts) {
        logger.info("体育消费卷-消费券总金额-批量导入, 条数 : {}", couponAmounts.size());
        try {
            return Response.ok(couponAmountService.batchSave(couponAmounts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-消费券领卷用券-批量导入")
    @AnonymousPostMapping("/stock")
    public Response<Boolean> stockImport(@RequestBody List<HydResultStockReq> stocks) {
        logger.info("体育消费卷-消费券领卷用券-批量导入, 条数 : {}", stocks.size());
        try {
            return Response.ok(stockService.batchSave(stocks) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-项目消费券订单金额Top5-批量导入")
    @AnonymousPostMapping("/orderSport")
    public Response<Boolean> orderSportImport(@RequestBody List<HydResultOrderSportReq> orderSports) {
        logger.info("体育消费卷-项目消费券订单金额Top5-批量导入, 条数 : {}", orderSports.size());
        try {
            return Response.ok(orderSportService.batchSave(orderSports) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-场馆消费券订单金额Top5-批量导入")
    @AnonymousPostMapping("/orderStadium")
    public Response<Boolean> orderStadiumImport(@RequestBody List<HydResultOrderStadiumReq> orderStadiums) {
        logger.info("体育消费卷-场馆消费券订单金额Top5-批量导入, 条数 : {}", orderStadiums.size());
        try {
            return Response.ok(orderStadiumService.batchSave(orderStadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-订单数量-批量导入")
    @AnonymousPostMapping("/order")
    public Response<Boolean> orderImport(@RequestBody List<HydResultOrderReq> orders) {
        logger.info("体育消费卷-订单数量-批量导入, 条数 : {}", orders.size());
        try {
            return Response.ok(orderService.batchSave(orders) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-订单趋势-批量导入")
    @AnonymousPostMapping("/orderMonth")
    public Response<Boolean> orderMonthImport(@RequestBody List<HydResultOrderMonthReq> orderMonths) {
        logger.info("体育消费卷-订单趋势-批量导入, 条数 : {}", orderMonths.size());
        try {
            return Response.ok(orderMonthService.batchSave(orderMonths) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-券用户年龄分布-批量导入")
    @AnonymousPostMapping("/couponUserAge")
    public Response<Boolean> couponUserAgeImport(@RequestBody List<HydResultCouponUserAge> couponUserAges) {
        logger.info("体育消费卷-券用户年龄分布-批量导入, 条数 : {}", couponUserAges.size());
        try {
            return Response.ok(couponUserAgeService.batchSave(couponUserAges) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation(value = "体育消费卷-券用户分析-批量导入")
    @AnonymousPostMapping("/couponUser")
    public Response<Boolean> couponUserImport(@RequestBody List<HydResultCouponUser> couponUsers) {
        logger.info("体育消费卷-券用户分析-批量导入, 条数 : {}", couponUsers.size());
        try {
            return Response.ok(couponUserService.batchSave(couponUsers) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    // ------------------------------------- 场馆预定 -------------------------------------

    @ApiOperation("场馆预定-在线场馆各区情况-批量导入")
    @AnonymousPostMapping("/stadiumDistrict")
    public Response<Boolean> stadiumDistrictImport(@RequestBody List<HydResultStadiumDistrict> stadiumDistricts) {
        logger.info("场馆预定-在线场馆各区情况-批量导入, 条数 : {}", stadiumDistricts.size());
        try {
            return Response.ok(stadiumDistrictService.batchSave(stadiumDistricts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆预定-消费券场馆预订Top-批量导入")
    @AnonymousPostMapping("/couponStadiumTop")
    public Response<Boolean> couponStadiumTopImport(@RequestBody List<HydResultCouponStadiumTop> couponStadiumTops) {
        logger.info("场馆预定-消费券场馆预订Top-批量导入, 条数 : {}", couponStadiumTops.size());
        try {
            return Response.ok(couponStadiumTopService.batchSave(couponStadiumTops) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-在线场馆数量-批量导入")
    @AnonymousPostMapping("/stadium")
    public Response<Boolean> stadiumImport(@RequestBody List<HydResultStadium> stadiums) {
        logger.info("结果表-在线场馆数量-批量导入, 条数 : {}", stadiums.size());
        try {
            return Response.ok(stadiumService.batchSave(stadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-运动项目分布用券数占比-批量导入")
    @AnonymousPostMapping("/stadiumSportCoupon")
    public Response<Boolean> stadiumSportCouponImport(@RequestBody List<HydResultStadiumSportCoupon> stadiumSportCoupons) {
        logger.info("结果表-运动项目分布用券数占比-批量导入, 条数 : {}", stadiumSportCoupons.size());
        try {
            return Response.ok(stadiumSportCouponService.batchSave(stadiumSportCoupons) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆预定-年龄占比-批量导入")
    @AnonymousPostMapping("/userAge")
    public Response<Boolean> userAgeImport(@RequestBody List<HydResultUserAge> userAges) {
        logger.info("场馆预定-年龄占比-批量导入, 条数 : {}", userAges.size());
        try {
            return Response.ok(userAgeService.batchSave(userAges) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆预定-用户来源渠道-批量导入")
    @AnonymousPostMapping("/userChannel")
    public Response<Boolean> userChannelImport(@RequestBody List<HydResultUserChannel> userChannels) {
        logger.info("场馆预定-用户来源渠道-批量导入, 条数 : {}", userChannels.size());
        try {
            return Response.ok(userChannelService.batchSave(userChannels) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆预定-每月新增用户-批量导入")
    @AnonymousPostMapping("/userRegister")
    public Response<Boolean> userRegisterImport(@RequestBody List<HydResultUserRegister> userRegisters) {
        logger.info("场馆预定-每月新增用户-批量导入, 条数 : {}", userRegisters.size());
        try {
            return Response.ok(userRegisterService.batchSave(userRegisters) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆预定-复购率-批量导入")
    @AnonymousPostMapping("/userRepurchase")
    public Response<Boolean> userRepurchaseImport(@RequestBody List<HydResultUserRepurchase> userRepurchases) {
        logger.info("场馆预定-复购率-批量导入, 条数 : {}", userRepurchases.size());
        try {
            return Response.ok(userRepurchaseService.batchSave(userRepurchases) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("场馆预定-男女占比-批量导入")
    @AnonymousPostMapping("/userSex")
    public Response<Boolean> userSexImport(@RequestBody List<HydResultUserSex> userSexes) {
        logger.info("场馆预定-男女占比-批量导入, 条数 : {}", userSexes.size());
        try {
            return Response.ok(userSexService.batchSave(userSexes) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    // ------------------------------------- 原始表 -------------------------------------

    @ApiOperation("原始表-校外培训机构附件表-批量导入")
    @AnonymousPostMapping("/laStadiumFiles")
    public Response<Boolean> laStadiumFilesImport(@RequestBody List<HydOriginLaStadiumFile> laStadiumFiles) {
        logger.info("原始表-校外培训机构附件表-批量导入, 条数 : {}", laStadiumFiles.size());
        try {
            return Response.ok(laStadiumFileService.batchSave(laStadiumFiles) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-校外培训机构-批量导入")
    @AnonymousPostMapping("/laStadiums")
    public Response<Boolean> laStadiumsImport(@RequestBody List<HydOriginLaStadium> laStadiums) {
        logger.info("原始表-校外培训机构-批量导入, 条数 : {}", laStadiums.size());
        try {
            return Response.ok(laStadiumService.batchSave(laStadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-订单-批量导入")
    @AnonymousPostMapping("/orders")
    public Response<Boolean> ordersImport(@RequestBody List<HydOriginOrder> orders) {
        logger.info("原始表-订单-批量导入, 条数 : {}", orders.size());
        try {
            return Response.ok(originOrderService.batchSave(orders) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-场馆培训项目-批量导入")
    @AnonymousPostMapping("/stadiumItems")
    public Response<Boolean> stadiumItemsImport(@RequestBody List<HydOriginStadiumItem> stadiumItems) {
        logger.info("原始表-场馆培训项目-批量导入, 条数 : {}", stadiumItems.size());
        try {
            return Response.ok(stadiumItemService.batchSave(stadiumItems) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-培训场馆-批量导入")
    @AnonymousPostMapping("/stadiums")
    public Response<Boolean> stadiumsImport(@RequestBody List<HydOriginStadium> stadiums) {
        logger.info("原始表-培训场馆-批量导入, 条数 : {}", stadiums.size());
        try {
            return Response.ok(originStadiumService.batchSave(stadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-组织机构-批量导入")
    @AnonymousPostMapping("/tenants")
    public Response<Boolean> tenantsImport(@RequestBody List<HydOriginTenant> tenants) {
        logger.info("原始表-组织机构-批量导入, 条数 : {}", tenants.size());
        try {
            return Response.ok(tenantService.batchSave(tenants) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-培训活动支持的项目-批量导入")
    @AnonymousPostMapping("/trainingActivityItems")
    public Response<Boolean> trainingActivityItemsImport(@RequestBody List<HydOriginTrainingActivityItem> trainingActivityItems) {
        logger.info("原始表-培训活动支持的项目-批量导入, 条数 : {}", trainingActivityItems.size());
        try {
            return Response.ok(trainingActivityItemService.batchSave(trainingActivityItems) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-培训活动场馆支持的项目-批量导入")
    @AnonymousPostMapping("/trainingActivityItemStadiums")
    public Response<Boolean> trainingActivityItemStadiumsImport(@RequestBody List<HydOriginTrainingActivityItemStadium> trainingActivityItemStadiums) {
        logger.info("原始表-培训活动场馆支持的项目-批量导入, 条数 : {}", trainingActivityItemStadiums.size());
        try {
            return Response.ok(trainingActivityItemStadiumService.batchSave(trainingActivityItemStadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-培训活动-批量导入")
    @AnonymousPostMapping("/trainingActivities")
    public Response<Boolean> trainingActivitiesImport(@RequestBody List<HydOriginTrainingActivity> trainingActivities) {
        logger.info("原始表-培训活动-批量导入, 条数 : {}", trainingActivities.size());
        try {
            return Response.ok(trainingActivityService.batchSave(trainingActivities) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("原始表-培训课程-批量导入")
    @AnonymousPostMapping("/trainingCourses")
    public Response<Boolean> trainingCoursesImport(@RequestBody List<HydOriginTrainingCourse> trainingCourses) {
        logger.info("原始表-培训课程-批量导入, 条数 : {}", trainingCourses.size());
        try {
            return Response.ok(trainingCourseService.batchSave(trainingCourses) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }
}
