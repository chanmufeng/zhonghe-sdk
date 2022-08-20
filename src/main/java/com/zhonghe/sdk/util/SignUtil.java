package com.zhonghe.sdk.util;

import com.zhonghe.sdk.domain.BaseRequestVo;
import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author huiyingzhang
 * 计算签名
 */
public class SignUtil {

    private static Long timestamp;
    private static String signature;

    public static BaseRequestVo update(Map<String, Object> params, String appKey, String appSecret) {
        BaseRequestVo request = new BaseRequestVo();
        timestamp = System.currentTimeMillis() / 1000;
        BaseRequestVo.SystemDTO systemDTO = new BaseRequestVo.SystemDTO();
        systemDTO.setAppKey(appKey);
        systemDTO.setTime(timestamp);
        request.setSystem(systemDTO);

        request.setParams(params);
        signature = ComputeSign(request, appSecret);
        systemDTO.setSign(signature);
        return request;
    }

    private static String ComputeSign(BaseRequestVo request, String appSecret) {
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

        String expectedSign = "";
        try {
            expectedSign = DigestUtils.md5Hex(sb.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return expectedSign;
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
