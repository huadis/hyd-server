package cn.wuhan.hyd.sports.controller;

import cn.wuhan.hyd.framework.annotation.rest.AnonymousGetMapping;
import cn.wuhan.hyd.framework.annotation.rest.AnonymousPostMapping;
import cn.wuhan.hyd.framework.utils.ExcelUtils;
import cn.wuhan.hyd.sports.service.IHydExcelSportsOrganizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * 功能说明： 体育组织 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月28日 <br>
 */
@RestController
@RequestMapping("/api/sportsOrg")
@Api(tags = "体育组织")
public class HydSportsOrgController {

    @Resource
    private IHydExcelSportsOrganizationService organizationService;

    /**
     * 下载指定模板文件
     *
     * @return 模板文件的响应实体
     */
    @ApiOperation("下载模板")
    @AnonymousGetMapping(value = "/template/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadTemplate(HttpServletResponse response) {
        String fileName = "体育组织导入模板.xlsx";
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
            boolean flag = organizationService.importExcel(sheetMapData);
            return new ResponseEntity<>("文件上传成功", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("文件上传或处理失败", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
