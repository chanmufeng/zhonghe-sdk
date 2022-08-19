import com.zhonghe.sdk.ZhongheApplication;
import com.zhonghe.sdk.config.ApiConfiguration;
import com.zhonghe.sdk.service.ObtainTokenService;
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
    ObtainTokenService obtainTokenService;

    @Autowired
    private ApiConfiguration apiConfiguration;

    @Test
    public void test() {
        System.out.println("apiConfiguration = " + apiConfiguration);
    }

    @Test
    public void test1(){
        Object s = obtainTokenService.getDeviceList(1, 10);
        System.out.println("s = " + s);
    }

    @Test
    public void test2() {
        Object s = obtainTokenService.getDeviceLatestData("DE2DB30823F1");
        System.out.println("s = " + s);
    }

    @Test
    public void test3() {
        Object s = obtainTokenService.getDeviceOrder("DE2DB30823F1", "DE2DB30823F101", 1);
        System.out.println("s = " + s);
    }

    @Test
    public void test4() {
        Object s = obtainTokenService.getPestStationList(1, 10);
        System.out.println("s = " + s);
    }

    @Test
    public void test5() {
        Object s = obtainTokenService.getPestStationLatestData("171CB7400943");
        System.out.println("s = " + s);
    }

    @Test
    public void test6() {
        String dateStr = "2022-05-13";

        Object s = obtainTokenService.getPestStationDayReports("171CB7400943", dateStr);
        System.out.println("s = " + s);
    }
}
