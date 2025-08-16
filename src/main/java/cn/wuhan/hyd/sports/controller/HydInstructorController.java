package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.base.Response;
import cn.wuhan.hyd.framework.utils.ExcelUtils;
import cn.wuhan.hyd.sports.service.IHydExcelInstructorService;
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
            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("文件上传或处理失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 指导项目top15
     */
    @ApiOperation("指导项目top15")
    @AnonymousGetMapping("/serviceProjectTop15")
    public Response<List<Map<String, Object>>> serviceProjectTop15(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydInstructorService.serviceProjectTop15());
    }

    /**
     * 性别统计
     */
    @ApiOperation("性别统计")
    @AnonymousGetMapping("/genderStat")
    public Response<List<Map<String, Object>>> genderStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydInstructorService.genderStat());
    }

    /**
     * 级别统计
     */
    @ApiOperation("级别统计")
    @AnonymousGetMapping("/levelStat")
    public Response<List<Map<String, Object>>> levelStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydInstructorService.levelStat());
    }

    /**
     * 各区指导人员统计
     */
    @ApiOperation("各区指导人员统计")
    @AnonymousGetMapping("/regionInstructorStat")
    public Response<List<Map<String, Object>>> regionInstructorStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydInstructorService.regionInstructorStat());
    }

    /**
     * 年龄统计
     */
    @ApiOperation("年龄统计")
    @AnonymousGetMapping("/ageIntervalStat")
    public Response<List<Map<String, Object>>> ageIntervalStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydInstructorService.ageIntervalStat());
    }

    /**
     * 人数增长统计
     */
    @ApiOperation("人数增长统计")
    @AnonymousGetMapping("/ageGrowthStat")
    public Response<List<Map<String, Object>>> ageGrowthStat(@ApiParam(value = "年份") @RequestParam String year) {
        return Response.ok(hydInstructorService.ageGrowthStat());
    }
}
