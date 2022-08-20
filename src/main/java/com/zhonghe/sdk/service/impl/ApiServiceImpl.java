package com.zhonghe.sdk.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhonghe.sdk.cache.ConcurrentHashMapCache;
import com.zhonghe.sdk.config.ApiConfiguration;
import com.zhonghe.sdk.constant.HttpStatus;
import com.zhonghe.sdk.domain.BaseRequestVo;
import com.zhonghe.sdk.service.ApiService;
import com.zhonghe.sdk.util.HttpGetWithEntity;
import com.zhonghe.sdk.util.SignUtil;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static com.zhonghe.sdk.cache.ConcurrentHashMapCache.ACCESS_TOKEN_KEY;

/**
 * @author huiyingzhang
 * api调用接口
 */
public class ApiServiceImpl implements ApiService {

    /**
     * 获取配置中的appKey
     */
    private String appKey;

    /**
     * 获取appSecret
     */
    private String appSecret;

    /**
     * 获取配置中的url
     */
    String url = ApiConfiguration.getUrlPrefix();

    public ApiServiceImpl(String appKey, String appSecret) {
        this.appKey = appKey;
        this.appSecret = appSecret;
    }

    public ApiServiceImpl() {
    }

    /**
     * 计算签名
     */
//    SignUtil u = new SignUtil();
    @Override
    public String getToken(String appKey, String appSecret) {
        try {
            JSONObject cacheObject = (JSONObject) ConcurrentHashMapCache.get(ACCESS_TOKEN_KEY + appKey);
            if (cacheObject != null) {
                return cacheObject.get("accessToken").toString();
            }
            Map<String, Object> params = new HashMap<>();
            params.put("appKey", appKey);
            params.put("appSecret", appSecret);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            String token = "";
            JSONObject res = HttpGetWithEntity.sendJsonByGetReq(url + "token", reqParams, "UTF-8");

            if (res.get("code").equals(HttpStatus.SUCCESS)) {
                JSONObject data = (JSONObject) res.get("data");

                ConcurrentHashMapCache.put(ACCESS_TOKEN_KEY + appKey, data);
                token = String.valueOf(data.get("accessToken"));
            }
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }

    }

    @Override
    public Object getDeviceList(int pageNum, int pageSize) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("pageNum", pageNum);
            params.put("pageSize", pageSize);

            ApiConfiguration.check();
            String token = getToken(appKey, appSecret);
            params.put("token", token);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            return HttpGetWithEntity.sendJsonByGetReq(url + "device/list", reqParams, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Object getDeviceLatestData(String sno) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("sno", sno);

            ApiConfiguration.check();
            String token = getToken(appKey, appSecret);
            params.put("token", token);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            return HttpGetWithEntity.sendJsonByGetReq(url + "device/latestData", reqParams, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Object getDeviceOrder(String sno, String sensorId, int val) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("sno", sno);
            params.put("sensorId", sensorId);
            params.put("val", val);

            ApiConfiguration.check();
            String token = getToken(appKey, appSecret);
            params.put("token", token);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            return HttpGetWithEntity.sendJsonByGetReq(url + "device/order", reqParams, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Object getPestStationList(int pageNum, int pageSize) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("pageNum", pageNum);
            params.put("pageSize", pageSize);

            ApiConfiguration.check();
            String token = getToken(appKey, appSecret);
            params.put("token", token);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            return HttpGetWithEntity.sendJsonByGetReq(url + "pestStation/list", reqParams, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Object getPestStationLatestData(String sno) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("sno", sno);

            ApiConfiguration.check();
            String token = getToken(appKey, appSecret);
            params.put("token", token);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            return HttpGetWithEntity.sendJsonByGetReq(url + "pestStation/latestData", reqParams, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }

    @Override
    public Object getPestStationDayReports(String sno, String date) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("sno", sno);
            params.put("date", date);

            ApiConfiguration.check();
            String token = getToken(appKey, appSecret);
            params.put("token", token);

            BaseRequestVo update = SignUtil.update(params, appKey, appSecret);
            String reqParams = JSONArray.toJSON(update).toString();
            return HttpGetWithEntity.sendJsonByGetReq(url + "pestStation/dayReports", reqParams, "UTF-8");

        } catch (Exception e) {
            e.printStackTrace();
            return e.toString();
        }
    }


}
