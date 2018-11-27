package ru.stqa.training.selenium.tests;

import org.junit.Test;

public class WorkingWithCartTest extends TestBase {

    @Test
    public void putAndLayOutProducts() {
        putFewRandomProductsWithDefaultParametersToCart(3);
        goToCart();
        deleteAllProductsFromCart();
    }

}
