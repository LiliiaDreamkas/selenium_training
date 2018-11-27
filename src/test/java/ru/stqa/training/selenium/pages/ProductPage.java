package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.Product;

import java.util.concurrent.TimeUnit;

public class ProductPage extends BasePage{

    public ProductPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void putProductToCart(Product product) {
        String currentQuantityInCart = driver.findElement(By.cssSelector("span.quantity")).getText();
        String expectedQuantity = Integer.toString(Integer.parseInt(currentQuantityInCart) + 1);
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        try {
            Select duckSize = new Select(driver.findElement(By.cssSelector("select[name ^= options]")));
            duckSize.selectByValue(product.getSize());
        } catch (NoSuchElementException e) {}
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement Quantity = driver.findElement(By.cssSelector("input[name = quantity]"));
        Quantity.clear();
        Quantity.sendKeys(product.getQuantity());
        driver.findElement(By.cssSelector("button[name = add_cart_product]")).click();
        wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), expectedQuantity));
    }

}
