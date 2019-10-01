package com.huawei.baicai.datatransform.tools;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author wangzhicao
 * @date 2019-10-01-11:45
 */
public class AnalysisExcel {
    private static final Logger logger = Logger.getLogger(AnalysisExcel.class);

    /**
     * 将Excel转换为josn String
     * @param fileUrl
     * @return
     */
    public static String transformExcelToString(String fileUrl,int skipOverRowNum){
        File file = new File(fileUrl);

        if (!checkFile(file)) {
            return  null;
        }

        Workbook workbook = getWorkbook(file);
        List<String[]> list = new ArrayList<>();

        if (workbook != null) {
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (sheet == null) {
                    continue;
                }
                int firstRowNum = sheet.getFirstRowNum();
                int lastRowNum = sheet.getLastRowNum();
                for (int j = firstRowNum+skipOverRowNum; j <= lastRowNum; j++) {
                    Row row = sheet.getRow(j);
                    if (row == null) {
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getLastCellNum();//为空列获取

                    String[] cells = new String[row.getLastCellNum()];
                    for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
                        Cell cell = row.getCell(cellNum);
                        cells[cellNum] = getCellValue(cell);
                    }
                    list.add(cells);
                }
            }


        }
        System.out.println(list.toString());
        return list.toString();
    }

    /**
     *判断文件是否存在
     * @param file
     * @return
     */
    private static boolean checkFile(File file){
        if (file == null) {
            logger.error("File not exisit!");
            return false;
        }

        String fileName = file.getName();
        if(!fileName.endsWith("xls") && !fileName.endsWith("xlsx")){
            logger.error("File is not a Excel!");
            return false;
        }

        return true;
    }

    /**
     * 获取Workbook对象
     * @param file
     * @return
     */
    private static Workbook getWorkbook(File file){
        String fileName = file.getName();

        Workbook workbook = null;
        try {
            InputStream in = new FileInputStream(file);
            if(fileName.endsWith("xls")){
                //2003
                workbook = new HSSFWorkbook(in);
            }else if(fileName.endsWith("xlsx")){
                //2007
                workbook = new XSSFWorkbook(in);
            }
        } catch (FileNotFoundException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        return  workbook;
    }

    /**
     *
     * @param cell
     * @return
     */
    private static String getCellValue(Cell cell){
        String cellValue = "";
        if (cellValue == null) {
            return  cellValue;
        }

        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_NUMERIC);
        }
        //判断数据的类型
        switch(cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "Invalid Symbol";
                break;
            default:
                cellValue = "Unknown Type";
                break;
        }
        return cellValue;
    }
}
