import com.zhonghe.sdk.ApiHelper;
import com.zhonghe.sdk.ZhongheApplication;
import com.zhonghe.sdk.service.ApiService;;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author huiyingzhang
 * api调用测试
 */
@SpringBootTest(classes = ZhongheApplication.class)
@RunWith(SpringRunner.class)
public class testController {

    ApiService apiService = ApiHelper.newApi();

    @Test
    public void test1() {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", 1);
        params.put("pageSize", 10);
        Object s = apiService.getDeviceList(params);
        System.out.println("获取设备列表 = " + s);
    }

    @Test
    public void test2() {
        Map<String, Object> params = new HashMap<>();
        params.put("sno", "DE2DB30823F1");
        Object s = apiService.getDeviceLatestData(params);
        System.out.println("获取设备最新数据 = " + s);
    }

    @Test
    public void test3() {
        Map<String, Object> params = new HashMap<>();
        params.put("sno", "DE2DB30823F1");
        params.put("sensorId", "DE2DB30823F101");
        params.put("val", 1);
        Object s = apiService.getDeviceOrder(params);
        System.out.println("控制设备 = " + s);
    }

    @Test
    public void test4() {
        Map<String, Object> params = new HashMap<>();
        params.put("pageNum", 1);
        params.put("pageSize", 10);
        Object s = apiService.getPestStationList(params);
        System.out.println("获取虫情站列表 = " + s);
    }

    @Test
    public void test5() {
        Map<String, Object> params = new HashMap<>();
        params.put("sno", "171CB7400943");
        Object s = apiService.getPestStationLatestData(params);
        System.out.println("获取虫情站最新数据 = " + s);
    }

    @Test
    public void test6() {
        Map<String, Object> params = new HashMap<>();
        params.put("sno", "171CB7400943");
        params.put("date", "2022-05-13");

        Object s = apiService.getPestStationDayReports(params);
        System.out.println("虫情站日报表 = " + s);
    }
}
