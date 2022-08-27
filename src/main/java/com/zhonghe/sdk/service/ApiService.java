package com.zhonghe.sdk.service;

import com.zhonghe.sdk.anno.ZhApi;

import java.util.Map;

/**
 * @author huiyingzhang
 * api调用接口
 */
public interface ApiService {

    /**
     * @param params 请求参数
     *
     * 获取设备列表
     * @return 设备列表
     * */
    @ZhApi(url = "device/list")
    Object getDeviceList(Map<String, Object> params);

    /**
     * @param params 请求参数
     *
     * 获取设备最新数据
     * @return 设备最新数据
     * */
    @ZhApi(url = "device/latestData")
    Object getDeviceLatestData(Map<String, Object> params);

    /**
     * @param params 请求参数
     *
     * 控制设备
     * @return 控制设备操作结果
     * */
    @ZhApi(url = "device/order")
    Object getDeviceOrder(Map<String, Object> params);

    /**
     * @param params 请求参数
     *
     * 获取虫情站列表
     * @return 虫情站列表
     * */
    @ZhApi(url = "pestStation/list")
    Object getPestStationList(Map<String, Object> params);

    /**
     * @param params 请求参数
     *
     * 获取虫情站最新数据
     * @return 虫情站最新数据
     * */
    @ZhApi(url = "pestStation/latestData")
    Object getPestStationLatestData(Map<String, Object> params);

    /**
     * @param params 请求参数
     *
     * 获取虫情站日报表
     * @return 虫情站日报表
     * */
    @ZhApi(url = "pestStation/dayReports")
    Object getPestStationDayReports(Map<String, Object> params);

}
