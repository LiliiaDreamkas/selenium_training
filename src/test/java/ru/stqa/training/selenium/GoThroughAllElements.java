package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GoThroughAllElements extends TestBase {

    @Test
    public void goThroughAllElements() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin");
        List<WebElement> pointsOfMenu = driver.findElements(By.cssSelector("ul#box-apps-menu > li"));
        for (int i = 0; i < pointsOfMenu.size(); i++) {
            driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + (i+1) + ")")).click();
            List<WebElement> subPoints = driver.findElements(By.cssSelector("ul#box-apps-menu > li:nth-child(" + (i+1) + ") li"));
            for (int j = 0; j < subPoints.size(); j++ ) {
                driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + (i+1) +") li:nth-child(" + (j+1) +")")).click();
            }
        }
    }
}
