package pageObject.app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageObject.pages.CartPage;
import pageObject.pages.MainPage;
import pageObject.pages.ProductPage;

public class Application {

    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;


    public Application() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);

    }

    public void stop() {
        driver.quit();
        driver = null;
    }

    public void removeProducts() {
        for (int i = cartPage.open().productRows.size(); i > 0; i--) {
            cartPage.removeProductFromCart(i);
        }
    }

    public void addProducts() {
        for (int i = 1; i < 4; i++) {
            mainPage.openProductPage();
            productPage.ifSizeSelectFirst();
            productPage.addProduct(i);
            driver.navigate().back();
        }
    }

    public void openMainPage() {
        mainPage.open();
    }

}
