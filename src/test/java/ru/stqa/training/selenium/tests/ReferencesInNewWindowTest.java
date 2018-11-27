package ru.stqa.training.selenium.tests;

import org.checkerframework.checker.nullness.compatqual.NullableDecl;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

public class ReferencesInNewWindowTest extends TestBase {

    @Test
    public void refInNewWindow() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath("//a[contains(text(), 'Add New Country')]")).click();
        List<WebElement> allReferences = driver.findElements(By.xpath("//a[./i[contains(@class, 'fa-external-link')]]"));
        for (WebElement currentReference : allReferences) {
            Set<String> openedWindows = driver.getWindowHandles();
            String currentHandle = driver.getWindowHandle();
            currentReference.click();
            String newHandle = wait.until(waitForNewWindow(openedWindows));
            driver.switchTo().window(newHandle);
            driver.close();
            driver.switchTo().window(currentHandle);
        }
    }

    public ExpectedCondition<String> waitForNewWindow(Set<String> windowsList) {
        return new ExpectedCondition<String>() {
            @NullableDecl
            @Override
            public String apply(@NullableDecl WebDriver driver) {
                Set<String> currentHandles = driver.getWindowHandles();
                currentHandles.removeAll(windowsList);
                return currentHandles.size() > 0 ? currentHandles.iterator().next() : null;
            }
        };
    }
}
