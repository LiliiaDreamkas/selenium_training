package ru.stqa.training.selenium.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.*;

public class AddNewProductTest extends TestBase {

    @Test
    public void addNewProduct() {
        loginAsAdmin();
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        driver.findElement(By.xpath("//a[contains(text(), 'Add New Product')]")).click();

        //General info
        driver.findElement(By.xpath("//label[contains(text(), 'Enabled')]")).click();
        String duckName = "Ninja duck" + randomAlphanumeric(5);
        driver.findElement(By.xpath("//input[contains(@name, 'name')]")).sendKeys(duckName);
        driver.findElement(By.xpath("//input[@name = 'code']")).sendKeys(randomAlphanumeric(6));
        WebElement currentCheckbox = driver.findElement(By.xpath("//input[@data-name = 'Rubber Ducks']"));
        setCheckboxValue(currentCheckbox, true);
        currentCheckbox = driver.findElement(By.xpath("//input[@data-name = 'Root']"));
        setCheckboxValue(currentCheckbox, false);
        Select categorySelect = new Select(driver.findElement(By.xpath("//select[@name = 'default_category_id']")));
        categorySelect.selectByVisibleText("Rubber Ducks");
        currentCheckbox = driver.findElement(
                By.xpath("//tr[./td[contains(text(),'Unisex')]]//input[contains(@name, 'product_groups[]')]"));
        setCheckboxValue(currentCheckbox, true);
        driver.findElement(By.xpath("//input[@name = 'quantity']")).sendKeys("6");

        File duck = new File("");
        driver.findElement(By.xpath("//input[contains(@name, 'new_images')]"))
                .sendKeys(duck.getAbsolutePath() + "\\src\\test\\resources\\ninja_duck.jpg");

        driver.findElement(By.xpath("//input[@name = 'date_valid_from']")).sendKeys("11012018");
        driver.findElement(By.xpath("//input[@name = 'date_valid_to']")).sendKeys(Keys.PAGE_UP + "12312018");

        //Information
        driver.findElement(By.xpath("//a[contains(text(), 'Information')]")).click();
        Select currentSelection = new Select(driver.findElement(By.xpath("//select[@name = 'manufacturer_id']")));
        currentSelection.selectByVisibleText("ACME Corp.");
        driver.findElement(By.xpath("//input[@name = 'keywords']")).sendKeys("Test duck");
        driver.findElement(By.xpath("//input[contains(@name,'short_description')]"))
                .sendKeys("This duck was created during homework");
        driver.findElement(By.xpath("//textarea[contains(@name,'description')]"))
                .sendKeys("This duck was created during selenium training homework");

        //Prices
        driver.findElement(By.xpath("//a[contains(text(), 'Prices')]")).click();
        driver.findElement(By.xpath("//input[@name = 'purchase_price']")).sendKeys(Keys.chord(Keys.CONTROL, "a") + "15");
        Select currentCurrency = new Select(driver.findElement(By.xpath("//select[@name = 'purchase_price_currency_code']")));
        currentCurrency.selectByValue("USD");
        driver.findElement(By.xpath("//input[@name = 'prices[USD]']")).sendKeys("30");

        driver.findElement(By.xpath("//button[@name = 'save']")).click();

        //check that product was created
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog");
        assertTrue("Element should be in group \"Rubber Ducks\"",
                driver.findElements(By.xpath("//a[contains(text(), '" + duckName + "')]")).size() == 0);
        driver.findElement(By.xpath("//a[contains(text(), 'Rubber Ducks')]")).click();
        assertTrue("Can't find product \"" + duckName + "\". Element wasn't created",
                driver.findElements(By.xpath("//a[contains(text(), '" + duckName + "')]")).size() == 1);
//        driver.findElement(By.xpath("//a[contains(text(), '" + duckName + "')]")).isDisplayed();

    }

    public void setCheckboxValue(WebElement checkbox, boolean setAsSelected) {
        if (setAsSelected) {
            if (!checkbox.isSelected()){
                checkbox.click();
            }
        } else if (checkbox.isSelected()){
            checkbox.click();
        }
    }
}
