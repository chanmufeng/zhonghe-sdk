package com.zhonghe.sdk.service;

import java.util.Date;

/**
 * @author huiyingzhang
 * @ClassName ApiService
 * @description api调用接口
 * @date 2022/8/11 16:47
 */
public interface ApiService {

    /**
     * @param appKey
     * @param appSecret
     *
     * 获取token
     * @return token
     * */
    String getToken(String appKey, String appSecret);

    /**
     * @param pageNum 起始页数
     * @param pageSize 每页条数
     *
     * 获取设备列表
     * @return 设备列表
     * */
    Object getDeviceList(int pageNum, int pageSize);

    /**
     * @param sno 设备编号
     *
     * 获取设备最新数据
     * @return 设备最新数据
     * */
    Object getDeviceLatestData(String sno);

    /**
     * @param sno 设备编号
     * @param sensorId 节点编号（设备号+两位数索引）
     * @param val 开关指令（0：关 1：开）
     *
     * 控制设备
     * @return 控制设备操作结果
     * */
    Object getDeviceOrder(String sno, String sensorId, int val);

    /**
     * @param pageNum 起始页数
     * @param pageSize 每页条数
     *
     * 获取虫情站列表
     * @return 虫情站列表
     * */
    Object getPestStationList(int pageNum, int pageSize);

    /**
     * @param sno 虫情站编号
     *
     * 获取虫情站最新数据
     * @return 虫情站最新数据
     * */
    Object getPestStationLatestData(String sno);

    /**
     * @param sno 设备编号
     * @param date 日期（yyyy-MM-dd）
     *
     * 获取虫情站日报表
     * @return 虫情站日报表
     * */
    Object getPestStationDayReports(String sno, String date);

}
