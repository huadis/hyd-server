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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.validation.Valid;
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
            boolean flag = hydPublicEventsService.importExcel(sheetMapData);
            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (Exception e) {
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
    public Response<Map<String, Object>> overview(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.overview());
    }

    /**
     * 各月办赛数据
     */
    @ApiOperation("各月办赛数据")
    @AnonymousGetMapping("/latestMonthStat")
    public Response<List<Map<String, Object>>> latestMonthStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.latestMonthStat());
    }

    /**
     * 赛事数量top5
     */
    @ApiOperation("赛事数量top5")
    @AnonymousGetMapping("/sportItemTop5")
    public Response<List<Map<String, Object>>> sportItemTop5(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydPublicEventsService.sportItemTop5());
    }

}
