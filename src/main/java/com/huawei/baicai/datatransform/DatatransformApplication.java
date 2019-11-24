package com.huawei.baicai.datatransform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.huawei.baicai.datatransform.dao")
public class DatatransformApplication {

        public static void main(String[] args) {

        SpringApplication.run(DatatransformApplication.class, args);
    }

}
