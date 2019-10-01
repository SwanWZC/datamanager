package com.huawei.baicai.datatransform;

import com.huawei.baicai.datatransform.tools.AnalysisExcel;

/**
 * @author
 * @date 2019-10-01-12:57
 */
public class test {
    public static void main(String[] args) {
        String url = "/Users/wangzhichao/eclipsetest/UploadFileTest/detail.xlsx";
        String dataStr = AnalysisExcel.transformExcelToString(url,1);
        System.out.println(dataStr);
    }
}
