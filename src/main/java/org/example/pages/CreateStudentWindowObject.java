package org.example.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class CreateStudentWindowObject {
    private ElementsCollection fields = $$x("//label[@class='mdc-text-field smui-text-field--standard']");
    private SelenideElement closeButton = $x("//button[@class='material-icons mdc-icon-button mdc-icon-button--display-flex mdc-dialog__close mdc-ripple-upgraded--unbounded mdc-ripple-upgraded']");
    private SelenideElement saveButton = $x("//button[@class='button mdc-button mdc-button--raised mdc-ripple-upgraded']");
    private SelenideElement loginField = fields.get(2);

    public void createNewStudent(String inputLogin){
        loginField.sendKeys(inputLogin);
        saveButton.should(Condition.visible).click();
        closeButton.should(Condition.visible).click();
    }

    public int getFields(){
        return fields.size();
    }
    public String getLoginName(){
        return loginField.getText();
    }
}
