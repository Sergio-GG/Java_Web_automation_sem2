package org.example.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class TableRowsClass {

    private final WebElement root;

    public TableRowsClass(WebElement root) {
        this.root = root;
    }

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
