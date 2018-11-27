package ru.stqa.training.selenium;

import org.junit.Test;

public class WorkingWithCartTest extends TestBase {

    @Test
    public void putAndLayOutProducts() {
        putFewRandomProductsWithDefaultParametersToCart(3);
        goToCart();
        deleteAllProductsFromCart();
    }

}
