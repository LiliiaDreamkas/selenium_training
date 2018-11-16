package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class CheckProductPageTest extends TestBase{

    //а
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

    //б
    @Test
    public void checkPrice() {
        driver.get("http://localhost/litecart/");
        WebElement currentProduct = driver.findElement(By.cssSelector("div#box-campaigns li[class *= product]"));
        String regularPrice = currentProduct.findElement(By.cssSelector("s.regular-price")).getText();
        String campaignPrice = currentProduct.findElement(By.cssSelector("strong.campaign-price")).getText();
        currentProduct.click();
        String regularPriceOnProductPage = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getText();
        String campaignPriceOnProductPage = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getText();

        Assert.assertTrue(String.format("Regular price on general page (\"%s\") and home page (\"%s\") don't match",
                regularPrice, regularPriceOnProductPage),
                regularPrice.equals(regularPriceOnProductPage));

        Assert.assertTrue(String.format("Campaign price on general page (\"%s\") and home page (\"%s\") don't match",
                campaignPrice, campaignPriceOnProductPage),
                campaignPrice.equals(campaignPriceOnProductPage));
    }

    //в
    @Test
    public void checkRegularPriceParameters() {
        driver.get("http://localhost/litecart/");
        WebElement currentProduct = driver.findElement(By.cssSelector("div#box-campaigns li[class *= product]"));
        String linePresence = currentProduct.findElement(By.cssSelector("s.regular-price")).getCssValue("text-decoration");
        Assert.assertTrue("Regular price on general page is not crossed", linePresence.contains("line-through"));
        String regularPriceColor = currentProduct.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        checkColorValue(regularPriceColor);
        currentProduct.click();
        WebElement regPrice = driver.findElement(By.cssSelector("div#box-product s.regular-price"));
        linePresence = regPrice.getCssValue("text-decoration");
        Assert.assertTrue("Regular price on product page is not crossed", linePresence.contains("line-through"));
        regularPriceColor = regPrice.getCssValue("color");
        checkColorValue(regularPriceColor);
    }

    private void checkColorValue(String regularPriceColor) {
        regularPriceColor = regularPriceColor.replace("rgb", "").replace("a", "").replace("(", "")
                .replace(")", "").replace(" ", "");
        String[] colorParameters = regularPriceColor.split(",");
        String r = colorParameters[0];
        String g = colorParameters[1];
        String b = colorParameters[2];
        Assert.assertTrue("Regular price is not gray",
                (r.equals(g) && g.equals(b)));
    }
}
