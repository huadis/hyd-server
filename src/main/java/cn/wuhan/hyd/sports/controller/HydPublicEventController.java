package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.ExcelUtils;
import cn.wuhan.hyd.sports.service.IHydPublicEventsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
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
    private IHydPublicEventsService hydPublicEventsService;

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
