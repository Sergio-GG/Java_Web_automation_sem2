package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class MainPageObject {

    private SelenideElement login = $x("//input[@type='text']");
    private SelenideElement password = $x("//input[@type='password']");
    private SelenideElement loginButton = $x("//button");
    private ElementsCollection errorList = $$x("//p[@class='svelte-uwkxn9']");


    public void sighIn(String inputLogin, String inputPass){
        login.sendKeys(inputLogin);
        password.sendKeys(inputPass);
        loginButton.click();
    }

    public ElementsCollection getErrorList(){
        return errorList;
    }
}
