package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.Product;
import ru.stqa.training.selenium.pages.CartPage;
import ru.stqa.training.selenium.pages.MainPage;
import ru.stqa.training.selenium.pages.ProductPage;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;
    public MainPage mainPage;
    public ProductPage productPage;
    public CartPage cartPage;

    @Before
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            mainPage = new MainPage(driver, wait);
            productPage = new ProductPage(driver, wait);
            cartPage = new CartPage(driver, wait);
            return;
        }
        driver = new ChromeDriver();
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setCapability("firefox_binary", "C:\\Program Files\\Firefox Nightly\\firefox.exe");
//        driver = new FirefoxDriver(caps);
//        driver = new InternetExplorerDriver();
//        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        tlDriver.set(driver);
        wait = new WebDriverWait(driver, 10);

        mainPage = new MainPage(driver, wait);
        productPage = new ProductPage(driver, wait);
        cartPage = new CartPage(driver, wait);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> { driver.quit(); driver = null; }));
    }

    @After
    public void stop() {
//        driver.quit();
//        driver = null;
    }

    protected void loginAsAdmin() {
        driver.get("http://localhost/litecart/admin/");
        List<WebElement> loginForm = driver.findElements(By.cssSelector("form[name = login_form]"));
        if (loginForm.size() > 0) {
            driver.findElement(By.name("username")).sendKeys("admin");
            driver.findElement(By.name("password")).sendKeys("admin");
            driver.findElement(By.name("login")).click();
        }
    }

    public void putFewRandomProductsWithDefaultParametersToCart(int productsCount) {
        for (int i = 0; i < productsCount; i++) {
            mainPage.open();
            mainPage.clickOnRandomProduct();
            Product product = new Product().withQuantity("1").withSize("Small");
            productPage.putProductToCart(product);
        }
    }

    public void deleteAllProductsFromCart() {
        int productCount = cartPage.getProductCount();
        for (int i = 0; i < productCount; i++) {
            cartPage.deleteRandomProduct();
        }
    }

    public void goToCart() {
        cartPage.goToCart();
    }
}
