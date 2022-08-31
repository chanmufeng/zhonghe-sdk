package com.zhonghe.sdk.anno;

import java.lang.annotation.*;

/**
 * @author huiyingzhang
 * 2022/8/26 11:14
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZhApi {

    String url();

}
