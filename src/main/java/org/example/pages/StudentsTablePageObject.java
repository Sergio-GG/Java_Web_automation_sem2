package org.example.pages;

import org.example.elements.TableRowsClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import java.util.List;
import java.util.stream.Collectors;

public class StudentsTablePageObject {

    // Ищу список строк
//    @FindBy(xpath="//table[@aria-label='Dummies list']/tbody/tr")
//    private List<WebElement> tableRows;

    @FindBy(xpath="//button[@id='create-btn']")
    private WebElement createStudentButton;

    public StudentsTablePageObject(WebDriver webDriver){
        PageFactory.initElements(webDriver, this);
    }

    public void createStudent(){
        createStudentButton.click();
    }

    // Делал как в конспекте. Почему-то null

    //List<TableRowsClass> groupOfElements = tableRows.stream().map(TableRowsClass::new).toList();

    //public List<TableRowsClass> getGroupOfElements(){
//        return groupOfElements;
//    }


}
