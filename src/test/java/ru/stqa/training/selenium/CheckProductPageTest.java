package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;

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
        assertTrue(String.format("Name on general page (\"%s\") and home page (\"%s\") don't match",
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

        assertTrue(String.format("Regular price on general page (\"%s\") and home page (\"%s\") don't match",
                regularPrice, regularPriceOnProductPage),
                regularPrice.equals(regularPriceOnProductPage));

        assertTrue(String.format("Campaign price on general page (\"%s\") and home page (\"%s\") don't match",
                campaignPrice, campaignPriceOnProductPage),
                campaignPrice.equals(campaignPriceOnProductPage));
    }

    //в
    @Test
    public void checkRegularPriceParameters() {
        driver.get("http://localhost/litecart/");
        WebElement currentProduct = driver.findElement(By.cssSelector("div#box-campaigns li[class *= product]"));
        String priceTag = currentProduct.findElement(By.cssSelector("s.regular-price")).getTagName();
        checkThatElementHaveLine(priceTag);
        String regularPriceColor = currentProduct.findElement(By.cssSelector("s.regular-price")).getCssValue("color");
        checkColorValue(regularPriceColor, "gray");

        currentProduct.click();

        WebElement regPrice = driver.findElement(By.cssSelector("div#box-product s.regular-price"));
        priceTag = regPrice.getTagName();
        checkThatElementHaveLine(priceTag);
        regularPriceColor = regPrice.getCssValue("color");
        checkColorValue(regularPriceColor, "gray");
    }

    //г
    @Test
    public void checkCampaignPriceParameters() {
        driver.get("http://localhost/litecart/");
        WebElement currentProduct = driver.findElement(By.cssSelector("div#box-campaigns li[class *= product]"));
        String priceTag = currentProduct.findElement(By.cssSelector("strong.campaign-price")).getTagName();
        checkThatElementIsBold(priceTag);
        String regularPriceColor = currentProduct.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
        checkColorValue(regularPriceColor, "red");

        currentProduct.click();

        WebElement campaignPrice = driver.findElement(By.cssSelector("div#box-product strong.campaign-price"));
        priceTag = campaignPrice.getTagName();
        checkThatElementIsBold(priceTag);
        regularPriceColor = campaignPrice.getCssValue("color");
        checkColorValue(regularPriceColor, "red");
    }

    //д
    @Test
    public void campaignPriceIsBiggerThanRegular() {
        driver.get("http://localhost/litecart/");
        WebElement currentProduct = driver.findElement(By.cssSelector("div#box-campaigns li[class *= product]"));
        int regularPriceHeight = currentProduct.findElement(By.cssSelector("s.regular-price")).getSize().height;
        int campaignPriceHeight = currentProduct.findElement(By.cssSelector("strong.campaign-price")).getSize().height;
        int regularPriceWidth= currentProduct.findElement(By.cssSelector("s.regular-price")).getSize().width;
        int campaignPriceWidth = currentProduct.findElement(By.cssSelector("strong.campaign-price")).getSize().width;
        assertTrue("Regular price is not smaller than campaign", (campaignPriceHeight > regularPriceHeight && campaignPriceWidth > regularPriceWidth));

        currentProduct.click();

        regularPriceHeight = driver.findElement(By.cssSelector("div#box-product s.regular-price")).getSize().height;
        campaignPriceHeight = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getSize().height;
        regularPriceWidth= driver.findElement(By.cssSelector("div#box-product s.regular-price")).getSize().width;
        campaignPriceWidth = driver.findElement(By.cssSelector("div#box-product strong.campaign-price")).getSize().width;
        assertTrue("Regular price is not smaller than campaign", (campaignPriceHeight > regularPriceHeight && campaignPriceWidth > regularPriceWidth));

    }

    private void checkThatElementHaveLine(String elementTag) {
        assertTrue("Element is not crossed", (elementTag.equals("s") || elementTag.equals("strike") || elementTag.equals("strike")));
    }

    private void checkThatElementIsBold(String elementTag) {
        assertTrue("Element is not bold", (elementTag.equals("strong") || elementTag.equals("b")));
    }

    private void checkColorValue(String regularPriceColor, String color) {
        regularPriceColor = regularPriceColor.replace("rgb", "").replace("a", "").replace("(", "")
                .replace(")", "").replace(" ", "");
        String[] colorParameters = regularPriceColor.split(",");
        String r = colorParameters[0];
        String g = colorParameters[1];
        String b = colorParameters[2];
        if (color.equals("gray")) {
            assertTrue("Regular price is not gray",
                    (r.equals(g) && g.equals(b)));
        } else if (color.equals("red")) {
            assertTrue("Campaign price is not red",
                    (g.equals("0") && b.equals("0")));
        }

    }
}
