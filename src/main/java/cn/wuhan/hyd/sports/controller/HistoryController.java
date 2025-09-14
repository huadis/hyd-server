package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.repository.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 功能说明： 历史数据管理 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月15日 <br>
 */
@RestController
@RequestMapping("/api/history")
@Api(tags = "历史数据管理")
public class HistoryController {

    @Resource
    private HydResultUserChannelHistoryRepo userChannelHistoryRepo;
    @Resource
    private HydResultUserRegisterHistoryRepo userRegisterHistoryRepo;
    @Resource
    private HydResultUserSexHistoryRepo userSexHistoryRepo;
    @Resource
    private HydResultUserAgeHistoryRepo userAgeHistoryRepo;
    @Resource
    private HydResultUserRepurchaseHistoryRepo userRepurchaseHistoryRepo;
    @Resource
    private HydResultOrderHistoryRepo orderHistoryRepo;
    @Resource
    private HydResultOrderMonthHistoryRepo orderMonthHistoryRepo;
    @Resource
    private HydResultOrderSportHistoryRepo orderSportHistoryRepo;
    @Resource
    private HydResultOrderStadiumHistoryRepo orderStadiumHistoryRepo;
    @Resource
    private HydResultStadiumHistoryRepo stadiumHistoryRepo;
    @Resource
    private HydResultStadiumDistrictHistoryRepo stadiumDistrictHistoryRepo;
    @Resource
    private HydResultStadiumSportCouponHistoryRepo stadiumSportCouponHistoryRepo;
    @Resource
    private HydResultFacilityHistoryRepo facilityHistoryRepo;
    @Resource
    private HydResultFacilityDistrictHistoryRepo facilityDistrictHistoryRepo;
    @Resource
    private HydResultStadiumMapHistoryRepo stadiumMapHistoryRepo;
    @Resource
    private HydResultFacilityYearHistoryRepo facilityYearHistoryRepo;
    @Resource
    private HydResultFacilityDistrictMonthHistoryRepo facilityDistrictMonthHistoryRepo;
    @Resource
    private HydResultFacilityInspectHistoryRepo facilityInspectHistoryRepo;
    @Resource
    private HydResultCouponAmountHistoryRepo couponAmountHistoryRepo;
    @Resource
    private HydResultStockHistoryRepo stockHistoryRepo;
    @Resource
    private HydResultCouponUserHistoryRepo couponUserHistoryRepo;
    @Resource
    private HydResultCouponUserAgeHistoryRepo couponUserAgeHistoryRepo;
    @Resource
    private HydResultCouponStadiumTopHistoryRepo couponStadiumTopHistoryRepo;
    @Resource
    private HydResultStadiumMapPointHistoryRepo stadiumMapPointHistoryRepo;
    @Resource
    private HydOriginTenantHistoryRepo originTenantHistoryRepo;
    @Resource
    private HydOriginStadiumHistoryRepo originStadiumHistoryRepo;
    @Resource
    private HydOriginStadiumItemHistoryRepo originStadiumItemHistoryRepo;
    @Resource
    private HydOriginTrainingActivityHistoryRepo originTrainingActivityHistoryRepo;
    @Resource
    private HydOriginTrainingActivityItemHistoryRepo originTrainingActivityItemHistoryRepo;
    @Resource
    private HydOriginTrainingActivityItemStadiumHistoryRepo originTrainingActivityItemStadiumHistoryRepo;
    @Resource
    private HydOriginTrainingCourseHistoryRepo originTrainingCourseHistoryRepo;
    @Resource
    private HydOriginOrderHistoryRepo originOrderHistoryRepo;
    @Resource
    private HydOriginLaStadiumHistoryRepo originLaStadiumHistoryRepo;
    @Resource
    private HydOriginLaStadiumFileHistoryRepo originLaStadiumFileHistoryRepo;
    @Resource
    private HydExcelPublicEventsHistoryRepo eventsHistoryRepo;
    @Resource
    private HydExcelInstructorInfoHistoryRepo instructorInfoHistoryRepo;
    @Resource
    private HydExcelSportsOrgRepo sportsOrgRepo;
    @Resource
    private HydExcelIndustryCoreIndicatorsHistoryRepo industryCoreIndicatorsHistoryRepo;
    @Resource
    private HydExcelIndustryEmployeeCountHistoryRepo industryEmployeeCountHistoryRepo;
    @Resource
    private HydExcelIndustryEntityCountRatioHistoryRepo industryEntityCountRatioHistoryRepo;
    @Resource
    private HydExcelIndustryGoodsPurchaseRateHistoryRepo industryGoodsPurchaseRateHistoryRepo;
    @Resource
    private HydExcelIndustryGrowthValueTrendHistoryRepo industryGrowthValueTrendHistoryRepo;
    @Resource
    private HydExcelIndustryScaleTrendHistoryRepo industryScaleTrendHistoryRepo;
    @Resource
    private HydExcelIndustryTrainingParticipationRateHistoryRepo industryTrainingParticipationRateHistoryRepo;
    @Resource
    private HydExcelInstructorAgeGrowthHistoryRepo instructorAgeGrowthHistoryRepo;
    @Resource
    private HydExcelInstructorAgeStatsHistoryRepo instructorAgeStatsHistoryRepo;

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @ApiOperation("1. 场馆预定-用户来源渠道-分页查询")
    @AnonymousGetMapping("/userChannel/list")
    public Response<PageResult<HydResultUserChannelHistory>> userChannelList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultUserChannelHistory> pageResult = userChannelHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultUserChannelHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("2. 场馆预定-每月新增用户-分页查询")
    @AnonymousGetMapping("/userRegister/list")
    public Response<PageResult<HydResultUserRegisterHistory>> userRegisterList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultUserRegisterHistory> pageResult = userRegisterHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultUserRegisterHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("3. 场馆预定-男女占比-分页查询")
    @AnonymousGetMapping("/userSex/list")
    public Response<PageResult<HydResultUserSexHistory>> userSexList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultUserSexHistory> pageResult = userSexHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultUserSexHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("4. 场馆预定-年龄占比-分页查询")
    @AnonymousGetMapping("/userAge/list")
    public Response<PageResult<HydResultUserAgeHistory>> userAgeList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultUserAgeHistory> pageResult = userAgeHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultUserAgeHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("5. 场馆预定-复购率-分页查询")
    @AnonymousGetMapping("/userRepurchase/list")
    public Response<PageResult<HydResultUserRepurchaseHistory>> userRepurchaseList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultUserRepurchaseHistory> pageResult = userRepurchaseHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultUserRepurchaseHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("6. 体育消费卷-订单数量-分页查询")
    @AnonymousGetMapping("/order/list")
    public Response<PageResult<HydResultOrderHistory>> orderList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultOrderHistory> pageResult = orderHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultOrderHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("7. 体育消费卷-订单趋势-分页查询")
    @AnonymousGetMapping("/orderMonth/list")
    public Response<PageResult<HydResultOrderMonthHistory>> orderMonthList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultOrderMonthHistory> pageResult = orderMonthHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultOrderMonthHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("8. 体育消费卷-项目消费券订单金额Top5-分页查询")
    @AnonymousGetMapping("/orderSport/list")
    public Response<PageResult<HydResultOrderSportHistory>> orderSportList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultOrderSportHistory> pageResult = orderSportHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultOrderSportHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("9. 体育消费卷-场馆消费券订单金额Top5-分页查询")
    @AnonymousGetMapping("/orderStadium/list")
    public Response<PageResult<HydResultOrderStadiumHistory>> orderStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultOrderStadiumHistory> pageResult = orderStadiumHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultOrderStadiumHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("10. 场馆预定-在线场馆数量-分页查询")
    @AnonymousGetMapping("/stadium/list")
    public Response<PageResult<HydResultStadiumHistory>> stadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultStadiumHistory> pageResult = stadiumHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultStadiumHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("11. 场馆预定-在线场馆各区情况-分页查询")
    @AnonymousGetMapping("/stadiumDistrict/list")
    public Response<PageResult<HydResultStadiumDistrictHistory>> stadiumDistrictList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultStadiumDistrictHistory> pageResult = stadiumDistrictHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultStadiumDistrictHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("12. 场馆预定-运动项目分布用券数占比-分页查询")
    @AnonymousGetMapping("/stadiumSportCoupon/list")
    public Response<PageResult<HydResultStadiumSportCouponHistory>> stadiumSportCouponList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultStadiumSportCouponHistory> pageResult = stadiumSportCouponHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultStadiumSportCouponHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("13. 体育基础设施-设施全貌-分页查询")
    @AnonymousGetMapping("/facility/list")
    public Response<PageResult<HydResultFacilityHistory>> facilityList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultFacilityHistory> pageResult = facilityHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultFacilityHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("14. 体育基础设施-设施各区分布-分页查询")
    @AnonymousGetMapping("/facilityDistrict/list")
    public Response<PageResult<HydResultFacilityDistrictHistory>> facilityDistrictList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultFacilityDistrictHistory> pageResult = facilityDistrictHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultFacilityDistrictHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("15. 体育基础设施-电子地图-分页查询")
    @AnonymousGetMapping("/stadiumMap/list")
    public Response<PageResult<HydResultStadiumMapHistory>> stadiumMapList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultStadiumMapHistory> pageResult = stadiumMapHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultStadiumMapHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("16. 体育基础设施-健身点位年数据-分页查询")
    @AnonymousGetMapping("/facilityYear/list")
    public Response<PageResult<HydResultFacilityYearHistory>> facilityYearList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultFacilityYearHistory> pageResult = facilityYearHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultFacilityYearHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("17. 体育基础设施-设施各区数据-分页查询")
    @AnonymousGetMapping("/facilityDistrictMonth/list")
    public Response<PageResult<HydResultFacilityDistrictMonthHistory>> facilityDistrictMonthList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultFacilityDistrictMonthHistory> pageResult = facilityDistrictMonthHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultFacilityDistrictMonthHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("18. 体育基础设施-巡维动态-分页查询")
    @AnonymousGetMapping("/facilityInspect/list")
    public Response<PageResult<HydResultFacilityInspectHistory>> facilityInspectList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultFacilityInspectHistory> pageResult = facilityInspectHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultFacilityInspectHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("19. 体育消费卷-消费券总金额-分页查询")
    @AnonymousGetMapping("/couponAmount/list")
    public Response<PageResult<HydResultCouponAmountHistory>> couponAmountList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultCouponAmountHistory> pageResult = couponAmountHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultCouponAmountHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("20. 体育消费卷-消费券领券用券-分页查询")
    @AnonymousGetMapping("/stock/list")
    public Response<PageResult<HydResultStockHistory>> stockList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultStockHistory> pageResult = stockHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultStockHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("21. 体育消费卷-券用户分析-分页查询")
    @AnonymousGetMapping("/couponUser/list")
    public Response<PageResult<HydResultCouponUserHistory>> couponUserList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultCouponUserHistory> pageResult = couponUserHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultCouponUserHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("22. 体育消费卷-券用户年龄分布-分页查询")
    @AnonymousGetMapping("/couponUserAge/list")
    public Response<PageResult<HydResultCouponUserAgeHistory>> couponUserAgeList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultCouponUserAgeHistory> pageResult = couponUserAgeHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultCouponUserAgeHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("23. 场馆预定-消费券场馆预订top-分页查询")
    @AnonymousGetMapping("/couponStadiumTop/list")
    public Response<PageResult<HydResultCouponStadiumTopHistory>> couponStadiumTopList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultCouponStadiumTopHistory> pageResult = couponStadiumTopHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultCouponStadiumTopHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("24. 体育基础设施-电子地图点位信息-分页查询")
    @AnonymousGetMapping("/stadiumMapPoint/list")
    public Response<PageResult<HydResultStadiumMapPointHistory>> stadiumMapPointList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydResultStadiumMapPointHistory> pageResult = stadiumMapPointHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydResultStadiumMapPointHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("25. 青少年技能培训-组织机构名-分页查询")
    @AnonymousGetMapping("/originTenant/list")
    public Response<PageResult<HydOriginTenantHistory>> originTenantList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginTenantHistory> pageResult = originTenantHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginTenantHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("26. 青少年技能培训-培训场馆-分页查询")
    @AnonymousGetMapping("/originStadium/list")
    public Response<PageResult<HydOriginStadiumHistory>> originStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginStadiumHistory> pageResult = originStadiumHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginStadiumHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("27. 青少年技能培训-场馆培训项目-分页查询")
    @AnonymousGetMapping("/originStadiumItem/list")
    public Response<PageResult<HydOriginStadiumItemHistory>> originStadiumItemList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginStadiumItemHistory> pageResult = originStadiumItemHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginStadiumItemHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("28. 青少年技能培训-培训活动-分页查询")
    @AnonymousGetMapping("/originTrainingActivity/list")
    public Response<PageResult<HydOriginTrainingActivityHistory>> originTrainingActivityList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginTrainingActivityHistory> pageResult = originTrainingActivityHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginTrainingActivityHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("29. 青少年技能培训-培训活动支持的项目-分页查询")
    @AnonymousGetMapping("/originTrainingActivityItem/list")
    public Response<PageResult<HydOriginTrainingActivityItemHistory>> originTrainingActivityItemList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginTrainingActivityItemHistory> pageResult = originTrainingActivityItemHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginTrainingActivityItemHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("30. 青少年技能培训-培训活动场馆支持的项目-分页查询")
    @AnonymousGetMapping("/originTrainingActivityItemStadium/list")
    public Response<PageResult<HydOriginTrainingActivityItemStadiumHistory>> originTrainingActivityItemStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginTrainingActivityItemStadiumHistory> pageResult = originTrainingActivityItemStadiumHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginTrainingActivityItemStadiumHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("31. 青少年技能培训-培训课程-分页查询")
    @AnonymousGetMapping("/originTrainingCourse/list")
    public Response<PageResult<HydOriginTrainingCourseHistory>> originTrainingCourseList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginTrainingCourseHistory> pageResult = originTrainingCourseHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginTrainingCourseHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("32. 青少年技能培训-订单-分页查询")
    @AnonymousGetMapping("/originOrder/list")
    public Response<PageResult<HydOriginOrderHistory>> originOrderList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createdTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginOrderHistory> pageResult = originOrderHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginOrderHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("33. 校外培训机构-分页查询")
    @AnonymousGetMapping("/originLaStadium/list")
    public Response<PageResult<HydOriginLaStadiumHistory>> originLaStadiumList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginLaStadiumHistory> pageResult = originLaStadiumHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginLaStadiumHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("34. 校外培训机构附件-分页查询")
    @AnonymousGetMapping("/originLaStadiumFile/list")
    public Response<PageResult<HydOriginLaStadiumFileHistory>> originLaStadiumFileList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydOriginLaStadiumFileHistory> pageResult = originLaStadiumFileHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydOriginLaStadiumFileHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("35. 大众赛事-分页查询")
    @AnonymousGetMapping("/publicEvents/list")
    public Response<PageResult<HydExcelPublicEventsHistory>> publicEventsList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelPublicEventsHistory> pageResult = eventsHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelPublicEventsHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("36. 社会体育指导员-分页查询")
    @AnonymousGetMapping("/instructorInfo/list")
    public Response<PageResult<HydExcelInstructorInfoHistory>> instructorInfoList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelInstructorInfoHistory> pageResult = instructorInfoHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelInstructorInfoHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("37. 体育组织-分页查询")
    @AnonymousGetMapping("/sportsOrg/list")
    public Response<PageResult<HydExcelSportsOrg>> sportsOrgList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelSportsOrg> pageResult = sportsOrgRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelSportsOrg> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }


    @ApiOperation("38. 体育产业-核心指标总表-分页查询")
    @AnonymousGetMapping("/industryCoreIndicators/list")
    public Response<PageResult<HydExcelIndustryCoreIndicatorsHistory>> industryCoreIndicatorsList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryCoreIndicatorsHistory> pageResult = industryCoreIndicatorsHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryCoreIndicatorsHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("39. 体育产业-从业人员数量-分页查询")
    @AnonymousGetMapping("/industryEmployeeCount/list")
    public Response<PageResult<HydExcelIndustryEmployeeCountHistory>> industryEmployeeCountList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryEmployeeCountHistory> pageResult = industryEmployeeCountHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryEmployeeCountHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("40. 体育产业-场主体数量-分页查询")
    @AnonymousGetMapping("/industryEntityCountRatio/list")
    public Response<PageResult<HydExcelIndustryEntityCountRatioHistory>> industryEntityCountRatioList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryEntityCountRatioHistory> pageResult = industryEntityCountRatioHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryEntityCountRatioHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("41. 体育产业-居民体育用品购买率表-分页查询")
    @AnonymousGetMapping("/industryGoodsPurchaseRate/list")
    public Response<PageResult<HydExcelIndustryGoodsPurchaseRateHistory>> industryGoodsPurchaseRateList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryGoodsPurchaseRateHistory> pageResult = industryGoodsPurchaseRateHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryGoodsPurchaseRateHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("42. 体育产业-总增速和增加值-分页查询")
    @AnonymousGetMapping("/industryGrowthValueTrend/list")
    public Response<PageResult<HydExcelIndustryGrowthValueTrendHistory>> industryGrowthValueTrendList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryGrowthValueTrendHistory> pageResult = industryGrowthValueTrendHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryGrowthValueTrendHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("43. 体育产业-总规模-分页查询")
    @AnonymousGetMapping("/industryScaleTrend/list")
    public Response<PageResult<HydExcelIndustryScaleTrendHistory>> industryScaleTrendList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryScaleTrendHistory> pageResult = industryScaleTrendHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryScaleTrendHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("44. 体育产业-居民体育培训项目参与率表-分页查询")
    @AnonymousGetMapping("/industryTrainingParticipationRate/list")
    public Response<PageResult<HydExcelIndustryTrainingParticipationRateHistory>> industryTrainingParticipationRateList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelIndustryTrainingParticipationRateHistory> pageResult = industryTrainingParticipationRateHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelIndustryTrainingParticipationRateHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("45. 体育指导员-人数增长统计明细表-分页查询")
    @AnonymousGetMapping("/instructorAgeGrowth/list")
    public Response<PageResult<HydExcelInstructorAgeGrowthHistory>> instructorAgeGrowthList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelInstructorAgeGrowthHistory> pageResult = instructorAgeGrowthHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelInstructorAgeGrowthHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }

    @ApiOperation("46. 体育指导员-年龄统计明细表-分页查询")
    @AnonymousGetMapping("/instructorAgeStats/list")
    public Response<PageResult<HydExcelInstructorAgeStatsHistory>> instructorAgeStatsList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size,
            @ApiParam(value = "开始时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-01-01 00:00:00")
            @RequestParam(required = false) String startTime,
            @ApiParam(value = "结束时间，格式：yyyy-MM-dd HH:mm:ss", example = "2025-12-31 23:59:59")
            @RequestParam(required = false) String endTime) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("createTime").descending());
        Timestamp start, end;
        try {
            // 将字符串时间转换为 Timestamp
            start = startTime != null ? new Timestamp(sdf.parse(startTime).getTime()) : null;
            end = endTime != null ? new Timestamp(sdf.parse(endTime).getTime()) : null;
        } catch (ParseException e) {
            throw new IllegalArgumentException("时间格式错误，正确格式：yyyy-MM-dd HH:mm:ss", e);
        }
        // 按时间参数是否存在执行不同查询
        Page<HydExcelInstructorAgeStatsHistory> pageResult = instructorAgeStatsHistoryRepo.findAllByTimeRange(pageable, start, end);
        PageResult<HydExcelInstructorAgeStatsHistory> result = new PageResult<>();
        result.setTotalElements(pageResult.getTotalElements());
        result.setContent(pageResult.getContent());
        return Response.ok(result);
    }
}
