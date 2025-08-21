package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.*;
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
 * 功能说明：  <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月04日 <br>
 */
@RestController
@RequestMapping("/api/batchImport")
@Api(tags = "数据导入")
public class ImportController {

    @Resource
    private IHydResultCouponAmountService hydCouponAmountService;
    @Resource
    private IHydResultCouponStadiumTopService hydCouponStadiumTopService;
    @Resource
    private IHydResultStadiumMapPointService stadiumMapPointService;
    @Resource
    private IHydResultCouponUserAgeService hydCouponUserAgeService;
    @Resource
    private IHydResultCouponUserService hydCouponUserService;
    @Resource
    private IHydResultFacilityDistrictMonthService hydFacilityDistrictMonthService;
    @Resource
    private IHydResultFacilityDistrictService hydFacilityDistrictService;
    @Resource
    private IHydResultFacilityInspectService hydFacilityInspectService;
    @Resource
    private IHydResultFacilityService hydFacilityService;
    @Resource
    private IHydResultFacilityYearService hydFacilityYearService;
    @Resource
    private IHydResultOrderMonthService hydOrderMonthService;
    @Resource
    private IHydResultOrderService hydOrderService;
    @Resource
    private IHydResultOrderSportService hydOrderSportService;
    @Resource
    private IHydResultOrderStadiumService hydOrderStadiumService;
    @Resource
    private IHydResultStadiumDistrictService hydStadiumDistrictService;
    @Resource
    private IHydResultStadiumMapService hydStadiumMapService;
    @Resource
    private IHydResultStadiumService hydStadiumService;
    @Resource
    private IHydResultStadiumSportCouponService hydStadiumSportCouponService;
    @Resource
    private IHydResultStockService hydStockService;
    @Resource
    private IHydResultUserAgeService hydUserAgeService;
    @Resource
    private IHydResultUserChannelService hydUserChannelService;
    @Resource
    private IHydResultUserRegisterService hydUserRegisterService;
    @Resource
    private IHydResultUserRepurchaseService hydUserRepurchaseService;
    @Resource
    private IHydResultUserSexService hydUserSexService;
    @Resource
    private IHydOriginLaStadiumFileService laStadiumFileService;
    @Resource
    private IHydOriginLaStadiumService laStadiumService;
    @Resource
    private IHydOriginOrderService orderService;
    @Resource
    private IHydOriginStadiumItemService stadiumItemService;
    @Resource
    private IHydOriginStadiumService stadiumService;
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

    @ApiOperation("结果表-消费券总金额-批量导入")
    @AnonymousPostMapping("/couponAmount")
    public Response<Boolean> couponAmountImport(@RequestBody List<HydResultCouponAmount> couponAmounts) {
        logger.info("结果表-消费券总金额-批量导入, 条数 : {}", couponAmounts.size());
        try {
            return Response.ok(hydCouponAmountService.batchSave(couponAmounts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-消费券场馆预订Top-批量导入")
    @AnonymousPostMapping("/couponStadiumTop")
    public Response<Boolean> couponStadiumTopImport(@RequestBody List<HydResultCouponStadiumTop> couponStadiumTops) {
        logger.info("结果表-消费券场馆预订Top-批量导入, 条数 : {}", couponStadiumTops.size());
        try {
            return Response.ok(hydCouponStadiumTopService.batchSave(couponStadiumTops) > 0);
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

    @ApiOperation("结果表-券用户年龄分布-批量导入")
    @AnonymousPostMapping("/couponUserAge")
    public Response<Boolean> couponUserAgeImport(@RequestBody List<HydResultCouponUserAge> couponUserAges) {
        logger.info("结果表-券用户年龄分布-批量导入, 条数 : {}", couponUserAges.size());
        try {
            return Response.ok(hydCouponUserAgeService.batchSave(couponUserAges) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-券用户分析-批量导入")
    @AnonymousPostMapping("/couponUser")
    public Response<Boolean> couponUserImport(@RequestBody List<HydResultCouponUser> couponUsers) {
        logger.info("结果表-券用户分析-批量导入, 条数 : {}", couponUsers.size());
        try {
            return Response.ok(hydCouponUserService.batchSave(couponUsers) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-设施各区月数据-批量导入")
    @AnonymousPostMapping("/facilityDistrictMonth")
    public Response<Boolean> facilityDistrictMonthImport(@RequestBody List<HydResultFacilityDistrictMonth> facilityDistrictMonths) {
        logger.info("结果表-设施各区月数据-批量导入, 条数 : {}", facilityDistrictMonths.size());
        try {
            return Response.ok(hydFacilityDistrictMonthService.batchSave(facilityDistrictMonths) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-设施各区分布-批量导入")
    @AnonymousPostMapping("/facilityDistrict")
    public Response<Boolean> facilityDistrictImport(@RequestBody List<HydResultFacilityDistrict> facilityDistricts) {
        logger.info("结果表-设施各区分布-批量导入, 条数 : {}", facilityDistricts.size());
        try {
            return Response.ok(hydFacilityDistrictService.batchSave(facilityDistricts) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-巡检动态-批量导入")
    @AnonymousPostMapping("/facilityInspect")
    public Response<Boolean> facilityInspectImport(@RequestBody List<HydResultFacilityInspect> facilityInspects) {
        logger.info("结果表-巡检动态-批量导入, 条数 : {}", facilityInspects.size());
        try {
            return Response.ok(hydFacilityInspectService.batchSave(facilityInspects) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-设施全貌-批量导入")
    @AnonymousPostMapping("/facility")
    public Response<Boolean> facilityImport(@RequestBody List<HydResultFacility> facilities) {
        logger.info("结果表-设施全貌-批量导入, 条数 : {}", facilities.size());
        try {
            return Response.ok(hydFacilityService.batchSave(facilities) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-健身点位年数据-批量导入")
    @AnonymousPostMapping("/facilityYear")
    public Response<Boolean> facilityYearImport(@RequestBody List<HydResultFacilityYear> facilityYears) {
        logger.info("结果表-健身点位年数据-批量导入, 条数 : {}", facilityYears.size());
        try {
            return Response.ok(hydFacilityYearService.batchSave(facilityYears) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-订单趋势-批量导入")
    @AnonymousPostMapping("/orderMonth")
    public Response<Boolean> orderMonthImport(@RequestBody List<HydResultOrderMonth> orderMonths) {
        logger.info("结果表-订单趋势-批量导入, 条数 : {}", orderMonths.size());
        try {
            return Response.ok(hydOrderMonthService.batchSave(orderMonths) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-订单数量-批量导入")
    @AnonymousPostMapping("/order")
    public Response<Boolean> orderImport(@RequestBody List<HydResultOrder> orders) {
        logger.info("结果表-订单数量-批量导入, 条数 : {}", orders.size());
        try {
            return Response.ok(hydOrderService.batchSave(orders) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-项目消费券订单金额Top5-批量导入")
    @AnonymousPostMapping("/orderSport")
    public Response<Boolean> orderSportImport(@RequestBody List<HydResultOrderSport> orderSports) {
        logger.info("结果表-项目消费券订单金额Top5-批量导入, 条数 : {}", orderSports.size());
        try {
            return Response.ok(hydOrderSportService.batchSave(orderSports) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-场馆消费券订单金额Top5-批量导入")
    @AnonymousPostMapping("/orderStadium")
    public Response<Boolean> orderStadiumImport(@RequestBody List<HydResultOrderStadium> orderStadiums) {
        logger.info("结果表-场馆消费券订单金额Top5-批量导入, 条数 : {}", orderStadiums.size());
        try {
            return Response.ok(hydOrderStadiumService.batchSave(orderStadiums) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-在线场馆各区情况-批量导入")
    @AnonymousPostMapping("/stadiumDistrict")
    public Response<Boolean> stadiumDistrictImport(@RequestBody List<HydResultStadiumDistrict> stadiumDistricts) {
        logger.info("结果表-在线场馆各区情况-批量导入, 条数 : {}", stadiumDistricts.size());
        try {
            return Response.ok(hydStadiumDistrictService.batchSave(stadiumDistricts) > 0);
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
            return Response.ok(hydStadiumMapService.batchSave(stadiumMaps) > 0);
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
            return Response.ok(hydStadiumService.batchSave(stadiums) > 0);
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
            return Response.ok(hydStadiumSportCouponService.batchSave(stadiumSportCoupons) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-消费券领用券-批量导入")
    @AnonymousPostMapping("/stock")
    public Response<Boolean> stockImport(@RequestBody List<HydResultStock> stocks) {
        logger.info("结果表-消费券领用券-批量导入, 条数 : {}", stocks.size());
        try {
            return Response.ok(hydStockService.batchSave(stocks) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-年龄占比-批量导入")
    @AnonymousPostMapping("/userAge")
    public Response<Boolean> userAgeImport(@RequestBody List<HydResultUserAge> userAges) {
        logger.info("结果表-年龄占比-批量导入, 条数 : {}", userAges.size());
        try {
            return Response.ok(hydUserAgeService.batchSave(userAges) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-用户来源渠道-批量导入")
    @AnonymousPostMapping("/userChannel")
    public Response<Boolean> userChannelImport(@RequestBody List<HydResultUserChannel> userChannels) {
        logger.info("结果表-用户来源渠道-批量导入, 条数 : {}", userChannels.size());
        try {
            return Response.ok(hydUserChannelService.batchSave(userChannels) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-每月新增用户-批量导入")
    @AnonymousPostMapping("/userRegister")
    public Response<Boolean> userRegisterImport(@RequestBody List<HydResultUserRegister> userRegisters) {
        logger.info("结果表-每月新增用户-批量导入, 条数 : {}", userRegisters.size());
        try {
            return Response.ok(hydUserRegisterService.batchSave(userRegisters) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-复购率-批量导入")
    @AnonymousPostMapping("/userRepurchase")
    public Response<Boolean> userRepurchaseImport(@RequestBody List<HydResultUserRepurchase> userRepurchases) {
        logger.info("结果表-复购率-批量导入, 条数 : {}", userRepurchases.size());
        try {
            return Response.ok(hydUserRepurchaseService.batchSave(userRepurchases) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

    @ApiOperation("结果表-男女占比-批量导入")
    @AnonymousPostMapping("/userSex")
    public Response<Boolean> userSexImport(@RequestBody List<HydResultUserSex> userSexes) {
        logger.info("结果表-男女占比-批量导入, 条数 : {}", userSexes.size());
        try {
            return Response.ok(hydUserSexService.batchSave(userSexes) > 0);
        } catch (IllegalArgumentException e) {
            return Response.fail(e.getMessage());
        } catch (Exception e) {
            return Response.fail("服务异常");
        }
    }

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
            return Response.ok(orderService.batchSave(orders) > 0);
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
            return Response.ok(stadiumService.batchSave(stadiums) > 0);
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
