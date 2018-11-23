package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class WorkingWithCartTest extends TestBase {

    @Test
    public void putAndLayOutProducts() {
        for (int i = 0; i < 3; i++) {
            driver.get("http://localhost/litecart/");
            driver.findElement(By.cssSelector("li[class ^= product]")).click();
            driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
            try {
                Select duckSize = new Select(driver.findElement(By.cssSelector("select[name ^= options]")));
                duckSize.selectByValue("Small");
            } catch (NoSuchElementException e) {}

            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            WebElement Quantity = driver.findElement(By.cssSelector("input[name = quantity]"));
            Quantity.clear();
            Quantity.sendKeys("1");
            driver.findElement(By.cssSelector("button[name = add_cart_product]")).click();
            wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), Integer.toString(i+1)));
        }

        driver.findElement(By.xpath("//a[contains(text(), 'Checkout')]")).click();

        int productCount = driver.findElements(By.cssSelector("li.item")).size();

        for (int i = 0; i < productCount; i++) {
            WebElement productsTable = driver.findElement(By.cssSelector("div#box-checkout-summary"));
            driver.findElement(By.cssSelector("button[name = remove_cart_item]")).click();
            wait.until(ExpectedConditions.stalenessOf(productsTable));
        }
    }
}
