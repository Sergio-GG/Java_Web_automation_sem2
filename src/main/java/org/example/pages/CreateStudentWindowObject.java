package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CreateStudentWindowObject {

//    @FindBy(xpath="//label[@class='mdc-text-field smui-text-field--standard']")
//    private List<WebElement> fields;

    @FindBy(xpath="//button[@class='material-icons mdc-icon-button mdc-icon-button--display-flex mdc-dialog__close mdc-ripple-upgraded--unbounded mdc-ripple-upgraded']")
    private WebElement closeButton;

    public CreateStudentWindowObject(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    //public WebElement loginField = fields.get(3);


    public void createNewStudent(){
        //loginField.sendKeys(inputLogin);
        closeButton.click();
    }
}
