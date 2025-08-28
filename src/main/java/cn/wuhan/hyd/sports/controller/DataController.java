package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousDeleteMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * 功能说明： 数据管理 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@RestController
@RequestMapping("/api/manage")
@Api(tags = "数据管理")
public class DataController {

    @Resource
    private IHydResultCouponAmountService hydCouponAmountService;
    @Resource
    private IHydResultCouponStadiumTopService hydCouponStadiumTopService;
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
    @Resource
    private IHydYktService yktService;
    @Resource
    private IHydExcelPublicEventsService publicEventsService;

    @Resource
    private IHydResultInstructorService instructorService;
    @Resource
    private IHydResultLaStadiumStatService laStadiumStatService;

    private final Logger logger = LoggerFactory.getLogger(ImportController.class);

    // ----------------------------------- 结果表-消费券总金额 -----------------------------------
    @ApiOperation("结果表-消费券总金额-分页查询")
    @AnonymousGetMapping("/couponAmount/list")
    public Response<PageResult<HydResultCouponAmount>> couponAmountList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydCouponAmountService.queryAll(page, size));
    }

    @ApiOperation("结果表-消费券总金额-根据ID查询详情")
    @AnonymousGetMapping("/couponAmount/detail/{id}")
    public Response<HydResultCouponAmount> couponAmount(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydCouponAmountService.findById(id));
    }

    @ApiOperation("结果表-消费券总金额-增加")
    @AnonymousPostMapping("/couponAmount/add")
    public ResponseEntity<HydResultCouponAmount> couponAmountAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultCouponAmount couponAmount) {
        return ResponseEntity.ok(hydCouponAmountService.save(couponAmount));
    }

    @ApiOperation("结果表-消费券总金额-删除")
    @AnonymousDeleteMapping("/couponAmount/delete/{id}")
    public Response<Boolean> couponAmountDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydCouponAmountService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-消费券总金额-更新")
    @AnonymousPostMapping("/couponAmount/update")
    public Response<HydResultCouponAmount> couponAmountUpdate(@RequestBody HydResultCouponAmount couponAmount) {
        return Response.ok(hydCouponAmountService.update(couponAmount));
    }

    // ----------------------------------- 结果表-场馆预定/体育消费卷-消费券场馆预订Top -----------------------------------

    @ApiOperation("结果表-场馆预定/体育消费卷-消费券场馆预订Top-分页查询")
    @AnonymousGetMapping("/couponStadiumTop/list")
    public Response<PageResult<HydResultCouponStadiumTop>> couponStadiumTopList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydCouponStadiumTopService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-消费券场馆预订Top-根据ID查询详情")
    @AnonymousGetMapping("/couponStadiumTop/detail/{id}")
    public Response<HydResultCouponStadiumTop> getCouponStadiumTop(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydCouponStadiumTopService.findById(id));
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-消费券场馆预订Top-增加")
    @AnonymousPostMapping("/couponStadiumTop/add")
    public ResponseEntity<HydResultCouponStadiumTop> couponStadiumTopAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultCouponStadiumTop couponStadiumTop) {
        return ResponseEntity.ok(hydCouponStadiumTopService.save(couponStadiumTop));
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-消费券场馆预订Top-删除")
    @AnonymousDeleteMapping("/couponStadiumTop/delete/{id}")
    public Response<Boolean> couponStadiumTopDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydCouponStadiumTopService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-消费券场馆预订Top-更新")
    @AnonymousPostMapping("/couponStadiumTop/update")
    public Response<HydResultCouponStadiumTop> couponStadiumTopUpdate(@RequestBody HydResultCouponStadiumTop couponStadiumTop) {
        return Response.ok(hydCouponStadiumTopService.update(couponStadiumTop));
    }

    // ----------------------------------- 结果表-体育消费卷-券用户年龄分布 -----------------------------------

    @ApiOperation("结果表-体育消费卷-券用户年龄分布-分页查询")
    @AnonymousGetMapping("/couponUserAge/list")
    public Response<PageResult<HydResultCouponUserAge>> couponUserAgeList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydCouponUserAgeService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育消费卷-券用户年龄分布-根据ID查询详情")
    @AnonymousGetMapping("/couponUserAge/detail/{id}")
    public Response<HydResultCouponUserAge> couponUserAge(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydCouponUserAgeService.findById(id));
    }

    @ApiOperation("结果表-体育消费卷-券用户年龄分布-增加")
    @AnonymousPostMapping("/couponUserAge/add")
    public ResponseEntity<HydResultCouponUserAge> couponUserAgeAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultCouponUserAge couponUserAge) {
        return ResponseEntity.ok(hydCouponUserAgeService.save(couponUserAge));
    }

    @ApiOperation("结果表-体育消费卷-券用户年龄分布-删除")
    @AnonymousDeleteMapping("/couponUserAge/delete/{id}")
    public Response<Boolean> couponUserAgeDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydCouponUserAgeService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育消费卷-券用户年龄分布-更新")
    @AnonymousPostMapping("/couponUserAge/update")
    public Response<HydResultCouponUserAge> couponUserAgeUpdate(@RequestBody HydResultCouponUserAge couponUserAge) {
        return Response.ok(hydCouponUserAgeService.update(couponUserAge));
    }

    // ----------------------------------- 结果表-体育消费卷-券用户分析 -----------------------------------

    @ApiOperation("结果表-体育消费卷-券用户分析-分页查询")
    @AnonymousGetMapping("/couponUser/list")
    public Response<PageResult<HydResultCouponUser>> couponUserList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydCouponUserService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育消费卷-券用户分析-根据ID查询详情")
    @AnonymousGetMapping("/couponUser/detail/{id}")
    public Response<HydResultCouponUser> couponUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydCouponUserService.findById(id));
    }

    @ApiOperation("结果表-体育消费卷-券用户分析-增加")
    @AnonymousPostMapping("/couponUser/add")
    public ResponseEntity<HydResultCouponUser> couponUserAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultCouponUser couponUser) {
        return ResponseEntity.ok(hydCouponUserService.save(couponUser));
    }

    @ApiOperation("结果表-体育消费卷-券用户分析-删除")
    @AnonymousDeleteMapping("/couponUser/delete/{id}")
    public Response<Boolean> couponUserDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydCouponUserService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育消费卷-券用户分析-更新")
    @AnonymousPostMapping("/couponUser/update")
    public Response<HydResultCouponUser> couponUserUpdate(@RequestBody HydResultCouponUser couponUser) {
        return Response.ok(hydCouponUserService.update(couponUser));
    }

    // ----------------------------------- 结果表-体育基础设施-设施各区月数据 -----------------------------------

    @ApiOperation("结果表-体育基础设施-设施各区月数据-分页查询")
    @AnonymousGetMapping("/facilityDistrictMonth/list")
    public Response<PageResult<HydResultFacilityDistrictMonth>> facilityDistrictMonthList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydFacilityDistrictMonthService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育基础设施-设施各区月数据-根据ID查询详情")
    @AnonymousGetMapping("/facilityDistrictMonth/detail/{id}")
    public Response<HydResultFacilityDistrictMonth> facilityDistrictMonthUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydFacilityDistrictMonthService.findById(id));
    }

    @ApiOperation("结果表-体育基础设施-设施各区月数据-增加")
    @AnonymousPostMapping("/facilityDistrictMonth/add")
    public ResponseEntity<HydResultFacilityDistrictMonth> facilityDistrictMonthAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultFacilityDistrictMonth facilityDistrictMonth) {
        return ResponseEntity.ok(hydFacilityDistrictMonthService.save(facilityDistrictMonth));
    }

    @ApiOperation("结果表-体育基础设施-设施各区月数据-删除")
    @AnonymousDeleteMapping("/facilityDistrictMonth/delete/{id}")
    public Response<Boolean> facilityDistrictMonthDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydFacilityDistrictMonthService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育基础设施-设施各区月数据-更新")
    @AnonymousPostMapping("/facilityDistrictMonth/update")
    public Response<HydResultFacilityDistrictMonth> facilityDistrictMonthUpdate(@RequestBody HydResultFacilityDistrictMonth facilityDistrictMonth) {
        return Response.ok(hydFacilityDistrictMonthService.update(facilityDistrictMonth));
    }

    // ----------------------------------- 结果表-体育基础设施-设施各区分布 -----------------------------------

    @ApiOperation("结果表-体育基础设施-设施各区分布-分页查询")
    @AnonymousGetMapping("/facilityDistrict/list")
    public Response<PageResult<HydResultFacilityDistrict>> facilityDistrictList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydFacilityDistrictService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育基础设施-设施各区分布-根据ID查询详情")
    @AnonymousGetMapping("/facilityDistrict/detail/{id}")
    public Response<HydResultFacilityDistrict> facilityDistrictUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydFacilityDistrictService.findById(id));
    }

    @ApiOperation("结果表-体育基础设施-设施各区分布-增加")
    @AnonymousPostMapping("/facilityDistrict/add")
    public ResponseEntity<HydResultFacilityDistrict> facilityDistrictAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultFacilityDistrict facilityDistrict) {
        return ResponseEntity.ok(hydFacilityDistrictService.save(facilityDistrict));
    }

    @ApiOperation("结果表-体育基础设施-设施各区分布-删除")
    @AnonymousDeleteMapping("/facilityDistrict/delete/{id}")
    public Response<Boolean> facilityDistrictDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydFacilityDistrictService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育基础设施-设施各区分布-更新")
    @AnonymousPostMapping("/facilityDistrict/update")
    public Response<HydResultFacilityDistrict> facilityDistrictUpdate(@RequestBody HydResultFacilityDistrict facilityDistrict) {
        return Response.ok(hydFacilityDistrictService.update(facilityDistrict));
    }

    // ----------------------------------- 结果表-体育基础设施-巡检动态 -----------------------------------

    @ApiOperation("结果表-体育基础设施-巡检动态-分页查询")
    @AnonymousGetMapping("/facilityInspect/list")
    public Response<PageResult<HydResultFacilityInspect>> facilityInspectList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydFacilityInspectService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育基础设施-巡检动态-根据ID查询详情")
    @AnonymousGetMapping("/facilityInspect/detail/{id}")
    public Response<HydResultFacilityInspect> facilityInspectUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydFacilityInspectService.findById(id));
    }

    @ApiOperation("结果表-体育基础设施-巡检动态-增加")
    @AnonymousPostMapping("/facilityInspect/add")
    public ResponseEntity<HydResultFacilityInspect> facilityInspectAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultFacilityInspect facilityInspect) {
        return ResponseEntity.ok(hydFacilityInspectService.save(facilityInspect));
    }

    @ApiOperation("结果表-体育基础设施-巡检动态-删除")
    @AnonymousDeleteMapping("/facilityInspect/delete/{id}")
    public Response<Boolean> facilityInspectDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydFacilityInspectService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育基础设施-巡检动态-更新")
    @AnonymousPostMapping("/facilityInspect/update")
    public Response<HydResultFacilityInspect> facilityInspectUpdate(@RequestBody HydResultFacilityInspect facilityInspect) {
        return Response.ok(hydFacilityInspectService.update(facilityInspect));
    }

    // ----------------------------------- 结果表-体育基础设施-设施全貌 -----------------------------------

    @ApiOperation("结果表-体育基础设施-设施全貌-分页查询")
    @AnonymousGetMapping("/facility/list")
    public Response<PageResult<HydResultFacility>> facilityList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydFacilityService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育基础设施-设施全貌-根据ID查询详情")
    @AnonymousGetMapping("/facility/detail/{id}")
    public Response<HydResultFacility> facilityUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydFacilityService.findById(id));
    }

    @ApiOperation("结果表-体育基础设施-设施全貌-增加")
    @AnonymousPostMapping("/facility/add")
    public ResponseEntity<HydResultFacility> facilityAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultFacility facility) {
        return ResponseEntity.ok(hydFacilityService.save(facility));
    }

    @ApiOperation("结果表-体育基础设施-设施全貌-删除")
    @AnonymousDeleteMapping("/facility/delete/{id}")
    public Response<Boolean> facilityDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydFacilityService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育基础设施-设施全貌-更新")
    @AnonymousPostMapping("/facility/update")
    public Response<HydResultFacility> facilityUpdate(@RequestBody HydResultFacility facility) {
        return Response.ok(hydFacilityService.update(facility));
    }

    // ----------------------------------- 结果表-体育基础设施-健身点位年数据 -----------------------------------

    @ApiOperation("结果表-体育基础设施-健身点位年数据-分页查询")
    @AnonymousGetMapping("/facilityYear/list")
    public Response<PageResult<HydResultFacilityYear>> facilityYearList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydFacilityYearService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育基础设施-健身点位年数据-根据ID查询详情")
    @AnonymousGetMapping("/facilityYear/detail/{id}")
    public Response<HydResultFacilityYear> facilityYearUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydFacilityYearService.findById(id));
    }

    @ApiOperation("结果表-体育基础设施-健身点位年数据-增加")
    @AnonymousPostMapping("/facilityYear/add")
    public ResponseEntity<HydResultFacilityYear> facilityYearAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultFacilityYear facilityYear) {
        return ResponseEntity.ok(hydFacilityYearService.save(facilityYear));
    }

    @ApiOperation("结果表-体育基础设施-健身点位年数据-删除")
    @AnonymousDeleteMapping("/facilityYear/delete/{id}")
    public Response<Boolean> facilityYearDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydFacilityYearService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育基础设施-健身点位年数据-更新")
    @AnonymousPostMapping("/facilityYear/update")
    public Response<HydResultFacilityYear> facilityYearUpdate(@RequestBody HydResultFacilityYear facilityYear) {
        return Response.ok(hydFacilityYearService.update(facilityYear));
    }


    // ----------------------------------- 结果表-体育消费卷-订单趋势 -----------------------------------

    @ApiOperation("结果表-体育消费卷-订单趋势-分页查询")
    @AnonymousGetMapping("/orderMonth/list")
    public Response<PageResult<HydResultOrderMonth>> orderMonthList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydOrderMonthService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育消费卷-订单趋势-根据ID查询详情")
    @AnonymousGetMapping("/orderMonth/detail/{id}")
    public Response<HydResultOrderMonth> orderMonthUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydOrderMonthService.findById(id));
    }

    @ApiOperation("结果表-体育消费卷-订单趋势-增加")
    @AnonymousPostMapping("/orderMonth/add")
    public ResponseEntity<HydResultOrderMonth> orderMonthAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderMonth orderMonth) {
        return ResponseEntity.ok(hydOrderMonthService.save(orderMonth));
    }

    @ApiOperation("结果表-体育消费卷-订单趋势-删除")
    @AnonymousDeleteMapping("/orderMonth/delete/{id}")
    public Response<Boolean> orderMonthDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydOrderMonthService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育消费卷-订单趋势-更新")
    @AnonymousPostMapping("/orderMonth/update")
    public Response<HydResultOrderMonth> orderMonthUpdate(@RequestBody HydResultOrderMonth orderMonth) {
        return Response.ok(hydOrderMonthService.update(orderMonth));
    }


    // ----------------------------------- 结果表-场馆预定-订单数量 -----------------------------------

    @ApiOperation("结果表-场馆预定-订单数量-分页查询")
    @AnonymousGetMapping("/order/list")
    public Response<PageResult<HydResultOrder>> orderList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydOrderService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-订单数量-根据ID查询详情")
    @AnonymousGetMapping("/order/detail/{id}")
    public Response<HydResultOrder> orderUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydOrderService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-订单数量-增加")
    @AnonymousPostMapping("/order/add")
    public ResponseEntity<HydResultOrder> orderAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrder order) {
        return ResponseEntity.ok(hydOrderService.save(order));
    }

    @ApiOperation("结果表-场馆预定-订单数量-删除")
    @AnonymousDeleteMapping("/order/delete/{id}")
    public Response<Boolean> orderDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydOrderService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-订单数量-更新")
    @AnonymousPostMapping("/order/update")
    public Response<HydResultOrder> orderUpdate(@RequestBody HydResultOrder order) {
        return Response.ok(hydOrderService.update(order));
    }


    // ----------------------------------- 结果表-场馆预定/体育消费卷-项目消费券订单金额Top5 -----------------------------------

    @ApiOperation("结果表-场馆预定/体育消费卷-项目消费券订单金额Top5-分页查询")
    @AnonymousGetMapping("/orderSport/list")
    public Response<PageResult<HydResultOrderSport>> orderSportList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydOrderSportService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-项目消费券订单金额Top5-根据ID查询详情")
    @AnonymousGetMapping("/orderSport/detail/{id}")
    public Response<HydResultOrderSport> orderSportUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydOrderSportService.findById(id));
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-项目消费券订单金额Top5-增加")
    @AnonymousPostMapping("/orderSport/add")
    public ResponseEntity<HydResultOrderSport> orderSportAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderSport orderSport) {
        return ResponseEntity.ok(hydOrderSportService.save(orderSport));
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-项目消费券订单金额Top5-删除")
    @AnonymousDeleteMapping("/orderSport/delete/{id}")
    public Response<Boolean> orderSportDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydOrderSportService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定/体育消费卷-项目消费券订单金额Top5-更新")
    @AnonymousPostMapping("/orderSport/update")
    public Response<HydResultOrderSport> orderSportUpdate(@RequestBody HydResultOrderSport orderSport) {
        return Response.ok(hydOrderSportService.update(orderSport));
    }


    // ----------------------------------- 结果表-体育消费卷-场馆消费券订单金额Top5 -----------------------------------

    @ApiOperation("结果表-体育消费卷-场馆消费券订单金额Top5-分页查询")
    @AnonymousGetMapping("/orderStadium/list")
    public Response<PageResult<HydResultOrderStadium>> orderStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydOrderStadiumService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育消费卷-场馆消费券订单金额Top5-根据ID查询详情")
    @AnonymousGetMapping("/orderStadium/detail/{id}")
    public Response<HydResultOrderStadium> orderStadiumUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydOrderStadiumService.findById(id));
    }

    @ApiOperation("结果表-体育消费卷-场馆消费券订单金额Top5-增加")
    @AnonymousPostMapping("/orderStadium/add")
    public ResponseEntity<HydResultOrderStadium> orderStadiumAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderStadium orderStadium) {
        return ResponseEntity.ok(hydOrderStadiumService.save(orderStadium));
    }

    @ApiOperation("结果表-体育消费卷-场馆消费券订单金额Top5-删除")
    @AnonymousDeleteMapping("/orderStadium/delete/{id}")
    public Response<Boolean> orderStadiumDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydOrderStadiumService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育消费卷-场馆消费券订单金额Top5-更新")
    @AnonymousPostMapping("/orderStadium/update")
    public Response<HydResultOrderStadium> orderStadiumUpdate(@RequestBody HydResultOrderStadium orderStadium) {
        return Response.ok(hydOrderStadiumService.update(orderStadium));
    }


    // ----------------------------------- 结果表-场馆预定-在线场馆各区情况 -----------------------------------

    @ApiOperation("结果表-场馆预定-在线场馆各区情况-分页查询")
    @AnonymousGetMapping("/stadiumDistrict/list")
    public Response<PageResult<HydResultStadiumDistrict>> stadiumDistrictList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydStadiumDistrictService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-在线场馆各区情况-根据ID查询详情")
    @AnonymousGetMapping("/stadiumDistrict/detail/{id}")
    public Response<HydResultStadiumDistrict> stadiumDistrictUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydStadiumDistrictService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-在线场馆各区情况-增加")
    @AnonymousPostMapping("/stadiumDistrict/add")
    public ResponseEntity<HydResultStadiumDistrict> stadiumDistrictAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultStadiumDistrict stadiumDistrict) {
        return ResponseEntity.ok(hydStadiumDistrictService.save(stadiumDistrict));
    }

    @ApiOperation("结果表-场馆预定-在线场馆各区情况-删除")
    @AnonymousDeleteMapping("/stadiumDistrict/delete/{id}")
    public Response<Boolean> stadiumDistrictDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydStadiumDistrictService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-在线场馆各区情况-更新")
    @AnonymousPostMapping("/stadiumDistrict/update")
    public Response<HydResultStadiumDistrict> stadiumDistrictUpdate(@RequestBody HydResultStadiumDistrict stadiumDistrict) {
        return Response.ok(hydStadiumDistrictService.update(stadiumDistrict));
    }

    // ----------------------------------- 结果表-体育基础设施-电子地图 -----------------------------------

    @ApiOperation("结果表-体育基础设施-电子地图-分页查询")
    @AnonymousGetMapping("/stadiumMap/list")
    public Response<PageResult<HydResultStadiumMap>> stadiumMapList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydStadiumMapService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育基础设施-电子地图-根据ID查询详情")
    @AnonymousGetMapping("/stadiumMap/detail/{id}")
    public Response<HydResultStadiumMap> stadiumMapUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydStadiumMapService.findById(id));
    }

    @ApiOperation("结果表-体育基础设施-电子地图-增加")
    @AnonymousPostMapping("/stadiumMap/add")
    public ResponseEntity<HydResultStadiumMap> stadiumMapAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultStadiumMap stadiumMap) {
        return ResponseEntity.ok(hydStadiumMapService.save(stadiumMap));
    }

    @ApiOperation("结果表-体育基础设施-电子地图-删除")
    @AnonymousDeleteMapping("/stadiumMap/delete/{id}")
    public Response<Boolean> stadiumMapDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydStadiumMapService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育基础设施-电子地图-更新")
    @AnonymousPostMapping("/stadiumMap/update")
    public Response<HydResultStadiumMap> stadiumMapUpdate(@RequestBody HydResultStadiumMap stadiumMap) {
        return Response.ok(hydStadiumMapService.update(stadiumMap));
    }


    // ----------------------------------- 结果表-场馆预定-在线场馆数量 -----------------------------------

    @ApiOperation("结果表-场馆预定-在线场馆数量-分页查询")
    @AnonymousGetMapping("/stadium/list")
    public Response<PageResult<HydResultStadium>> stadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydStadiumService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-在线场馆数量-根据ID查询详情")
    @AnonymousGetMapping("/stadium/detail/{id}")
    public Response<HydResultStadium> stadiumUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydStadiumService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-在线场馆数量-增加")
    @AnonymousPostMapping("/stadium/add")
    public ResponseEntity<HydResultStadium> stadiumAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultStadium stadium) {
        return ResponseEntity.ok(hydStadiumService.save(stadium));
    }

    @ApiOperation("结果表-场馆预定-在线场馆数量-删除")
    @AnonymousDeleteMapping("/stadium/delete/{id}")
    public Response<Boolean> stadiumDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydStadiumService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-在线场馆数量-更新")
    @AnonymousPostMapping("/stadium/update")
    public Response<HydResultStadium> stadiumUpdate(@RequestBody HydResultStadium stadium) {
        return Response.ok(hydStadiumService.update(stadium));
    }

    // ----------------------------------- 结果表-体育消费卷/场馆预定-运动项目分布用券数占比 -----------------------------------

    @ApiOperation("结果表-体育消费卷/场馆预定-运动项目分布用券数占比-分页查询")
    @AnonymousGetMapping("/stadiumSportCoupon/list")
    public Response<PageResult<HydResultStadiumSportCoupon>> stadiumSportCouponList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydStadiumSportCouponService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育消费卷/场馆预定-运动项目分布用券数占比-根据ID查询详情")
    @AnonymousGetMapping("/stadiumSportCoupon/detail/{id}")
    public Response<HydResultStadiumSportCoupon> stadiumSportCouponUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydStadiumSportCouponService.findById(id));
    }

    @ApiOperation("结果表-体育消费卷/场馆预定-运动项目分布用券数占比-增加")
    @AnonymousPostMapping("/stadiumSportCoupon/add")
    public ResponseEntity<HydResultStadiumSportCoupon> stadiumSportCouponAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultStadiumSportCoupon stadiumSportCoupon) {
        return ResponseEntity.ok(hydStadiumSportCouponService.save(stadiumSportCoupon));
    }

    @ApiOperation("结果表-体育消费卷/场馆预定-运动项目分布用券数占比-删除")
    @AnonymousDeleteMapping("/stadiumSportCoupon/delete/{id}")
    public Response<Boolean> stadiumSportCouponDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydStadiumSportCouponService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育消费卷/场馆预定-运动项目分布用券数占比-更新")
    @AnonymousPostMapping("/stadiumSportCoupon/update")
    public Response<HydResultStadiumSportCoupon> stadiumSportCouponUpdate(@RequestBody HydResultStadiumSportCoupon stadiumSportCoupon) {
        return Response.ok(hydStadiumSportCouponService.update(stadiumSportCoupon));
    }

    // ----------------------------------- 结果表-体育消费卷-消费券领用券 -----------------------------------

    @ApiOperation("结果表-体育消费卷-消费券领用券-分页查询")
    @AnonymousGetMapping("/stock/list")
    public Response<PageResult<HydResultStock>> stockList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydStockService.queryAll(page, size));
    }

    @ApiOperation("结果表-体育消费卷-消费券领用券-根据ID查询详情")
    @AnonymousGetMapping("/stock/detail/{id}")
    public Response<HydResultStock> stockUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydStockService.findById(id));
    }

    @ApiOperation("结果表-体育消费卷-消费券领用券-增加")
    @AnonymousPostMapping("/stock/add")
    public ResponseEntity<HydResultStock> stockAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultStock stock) {
        return ResponseEntity.ok(hydStockService.save(stock));
    }

    @ApiOperation("结果表-体育消费卷-消费券领用券-删除")
    @AnonymousDeleteMapping("/stock/delete/{id}")
    public Response<Boolean> stockDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydStockService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-体育消费卷-消费券领用券-更新")
    @AnonymousPostMapping("/stock/update")
    public Response<HydResultStock> stockUpdate(@RequestBody HydResultStock stock) {
        return Response.ok(hydStockService.update(stock));
    }


    // ----------------------------------- 结果表-场馆预定-年龄占比 -----------------------------------

    @ApiOperation("结果表-场馆预定-年龄占比-分页查询")
    @AnonymousGetMapping("/userAge/list")
    public Response<PageResult<HydResultUserAge>> userAgeList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydUserAgeService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-年龄占比-根据ID查询详情")
    @AnonymousGetMapping("/userAge/detail/{id}")
    public Response<HydResultUserAge> userAgeUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydUserAgeService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-年龄占比-增加")
    @AnonymousPostMapping("/userAge/add")
    public ResponseEntity<HydResultUserAge> userAgeAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultUserAge userAge) {
        return ResponseEntity.ok(hydUserAgeService.save(userAge));
    }

    @ApiOperation("结果表-场馆预定-年龄占比-删除")
    @AnonymousDeleteMapping("/userAge/delete/{id}")
    public Response<Boolean> userAgeDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydUserAgeService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-年龄占比-更新")
    @AnonymousPostMapping("/userAge/update")
    public Response<HydResultUserAge> userAgeUpdate(@RequestBody HydResultUserAge userAge) {
        return Response.ok(hydUserAgeService.update(userAge));
    }


    // ----------------------------------- 结果表-场馆预定-用户来源渠道 -----------------------------------

    @ApiOperation("结果表-场馆预定-用户来源渠道-分页查询")
    @AnonymousGetMapping("/userChannel/list")
    public Response<PageResult<HydResultUserChannel>> userChannelList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydUserChannelService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-用户来源渠道-根据ID查询详情")
    @AnonymousGetMapping("/userChannel/detail/{id}")
    public Response<HydResultUserChannel> userChannelUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydUserChannelService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-用户来源渠道-增加")
    @AnonymousPostMapping("/userChannel/add")
    public ResponseEntity<HydResultUserChannel> userChannelAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultUserChannel userChannel) {
        return ResponseEntity.ok(hydUserChannelService.save(userChannel));
    }

    @ApiOperation("结果表-场馆预定-用户来源渠道-删除")
    @AnonymousDeleteMapping("/userChannel/delete/{id}")
    public Response<Boolean> userChannelDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydUserChannelService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-用户来源渠道-更新")
    @AnonymousPostMapping("/userChannel/update")
    public Response<HydResultUserChannel> userChannelUpdate(@RequestBody HydResultUserChannel userChannel) {
        return Response.ok(hydUserChannelService.update(userChannel));
    }


    // ----------------------------------- 结果表-场馆预定-每月新增用户 -----------------------------------

    @ApiOperation("结果表-场馆预定-每月新增用户-分页查询")
    @AnonymousGetMapping("/userRegister/list")
    public Response<PageResult<HydResultUserRegister>> userRegisterList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydUserRegisterService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-每月新增用户-根据ID查询详情")
    @AnonymousGetMapping("/userRegister/detail/{id}")
    public Response<HydResultUserRegister> userRegisterUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydUserRegisterService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-每月新增用户-增加")
    @AnonymousPostMapping("/userRegister/add")
    public ResponseEntity<HydResultUserRegister> userRegisterAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultUserRegister userRegister) {
        return ResponseEntity.ok(hydUserRegisterService.save(userRegister));
    }

    @ApiOperation("结果表-场馆预定-每月新增用户-删除")
    @AnonymousDeleteMapping("/userRegister/delete/{id}")
    public Response<Boolean> userRegisterDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydUserRegisterService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-每月新增用户-更新")
    @AnonymousPostMapping("/userRegister/update")
    public Response<HydResultUserRegister> userRegisterUpdate(@RequestBody HydResultUserRegister userRegister) {
        return Response.ok(hydUserRegisterService.update(userRegister));
    }


    // ----------------------------------- 结果表-场馆预定-复购率 -----------------------------------

    @ApiOperation("结果表-场馆预定-复购率-分页查询")
    @AnonymousGetMapping("/userRepurchase/list")
    public Response<PageResult<HydResultUserRepurchase>> userRepurchaseList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydUserRepurchaseService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-复购率-根据ID查询详情")
    @AnonymousGetMapping("/userRepurchase/detail/{id}")
    public Response<HydResultUserRepurchase> userRepurchaseUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydUserRepurchaseService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-复购率-增加")
    @AnonymousPostMapping("/userRepurchase/add")
    public ResponseEntity<HydResultUserRepurchase> userRepurchaseAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultUserRepurchase userRepurchase) {
        return ResponseEntity.ok(hydUserRepurchaseService.save(userRepurchase));
    }

    @ApiOperation("结果表-场馆预定-复购率-删除")
    @AnonymousDeleteMapping("/userRepurchase/delete/{id}")
    public Response<Boolean> userRepurchaseDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydUserRepurchaseService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-复购率-更新")
    @AnonymousPostMapping("/userRepurchase/update")
    public Response<HydResultUserRepurchase> userRepurchaseUpdate(@RequestBody HydResultUserRepurchase userRepurchase) {
        return Response.ok(hydUserRepurchaseService.update(userRepurchase));
    }

    // ----------------------------------- 结果表-场馆预定-男女占比 -----------------------------------

    @ApiOperation("结果表-场馆预定-男女占比-分页查询")
    @AnonymousGetMapping("/userSex/list")
    public Response<PageResult<HydResultUserSex>> userSexList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydUserSexService.queryAll(page, size));
    }

    @ApiOperation("结果表-场馆预定-男女占比-根据ID查询详情")
    @AnonymousGetMapping("/userSex/detail/{id}")
    public Response<HydResultUserSex> userSexUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydUserSexService.findById(id));
    }

    @ApiOperation("结果表-场馆预定-男女占比-增加")
    @AnonymousPostMapping("/userSex/add")
    public ResponseEntity<HydResultUserSex> userSexAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultUserSex userSex) {
        return ResponseEntity.ok(hydUserSexService.save(userSex));
    }

    @ApiOperation("结果表-场馆预定-男女占比-删除")
    @AnonymousDeleteMapping("/userSex/delete/{id}")
    public Response<Boolean> userSexDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydUserSexService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("结果表-场馆预定-男女占比-更新")
    @AnonymousPostMapping("/userSex/update")
    public Response<HydResultUserSex> userSexUpdate(@RequestBody HydResultUserSex userSex) {
        return Response.ok(hydUserSexService.update(userSex));
    }


    // ----------------------------------- 原始表-校外培训机构附件 -----------------------------------

    @ApiOperation("原始表-校外培训机构附件-分页查询")
    @AnonymousGetMapping("/laStadiumFile/list")
    public Response<PageResult<HydOriginLaStadiumFile>> laStadiumFileList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(laStadiumFileService.queryAll(page, size));
    }

    @ApiOperation("原始表-校外培训机构附件-根据ID查询详情")
    @AnonymousGetMapping("/laStadiumFile/detail/{id}")
    public Response<HydOriginLaStadiumFile> laStadiumFileUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Integer id) {
        return Response.ok(laStadiumFileService.findById(id));
    }

    @ApiOperation("原始表-校外培训机构附件-增加")
    @AnonymousPostMapping("/laStadiumFile/add")
    public ResponseEntity<HydOriginLaStadiumFile> laStadiumFileAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginLaStadiumFile laStadiumFile) {
        return ResponseEntity.ok(laStadiumFileService.save(laStadiumFile));
    }

    @ApiOperation("原始表-校外培训机构附件-删除")
    @AnonymousDeleteMapping("/laStadiumFile/delete/{id}")
    public Response<Boolean> laStadiumFileDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Integer id) {
        try {
            laStadiumFileService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-校外培训机构附件-更新")
    @AnonymousPostMapping("/laStadiumFile/update")
    public Response<HydOriginLaStadiumFile> laStadiumFileUpdate(@RequestBody HydOriginLaStadiumFile laStadiumFile) {
        return Response.ok(laStadiumFileService.update(laStadiumFile));
    }

    // ----------------------------------- 原始表-校外培训机构 -----------------------------------

    @ApiOperation("原始表-校外培训机构-分页查询")
    @AnonymousGetMapping("/laStadium/list")
    public Response<PageResult<HydOriginLaStadium>> laStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(laStadiumService.queryAll(page, size));
    }

    @ApiOperation("原始表-校外培训机构-根据ID查询详情")
    @AnonymousGetMapping("/laStadium/detail/{id}")
    public Response<HydOriginLaStadium> laStadiumUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Integer id) {
        return Response.ok(laStadiumService.findById(id));
    }

    @ApiOperation("原始表-校外培训机构-增加")
    @AnonymousPostMapping("/laStadium/add")
    public ResponseEntity<HydOriginLaStadium> laStadiumAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginLaStadium laStadium) {
        return ResponseEntity.ok(laStadiumService.save(laStadium));
    }

    @ApiOperation("原始表-校外培训机构-删除")
    @AnonymousDeleteMapping("/laStadium/delete/{id}")
    public Response<Boolean> laStadiumDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Integer id) {
        try {
            laStadiumService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-校外培训机构-更新")
    @AnonymousPostMapping("/laStadium/update")
    public Response<HydOriginLaStadium> laStadiumUpdate(@RequestBody HydOriginLaStadium laStadium) {
        return Response.ok(laStadiumService.update(laStadium));
    }

    // ----------------------------------- 原始表-校外培训订单 -----------------------------------

    @ApiOperation("原始表-校外培训订单-分页查询")
    @AnonymousGetMapping("/originOrder/list")
    public Response<PageResult<HydOriginOrder>> originOrderList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(orderService.queryAll(page, size));
    }

    @ApiOperation("原始表-校外培训订单-根据ID查询详情")
    @AnonymousGetMapping("/originOrder/detail/{id}")
    public Response<HydOriginOrder> originOrderUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(orderService.findById(id));
    }

    @ApiOperation("原始表-校外培训订单-增加")
    @AnonymousPostMapping("/originOrder/add")
    public ResponseEntity<HydOriginOrder> originOrderAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginOrder originOrder) {
        return ResponseEntity.ok(orderService.save(originOrder));
    }

    @ApiOperation("原始表-校外培训订单-删除")
    @AnonymousDeleteMapping("/originOrder/delete/{id}")
    public Response<Boolean> originOrderDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            orderService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-校外培训订单-更新")
    @AnonymousPostMapping("/originOrder/update")
    public Response<HydOriginOrder> originOrderUpdate(@RequestBody HydOriginOrder originOrder) {
        return Response.ok(orderService.update(originOrder));
    }


    // ----------------------------------- 原始表-场馆培训项目 -----------------------------------

    @ApiOperation("原始表-场馆培训项目-分页查询")
    @AnonymousGetMapping("/stadiumItem/list")
    public Response<PageResult<HydOriginStadiumItem>> stadiumItemList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(stadiumItemService.queryAll(page, size));
    }

    @ApiOperation("原始表-场馆培训项目-根据ID查询详情")
    @AnonymousGetMapping("/stadiumItem/detail/{id}")
    public Response<HydOriginStadiumItem> stadiumItemUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(stadiumItemService.findById(id));
    }

    @ApiOperation("原始表-场馆培训项目-增加")
    @AnonymousPostMapping("/stadiumItem/add")
    public ResponseEntity<HydOriginStadiumItem> stadiumItemAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginStadiumItem stadiumItem) {
        return ResponseEntity.ok(stadiumItemService.save(stadiumItem));
    }

    @ApiOperation("原始表-场馆培训项目-删除")
    @AnonymousDeleteMapping("/stadiumItem/delete/{id}")
    public Response<Boolean> stadiumItemDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            stadiumItemService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-场馆培训项目-更新")
    @AnonymousPostMapping("/stadiumItem/update")
    public Response<HydOriginStadiumItem> stadiumItemUpdate(@RequestBody HydOriginStadiumItem stadiumItem) {
        return Response.ok(stadiumItemService.update(stadiumItem));
    }


    // ----------------------------------- 原始表-培训场馆 -----------------------------------

    @ApiOperation("原始表-培训场馆-分页查询")
    @AnonymousGetMapping("/originStadium/list")
    public Response<PageResult<HydOriginStadium>> originStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(stadiumService.queryAll(page, size));
    }

    @ApiOperation("原始表-培训场馆-根据ID查询详情")
    @AnonymousGetMapping("/originStadium/detail/{id}")
    public Response<HydOriginStadium> originStadiumUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(stadiumService.findById(id));
    }

    @ApiOperation("原始表-培训场馆-增加")
    @AnonymousPostMapping("/originStadium/add")
    public ResponseEntity<HydOriginStadium> originStadiumAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginStadium originStadium) {
        return ResponseEntity.ok(stadiumService.save(originStadium));
    }

    @ApiOperation("原始表-培训场馆-删除")
    @AnonymousDeleteMapping("/originStadium/delete/{id}")
    public Response<Boolean> originStadiumDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            stadiumService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-培训场馆-更新")
    @AnonymousPostMapping("/originStadium/update")
    public Response<HydOriginStadium> originStadiumUpdate(@RequestBody HydOriginStadium originStadium) {
        return Response.ok(stadiumService.update(originStadium));
    }


    // ----------------------------------- 原始表-组织机构 -----------------------------------

    @ApiOperation("原始表-组织机构-分页查询")
    @AnonymousGetMapping("/tenant/list")
    public Response<PageResult<HydOriginTenant>> tenantList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(tenantService.queryAll(page, size));
    }

    @ApiOperation("原始表-组织机构-根据ID查询详情")
    @AnonymousGetMapping("/tenant/detail/{id}")
    public Response<HydOriginTenant> tenantUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(tenantService.findById(id));
    }

    @ApiOperation("原始表-组织机构-增加")
    @AnonymousPostMapping("/tenant/add")
    public ResponseEntity<HydOriginTenant> tenantAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginTenant tenant) {
        return ResponseEntity.ok(tenantService.save(tenant));
    }

    @ApiOperation("原始表-组织机构-删除")
    @AnonymousDeleteMapping("/tenant/delete/{id}")
    public Response<Boolean> tenantDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            tenantService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-组织机构-更新")
    @AnonymousPostMapping("/tenant/update")
    public Response<HydOriginTenant> tenantUpdate(@RequestBody HydOriginTenant tenant) {
        return Response.ok(tenantService.update(tenant));
    }

    // ----------------------------------- 原始表-培训活动支持的项目 -----------------------------------

    @ApiOperation("原始表-培训活动支持的项目-分页查询")
    @AnonymousGetMapping("/trainingActivityItem/list")
    public Response<PageResult<HydOriginTrainingActivityItem>> trainingActivityItemList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(trainingActivityItemService.queryAll(page, size));
    }

    @ApiOperation("原始表-培训活动支持的项目-根据ID查询详情")
    @AnonymousGetMapping("/trainingActivityItem/detail/{id}")
    public Response<HydOriginTrainingActivityItem> trainingActivityItemUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(trainingActivityItemService.findById(id));
    }

    @ApiOperation("原始表-培训活动支持的项目-增加")
    @AnonymousPostMapping("/trainingActivityItem/add")
    public ResponseEntity<HydOriginTrainingActivityItem> trainingActivityItemAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginTrainingActivityItem trainingActivityItem) {
        return ResponseEntity.ok(trainingActivityItemService.save(trainingActivityItem));
    }

    @ApiOperation("原始表-培训活动支持的项目-删除")
    @AnonymousDeleteMapping("/trainingActivityItem/delete/{id}")
    public Response<Boolean> trainingActivityItemDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            trainingActivityItemService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-培训活动支持的项目-更新")
    @AnonymousPostMapping("/trainingActivityItem/update")
    public Response<HydOriginTrainingActivityItem> trainingActivityItemUpdate(@RequestBody HydOriginTrainingActivityItem trainingActivityItem) {
        return Response.ok(trainingActivityItemService.update(trainingActivityItem));
    }

    // ----------------------------------- 原始表-培训活动场馆支持的项目 -----------------------------------

    @ApiOperation("原始表-培训活动场馆支持的项目-分页查询")
    @AnonymousGetMapping("/trainingActivityItemStadium/list")
    public Response<PageResult<HydOriginTrainingActivityItemStadium>> trainingActivityItemStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(trainingActivityItemStadiumService.queryAll(page, size));
    }

    @ApiOperation("原始表-培训活动场馆支持的项目-根据ID查询详情")
    @AnonymousGetMapping("/trainingActivityItemStadium/detail/{id}")
    public Response<HydOriginTrainingActivityItemStadium> trainingActivityItemStadiumUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(trainingActivityItemStadiumService.findById(id));
    }

    @ApiOperation("原始表-培训活动场馆支持的项目-增加")
    @AnonymousPostMapping("/trainingActivityItemStadium/add")
    public ResponseEntity<HydOriginTrainingActivityItemStadium> trainingActivityItemStadiumAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginTrainingActivityItemStadium trainingActivityItemStadium) {
        return ResponseEntity.ok(trainingActivityItemStadiumService.save(trainingActivityItemStadium));
    }

    @ApiOperation("原始表-培训活动场馆支持的项目-删除")
    @AnonymousDeleteMapping("/trainingActivityItemStadium/delete/{id}")
    public Response<Boolean> trainingActivityItemStadiumDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            trainingActivityItemStadiumService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-培训活动场馆支持的项目-更新")
    @AnonymousPostMapping("/trainingActivityItemStadium/update")
    public Response<HydOriginTrainingActivityItemStadium> trainingActivityItemStadiumUpdate(@RequestBody HydOriginTrainingActivityItemStadium trainingActivityItemStadium) {
        return Response.ok(trainingActivityItemStadiumService.update(trainingActivityItemStadium));
    }


    // ----------------------------------- 原始表-培训活动 -----------------------------------

    @ApiOperation("原始表-培训活动-分页查询")
    @AnonymousGetMapping("/trainingActivity/list")
    public Response<PageResult<HydOriginTrainingActivity>> trainingActivityList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(trainingActivityService.queryAll(page, size));
    }

    @ApiOperation("原始表-培训活动-根据ID查询详情")
    @AnonymousGetMapping("/trainingActivity/detail/{id}")
    public Response<HydOriginTrainingActivity> trainingActivityUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(trainingActivityService.findById(id));
    }

    @ApiOperation("原始表-培训活动-增加")
    @AnonymousPostMapping("/trainingActivity/add")
    public ResponseEntity<HydOriginTrainingActivity> trainingActivityAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginTrainingActivity trainingActivity) {
        return ResponseEntity.ok(trainingActivityService.save(trainingActivity));
    }

    @ApiOperation("原始表-培训活动-删除")
    @AnonymousDeleteMapping("/trainingActivity/delete/{id}")
    public Response<Boolean> trainingActivityDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            trainingActivityService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-培训活动-更新")
    @AnonymousPostMapping("/trainingActivity/update")
    public Response<HydOriginTrainingActivity> trainingActivityUpdate(@RequestBody HydOriginTrainingActivity trainingActivity) {
        return Response.ok(trainingActivityService.update(trainingActivity));
    }


    // ----------------------------------- 原始表-培训课程 -----------------------------------

    @ApiOperation("原始表-培训课程-分页查询")
    @AnonymousGetMapping("/trainingCourse/list")
    public Response<PageResult<HydOriginTrainingCourse>> trainingCourseList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(trainingCourseService.queryAll(page, size));
    }

    @ApiOperation("原始表-培训课程-根据ID查询详情")
    @AnonymousGetMapping("/trainingCourse/detail/{id}")
    public Response<HydOriginTrainingCourse> trainingCourseUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        return Response.ok(trainingCourseService.findById(id));
    }

    @ApiOperation("原始表-培训课程-增加")
    @AnonymousPostMapping("/trainingCourse/add")
    public ResponseEntity<HydOriginTrainingCourse> trainingCourseAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydOriginTrainingCourse trainingCourse) {
        return ResponseEntity.ok(trainingCourseService.save(trainingCourse));
    }

    @ApiOperation("原始表-培训课程-删除")
    @AnonymousDeleteMapping("/trainingCourse/delete/{id}")
    public Response<Boolean> trainingCourseDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable String id) {
        try {
            trainingCourseService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("原始表-培训课程-更新")
    @AnonymousPostMapping("/trainingCourse/update")
    public Response<HydOriginTrainingCourse> trainingCourseUpdate(@RequestBody HydOriginTrainingCourse trainingCourse) {
        return Response.ok(trainingCourseService.update(trainingCourse));
    }

    // ----------------------------------- 青少年技能培训-培训课程 -----------------------------------
    @ApiOperation("青少年技能培训-各区机构数量统计-分页查询")
    @AnonymousGetMapping("/yktDistrictStat/list")
    public Response<PageResult<HydResultOrderYktDistrictStat>> yktDistrictStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(yktService.queryAllDistrict(page, size));
    }

    @ApiOperation("青少年技能培训-各区机构数量统计-根据ID查询详情")
    @AnonymousGetMapping("/yktDistrictStat/detail/{id}")
    public Response<HydResultOrderYktDistrictStat> yktDistrictStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(yktService.findDistrictById(id));
    }

    @ApiOperation("青少年技能培训-各区机构数量统计-增加")
    @AnonymousPostMapping("/yktDistrictStat/add")
    public ResponseEntity<HydResultOrderYktDistrictStat> yktDistrictStatAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderYktDistrictStat districtStat) {
        return ResponseEntity.ok(yktService.save(districtStat));
    }

    @ApiOperation("青少年技能培训-各区机构数量统计-删除")
    @AnonymousDeleteMapping("/yktDistrictStat/delete/{id}")
    public Response<Boolean> yktDistrictStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            yktService.deleteDistrictById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("青少年技能培训-各区机构数量统计-更新")
    @AnonymousPostMapping("/yktDistrictStat/update")
    public Response<HydResultOrderYktDistrictStat> yktDistrictStatUpdate(@RequestBody HydResultOrderYktDistrictStat districtStat) {
        return Response.ok(yktService.update(districtStat));
    }


    @ApiOperation("青少年技能培训-课程热度排行TOP5-分页查询")
    @AnonymousGetMapping("/yktCourseStat/list")
    public Response<PageResult<HydResultOrderYktCourseStat>> yktCourseStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(yktService.queryAllCourse(page, size));
    }

    @ApiOperation("青少年技能培训-课程热度排行TOP5-根据ID查询详情")
    @AnonymousGetMapping("/yktCourseStat/detail/{id}")
    public Response<HydResultOrderYktCourseStat> yktCourseStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(yktService.findCourseById(id));
    }

    @ApiOperation("青少年技能培训-课程热度排行TOP5-增加")
    @AnonymousPostMapping("/yktCourseStat/add")
    public ResponseEntity<HydResultOrderYktCourseStat> yktCourseStatAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderYktCourseStat yktCourseStat) {
        return ResponseEntity.ok(yktService.save(yktCourseStat));
    }

    @ApiOperation("青少年技能培训-课程热度排行TOP5-删除")
    @AnonymousDeleteMapping("/yktCourseStat/delete/{id}")
    public Response<Boolean> yktCourseStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            yktService.deleteCourseById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("青少年技能培训-课程热度排行TOP5-更新")
    @AnonymousPostMapping("/yktCourseStat/update")
    public Response<HydResultOrderYktCourseStat> yktCourseStatUpdate(@RequestBody HydResultOrderYktCourseStat yktCourseStat) {
        return Response.ok(yktService.update(yktCourseStat));
    }


    @ApiOperation("青少年技能培训-热门项目机构数量统计-分页查询")
    @AnonymousGetMapping("/yktProjectStat/list")
    public Response<PageResult<HydResultOrderYktProjectStat>> yktProjectStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(yktService.queryAllProject(page, size));
    }

    @ApiOperation("青少年技能培训-热门项目机构数量统计-根据ID查询详情")
    @AnonymousGetMapping("/yktProjectStat/detail/{id}")
    public Response<HydResultOrderYktProjectStat> yktProjectStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(yktService.findProjectById(id));
    }

    @ApiOperation("青少年技能培训-热门项目机构数量统计-增加")
    @AnonymousPostMapping("/yktProjectStat/add")
    public ResponseEntity<HydResultOrderYktProjectStat> yktProjectStatAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderYktProjectStat yktProjectStat) {
        return ResponseEntity.ok(yktService.save(yktProjectStat));
    }

    @ApiOperation("青少年技能培训-热门项目机构数量统计-删除")
    @AnonymousDeleteMapping("/yktProjectStat/delete/{id}")
    public Response<Boolean> yktProjectStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            yktService.deleteProjectById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("青少年技能培训-热门项目机构数量统计-更新")
    @AnonymousPostMapping("/yktProjectStat/update")
    public Response<HydResultOrderYktProjectStat> yktProjectStatUpdate(@RequestBody HydResultOrderYktProjectStat yktProjectStat) {
        return Response.ok(yktService.update(yktProjectStat));
    }


    @ApiOperation("青少年技能培训-培训场馆销售统计TOP10-分页查询")
    @AnonymousGetMapping("/yktStadiumStat/list")
    public Response<PageResult<HydResultOrderYktStadiumStat>> yktStadiumStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(yktService.queryAllStadium(page, size));
    }

    @ApiOperation("青少年技能培训-培训场馆销售统计TOP10-根据ID查询详情")
    @AnonymousGetMapping("/yktStadiumStat/detail/{id}")
    public Response<HydResultOrderYktStadiumStat> yktStadiumStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(yktService.findStadiumById(id));
    }

    @ApiOperation("青少年技能培训-培训场馆销售统计TOP10-增加")
    @AnonymousPostMapping("/yktStadiumStat/add")
    public ResponseEntity<HydResultOrderYktStadiumStat> yktStadiumStatAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderYktStadiumStat yktStadiumStat) {
        return ResponseEntity.ok(yktService.save(yktStadiumStat));
    }

    @ApiOperation("青少年技能培训-培训场馆销售统计TOP10-删除")
    @AnonymousDeleteMapping("/yktStadiumStat/delete/{id}")
    public Response<Boolean> yktStadiumStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            yktService.deleteStadiumById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("青少年技能培训-培训场馆销售统计TOP10-更新")
    @AnonymousPostMapping("/yktStadiumStat/update")
    public Response<HydResultOrderYktStadiumStat> yktStadiumStatUpdate(@RequestBody HydResultOrderYktStadiumStat yktStadiumStat) {
        return Response.ok(yktService.update(yktStadiumStat));
    }


    @ApiOperation("青少年技能培训-年龄分布-分页查询")
    @AnonymousGetMapping("/yktUserAgeStat/list")
    public Response<PageResult<HydResultOrderYktUserAgeStat>> yktUserAgeStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(yktService.queryAllUserAge(page, size));
    }

    @ApiOperation("青少年技能培训-年龄分布-根据ID查询详情")
    @AnonymousGetMapping("/yktUserAgeStat/detail/{id}")
    public Response<HydResultOrderYktUserAgeStat> yktUserAgeStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(yktService.findUserAgeById(id));
    }

    @ApiOperation("青少年技能培训-年龄分布-增加")
    @AnonymousPostMapping("/yktUserAgeStat/add")
    public ResponseEntity<HydResultOrderYktUserAgeStat> yktUserAgeStatAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderYktUserAgeStat yktUserAgeStat) {
        return ResponseEntity.ok(yktService.save(yktUserAgeStat));
    }

    @ApiOperation("青少年技能培训-年龄分布-删除")
    @AnonymousDeleteMapping("/yktUserAgeStat/delete/{id}")
    public Response<Boolean> yktUserAgeStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            yktService.deleteUserAgeById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("青少年技能培训-年龄分布-更新")
    @AnonymousPostMapping("/yktUserAgeStat/update")
    public Response<HydResultOrderYktUserAgeStat> yktUserAgeStatUpdate(@RequestBody HydResultOrderYktUserAgeStat yktUserAgeStat) {
        return Response.ok(yktService.update(yktUserAgeStat));
    }


    @ApiOperation("青少年技能培训-性别统计-分页查询")
    @AnonymousGetMapping("/yktUserSexStat/list")
    public Response<PageResult<HydResultOrderYktUserSexStat>> yktUserSexStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(yktService.queryAllUserSex(page, size));
    }

    @ApiOperation("青少年技能培训-性别统计-根据ID查询详情")
    @AnonymousGetMapping("/yktUserSexStat/detail/{id}")
    public Response<HydResultOrderYktUserSexStat> yktUserSexStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(yktService.findUserSexById(id));
    }

    @ApiOperation("青少年技能培训-性别统计-增加")
    @AnonymousPostMapping("/yktUserSexStat/add")
    public ResponseEntity<HydResultOrderYktUserSexStat> yktUserSexStatAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydResultOrderYktUserSexStat yktUserSexStat) {
        return ResponseEntity.ok(yktService.save(yktUserSexStat));
    }

    @ApiOperation("青少年技能培训-性别统计-删除")
    @AnonymousDeleteMapping("/yktUserSexStat/delete/{id}")
    public Response<Boolean> yktUserSexStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            yktService.deleteUserSexById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("青少年技能培训-性别统计-更新")
    @AnonymousPostMapping("/yktUserSexStat/update")
    public Response<HydResultOrderYktUserSexStat> yktUserSexStatUpdate(@RequestBody HydResultOrderYktUserSexStat yktUserSexStat) {
        return Response.ok(yktService.update(yktUserSexStat));
    }


    @ApiOperation("大众赛事-总览信息表-分页查询")
    @AnonymousGetMapping("/eventsOverviewStat/list")
    public Response<PageResult<HydResultEventsOverviewStat>> eventsOverviewStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(publicEventsService.queryAllOverview(page, size));
    }

    @ApiOperation("大众赛事-总览信息表-根据ID查询详情")
    @AnonymousGetMapping("/eventsOverviewStat/detail/{id}")
    public Response<HydResultEventsOverviewStat> eventsOverviewStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(publicEventsService.findOverviewById(id));
    }

    @ApiOperation("大众赛事-总览信息表-增加")
    @AnonymousPostMapping("/eventsOverviewStat/add")
    public ResponseEntity<HydResultEventsOverviewStat> eventsOverviewStatAdd(
            @ApiParam(value = "大众赛事-总览信息表", required = true) @Valid @RequestBody HydResultEventsOverviewStat eventsOverviewStat) {
        return ResponseEntity.ok(publicEventsService.saveOverview(eventsOverviewStat));
    }

    @ApiOperation("大众赛事-总览信息表-删除")
    @AnonymousDeleteMapping("/eventsOverviewStat/delete/{id}")
    public Response<Boolean> eventsOverviewStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            publicEventsService.deleteOverviewById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("大众赛事-总览信息表-更新")
    @AnonymousPostMapping("/eventsOverviewStat/update")
    public Response<HydResultEventsOverviewStat> eventsOverviewStatUpdate(@RequestBody HydResultEventsOverviewStat eventsOverviewStat) {
        return Response.ok(publicEventsService.updateOverview(eventsOverviewStat));
    }


    @ApiOperation("大众赛事-各月办赛数据表-分页查询")
    @AnonymousGetMapping("/eventsMonthCountStat/list")
    public Response<PageResult<HydResultEventsMonthCountStat>> eventsMonthCountStatList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(publicEventsService.queryAllMonthCount(page, size));
    }

    @ApiOperation("大众赛事-各月办赛数据表-根据ID查询详情")
    @AnonymousGetMapping("/eventsMonthCountStat/detail/{id}")
    public Response<HydResultEventsMonthCountStat> eventsMonthCountStatUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(publicEventsService.findMonthCountById(id));
    }

    @ApiOperation("大众赛事-各月办赛数据表-增加")
    @AnonymousPostMapping("/eventsMonthCountStat/add")
    public ResponseEntity<HydResultEventsMonthCountStat> eventsMonthCountStatAdd(
            @ApiParam(value = "大众赛事-各月办赛数据表", required = true) @Valid @RequestBody HydResultEventsMonthCountStat eventsMonthCountStat) {
        return ResponseEntity.ok(publicEventsService.saveMonthCount(eventsMonthCountStat));
    }

    @ApiOperation("大众赛事-各月办赛数据表-删除")
    @AnonymousDeleteMapping("/eventsMonthCountStat/delete/{id}")
    public Response<Boolean> eventsMonthCountStatDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            publicEventsService.deleteMonthCountById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("大众赛事-各月办赛数据表-更新")
    @AnonymousPostMapping("/eventsMonthCountStat/update")
    public Response<HydResultEventsMonthCountStat> eventsMonthCountStatUpdate(@RequestBody HydResultEventsMonthCountStat eventsMonthCountStat) {
        return Response.ok(publicEventsService.updateMonthCount(eventsMonthCountStat));
    }


    @ApiOperation("大众赛事-赛事数量TOP5项目表-分页查询")
    @AnonymousGetMapping("/eventsSportItemTop/list")
    public Response<PageResult<HydResultEventsSportItemTop>> eventsSportItemTopList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(publicEventsService.queryAllSportItemTop(page, size));
    }

    @ApiOperation("大众赛事-赛事数量TOP5项目表-根据ID查询详情")
    @AnonymousGetMapping("/eventsSportItemTop/detail/{id}")
    public Response<HydResultEventsSportItemTop> eventsSportItemTopUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(publicEventsService.findSportItemTopById(id));
    }

    @ApiOperation("大众赛事-赛事数量TOP5项目表-增加")
    @AnonymousPostMapping("/eventsSportItemTop/add")
    public ResponseEntity<HydResultEventsSportItemTop> eventsSportItemTopAdd(
            @ApiParam(value = "大众赛事-赛事数量TOP5项目表", required = true) @Valid @RequestBody HydResultEventsSportItemTop eventsSportItemTop) {
        return ResponseEntity.ok(publicEventsService.saveSportItemTop(eventsSportItemTop));
    }

    @ApiOperation("大众赛事-赛事数量TOP5项目表-删除")
    @AnonymousDeleteMapping("/eventsSportItemTop/delete/{id}")
    public Response<Boolean> eventsSportItemTopDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            publicEventsService.deleteSportItemTopById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("大众赛事-赛事数量TOP5项目表-更新")
    @AnonymousPostMapping("/eventsSportItemTop/update")
    public Response<HydResultEventsSportItemTop> eventsSportItemTopUpdate(@RequestBody HydResultEventsSportItemTop eventsSportItemTop) {
        return Response.ok(publicEventsService.updateSportItemTop(eventsSportItemTop));
    }


    @ApiOperation("大众赛事-参赛人数人档表-分页查询")
    @AnonymousGetMapping("/eventsParticipantLevel/list")
    public Response<PageResult<HydResultEventsParticipantLevel>> eventsParticipantLevelList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(publicEventsService.queryAllParticipantLevel(page, size));
    }

    @ApiOperation("大众赛事-参赛人数人档表-根据ID查询详情")
    @AnonymousGetMapping("/eventsParticipantLevel/detail/{id}")
    public Response<HydResultEventsParticipantLevel> eventsParticipantLevelUser(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(publicEventsService.findParticipantLevelById(id));
    }

    @ApiOperation("大众赛事-参赛人数人档表-增加")
    @AnonymousPostMapping("/eventsParticipantLevel/add")
    public ResponseEntity<HydResultEventsParticipantLevel> eventsParticipantLevelAdd(
            @ApiParam(value = "大众赛事-参赛人数人档表", required = true) @Valid @RequestBody HydResultEventsParticipantLevel eventsParticipantLevel) {
        return ResponseEntity.ok(publicEventsService.saveParticipantLevel(eventsParticipantLevel));
    }

    @ApiOperation("大众赛事-参赛人数人档表-删除")
    @AnonymousDeleteMapping("/eventsParticipantLevel/delete/{id}")
    public Response<Boolean> eventsParticipantLevelDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            publicEventsService.deleteParticipantLevelById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("大众赛事-参赛人数人档表-更新")
    @AnonymousPostMapping("/eventsParticipantLevel/update")
    public Response<HydResultEventsParticipantLevel> eventsParticipantLevelUpdate(@RequestBody HydResultEventsParticipantLevel eventsParticipantLevel) {
        return Response.ok(publicEventsService.updateParticipantLevel(eventsParticipantLevel));
    }


    // ========================== 社会体育指导员-级别统计（HydResultInstructorLevel） ==========================
    @ApiOperation("社会体育指导员-级别统计-分页查询")
    @AnonymousGetMapping("/instructorLevel/list")
    public Response<PageResult<HydResultInstructorLevel>> instructorLevelList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(instructorService.queryAllLevel(page, size));
    }

    @ApiOperation("社会体育指导员-级别统计-根据ID查询详情")
    @AnonymousGetMapping("/instructorLevel/detail/{id}")
    public Response<HydResultInstructorLevel> instructorLevelDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(instructorService.findLevelById(id));
    }

    @ApiOperation("社会体育指导员-级别统计-增加")
    @AnonymousPostMapping("/instructorLevel/add")
    public ResponseEntity<HydResultInstructorLevel> instructorLevelAdd(
            @ApiParam(value = "社会体育指导员-级别统计", required = true) @RequestBody HydResultInstructorLevel instructorLevel) {
        return ResponseEntity.ok(instructorService.save(instructorLevel));
    }

    @ApiOperation("社会体育指导员-级别统计-删除")
    @AnonymousDeleteMapping("/instructorLevel/delete/{id}")
    public Response<Boolean> instructorLevelDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            instructorService.deleteLevelById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员-级别统计-更新")
    @AnonymousPostMapping("/instructorLevel/update")
    public Response<HydResultInstructorLevel> instructorLevelUpdate(@RequestBody HydResultInstructorLevel instructorLevel) {
        return Response.ok(instructorService.update(instructorLevel));
    }


    // ========================== 社会体育指导员-概览（HydResultInstructorOverview） ==========================
    @ApiOperation("社会体育指导员-概览-分页查询")
    @AnonymousGetMapping("/instructorOverview/list")
    public Response<PageResult<HydResultInstructorOverview>> instructorOverviewList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(instructorService.queryAllOverview(page, size));
    }

    @ApiOperation("社会体育指导员-概览-根据ID查询详情")
    @AnonymousGetMapping("/instructorOverview/detail/{id}")
    public Response<HydResultInstructorOverview> instructorOverviewDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(instructorService.findOverviewById(id));
    }

    @ApiOperation("社会体育指导员-概览-增加")
    @AnonymousPostMapping("/instructorOverview/add")
    public ResponseEntity<HydResultInstructorOverview> instructorOverviewAdd(
            @ApiParam(value = "社会体育指导员-概览", required = true) @RequestBody HydResultInstructorOverview instructorOverview) {
        return ResponseEntity.ok(instructorService.save(instructorOverview));
    }

    @ApiOperation("社会体育指导员-概览-删除")
    @AnonymousDeleteMapping("/instructorOverview/delete/{id}")
    public Response<Boolean> instructorOverviewDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            instructorService.deleteOverviewById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员-概览-更新")
    @AnonymousPostMapping("/instructorOverview/update")
    public Response<HydResultInstructorOverview> instructorOverviewUpdate(@RequestBody HydResultInstructorOverview instructorOverview) {
        return Response.ok(instructorService.update(instructorOverview));
    }


    // ========================== 社会体育指导员-各区指导人员统计（HydResultInstructorRegion） ==========================
    @ApiOperation("社会体育指导员-各区指导人员统计-分页查询")
    @AnonymousGetMapping("/instructorRegion/list")
    public Response<PageResult<HydResultInstructorRegion>> instructorRegionList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(instructorService.queryAllRegion(page, size));
    }

    @ApiOperation("社会体育指导员-各区指导人员统计-根据ID查询详情")
    @AnonymousGetMapping("/instructorRegion/detail/{id}")
    public Response<HydResultInstructorRegion> instructorRegionDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(instructorService.findRegionById(id));
    }

    @ApiOperation("社会体育指导员-各区指导人员统计-增加")
    @AnonymousPostMapping("/instructorRegion/add")
    public ResponseEntity<HydResultInstructorRegion> instructorRegionAdd(
            @ApiParam(value = "社会体育指导员-各区指导人员统计", required = true) @RequestBody HydResultInstructorRegion instructorRegion) {
        return ResponseEntity.ok(instructorService.save(instructorRegion));
    }

    @ApiOperation("社会体育指导员-各区指导人员统计-删除")
    @AnonymousDeleteMapping("/instructorRegion/delete/{id}")
    public Response<Boolean> instructorRegionDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            instructorService.deleteRegionById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员-各区指导人员统计-更新")
    @AnonymousPostMapping("/instructorRegion/update")
    public Response<HydResultInstructorRegion> instructorRegionUpdate(@RequestBody HydResultInstructorRegion instructorRegion) {
        return Response.ok(instructorService.update(instructorRegion));
    }


    // ========================== 社会体育指导员-项目统计（HydResultInstructorServiceProject） ==========================
    @ApiOperation("社会体育指导员-项目统计-分页查询")
    @AnonymousGetMapping("/instructorServiceProject/list")
    public Response<PageResult<HydResultInstructorServiceProject>> instructorServiceProjectList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(instructorService.queryAllServiceProject(page, size));
    }

    @ApiOperation("社会体育指导员-项目统计-根据ID查询详情")
    @AnonymousGetMapping("/instructorServiceProject/detail/{id}")
    public Response<HydResultInstructorServiceProject> instructorServiceProjectDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(instructorService.findServiceProjectById(id));
    }

    @ApiOperation("社会体育指导员-项目统计-增加")
    @AnonymousPostMapping("/instructorServiceProject/add")
    public ResponseEntity<HydResultInstructorServiceProject> instructorServiceProjectAdd(
            @ApiParam(value = "社会体育指导员-项目统计", required = true) @RequestBody HydResultInstructorServiceProject instructorServiceProject) {
        return ResponseEntity.ok(instructorService.save(instructorServiceProject));
    }

    @ApiOperation("社会体育指导员-项目统计-删除")
    @AnonymousDeleteMapping("/instructorServiceProject/delete/{id}")
    public Response<Boolean> instructorServiceProjectDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            instructorService.deleteServiceProjectById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员-项目统计-更新")
    @AnonymousPostMapping("/instructorServiceProject/update")
    public Response<HydResultInstructorServiceProject> instructorServiceProjectUpdate(@RequestBody HydResultInstructorServiceProject instructorServiceProject) {
        return Response.ok(instructorService.update(instructorServiceProject));
    }


    // ========================== 社会体育指导员-指导项目统计TOP15（HydResultInstructorServiceProjectTop） ==========================
    @ApiOperation("社会体育指导员-指导项目统计TOP15-分页查询")
    @AnonymousGetMapping("/instructorServiceProjectTop/list")
    public Response<PageResult<HydResultInstructorServiceProjectTop>> instructorServiceProjectTopList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(instructorService.queryAllServiceProjectTop(page, size));
    }

    @ApiOperation("社会体育指导员-指导项目统计TOP15-根据ID查询详情")
    @AnonymousGetMapping("/instructorServiceProjectTop/detail/{id}")
    public Response<HydResultInstructorServiceProjectTop> instructorServiceProjectTopDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(instructorService.findServiceProjectTopById(id));
    }

    @ApiOperation("社会体育指导员-指导项目统计TOP15-增加")
    @AnonymousPostMapping("/instructorServiceProjectTop/add")
    public ResponseEntity<HydResultInstructorServiceProjectTop> instructorServiceProjectTopAdd(
            @ApiParam(value = "社会体育指导员-指导项目统计TOP15", required = true) @RequestBody HydResultInstructorServiceProjectTop instructorServiceProjectTop) {
        return ResponseEntity.ok(instructorService.save(instructorServiceProjectTop));
    }

    @ApiOperation("社会体育指导员-指导项目统计TOP15-删除")
    @AnonymousDeleteMapping("/instructorServiceProjectTop/delete/{id}")
    public Response<Boolean> instructorServiceProjectTopDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            instructorService.deleteServiceProjectTopById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员-指导项目统计TOP15-更新")
    @AnonymousPostMapping("/instructorServiceProjectTop/update")
    public Response<HydResultInstructorServiceProjectTop> instructorServiceProjectTopUpdate(@RequestBody HydResultInstructorServiceProjectTop instructorServiceProjectTop) {
        return Response.ok(instructorService.update(instructorServiceProjectTop));
    }


    // ========================== 社会体育指导员-性别统计（HydResultInstructorUserSex） ==========================
    @ApiOperation("社会体育指导员-性别统计-分页查询")
    @AnonymousGetMapping("/instructorUserSex/list")
    public Response<PageResult<HydResultInstructorUserSex>> instructorUserSexList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(instructorService.queryAllUserSex(page, size));
    }

    @ApiOperation("社会体育指导员-性别统计-根据ID查询详情")
    @AnonymousGetMapping("/instructorUserSex/detail/{id}")
    public Response<HydResultInstructorUserSex> instructorUserSexDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(instructorService.findUserSexById(id));
    }

    @ApiOperation("社会体育指导员-性别统计-增加")
    @AnonymousPostMapping("/instructorUserSex/add")
    public ResponseEntity<HydResultInstructorUserSex> instructorUserSexAdd(
            @ApiParam(value = "社会体育指导员-性别统计", required = true) @RequestBody HydResultInstructorUserSex instructorUserSex) {
        return ResponseEntity.ok(instructorService.save(instructorUserSex));
    }

    @ApiOperation("社会体育指导员-性别统计-删除")
    @AnonymousDeleteMapping("/instructorUserSex/delete/{id}")
    public Response<Boolean> instructorUserSexDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            instructorService.deleteUserSexById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员-性别统计-更新")
    @AnonymousPostMapping("/instructorUserSex/update")
    public Response<HydResultInstructorUserSex> instructorUserSexUpdate(@RequestBody HydResultInstructorUserSex instructorUserSex) {
        return Response.ok(instructorService.update(instructorUserSex));
    }


    // ========================== 校外培训机构-各区场馆数量统计（HydResultLaStadiumDistrict） ==========================
    @ApiOperation("校外培训机构-各区场馆数量统计-分页查询")
    @AnonymousGetMapping("/laStadiumDistrict/list")
    public Response<PageResult<HydResultLaStadiumDistrict>> laStadiumDistrictList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(laStadiumStatService.queryAllDistrictStat(page, size));
    }

    @ApiOperation("校外培训机构-各区场馆数量统计-根据ID查询详情")
    @AnonymousGetMapping("/laStadiumDistrict/detail/{id}")
    public Response<HydResultLaStadiumDistrict> laStadiumDistrictDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(laStadiumStatService.getDistrictStatById(id));
    }

    @ApiOperation("校外培训机构-各区场馆数量统计-增加")
    @AnonymousPostMapping("/laStadiumDistrict/add")
    public ResponseEntity<HydResultLaStadiumDistrict> laStadiumDistrictAdd(
            @ApiParam(value = "校外培训机构-各区场馆数量统计", required = true) @RequestBody HydResultLaStadiumDistrict laStadiumDistrict) {
        return ResponseEntity.ok(laStadiumStatService.saveDistrictStat(laStadiumDistrict));
    }

    @ApiOperation("校外培训机构-各区场馆数量统计-删除")
    @AnonymousDeleteMapping("/laStadiumDistrict/delete/{id}")
    public Response<Boolean> laStadiumDistrictDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            laStadiumStatService.deleteDistrictStatById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("校外培训机构-各区场馆数量统计-更新")
    @AnonymousPostMapping("/laStadiumDistrict/update")
    public Response<HydResultLaStadiumDistrict> laStadiumDistrictUpdate(@RequestBody HydResultLaStadiumDistrict laStadiumDistrict) {
        return Response.ok(laStadiumStatService.updateDistrictStat(laStadiumDistrict));
    }


    // ========================== 校外培训机构-项目类型统计（HydResultLaStadiumSportName） ==========================
    @ApiOperation("校外培训机构-项目类型统计-分页查询")
    @AnonymousGetMapping("/laStadiumSportName/list")
    public Response<PageResult<HydResultLaStadiumSportName>> laStadiumSportNameList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(laStadiumStatService.queryAllSportNameStat(page, size));
    }

    @ApiOperation("校外培训机构-项目类型统计-根据ID查询详情")
    @AnonymousGetMapping("/laStadiumSportName/detail/{id}")
    public Response<HydResultLaStadiumSportName> laStadiumSportNameDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(laStadiumStatService.getSportNameStatById(id));
    }

    @ApiOperation("校外培训机构-项目类型统计-增加")
    @AnonymousPostMapping("/laStadiumSportName/add")
    public ResponseEntity<HydResultLaStadiumSportName> laStadiumSportNameAdd(
            @ApiParam(value = "校外培训机构-项目类型统计", required = true) @RequestBody HydResultLaStadiumSportName laStadiumSportName) {
        return ResponseEntity.ok(laStadiumStatService.saveSportNameStat(laStadiumSportName));
    }

    @ApiOperation("校外培训机构-项目类型统计-删除")
    @AnonymousDeleteMapping("/laStadiumSportName/delete/{id}")
    public Response<Boolean> laStadiumSportNameDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            laStadiumStatService.deleteSportNameStatById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("校外培训机构-项目类型统计-更新")
    @AnonymousPostMapping("/laStadiumSportName/update")
    public Response<HydResultLaStadiumSportName> laStadiumSportNameUpdate(@RequestBody HydResultLaStadiumSportName laStadiumSportName) {
        return Response.ok(laStadiumStatService.updateSportNameStat(laStadiumSportName));
    }


    // ========================== 校外培训机构-项目类型占比TOP10统计（HydResultLaStadiumSportNameTop） ==========================
    @ApiOperation("校外培训机构-项目类型占比TOP10统计-分页查询")
    @AnonymousGetMapping("/laStadiumSportNameTop/list")
    public Response<PageResult<HydResultLaStadiumSportNameTop>> laStadiumSportNameTopList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(laStadiumStatService.queryAllSportNameTopStat(page, size));
    }

    @ApiOperation("校外培训机构-项目类型占比TOP10统计-根据ID查询详情")
    @AnonymousGetMapping("/laStadiumSportNameTop/detail/{id}")
    public Response<HydResultLaStadiumSportNameTop> laStadiumSportNameTopDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(laStadiumStatService.getSportNameTopStatById(id));
    }

    @ApiOperation("校外培训机构-项目类型占比TOP10统计-增加")
    @AnonymousPostMapping("/laStadiumSportNameTop/add")
    public ResponseEntity<HydResultLaStadiumSportNameTop> laStadiumSportNameTopAdd(
            @ApiParam(value = "校外培训机构-项目类型占比TOP10统计", required = true) @RequestBody HydResultLaStadiumSportNameTop laStadiumSportNameTop) {
        return ResponseEntity.ok(laStadiumStatService.saveSportNameTopStat(laStadiumSportNameTop));
    }

    @ApiOperation("校外培训机构-项目类型占比TOP10统计-删除")
    @AnonymousDeleteMapping("/laStadiumSportNameTop/delete/{id}")
    public Response<Boolean> laStadiumSportNameTopDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            laStadiumStatService.deleteSportNameTopStatById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("校外培训机构-项目类型占比TOP10统计-更新")
    @AnonymousPostMapping("/laStadiumSportNameTop/update")
    public Response<HydResultLaStadiumSportNameTop> laStadiumSportNameTopUpdate(@RequestBody HydResultLaStadiumSportNameTop laStadiumSportNameTop) {
        return Response.ok(laStadiumStatService.updateSportNameTopStat(laStadiumSportNameTop));
    }
}
