package ru.stqa.training.selenium.tests;

import org.junit.Test;

public class LoginTest extends TestBase {

    @Test
    public void loginTest() {
        loginAsAdmin();
    }
}
