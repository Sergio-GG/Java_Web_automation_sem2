import com.codeborne.selenide.*;
import org.example.elements.TableRowsClass;
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

import static com.codeborne.selenide.Selenide.$$x;

public class MainPageObjectTest {

    private WebDriver driver;
    @Test
    void signInFailure(){
        Selenide.open("https://test-stand.gb.ru/login");
        driver = WebDriverRunner.getWebDriver();


        MainPageObject mainPageObject = Selenide.page(MainPageObject.class);
        mainPageObject.sighIn("", "");

        Assertions.assertEquals("Invalid credentials.", mainPageObject.getErrorList().get(0).getText());

        WebDriverRunner.closeWebDriver();
    }
    @Test
    void changeStudentsCount() throws InterruptedException {
        Selenide.open("https://test-stand.gb.ru/login");
        driver = WebDriverRunner.getWebDriver();

        // Authorization
        MainPageObject mainPageObject = Selenide.page(MainPageObject.class);
        mainPageObject.sighIn("GB2023085e78711", "5632f36449");

        // Authorization assert
        ElementsCollection webElementList = $$x("//th[@data-column-id='SMUI-data-table-column-3']");
        webElementList.should(CollectionCondition.sizeGreaterThan(0));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        // Create StudentPage
        StudentsTablePageObject sto = Selenide.page(StudentsTablePageObject.class);
        ElementsCollection tableRows = $$x("//table[@aria-label='Dummies list']/tbody/tr");
        tableRows.should(CollectionCondition.sizeGreaterThanOrEqual(6));

        // assert cast to TableRowClass type
        int elementsCount = sto.getGroupOfElements().size();
        Assertions.assertEquals(10, elementsCount);
        sto.createStudent();

        // StudentTablePage assert
        ElementsCollection addButton = $$x("//button[@id='create-btn']");
        Assertions.assertEquals(1, addButton.size());

//        // Create StudentWindow
        CreateStudentWindowObject cswo = Selenide.page(CreateStudentWindowObject.class);
        Assertions.assertEquals(5, cswo.getFields());
        Assertions.assertEquals("Login", cswo.getLoginName());
        cswo.createNewStudent("Velikiy Student Lomonosov111");

//        // Assert student added to table
        driver.navigate().refresh();
        StudentsTablePageObject sto1 = Selenide.page(StudentsTablePageObject.class);
        List<TableRowsClass> newRows = sto1.getGroupOfElements();
        Assertions.assertEquals(elementsCount + 1, newRows.size());

        WebDriverRunner.closeWebDriver();
    }
}
