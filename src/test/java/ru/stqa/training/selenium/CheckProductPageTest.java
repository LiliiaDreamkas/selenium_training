package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CheckProductPageTest extends TestBase{

    @Test
    public void checkProductName() {
        driver.get("http://localhost/litecart/");
        WebElement currentProduct = driver.findElement(By.cssSelector("div#box-campaigns li[class *= product]"));
        String currentProductName = currentProduct.findElement(By.cssSelector("div.name")).getText();
        currentProduct.click();
        String currentProductNameOnProductPage = driver
                .findElement(By.cssSelector("div#box-product h1[itemprop = name]")).getText();
        Assert.assertTrue(String.format("Name on general page (\"%s\") and home page (\"%s\") don't match",
                currentProductName, currentProductNameOnProductPage),
                currentProductName.equals(currentProductNameOnProductPage));
    }
}
