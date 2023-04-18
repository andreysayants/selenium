package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("http://localhost/litecart/en/");
    }

    public void openProductPage() {
        driver.findElement(By.cssSelector("#box-most-popular .shadow")).click();
    }
}
