package com.zhonghe.sdk.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zhonghe.sdk.cache.ConcurrentHashMapCache;
import com.zhonghe.sdk.config.ApiConfiguration;
import com.zhonghe.sdk.constant.HttpStatus;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.net.URI;

import static com.zhonghe.sdk.cache.ConcurrentHashMapCache.ACCESS_TOKEN_KEY;

/**
 * @author huiyingzhang
 */
public class HttpGetWithEntity extends HttpEntityEnclosingRequestBase {

    private final static String METHOD_NAME = "GET";
    /**
     * 获取配置中的url
     */
    static String urlPrefix = ApiConfiguration.getUrlPrefix();

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpGetWithEntity() {
        super();
    }

    public HttpGetWithEntity(final URI uri) {
        super();
        setURI(uri);
    }

    HttpGetWithEntity(final String uri) {
        super();
        setURI(URI.create(uri));
    }

    public static String getToken(String url, String param, String appKey) throws Exception {
        String token = "";
        JSONObject res = sendJsonByGetReq(url, param);
        if (res.get("code").equals(HttpStatus.SUCCESS)) {
            JSONObject data = (JSONObject) res.get("data");

            ConcurrentHashMapCache.put(ACCESS_TOKEN_KEY + appKey, data);
            token = String.valueOf(data.get("accessToken"));
        }
        return token;
    }

    public static JSONObject sendJsonByGetReq(String url, String param) throws Exception {
        String body = "";
        JSONObject jsonObject = null;
        //创建httpclient对象
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGetWithEntity httpGetWithEntity = new HttpGetWithEntity(urlPrefix + url);
        HttpEntity httpEntity = new StringEntity(param, ContentType.APPLICATION_JSON);
        httpGetWithEntity.setEntity(httpEntity);
        //执行请求操作，并拿到结果（同步阻塞）
        CloseableHttpResponse response = client.execute(httpGetWithEntity);
        //获取结果实体
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            //按指定编码转换结果实体为String类型
            body = EntityUtils.toString(entity, "UTF-8");
            jsonObject = JSONArray.parseObject(body);
        }
        //释放链接
        response.close();
        return jsonObject;
    }

}
