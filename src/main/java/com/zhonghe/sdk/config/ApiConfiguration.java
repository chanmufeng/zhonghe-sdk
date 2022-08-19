package com.zhonghe.sdk.config;

import com.zhonghe.sdk.ApiException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


/**
 * @author huiyingzhang
 * @ClassName ApiConfiguration
 * @description 应用相关配置信息
 * @date 2022/8/13 9:08
 */
@Component //注册bean
@ConfigurationProperties(prefix = "zhonghe")
public class ApiConfiguration {

    /**
     * 应用key
     */
    private static String appKey;
    /**
     * 应用secret
     */
    private static String appSecret;
    /**
     * 调用链接urlPrefix
     */
    private static String urlPrefix;

    public static void check() {
        notBlank(appKey);
        notBlank(appSecret);
        notBlank(urlPrefix);
    }

    public static String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        ApiConfiguration.appKey = appKey;
    }

    public static String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        ApiConfiguration.appSecret = appSecret;
    }

    public static String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        ApiConfiguration.urlPrefix = urlPrefix;
    }

    public static void notBlank(String param) {
        if (StringUtils.isEmpty(param)) {
            throw new ApiException("参数不能为空,请查看是否添加相关配置");
        }
    }

}
