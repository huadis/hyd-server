package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousDeleteMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.ExcelUtils;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelPublicEvents;
import cn.wuhan.hyd.sports.service.IHydExcelPublicEventsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 大众赛事 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@RestController
@RequestMapping("/api/publicEvent")
@Api(tags = "大众赛事")
public class HydPublicEventController {

    @Resource
    private IHydExcelPublicEventsService hydPublicEventsService;
    private static final Logger log = LoggerFactory.getLogger(HydPublicEventController.class);

    @ApiOperation("手动刷新结果表")
    @AnonymousGetMapping("/refresh")
    public Response<Boolean> refresh() {
        hydPublicEventsService.syncResultData();
        return Response.ok(true);
    }

    /**
     * 下载指定模板文件
     *
     * @return 模板文件的响应实体
     */
    @ApiOperation("下载模板")
    @AnonymousGetMapping(value = "/template/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadTemplate(HttpServletResponse response) {
        String fileName = "大众赛事数据导入模版.xlsx";
        ClassPathResource resource = new ClassPathResource("templates/" + fileName);
        try (InputStream inputStream = resource.getInputStream()) {
            if (!resource.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "模板文件未找到");
                return;
            }

            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition",
                    "attachment; filename=\"" + URLEncoder.encode(fileName, "UTF-8") + "\"; " +
                            "filename*=UTF-8''" + URLEncoder.encode(fileName, "UTF-8"));

            // 获取输出流
            OutputStream outputStream = response.getOutputStream();

            // 复制输入流到输出流
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // 刷新确保数据写出
            outputStream.flush();

        } catch (IOException e) {
            try {
                // 避免重复响应
                if (!response.isCommitted()) {
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "文件下载失败");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @ApiOperation("上传数据")
    @AnonymousPostMapping("/uploadExcel")
    //@ApiImplicitParam(name = "files", value = "上传的文件", dataTypeClass = MultipartFile.class, required = true)
    public ResponseEntity<String> uploadExcel(@RequestPart @RequestParam("file") MultipartFile file) {
        // 校验文件是否为空
        if (file == null || file.isEmpty()) {
            return new ResponseEntity<>("请选择要上传的文件", HttpStatus.BAD_REQUEST);
        }

        // 获取文件名
        String fileName = file.getOriginalFilename();
        if (fileName == null) {
            return new ResponseEntity<>("文件名为空", HttpStatus.BAD_REQUEST);
        }

        // 校验文件格式是否为xlsx
        if (!fileName.toLowerCase().endsWith(".xlsx")) {
            return new ResponseEntity<>("文件必须是xlsx格式", HttpStatus.BAD_REQUEST);
        }

        try {
            log.info("开始执行Excel文件读取");
            Instant start1 = Instant.now();
            Map<String, List<Map<String, Object>>> sheetMapData = ExcelUtils.parseExcelData(file);
            long time1 = Duration.between(start1, Instant.now()).toMillis();
            log.info("Excel文件读取耗时：{}ms", time1);
            Instant start2 = Instant.now();
            boolean flag = hydPublicEventsService.importExcel(sheetMapData);
            long time2 = Duration.between(start2, Instant.now()).toMillis();
            log.info("Excel文件数据保存：{}ms, 成功：{}", time2, flag);
            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>("文件上传或处理失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // ----------------------------------- 大众赛事-体育赛事信息表 -----------------------------------

    @ApiOperation("大众赛事-体育赛事信息表-分页查询")
    @AnonymousGetMapping("/list")
    public Response<PageResult<HydExcelPublicEvents>> eventsList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydPublicEventsService.queryAll(page, size));
    }

    @ApiOperation("大众赛事-体育赛事信息表-根据ID查询详情")
    @AnonymousGetMapping("/detail/{id}")
    public Response<HydExcelPublicEvents> eventsDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydPublicEventsService.findById(id));
    }

    @ApiOperation("大众赛事-体育赛事信息表-增加")
    @AnonymousPostMapping("/add")
    public ResponseEntity<HydExcelPublicEvents> eventsAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelPublicEvents events) {
        return ResponseEntity.ok(hydPublicEventsService.save(events));
    }

    @ApiOperation("大众赛事-体育赛事信息表-删除")
    @AnonymousDeleteMapping("/delete/{id}")
    public Response<Boolean> eventsDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydPublicEventsService.deleteById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("大众赛事-体育赛事信息表-更新")
    @AnonymousPostMapping("/update")
    public Response<HydExcelPublicEvents> eventsUpdate(@RequestBody HydExcelPublicEvents events) {
        return Response.ok(hydPublicEventsService.update(events));
    }

    /**
     * 总览信息
     */
    @ApiOperation("总览信息")
    @AnonymousGetMapping("/overview")
    public Response<Map<String, Object>> overview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.overview(year));
    }

    /**
     * 各月办赛数据
     */
    @ApiOperation("各月办赛数据")
    @AnonymousGetMapping("/latestMonthStat")
    public Response<List<Map<String, Object>>> latestMonthStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.monthStat(year));
    }

    /**
     * 赛事数量top5
     */
    @ApiOperation("赛事数量top5")
    @AnonymousGetMapping("/sportItemTop5")
    public Response<List<Map<String, Object>>> sportItemTop5(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.sportItemTop5(year));
    }

    /**
     * 参赛人数人档
     */
    @ApiOperation("参赛人数人档")
    @AnonymousGetMapping("/participantCountStat")
    public Response<List<Map<String, Object>>> participantCountStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.participantCountStat(year));
    }

    /**
     * 本月赛事
     */
    @ApiOperation("本月赛事")
    @AnonymousGetMapping("/currentMouthEvents")
    public Response<List<Map<String, Object>>> currentMouthEvents(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.currentMouthEvents(year));
    }

    /**
     * 按区域统计赛事
     */
    @ApiOperation("按区域统计赛事")
    @AnonymousGetMapping("/districtCountByYear")
    public Response<List<Map<String, Object>>> districtCountByYear(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.districtCountByYear(year));
    }
}
