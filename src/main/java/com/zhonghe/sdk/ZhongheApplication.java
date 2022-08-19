package com.zhonghe.sdk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author huiyingzhang
 * @ClassName ZhongheApplication
 * @description
 * @date 2022/8/12 9:01
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ZhongheApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZhongheApplication.class, args);
        System.out.println("众禾api接口启动成功");
    }
}
