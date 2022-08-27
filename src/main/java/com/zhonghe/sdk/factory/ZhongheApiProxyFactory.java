package com.zhonghe.sdk.factory;

import com.alibaba.fastjson.JSONObject;
import com.zhonghe.sdk.anno.ZhApi;
import com.zhonghe.sdk.util.HttpGetWithEntity;
import com.zhonghe.sdk.util.SignUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

import static com.zhonghe.sdk.util.SignUtil.getToken;

/**
 * @author huiyingzhang
 * @description
 * @date 2022/8/27 8:57
 */
public class ZhongheApiProxyFactory {

    /**
     * 创建代理对象
     *
     * @param apiService
     * @param <T>
     * @return
     */
    public static <T> T createProxy(Class<T> apiService) {
        return (T) Proxy.newProxyInstance(apiService.getClassLoader(), new Class[]{apiService}, new ApiProxyHandler());
    }

    static class ApiProxyHandler implements InvocationHandler {
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Exception {
            try {
                if (Object.class.equals(method.getDeclaringClass())) {
                    return method.invoke(this, args);
                }
            } catch (Throwable t) {
                throw new RuntimeException(t);
            }
            Annotation annotation = method.getAnnotation(ZhApi.class);
            String url = ((ZhApi) annotation).url();

            Map<String, Object> params = (Map<String, Object>) args[0];
            params.put("token", getToken());
            String update = SignUtil.update(params);
            JSONObject jsonObject = HttpGetWithEntity.sendJsonByGetReq(url, update);
            return jsonObject;
        }
    }


}
