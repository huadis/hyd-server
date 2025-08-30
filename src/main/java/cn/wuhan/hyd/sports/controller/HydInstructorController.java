package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousDeleteMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.ExcelUtils;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeGrowth;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorAgeStats;
import cn.wuhan.hyd.sports.domain.HydExcelInstructorInfo;
import cn.wuhan.hyd.sports.service.IHydExcelInstructorService;
import cn.wuhan.hyd.sports.service.IHydResultInstructorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
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
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 社会体育指导员 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@RestController
@RequestMapping("/api/instructor")
@Api(tags = "社会体育指导员")
public class HydInstructorController {

    @Resource
    private IHydExcelInstructorService hydInstructorService;
    @Resource
    private IHydResultInstructorService hydResultInstructorService;

    @ApiOperation("手动刷新结果表")
    @AnonymousGetMapping("/refresh")
    public Response<Boolean> refresh() {
        hydResultInstructorService.syncResultData();
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
        String fileName = "社会体育指导员数据导入模版.xlsx";
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
            Map<String, List<Map<String, Object>>> sheetMapData = ExcelUtils.parseExcelData(file);
            boolean flag = hydInstructorService.importExcel(sheetMapData);
            hydResultInstructorService.syncResultData();
            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("文件上传或处理失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ----------------------------------- 社会体育指导员汇总信息 -----------------------------------

    @ApiOperation("社会体育指导员汇总信息-分页查询")
    @AnonymousGetMapping("/instructorInfo/list")
    public Response<PageResult<HydExcelInstructorInfo>> instructorInfoList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydInstructorService.queryAllInstructorInfo(page, size));
    }

    @ApiOperation("社会体育指导员汇总信息-根据ID查询详情")
    @AnonymousGetMapping("/instructorInfo/detail/{id}")
    public Response<HydExcelInstructorInfo> instructorInfoDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydInstructorService.findInstructorInfoById(id));
    }

    @ApiOperation("社会体育指导员汇总信息-增加")
    @AnonymousPostMapping("/instructorInfo/add")
    public ResponseEntity<HydExcelInstructorInfo> instructorInfoAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelInstructorInfo instructorInfo) {
        return ResponseEntity.ok(hydInstructorService.save(instructorInfo));
    }

    @ApiOperation("社会体育指导员汇总信息-删除")
    @AnonymousDeleteMapping("/instructorInfo/delete/{id}")
    public Response<Boolean> instructorInfoDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydInstructorService.deleteInstructorInfoById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("社会体育指导员汇总信息-更新")
    @AnonymousPostMapping("/instructorInfo/update")
    public Response<HydExcelInstructorInfo> instructorInfoUpdate(@RequestBody HydExcelInstructorInfo instructorInfo) {
        return Response.ok(hydInstructorService.updateInstructorInfo(instructorInfo));
    }

    // ----------------------------------- 体育指导员 - 年龄统计明细表 -----------------------------------

    @ApiOperation("体育指导员 - 年龄统计明细表-分页查询")
    @AnonymousGetMapping("/instructorAgeStats/list")
    public Response<PageResult<HydExcelInstructorAgeStats>> instructorAgeStatsList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydInstructorService.queryAllInstructorAgeStats(page, size));
    }

    @ApiOperation("体育指导员 - 年龄统计明细表-根据ID查询详情")
    @AnonymousGetMapping("/instructorAgeStats/detail/{id}")
    public Response<HydExcelInstructorAgeStats> instructorAgeStatsDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydInstructorService.findInstructorAgeStatsById(id));
    }

    @ApiOperation("体育指导员 - 年龄统计明细表-增加")
    @AnonymousPostMapping("/instructorAgeStats/add")
    public ResponseEntity<HydExcelInstructorAgeStats> instructorAgeStatsAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelInstructorAgeStats instructorAgeStats) {
        return ResponseEntity.ok(hydInstructorService.save(instructorAgeStats));
    }

    @ApiOperation("体育指导员 - 年龄统计明细表-删除")
    @AnonymousDeleteMapping("/instructorAgeStats/delete/{id}")
    public Response<Boolean> instructorAgeStatsDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydInstructorService.deleteInstructorAgeStatsById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育指导员 - 年龄统计明细表-更新")
    @AnonymousPostMapping("/instructorAgeStats/update")
    public Response<HydExcelInstructorAgeStats> instructorAgeStatsUpdate(@RequestBody HydExcelInstructorAgeStats instructorAgeStats) {
        return Response.ok(hydInstructorService.updateInstructorAgeStats(instructorAgeStats));
    }

    // ----------------------------------- 体育指导员 - 人数增长统计明细 -----------------------------------

    @ApiOperation("体育指导员 - 人数增长统计明细-分页查询")
    @AnonymousGetMapping("/instructorAgeGrowth/list")
    public Response<PageResult<HydExcelInstructorAgeGrowth>> instructorAgeGrowthList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydInstructorService.queryAllInstructorAgeGrowth(page, size));
    }

    @ApiOperation("体育指导员 - 人数增长统计明细-根据ID查询详情")
    @AnonymousGetMapping("/instructorAgeGrowth/detail/{id}")
    public Response<HydExcelInstructorAgeGrowth> instructorAgeGrowthDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydInstructorService.findInstructorAgeGrowthById(id));
    }

    @ApiOperation("体育指导员 - 人数增长统计明细-增加")
    @AnonymousPostMapping("/instructorAgeGrowth/add")
    public ResponseEntity<HydExcelInstructorAgeGrowth> instructorAgeGrowthAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelInstructorAgeGrowth instructorAgeGrowth) {
        return ResponseEntity.ok(hydInstructorService.save(instructorAgeGrowth));
    }

    @ApiOperation("体育指导员 - 人数增长统计明细-删除")
    @AnonymousDeleteMapping("/instructorAgeGrowth/delete/{id}")
    public Response<Boolean> instructorAgeGrowthDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydInstructorService.deleteInstructorAgeGrowthById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育指导员 - 人数增长统计明细-更新")
    @AnonymousPostMapping("/instructorAgeGrowth/update")
    public Response<HydExcelInstructorAgeGrowth> instructorAgeGrowthUpdate(@RequestBody HydExcelInstructorAgeGrowth instructorAgeGrowth) {
        return Response.ok(hydInstructorService.updateInstructorAgeGrowth(instructorAgeGrowth));
    }

    /**
     * 指导项目top15
     */
    @ApiOperation("指导项目top15")
    @AnonymousGetMapping("/serviceProjectTop15")
    public Response<List<Map<String, Object>>> serviceProjectTop15(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydResultInstructorService.serviceProjectTop15(year));
    }

    /**
     * 性别统计
     */
    @ApiOperation("性别统计")
    @AnonymousGetMapping("/genderStat")
    public Response<List<Map<String, Object>>> genderStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydResultInstructorService.genderStat(year));
    }

    /**
     * 级别统计
     */
    @ApiOperation("级别统计")
    @AnonymousGetMapping("/levelStat")
    public Response<List<Map<String, Object>>> levelStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydResultInstructorService.levelStat(year));
    }

    /**
     * 各区指导人员统计
     */
    @ApiOperation("各区指导人员统计")
    @AnonymousGetMapping("/regionInstructorStat")
    public Response<List<Map<String, Object>>> regionInstructorStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydResultInstructorService.regionInstructorStat(year));
    }

    /**
     * 年龄统计
     */
    @ApiOperation("年龄统计")
    @AnonymousGetMapping("/ageIntervalStat")
    public Response<List<Map<String, Object>>> ageIntervalStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydInstructorService.ageIntervalStat());
    }

    /**
     * 人数增长统计
     */
    @ApiOperation("人数增长统计")
    @AnonymousGetMapping("/ageGrowthStat")
    public Response<List<Map<String, Object>>> ageGrowthStat(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydInstructorService.ageGrowthStat());
    }

    /**
     * 总览-首页用
     */
    @ApiOperation("总览-首页用")
    @AnonymousGetMapping("/overview")
    public Response<Map<String, Object>> overview(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydResultInstructorService.overview(year));
    }

    /**
     * 指导项目统计-首页用
     */
    @ApiOperation("指导项目统计-首页用")
    @AnonymousGetMapping("/serviceProject")
    public Response<List<Map<String, Object>>> serviceProject(
            @ApiParam(value = "年份，格式为4位数字（如2025）", required = true)
            @NotBlank(message = "年份不能为空")
            @Pattern(regexp = "^\\d{4}$", message = "年份格式错误，必须为4位数字（如2025）") @RequestParam String year) {
        return Response.ok(hydResultInstructorService.serviceProject(year));
    }
}
