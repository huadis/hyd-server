package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousDeleteMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.ExcelUtils;
import cn.wuhan.hyd.framework.utils.PageResult;
import cn.wuhan.hyd.sports.domain.*;
import cn.wuhan.hyd.sports.service.IHydExcelIndustryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育产业 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
@RestController
@RequestMapping("/api/industry")
@Api(tags = "体育产业")
public class HydIndustryController {

    @Resource
    private IHydExcelIndustryService hydIndustryService;

    /**
     * 下载指定模板文件
     *
     * @return 模板文件的响应实体
     */
    @ApiOperation("下载模板")
    @AnonymousGetMapping(value = "/template/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadTemplate(HttpServletResponse response) {
        String fileName = "体育产业数据导入模版.xlsx";
        ClassPathResource resource = new ClassPathResource("templates/" + fileName);
        try (InputStream inputStream = resource.getInputStream()) {
            if (!resource.exists()) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "模板文件未找到");
                return;
            }

            // 设置响应头
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + fileName);

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

    @ApiOperation("上传模板数据")
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
            boolean flag = hydIndustryService.importExcel(sheetMapData);
            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("文件上传或处理失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ----------------------------------- 体育产业-核心指标 -----------------------------------

    @ApiOperation("体育产业-核心指标-分页查询")
    @AnonymousGetMapping("/industryCoreIndicators/list")
    public Response<PageResult<HydExcelIndustryCoreIndicators>> industryCoreIndicatorsList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryCoreIndicators(page, size));
    }

    @ApiOperation("体育产业-核心指标-根据ID查询详情")
    @AnonymousGetMapping("/industryCoreIndicators/detail/{id}")
    public Response<HydExcelIndustryCoreIndicators> industryCoreIndicatorsDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryCoreIndicatorsById(id));
    }

    @ApiOperation("体育产业-核心指标-增加")
    @AnonymousPostMapping("/industryCoreIndicators/add")
    public ResponseEntity<HydExcelIndustryCoreIndicators> industryCoreIndicatorsAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryCoreIndicators industryCoreIndicators) {
        return ResponseEntity.ok(hydIndustryService.save(industryCoreIndicators));
    }

    @ApiOperation("体育产业-核心指标-删除")
    @AnonymousDeleteMapping("/industryCoreIndicators/delete/{id}")
    public Response<Boolean> industryCoreIndicatorsDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryCoreIndicatorsById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-核心指标-更新")
    @AnonymousPostMapping("/industryCoreIndicators/update")
    public Response<HydExcelIndustryCoreIndicators> industryCoreIndicatorsUpdate(@RequestBody HydExcelIndustryCoreIndicators industryCoreIndicators) {
        return Response.ok(hydIndustryService.updateIndustryCoreIndicators(industryCoreIndicators));
    }

    // ----------------------------------- 体育产业-从业人员数量（分类统计） -----------------------------------

    @ApiOperation("体育产业-从业人员数量（分类统计）-分页查询")
    @AnonymousGetMapping("/industryEmployeeCount/list")
    public Response<PageResult<HydExcelIndustryEmployeeCount>> industryEmployeeCountList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryEmployeeCount(page, size));
    }

    @ApiOperation("体育产业-从业人员数量（分类统计）-根据ID查询详情")
    @AnonymousGetMapping("/industryEmployeeCount/detail/{id}")
    public Response<HydExcelIndustryEmployeeCount> industryEmployeeCountDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryEmployeeCountById(id));
    }

    @ApiOperation("体育产业-从业人员数量（分类统计）-增加")
    @AnonymousPostMapping("/industryEmployeeCount/add")
    public ResponseEntity<HydExcelIndustryEmployeeCount> industryEmployeeCountAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryEmployeeCount industryEmployeeCount) {
        return ResponseEntity.ok(hydIndustryService.save(industryEmployeeCount));
    }

    @ApiOperation("体育产业-从业人员数量（分类统计）-删除")
    @AnonymousDeleteMapping("/industryEmployeeCount/delete/{id}")
    public Response<Boolean> industryEmployeeCountDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryEmployeeCountById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-从业人员数量（分类统计）-更新")
    @AnonymousPostMapping("/industryEmployeeCount/update")
    public Response<HydExcelIndustryEmployeeCount> industryEmployeeCountUpdate(@RequestBody HydExcelIndustryEmployeeCount industryEmployeeCount) {
        return Response.ok(hydIndustryService.updateIndustryEmployeeCount(industryEmployeeCount));
    }

    // ----------------------------------- 体育产业-场主体数量（分类占比） -----------------------------------

    @ApiOperation("体育产业-场主体数量（分类占比）-分页查询")
    @AnonymousGetMapping("/industryEntityCountRatio/list")
    public Response<PageResult<HydExcelIndustryEntityCountRatio>> industryEntityCountRatioList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryEntityCountRatio(page, size));
    }

    @ApiOperation("体育产业-场主体数量（分类占比）-根据ID查询详情")
    @AnonymousGetMapping("/industryEntityCountRatio/detail/{id}")
    public Response<HydExcelIndustryEntityCountRatio> industryEntityCountRatioDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryEntityCountRatioById(id));
    }

    @ApiOperation("体育产业-场主体数量（分类占比）-增加")
    @AnonymousPostMapping("/industryEntityCountRatio/add")
    public ResponseEntity<HydExcelIndustryEntityCountRatio> industryEntityCountRatioAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryEntityCountRatio industryEntityCountRatio) {
        return ResponseEntity.ok(hydIndustryService.save(industryEntityCountRatio));
    }

    @ApiOperation("体育产业-场主体数量（分类占比）-删除")
    @AnonymousDeleteMapping("/industryEntityCountRatio/delete/{id}")
    public Response<Boolean> industryEntityCountRatioDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryEntityCountRatioById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-场主体数量（分类占比）-更新")
    @AnonymousPostMapping("/industryEntityCountRatio/update")
    public Response<HydExcelIndustryEntityCountRatio> industryEntityCountRatioUpdate(@RequestBody HydExcelIndustryEntityCountRatio industryEntityCountRatio) {
        return Response.ok(hydIndustryService.updateIndustryEntityCountRatio(industryEntityCountRatio));
    }

    // ----------------------------------- 体育产业-居民体育用品购买率表 -----------------------------------

    @ApiOperation("体育产业-居民体育用品购买率表-分页查询")
    @AnonymousGetMapping("/industryGoodsPurchaseRate/list")
    public Response<PageResult<HydExcelIndustryGoodsPurchaseRate>> industryGoodsPurchaseRateList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryGoodsPurchaseRate(page, size));
    }

    @ApiOperation("体育产业-居民体育用品购买率表-根据ID查询详情")
    @AnonymousGetMapping("/industryGoodsPurchaseRate/detail/{id}")
    public Response<HydExcelIndustryGoodsPurchaseRate> industryGoodsPurchaseRateDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryGoodsPurchaseRateById(id));
    }

    @ApiOperation("体育产业-居民体育用品购买率表-增加")
    @AnonymousPostMapping("/industryGoodsPurchaseRate/add")
    public ResponseEntity<HydExcelIndustryGoodsPurchaseRate> industryGoodsPurchaseRateAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryGoodsPurchaseRate industryGoodsPurchaseRate) {
        return ResponseEntity.ok(hydIndustryService.save(industryGoodsPurchaseRate));
    }

    @ApiOperation("体育产业-居民体育用品购买率表-删除")
    @AnonymousDeleteMapping("/industryGoodsPurchaseRate/delete/{id}")
    public Response<Boolean> industryGoodsPurchaseRateDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryGoodsPurchaseRateById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-居民体育用品购买率表-更新")
    @AnonymousPostMapping("/industryGoodsPurchaseRate/update")
    public Response<HydExcelIndustryGoodsPurchaseRate> industryGoodsPurchaseRateUpdate(@RequestBody HydExcelIndustryGoodsPurchaseRate industryGoodsPurchaseRate) {
        return Response.ok(hydIndustryService.updateIndustryGoodsPurchaseRate(industryGoodsPurchaseRate));
    }

    // ----------------------------------- 体育产业-总增速和增加值（年度趋势） -----------------------------------

    @ApiOperation("体育产业-总增速和增加值（年度趋势）-分页查询")
    @AnonymousGetMapping("/industryGrowthValueTrend/list")
    public Response<PageResult<HydExcelIndustryGrowthValueTrend>> industryGrowthValueTrendList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryGrowthValueTrend(page, size));
    }

    @ApiOperation("体育产业-总增速和增加值（年度趋势）-根据ID查询详情")
    @AnonymousGetMapping("/industryGrowthValueTrend/detail/{id}")
    public Response<HydExcelIndustryGrowthValueTrend> industryGrowthValueTrendDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryGrowthValueTrendById(id));
    }

    @ApiOperation("体育产业-总增速和增加值（年度趋势）-增加")
    @AnonymousPostMapping("/industryGrowthValueTrend/add")
    public ResponseEntity<HydExcelIndustryGrowthValueTrend> industryGrowthValueTrendAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryGrowthValueTrend industryGrowthValueTrend) {
        return ResponseEntity.ok(hydIndustryService.save(industryGrowthValueTrend));
    }

    @ApiOperation("体育产业-总增速和增加值（年度趋势）-删除")
    @AnonymousDeleteMapping("/industryGrowthValueTrend/delete/{id}")
    public Response<Boolean> industryGrowthValueTrendDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryGrowthValueTrendById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-总增速和增加值（年度趋势）-更新")
    @AnonymousPostMapping("/industryGrowthValueTrend/update")
    public Response<HydExcelIndustryGrowthValueTrend> industryGrowthValueTrendUpdate(@RequestBody HydExcelIndustryGrowthValueTrend industryGrowthValueTrend) {
        return Response.ok(hydIndustryService.updateIndustryGrowthValueTrend(industryGrowthValueTrend));
    }

    // ----------------------------------- 体育产业-总规模（年度趋势） -----------------------------------

    @ApiOperation("体育产业-总规模（年度趋势）-分页查询")
    @AnonymousGetMapping("/industryScaleTrend/list")
    public Response<PageResult<HydExcelIndustryScaleTrend>> industryScaleTrendList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryScaleTrend(page, size));
    }

    @ApiOperation("体育产业-总规模（年度趋势）-根据ID查询详情")
    @AnonymousGetMapping("/industryScaleTrend/detail/{id}")
    public Response<HydExcelIndustryScaleTrend> industryScaleTrendDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryScaleTrendById(id));
    }

    @ApiOperation("体育产业-总规模（年度趋势）-增加")
    @AnonymousPostMapping("/industryScaleTrend/add")
    public ResponseEntity<HydExcelIndustryScaleTrend> industryScaleTrendAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryScaleTrend industryScaleTrend) {
        return ResponseEntity.ok(hydIndustryService.save(industryScaleTrend));
    }

    @ApiOperation("体育产业-总规模（年度趋势）-删除")
    @AnonymousDeleteMapping("/industryScaleTrend/delete/{id}")
    public Response<Boolean> industryScaleTrendDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryScaleTrendById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-总规模（年度趋势）-更新")
    @AnonymousPostMapping("/industryScaleTrend/update")
    public Response<HydExcelIndustryScaleTrend> industryScaleTrendUpdate(@RequestBody HydExcelIndustryScaleTrend industryScaleTrend) {
        return Response.ok(hydIndustryService.updateIndustryScaleTrend(industryScaleTrend));
    }


    // ----------------------------------- 体育产业-居民体育培训项目参与率 -----------------------------------

    @ApiOperation("体育产业-居民体育培训项目参与率-分页查询")
    @AnonymousGetMapping("/industryTrainingParticipationRate/list")
    public Response<PageResult<HydExcelIndustryTrainingParticipationRate>> industryTrainingParticipationRateList(
            @ApiParam(value = "页码，从0开始", example = "0") @RequestParam(defaultValue = "0") int page,
            @ApiParam(value = "每页条数", example = "10") @RequestParam(defaultValue = "10") int size) {
        return Response.ok(hydIndustryService.queryAllIndustryTrainingParticipationRate(page, size));
    }

    @ApiOperation("体育产业-居民体育培训项目参与率-根据ID查询详情")
    @AnonymousGetMapping("/industryTrainingParticipationRate/detail/{id}")
    public Response<HydExcelIndustryTrainingParticipationRate> industryTrainingParticipationRateDetail(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        return Response.ok(hydIndustryService.findIndustryTrainingParticipationRateById(id));
    }

    @ApiOperation("体育产业-居民体育培训项目参与率-增加")
    @AnonymousPostMapping("/industryTrainingParticipationRate/add")
    public ResponseEntity<HydExcelIndustryTrainingParticipationRate> industryTrainingParticipationRateAdd(
            @ApiParam(value = "结果表-消费券总金额", required = true) @Valid @RequestBody HydExcelIndustryTrainingParticipationRate industryTrainingParticipationRate) {
        return ResponseEntity.ok(hydIndustryService.save(industryTrainingParticipationRate));
    }

    @ApiOperation("体育产业-居民体育培训项目参与率-删除")
    @AnonymousDeleteMapping("/industryTrainingParticipationRate/delete/{id}")
    public Response<Boolean> industryTrainingParticipationRateDelete(
            @ApiParam(value = "主键ID", required = true, example = "1") @PathVariable Long id) {
        try {
            hydIndustryService.deleteIndustryTrainingParticipationRateById(id);
            return Response.ok(true);
        } catch (Exception e) {
            return Response.fail(e.getMessage());
        }
    }

    @ApiOperation("体育产业-居民体育培训项目参与率-更新")
    @AnonymousPostMapping("/industryTrainingParticipationRate/update")
    public Response<HydExcelIndustryTrainingParticipationRate> industryTrainingParticipationRateUpdate(@RequestBody HydExcelIndustryTrainingParticipationRate industryTrainingParticipationRate) {
        return Response.ok(hydIndustryService.updateIndustryTrainingParticipationRate(industryTrainingParticipationRate));
    }

    /**
     * 总览
     */
    @ApiOperation("总览")
    @AnonymousGetMapping("/overview")
    public Response<HydExcelIndustryCoreIndicators> overview(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.overview());
    }

    /**
     * 体育产业总规模
     */
    @ApiOperation("体育产业总规模")
    @AnonymousGetMapping("/industryScaleTrendStat")
    public Response<List<Map<String, Object>>> industryScaleTrendStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.industryScaleTrendStat());
    }

    /**
     * 体育产业市场主体数量
     */
    @ApiOperation("体育产业市场主体数量")
    @AnonymousGetMapping("/industryEntityCountRatioStat")
    public Response<List<Map<String, Object>>> industryEntityCountRatioStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.industryEntityCountRatioStat());
    }

    /**
     * 体育产业总增速和增加值
     */
    @ApiOperation("体育产业总增速和增加值")
    @AnonymousGetMapping("/industryGrowthValueTrendStat")
    public Response<List<Map<String, Object>>> industryGrowthValueTrendStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.industryGrowthValueTrendStat());
    }

    /**
     * 居民体育用品购买率
     */
    @ApiOperation("居民体育用品购买率")
    @AnonymousGetMapping("/industryGoodsPurchaseRateStat")
    public Response<List<Map<String, Object>>> industryGoodsPurchaseRateStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.industryGoodsPurchaseRateStat());
    }

    /**
     * 体育产业从业人员数量
     */
    @ApiOperation("体育产业从业人员数量")
    @AnonymousGetMapping("/HydExcelIndustryEmployeeCountStat")
    public Response<List<Map<String, Object>>> HydExcelIndustryEmployeeCountStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.industryEmployeeCountStat());
    }

    /**
     * 居民体育培训项目参与率
     */
    @ApiOperation("居民体育培训项目参与率")
    @AnonymousGetMapping("/industryTrainingParticipationRateStat")
    public Response<List<Map<String, Object>>> industryTrainingParticipationRateStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydIndustryService.industryTrainingParticipationRateStat());
    }
}
