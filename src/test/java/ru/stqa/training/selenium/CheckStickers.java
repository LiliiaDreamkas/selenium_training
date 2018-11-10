package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CheckStickers extends TestBase {

    @Test
    public void checkStickers() {
        driver.get("http://localhost/litecart/");
        List<WebElement> products = driver.findElements(By.cssSelector("li[class ^= product]"));
        for (int i = 0; i < products.size(); i++) {
            WebElement currentProduct = products.get(i);
            List<WebElement> stickers = currentProduct.findElements(By.cssSelector("div[class ^= sticker]"));
            Assert.assertTrue("Current element has "  + stickers.size() + " stickers", stickers.size() == 1);
        }
    }
}
