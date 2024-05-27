package org.example.elements;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.example.pages.StudentsTablePageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.codeborne.selenide.Selenide.$x;

public class TableRowsClass {

    private final SelenideElement root = $x("//table[@aria-label='Dummies list']/tbody/tr");

    public String getId(){
        return root.findElement(By.xpath("./th[1]")).getText();
    }

    public String getName(){
        return root.findElement(By.xpath("./th[2]")).getText();
    }

    public String getPhone(){
        return root.findElement(By.xpath("./th[3]")).getText();
    }
}
