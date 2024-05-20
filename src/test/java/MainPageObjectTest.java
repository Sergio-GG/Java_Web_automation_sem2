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

import java.time.Duration;
import java.util.List;

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
    void changeStudentsCount() throws InterruptedException {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("start-maximized");
        chromeOptions.addArguments("incognito");
        driver = new ChromeDriver(chromeOptions);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://test-stand.gb.ru/login");

        // Authorization
        MainPageObject mainPageObject = new MainPageObject(driver);
        mainPageObject.sighIn("GB2023085e78711", "5632f36449");

        // Authorization assert
        List<WebElement> webElementList = driver.findElements(By.xpath("//th[@data-column-id='SMUI-data-table-column-3']"));
        Assertions.assertEquals(1, webElementList.size());

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        StudentsTablePageObject sto = new StudentsTablePageObject(driver);
        List<WebElement> list = driver.findElements(By.xpath("//table[@aria-label='Dummies list']/tbody/tr"));
        Assertions.assertEquals(6, list.size());

        int elementsCount = sto.getGroupOfElements().size();

        sto.createStudent();

        // StudentTablePage assert
        List<WebElement> addButton = driver.findElements(By.xpath("//button[@id='create-btn']"));
        Assertions.assertEquals(1, addButton.size());

        CreateStudentWindowObject cswo = new CreateStudentWindowObject(driver);
        cswo.createNewStudent("Student1");

        //Assert add Student
        Assertions.assertEquals(elementsCount + 1, sto.getGroupOfElements().size());

        driver.quit();
    }
}
