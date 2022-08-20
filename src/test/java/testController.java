import com.zhonghe.sdk.ZhongheApplication;
import com.zhonghe.sdk.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huiyingzhang
 * api调用测试
 */
@SpringBootTest(classes = ZhongheApplication.class)
@RunWith(SpringRunner.class)
public class testController {

    @Autowired
    ApiService apiService;

    @Test
    public void test1(){
        Object s = apiService.getDeviceList(1, 10);
        System.out.println("获取设备列表 = " + s);
    }

    @Test
    public void test2() {
        Object s = apiService.getDeviceLatestData("DE2DB30823F1");
        System.out.println("获取设备最新数据 = " + s);
    }

    @Test
    public void test3() {
        Object s = apiService.getDeviceOrder("DE2DB30823F1", "DE2DB30823F101", 1);
        System.out.println("控制设备 = " + s);
    }

    @Test
    public void test4() {
        Object s = apiService.getPestStationList(1, 10);
        System.out.println("获取虫情站列表 = " + s);
    }

    @Test
    public void test5() {
        Object s = apiService.getPestStationLatestData("171CB7400943");
        System.out.println("获取虫情站最新数据 = " + s);
    }

    @Test
    public void test6() {
        String dateStr = "2022-05-13";

        Object s = apiService.getPestStationDayReports("171CB7400943", dateStr);
        System.out.println("虫情站日报表 = " + s);
    }
}
