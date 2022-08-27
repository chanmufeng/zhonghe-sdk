package com.zhonghe.sdk.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhonghe.sdk.cache.ConcurrentHashMapCache;
import com.zhonghe.sdk.config.ApiConfiguration;
import com.zhonghe.sdk.domain.BaseRequestVo;
import lombok.SneakyThrows;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static com.zhonghe.sdk.cache.ConcurrentHashMapCache.ACCESS_TOKEN_KEY;

/**
 * @author huiyingzhang
 * 计算签名、获取token
 */
public class SignUtil {

    private static String appKey = ApiConfiguration.getAppKey();
    private static String appSecret = ApiConfiguration.getAppSecret();

    @SneakyThrows
    public static String getToken() {
        JSONObject cacheObject = (JSONObject) ConcurrentHashMapCache.get(ACCESS_TOKEN_KEY + appKey);

        if (cacheObject != null) {
            return cacheObject.get("accessToken").toString();
        }
        Map<String, Object> params = new HashMap<>();
        params.put("appKey", appKey);
        params.put("appSecret", appSecret);

        String reqParams = SignUtil.update(params);

        return HttpGetWithEntity.getToken("token", reqParams, appKey);

    }

    public static String update(Map<String, Object> params) {
        BaseRequestVo request = new BaseRequestVo();
        BaseRequestVo.SystemDTO systemDTO = new BaseRequestVo.SystemDTO();
        systemDTO.setAppKey(appKey);
        systemDTO.setTime(System.currentTimeMillis() / 1000);
        request.setSystem(systemDTO);

        request.setParams(params);
        systemDTO.setSign(ComputeSign(request));
        return JSONArray.toJSON(request).toString();
    }

    @SneakyThrows
    private static String ComputeSign(BaseRequestVo request) {
        BaseRequestVo.SystemDTO system = request.getSystem();
        Map<String, Object> map = request.getParams();

        StringBuilder sb = new StringBuilder();
        sb.append("appKey").append(system.getAppKey()).append(",")
                .append("appSecret").append(appSecret).append(",")
                .append("time").append(system.getTime()).append(",");
        Map<String, Object> sortedMap = sortMapByKey(map);
        for (Map.Entry<String, Object> entry : sortedMap.entrySet()) {
            sb.append(entry.getKey()).append(entry.getValue()).append(",");
        }

        return DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
    }

    /**
     * 使用 Map按key进行排序
     *
     * @param map
     * @return
     */
    private static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }

        Map<String, Object> sortMap = new TreeMap<>(new MapKeyComparator());
        sortMap.putAll(map);

        return sortMap;
    }

    static class MapKeyComparator implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            return str1.compareTo(str2);
        }
    }
}
