package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage{

    public CartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/checkout");
    }

    public void goToCart() {
        driver.findElement(By.xpath("//a[contains(text(), 'Checkout')]")).click();
    }

    public int getProductCount() {
        return driver.findElements(By.cssSelector("li.item")).size();
    }

    public void deleteRandomProduct() {
        WebElement productsTable = driver.findElement(By.cssSelector("div#box-checkout-summary"));
        driver.findElement(By.cssSelector("button[name = remove_cart_item]")).click();
        wait.until(ExpectedConditions.stalenessOf(productsTable));
    }

}
