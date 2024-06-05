import org.example.pages.CreateStudentWindowObject;
import org.example.pages.MainPageObject;
import org.example.pages.StudentsTablePageObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.lang.module.Configuration;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainPageObjectTest {

    private WebDriver driver;
    @Test
    void signInFailure(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://test-stand.gb.ru/login");

        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.sighIn("", "");

        Assertions.assertEquals("Invalid credentials.", mainPageObject.getErrorList().get(0).getText());

        driver.quit();
    }
    @Test
    void changeStudentsCount() throws InterruptedException, MalformedURLException {
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("start-maximized");
//        chromeOptions.addArguments("incognito");
//        driver = new ChromeDriver(chromeOptions);
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        DesiredCapabilities dc = new DesiredCapabilities();
        dc.setBrowserName("chrome");
        Map<String, Object> options = new HashMap<>();
        options.put("enableVnc", true);
        dc.setCapability("selenoid:options", options);
        driver = new RemoteWebDriver(new URL("http://Localhost:4444/wd/hub"), dc);
        driver.get("https://test-stand.gb.ru/login");

        // Authorization
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.sighIn("GB2023085e78711", "5632f36449");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Authorization assert
        List<WebElement> list0 = driver.findElements(By.xpath("//th[@data-column-id='SMUI-data-table-column-3']"));
        Assertions.assertEquals("Status", list0.get(0).getText());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        StudentsTablePageObject sto = new StudentsTablePageObject(driver);
        List<WebElement> list = driver.findElements(By.xpath("//table[@aria-label='Dummies list']/tbody/tr"));
        Assertions.assertEquals(10, list.size());

        int elementsCount = list.size();

        sto.createStudent();

        // StudentTablePage assert
        List<WebElement> addButton = driver.findElements(By.xpath("//button[@id='create-btn']"));
        Assertions.assertEquals(1, addButton.size());

        CreateStudentWindowObject cswo = new CreateStudentWindowObject(driver);
        List<WebElement> fields = driver.findElements(By.xpath("//label[@class='mdc-text-field smui-text-field--standard']"));
        WebElement loginField = fields.get(3);
        loginField.sendKeys("Studento_Vero1");
        cswo.createNewStudent();

        List<WebElement> list2 = driver.findElements(By.xpath("//table[@aria-label='Dummies list']/tbody/tr"));
        Assertions.assertEquals(10, list2.size());

        Thread.sleep(20000);

        driver.quit();
    }
}
