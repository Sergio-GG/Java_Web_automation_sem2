package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MainPageObject {

    @FindBy(xpath="//input[@type='text']")
    private WebElement login;

    @FindBy(xpath="//input[@type='password']")
    private WebElement password;

    @FindBy(xpath="//button")
    private WebElement loginButton;

    @FindBy(xpath="//p[@class='svelte-uwkxn9']")
    private List<WebElement> errorList;

    public MainPageObject(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void sighIn(String inputLogin, String inputPass){
        login.sendKeys(inputLogin);
        password.sendKeys(inputPass);
        loginButton.click();
    }

    public List<WebElement> getErrorList(){
        return errorList;
    }
}
