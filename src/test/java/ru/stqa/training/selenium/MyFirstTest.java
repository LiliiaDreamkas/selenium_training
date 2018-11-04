package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class MyFirstTest extends TestBase {

    @Test
    public void myFirstTest() throws InterruptedException {
        driver.get("http://www.google.com/");
        Thread.sleep(30000);
        driver.findElement(By.name("q")).sendKeys("selenium");
        driver.findElement(By.name("btnK")).click();
    }

}
