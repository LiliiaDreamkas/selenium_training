package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

public class NewCustomRegistrationTest extends TestBase {

    @Test
    public void createNewAccountTest() {
        //create user
        driver.get("http://localhost/litecart/en/create_account");
        driver.findElement(By.cssSelector("input[name = firstname]")).sendKeys("Test FirstName");
        driver.findElement(By.cssSelector("input[name = lastname]")).sendKeys("Test LastName");
        driver.findElement(By.cssSelector("input[name = address1]")).sendKeys("Test Address 1");
        driver.findElement(By.cssSelector("input[name = postcode]")).sendKeys("12345-6789");
        driver.findElement(By.cssSelector("input[name = city]")).sendKeys("Sacramento");
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name = country_code]")));
        countrySelect.selectByValue("US");
        Select zoneSelect = new Select(driver.findElement(By.cssSelector("select[name = zone_code]")));
        zoneSelect.selectByValue("CA");
        String userEmail = randomAlphabetic(7)  + "@test.ru";
        String userPassword = "password";
        driver.findElement(By.cssSelector("input[name = email]")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[name = phone]")).sendKeys("+123456789");
        driver.findElement(By.cssSelector("input[name = password]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("input[name = confirmed_password]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("button[name = create_account]")).click();

        //logout
        driver.findElement(By.xpath("//div[@id = 'box-account']//a[contains(., 'Logout')]")).click();

        //login
        driver.findElement(By.cssSelector("input[name = email]")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[name = password]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("button[name = login]")).click();

        //logout
        driver.findElement(By.xpath("//div[@id = 'box-account']//a[contains(., 'Logout')]")).click();
    }

    @Test
    public void createNewAccountTestFireFox() {
        //create user
        driver.get("http://localhost/litecart/en/create_account");
        driver.findElement(By.cssSelector("input[name = firstname]")).sendKeys("Test FirstName");
        driver.findElement(By.cssSelector("input[name = lastname]")).sendKeys("Test LastName");
        driver.findElement(By.cssSelector("input[name = address1]")).sendKeys("Test Address 1");
        driver.findElement(By.cssSelector("input[name = postcode]")).sendKeys("12345-6789");
        driver.findElement(By.cssSelector("input[name = city]")).sendKeys("Sacramento");
        driver.findElement(By.cssSelector("span.select2-selection__arrow")).click();
        driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys("United St");
        driver.findElement(By.xpath("//li[contains(text(), 'United States')]")).click();
        Select countrySelect = new Select(driver.findElement(By.cssSelector("select[name = country_code]")));
        countrySelect.selectByValue("US");
        Select zoneSelect = new Select(driver.findElement(By.cssSelector("select[name = zone_code]")));
        zoneSelect.selectByValue("CA");
        String userEmail = randomAlphabetic(7)  + "@test.ru";
        String userPassword = "password";
        driver.findElement(By.cssSelector("input[name = email]")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[name = phone]")).sendKeys("+123456789");
        driver.findElement(By.cssSelector("input[name = password]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("input[name = confirmed_password]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("button[name = create_account]")).click();

        //logout
        driver.findElement(By.xpath("//div[@id = 'box-account']//a[contains(., 'Logout')]")).click();

        //login
        driver.findElement(By.cssSelector("input[name = email]")).sendKeys(userEmail);
        driver.findElement(By.cssSelector("input[name = password]")).sendKeys(userPassword);
        driver.findElement(By.cssSelector("button[name = login]")).click();

        //logout
        driver.findElement(By.xpath("//div[@id = 'box-account']//a[contains(., 'Logout')]")).click();
    }
}
