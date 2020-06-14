package com.base.common.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
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
        public Boolean isDate;
        public String dateFormat;
        public CellDTO(String text, Integer color) {
            this.text = text;
            this.color = color;
        }
        public CellDTO(String text) {
            this.text = text;
        }
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
    public static String insertExcelAndSave(InputStream inputStream, Integer beginRowNum, Integer beginColumnNum, String savePath, List<List<CellDTO>> rules) throws Exception{
        XSSFWorkbook wb = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = wb.getSheetAt(0);


        int i = beginRowNum;
        for(List<CellDTO> cells : rules) {
            XSSFRow row = createRow(sheet, i);
            int j = beginColumnNum;
            for(CellDTO cell : cells) {
                XSSFCell c = row.createCell(j++);

                CellStyle style = wb.createCellStyle();
                style.setBorderBottom(BorderStyle.THIN);
                style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
                style.setBorderTop(BorderStyle.THIN);
                style.setTopBorderColor(IndexedColors.BLACK.getIndex());
                style.setBorderLeft(BorderStyle.THIN);
                style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
                style.setBorderRight(BorderStyle.THIN);
                style.setRightBorderColor(IndexedColors.BLACK.getIndex());
                style.setAlignment(HorizontalAlignment.CENTER);

                c.setCellValue(cell.text);
                if(cell.color != null){
                    Font font  = wb.createFont();
                    font.setColor(cell.color.shortValue());
                    style.setFont(font);
                }
                if(cell.isDate) {
                    short df = wb.createDataFormat().getFormat(cell.dateFormat);
                    style.setDataFormat(df);
                }
                c.setCellStyle(style);

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
}
