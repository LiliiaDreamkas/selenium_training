package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;

public class LoginTest extends TestBase {

    @Test
    public void loginTest() {
        loginAsAdmin();
    }
}
