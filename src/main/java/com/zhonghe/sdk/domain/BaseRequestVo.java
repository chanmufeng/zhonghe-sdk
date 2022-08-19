package com.zhonghe.sdk.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Map;

/**
 * @author chanmufeng
 * @description 请求实体的公共父类
 * @date 2021/8/4
 */
@Data
public class BaseRequestVo {

    @JsonProperty("system")
    private SystemDTO system;

    @JsonProperty("params")
    private Map<String,Object> params;


    @Data
    public static class SystemDTO {
        @JsonProperty("appKey")
        private String appKey;

        @JsonProperty("sign")
        private String sign;

        @JsonProperty("time")
        private Long time;
    }


}
