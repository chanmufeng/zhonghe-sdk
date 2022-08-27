package com.zhonghe.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

/**
 * @author chanmufeng
 * 请求实体的公共父类
 */
public class BaseRequestVo {

    @JsonProperty("system")
    private SystemDTO system;

    @JsonProperty("params")
    private Map<String,Object> params;


    public static class SystemDTO {
        @JsonProperty("appKey")
        private String appKey;

        @JsonProperty("sign")
        private String sign;

        @JsonProperty("time")
        private Long time;

        public String getAppKey() {
            return appKey;
        }

        public void setAppKey(String appKey) {
            this.appKey = appKey;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public Long getTime() {
            return time;
        }

        public void setTime(Long time) {
            this.time = time;
        }
    }

    public SystemDTO getSystem() {
        return system;
    }

    public void setSystem(SystemDTO system) {
        this.system = system;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }
}
