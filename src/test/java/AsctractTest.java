import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AsctractTest {
    WebDriver webDriver;

    @BeforeEach
    public void initDriver() throws MalformedURLException {
        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName("chrome");
        Map<String, Object> options = new HashMap<>();
        options.put("enableVnc", true);
        dc.setCapability("selenoid:options", options);
        webDriver = new RemoteWebDriver(new URL("http://Localhost:4444/wd/hub"), dc);
        webDriver.get("https://test-stand.gb.ru/login");

    }

    public  WebDriver getWebDriver(){
        return this.webDriver;
    }
}
