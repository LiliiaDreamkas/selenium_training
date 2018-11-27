package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage extends BasePage{

    public MainPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("http://localhost/litecart/");
    }

    public void clickOnRandomProduct() {
        driver.findElement(By.cssSelector("li[class ^= product]")).click();
    }
}
