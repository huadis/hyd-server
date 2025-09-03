package cn.wuhan.hyd.framework.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 功能说明： Excel工具 <br>
 * 开发人员：@author huadi <br>
 * 开发时间: 2025年08月10日 <br>
 */
public class ExcelUtils {

    public static Map<String, List<Map<String, Object>>> parseExcelData(MultipartFile multipartFile) throws IOException {
        Map<String, List<Map<String, Object>>> sheetList = new HashMap<>();
        try (InputStream is = multipartFile.getInputStream();
             XSSFWorkbook workbook = new XSSFWorkbook(is)) {

            // 遍历所有 sheet
            for (int sheetIndex = 0; sheetIndex < workbook.getNumberOfSheets(); sheetIndex++) {
                XSSFSheet sheet = workbook.getSheetAt(sheetIndex);
                String sheetName = sheet.getSheetName();
                List<Map<String, Object>> sheetData = new ArrayList<>();
                // 获取表头行
                Row headerRow = sheet.getRow(0);
                List<String> headers = new ArrayList<>();
                if (headerRow != null) {
                    for (int cellIndex = 0; cellIndex < headerRow.getPhysicalNumberOfCells(); cellIndex++) {
                        Cell cell = headerRow.getCell(cellIndex);
                        if (cell != null) {
                            headers.add(cell.getStringCellValue());
                        } else {
                            headers.add("");
                        }
                    }
                }

                // 遍历数据行
                for (int rowIndex = 1; rowIndex < sheet.getPhysicalNumberOfRows(); rowIndex++) {
                    Row dataRow = sheet.getRow(rowIndex);
                    Map<String, Object> rowData = new HashMap<>();
                    for (int cellIndex = 0; cellIndex < headers.size(); cellIndex++) {
                        Cell cell = dataRow.getCell(cellIndex);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    rowData.put(headers.get(cellIndex), cell.getStringCellValue());
                                    break;
                                case NUMERIC:
                                    if (DateUtil.isCellDateFormatted(cell)) {
                                        Date date = cell.getDateCellValue();
                                        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                        rowData.put(headers.get(cellIndex), sqlDate);
                                    } else {
                                        double value = cell.getNumericCellValue();
                                        /*// 尝试转换为日期（即使不是显式日期格式）
                                        if (value >= 25569) { // 1970-01-01 对应的Excel数字是25569，过滤无效日期
                                            try {
                                                Date date = DateUtil.getJavaDate(value);
                                                java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                                                rowData.put(headers.get(cellIndex), sqlDate);
                                            } catch (Exception e) {
                                                rowData.put(headers.get(cellIndex), value);
                                            }
                                        } else {
                                            rowData.put(headers.get(cellIndex), value);
                                        }*/
                                        rowData.put(headers.get(cellIndex), value);
                                    }
                                    break;
                                case BOOLEAN:
                                    rowData.put(headers.get(cellIndex), cell.getBooleanCellValue());
                                    break;
                                case BLANK:
                                    rowData.put(headers.get(cellIndex), null);
                                    break;
                                default:
                                    rowData.put(headers.get(cellIndex), cell.toString());
                            }
                        } else {
                            rowData.put(headers.get(cellIndex), null);
                        }
                    }
                    sheetData.add(rowData);
                }
                sheetList.put(sheetName, sheetData);
            }
        }
        return sheetList;
    }
}
