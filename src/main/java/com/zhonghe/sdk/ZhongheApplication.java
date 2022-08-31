package com.zhonghe.sdk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author huiyingzhang
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.zhonghe.sdk.service.ApiService")
public class ZhongheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhongheApplication.class, args);
        System.out.println("众禾api接口启动成功");
    }
}
