package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntries;

import java.util.List;

public class CheckTestLogs extends TestBase {

    @Test
    public void checkLogs() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> productsList = driver.findElements(By.xpath("//form[@name = 'catalog_form']//tr[./td/img]"));
        for (int i = 0; i < productsList.size(); i++) {
            productsList.get(i).findElement(By.xpath("./td/a")).click();
            LogEntries browserLogs = driver.manage().logs().get("browser");
            int logsCount = browserLogs.getAll().size();
            browserLogs.forEach(log -> System.out.println(log));
            Assert.assertTrue("Browser logs was found", logsCount == 0);
            driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
            productsList = driver.findElements(By.xpath("//form[@name = 'catalog_form']//tr[./td/img]"));
        }
    }
}
