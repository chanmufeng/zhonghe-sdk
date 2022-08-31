package com.zhonghe.sdk;

import com.zhonghe.sdk.factory.ZhApiProxyFactory;
import com.zhonghe.sdk.service.ApiService;

/**
 * @author huiyingzhang
 * 2022/8/27 15:26
 */

public class ApiHelper {

    private final ZhApiProxyFactory zhongheApiProxyFactory;


    public static ApiHelper build() {
        ZhApiProxyFactory build = ZhApiProxyFactory.build();
        build.createProxy(ApiService.class);
        return new ApiHelper(build);
    }

    public ApiHelper(ZhApiProxyFactory zhongheApiProxyFactory) {
        this.zhongheApiProxyFactory = zhongheApiProxyFactory;
    }

    /**
     * 创建接口实例
     *
     * @param apiClass {@link ApiService}
     * @return 接口
     */
    public <T> T creatApi(Class<T> apiClass) {
        return this.zhongheApiProxyFactory.createProxy(apiClass);
    }

    public static ApiService newApi() {
        return build().creatApi(ApiService.class);
    }

}
