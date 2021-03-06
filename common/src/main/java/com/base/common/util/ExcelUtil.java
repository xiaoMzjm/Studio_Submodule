package com.base.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.List;
import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author:小M
 * @date:2020/5/24 10:48 PM
 */
public class ExcelUtil {

    public static class CellDTO{
        public String text;
        public Integer color;
        public boolean isDate;
        public boolean isString;
        public String dateFormat;
        public HorizontalAlignment horizontalAlignment;

        public CellDTO(String text, Integer color) {
            this.text = text;
            this.color = color;
        }
        public CellDTO(String text) {
            this.text = text;
        }
    }

    public static String insertExcelAndSave(InputStream inputStream, Integer beginRowNum, Integer beginColumnNum, String savePath, List<List<CellDTO>> rules, Map<String,CellDTO> replaceMap) throws Exception{
        return insertExcelAndSave(inputStream, 0, beginRowNum, beginColumnNum, savePath, rules, replaceMap);
    }

    /**
     *
     * @param inputStream
     * @param beginRowNum       开始插入的行数，第一行为1
     * @param beginColumnNum    开始插入的列数，第一列为0
     * @param savePath
     * @param rules
     * @return
     * @throws Exception
     */
    public static String insertExcelAndSave(InputStream inputStream, Integer sheetAt, Integer beginRowNum, Integer beginColumnNum, String savePath, List<List<CellDTO>> rules, Map<String,CellDTO> replaceMap) throws Exception{
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(sheetAt);
        CellStyle style = getCellStyle(wb);

        if(replaceMap != null) {
            Iterator rows = sheet.rowIterator();
            while(rows.hasNext()) {
                XSSFRow row = (XSSFRow) rows.next();
                if(row != null) {
                    int num = row.getLastCellNum();
                    for(int i=0; i < num; i++) {
                        XSSFCell cell = row.getCell(i);
                        if(cell!=null) {
                            cell.setCellType(CellType.STRING);
                        }
                        if(cell == null || cell.getStringCellValue() == null){
                            continue;
                        }
                        String value= cell.getStringCellValue();
                        if(StringUtils.isEmpty(value)) {
                            continue;
                        }
                        for(Map.Entry<String,CellDTO> entry : replaceMap.entrySet()) {
                            String key = entry.getKey();
                            if(value.contains(key)) {
                                CellDTO cellDTO = entry.getValue();
                                value = value.replaceAll(key,cellDTO.text);
                                setStyle(wb, cellDTO, cell, style);
                                cell.setCellValue(value);
                            }
                        }
                    }
                }
            }
        }

        int i = beginRowNum;

        for(List<CellDTO> cells : rules) {
            XSSFRow row = createRow(sheet, i);
            int j = beginColumnNum;
            for(CellDTO cell : cells) {
                XSSFCell c = row.createCell(j++);

                setStyle(wb, cell, c, style);

                try {
                    if(cell.isString) {
                        c.setCellValue(cell.text);
                    }else {
                        Double doubleValue = Double.valueOf(cell.text);
                        c.setCellValue(doubleValue);
                    }
                }catch (Exception e) {
                    c.setCellValue(cell.text);
                }

            }
            i++;
        }

        // 保存到新目录
        String newFileName = UUID.randomUUID() + ".xlsx";
        String newFilePath = savePath + newFileName;
        File newFile = new File(newFilePath);
        OutputStream os = new FileOutputStream(newFile);
        wb.write(os);
        os.flush();
        os.close();
        return newFileName;
    }

    private static void setStyle(XSSFWorkbook wb, CellDTO cell, XSSFCell c, CellStyle style){
        CellStyle styleNew = null;
        if(cell.color != null){
            Font font  = wb.createFont();
            font.setColor(cell.color.shortValue());
            if(styleNew == null) {
                styleNew = getCellStyle(wb);
            }
            styleNew.setFont(font);
        }
        if(cell.isDate) {
            short df = wb.createDataFormat().getFormat(cell.dateFormat);
            if(styleNew == null) {
                styleNew = getCellStyle(wb);
            }
            styleNew.setDataFormat(df);
        }
        if(cell.horizontalAlignment != null) {
            if(styleNew == null) {
                styleNew = getCellStyle(wb);
            }
            styleNew.setAlignment(cell.horizontalAlignment);
        }
        if(cell.isString) {
            short df = wb.createDataFormat().getFormat("@");
            if(styleNew == null) {
                styleNew = getCellStyle(wb);
            }
            styleNew.setDataFormat(df);
        }
        if(styleNew != null) {
            c.setCellStyle(styleNew);
        }else {
            c.setCellStyle(style);
        }
    }

    /**
     * 找到需要插入的行数，并新建一个POI的row对象
     * @param sheet
     * @param rowIndex
     * @return
     */
    private static XSSFRow createRow(XSSFSheet sheet, Integer rowIndex) {
        XSSFRow row = null;
        if (sheet.getRow(rowIndex) != null) {
            int lastRowNo = sheet.getLastRowNum();
            sheet.shiftRows(rowIndex, lastRowNo, 1);
        }
        row = sheet.createRow(rowIndex);
        return row;
    }

    private static CellStyle getCellStyle(XSSFWorkbook wb){
        CellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(BorderStyle.THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(BorderStyle.THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderRight(BorderStyle.THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setAlignment(HorizontalAlignment.CENTER);
        return style;
    }
}
