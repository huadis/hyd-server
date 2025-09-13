package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.sports.domain.HydOriginLaStadiumHistory;
import cn.wuhan.hyd.sports.service.IHydOriginLaStadiumService;
import cn.wuhan.hyd.sports.service.IHydResultLaStadiumStatService;
import cn.wuhan.hyd.sports.service.IHydSysConfigService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 校外培训机构 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@RestController
@RequestMapping("/api/laStadium")
@Api(tags = "校外培训机构")
public class HydLaStadiumController {

    @Resource
    private IHydOriginLaStadiumService laStadiumService;

    @Resource
    private IHydResultLaStadiumStatService laStadiumStatService;
    private static final Logger log = LoggerFactory.getLogger(HydLaStadiumController.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    @Resource
    private IHydSysConfigService configService;

    @ApiOperation("刷新结果集")
    @AnonymousGetMapping("/refresh")
    public Response<Boolean> refresh() {
        laStadiumStatService.syncResultData();
        return Response.ok(true);
    }

    // 每天凌晨 00:00 执行
    @Scheduled(cron = "0 0 0 * * ?")
    public void dailyCalculation() {
        boolean refresh = !configService.notRefresh("校外培训机构");
        // 是否冻结，不允许更新查询表
        if (refresh) {
            // 记录任务启动日志
            LocalDateTime startTime = LocalDateTime.now();
            log.info("【定时任务】每日数据同步任务启动，启动时间：{}", startTime.format(formatter));
            try {
                // 执行同步操作
                laStadiumStatService.syncResultData();
                // 记录任务完成日志
                LocalDateTime endTime = LocalDateTime.now();
                log.info("【定时任务】每日数据同步任务执行完成，完成时间：{}，耗时：{}毫秒",
                        endTime.format(formatter),
                        java.time.Duration.between(startTime, endTime).toMillis());
            } catch (Exception e) {
                // 记录任务异常日志
                LocalDateTime errorTime = LocalDateTime.now();
                log.error("【定时任务】每日数据同步任务执行失败，失败时间：{}，错误信息：{}",
                        errorTime.format(formatter), e.getMessage(), e);
            }
        } else {
            log.info("【定时任务】每日数据同步任务未执行，该模块查询表已被冻结，无法触发计算");
        }
    }

    /**
     * 所有场馆信息
     */
    @ApiOperation("所有场馆信息")
    @AnonymousGetMapping("/stadiums")
    public Response<List<HydOriginLaStadiumHistory>> stadiums() {
        return Response.ok(laStadiumService.queryAll());
    }

    /**
     * 所有培训机构信息
     */
    @ApiOperation("所有培训机构信息")
    @AnonymousGetMapping("/laStadiums")
    public Response<List<HydOriginLaStadiumHistory>> laStadiums() {
        return Response.ok(laStadiumService.queryAll());
    }

    /**
     * 各区场馆数量统计
     */
    @ApiOperation("各区场馆数量统计")
    @AnonymousGetMapping("/districtStadiumStat")
    public Response<List<Map<String, Object>>> districtStadiumStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(laStadiumStatService.stadiumCountByDistrict(year));
    }

    /**
     * 项目类型占比TOP10
     */
    @ApiOperation("项目类型占比TOP10")
    @AnonymousGetMapping("/sportNameTop10")
    public Response<List<Map<String, Object>>> sportNameTop10(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(laStadiumStatService.itemCountTop10BySportName(year));
    }

    /**
     * 项目类型占比Top5和其他
     */
    @ApiOperation("项目类型占比Top5和其他")
    @AnonymousGetMapping("/sportNameTop5AndOther")
    public Response<List<Map<String, Object>>> sportNameTop5AndOther(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(laStadiumStatService.itemCountBySportName(year));
    }
}
