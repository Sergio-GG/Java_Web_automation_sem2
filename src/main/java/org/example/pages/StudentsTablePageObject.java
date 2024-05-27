package org.example.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.example.elements.TableRowsClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class StudentsTablePageObject {

    // Ищу список строк
    private ElementsCollection tableRows = $$x("//table[@aria-label='Dummies list']/tbody/tr");
    private SelenideElement createStudentButton = $x("//button[@id='create-btn']");
    private ElementsCollection rows = this.tableRows;
    List<TableRowsClass> groupOfElements = rows
            .asDynamicIterable()
            .stream().map(o -> new TableRowsClass()).toList();

    public List<TableRowsClass> getGroupOfElements(){
        return groupOfElements;
    }

    public ElementsCollection getTableRows(){
        return tableRows;
    }
    public void createStudent(){
        createStudentButton.click();
    }


}
