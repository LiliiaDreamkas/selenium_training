package ru.stqa.training.selenium.model;

public class Product {

    private String quantity;
    private String size;

    public String getQuantity() {
        return quantity;
    }

    public Product withQuantity(String quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getSize() {
        return size;
    }

    public Product withSize(String size) {
        this.size = size;
        return this;
    }
}
