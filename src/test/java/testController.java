import com.zhonghe.sdk.ZhongheApplication;
import com.zhonghe.sdk.config.ApiConfiguration;
import com.zhonghe.sdk.service.ApiService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author huiyingzhang
 * @ClassName testController
 * @description
 * @date 2022/8/11 16:45
 */
@SpringBootTest(classes = ZhongheApplication.class)
@RunWith(SpringRunner.class)
public class testController {

    @Autowired
    ApiService apiService;

    @Autowired
    private ApiConfiguration apiConfiguration;

    @Test
    public void test() {
        System.out.println("apiConfiguration = " + apiConfiguration);
    }

    @Test
    public void test1(){
        Object s = apiService.getDeviceList(1, 10);
        System.out.println("s = " + s);
    }

    @Test
    public void test2() {
        Object s = apiService.getDeviceLatestData("DE2DB30823F1");
        System.out.println("s = " + s);
    }

    @Test
    public void test3() {
        Object s = apiService.getDeviceOrder("DE2DB30823F1", "DE2DB30823F101", 1);
        System.out.println("s = " + s);
    }

    @Test
    public void test4() {
        Object s = apiService.getPestStationList(1, 10);
        System.out.println("s = " + s);
    }

    @Test
    public void test5() {
        Object s = apiService.getPestStationLatestData("171CB7400943");
        System.out.println("s = " + s);
    }

    @Test
    public void test6() {
        String dateStr = "2022-05-13";

        Object s = apiService.getPestStationDayReports("171CB7400943", dateStr);
        System.out.println("s = " + s);
    }
}
