package com.zhonghe.sdk;

import com.zhonghe.sdk.factory.ZhongheApiProxyFactory;

/**
 * @author huiyingzhang
 * @description
 * @date 2022/8/27 15:26
 */

public class ApiHelper {

    private final ZhongheApiProxyFactory zhongheApiProxyFactory;


    public static ApiHelper build() {
        return new ApiHelper(ZhongheApiProxyFactory.build());
    }

    public ApiHelper(ZhongheApiProxyFactory zhongheApiProxyFactory) {
        this.zhongheApiProxyFactory = zhongheApiProxyFactory;
    }

    /**
     * 创建接口实例
     *
     * @param apiClass
     * @param <T>
     * @return
     */
    public <T> T creatApi(Class<T> apiClass) {
        return this.zhongheApiProxyFactory.createProxy(apiClass);
    }

}
