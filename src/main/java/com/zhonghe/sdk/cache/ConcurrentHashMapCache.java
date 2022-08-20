package com.zhonghe.sdk.cache;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huiyingzhang
 * 自定义缓存数据
 */
@Slf4j
public class ConcurrentHashMapCache {

    private static Map<String, Object> CACHE_MAP = new ConcurrentHashMap<String, Object>();

    /**
     * 每个缓存生效时间12小时
     */
    private static long CACHE_HOLD_TIME_12H = 12 * 60 * 60 * 1000L;

    /**
     * api调用的accessToken redis key access_token:appKey
     */
    public static final String ACCESS_TOKEN_KEY = "access_token:";

    /**
     * 存放一个缓存对象，默认保存时间12小时
     *
     * @param cacheName 缓存key
     * @param obj 缓存对象
     */
    public static void put(String cacheName, Object obj) {
        put(cacheName, obj, CACHE_HOLD_TIME_12H);
    }

    /**
     * 存放一个缓存对象，保存时间为holdTime
     *
     * @param cacheName 缓存key
     * @param obj 缓存对象
     * @param holdTime 过期时间
     */
    public static void put(String cacheName, Object obj, long holdTime) {
        CACHE_MAP.put(cacheName, obj);
        // 设置缓存失效时间
        CACHE_MAP.put(cacheName + "_HoldTime", System.currentTimeMillis() + holdTime);
        log.info("{}：成功存入缓存！ 过期时间：{}", cacheName, System.currentTimeMillis() + holdTime);
    }

    /**
     * 取出一个缓存对象
     *
     * @param cacheName 缓存key
     * @return 缓存对象
     */
    public static Object get(String cacheName) {
        if (checkCacheName(cacheName)) {
            return CACHE_MAP.get(cacheName);
        }
        return null;
    }

    /**
     * 删除所有缓存
     */
    public void removeAll() {
        CACHE_MAP.clear();
    }

    /**
     * 删除某个缓存
     *
     * @param cacheName 缓存key
     */
    public static void remove(String cacheName) {
        CACHE_MAP.remove(cacheName);
        CACHE_MAP.remove(cacheName + "_HoldTime");
    }

    /**
     * 检查缓存对象是否存在，
     * 若不存在，则返回false
     * 若存在，检查其是否已过有效期，如果已经过了则删除该缓存并返回false
     *
     * @param cacheName 缓存key
     * @return 缓存对象是否存在
     */
    public static boolean checkCacheName(String cacheName) {
        Long cacheHoldTime = (Long) CACHE_MAP.get(cacheName + "_HoldTime");
        if (cacheHoldTime == null || cacheHoldTime == 0L) {
            return false;
        }
        if (cacheHoldTime < System.currentTimeMillis()) {
            log.info("缓存：{}已失效！", cacheName);
            remove(cacheName);
            return false;
        }
        return true;
    }

    /**
     * 获取缓存大小
     *
     * @return 缓存大小
     */
    public Integer size() {
        if (null != CACHE_MAP && CACHE_MAP.size() > 0) {
            return CACHE_MAP.size();
        }
        return 0;
    }

    /**
     * 判断缓存的key是否存在
     *
     * @param cacheName 缓存key
     * @return 缓存对象是否存在
     */
    public boolean isExist(String cacheName) {
        if (!checkCacheName(cacheName)) {
            return Boolean.FALSE;
        }
        return CACHE_MAP.containsKey(cacheName);
    }

}

