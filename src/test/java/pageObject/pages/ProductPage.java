package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class ProductPage extends Page {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void ifSizeSelectFirst() {
        if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size() > 0) {
            driver.findElements(By.cssSelector("select option")).get(1).click();
        }
    }

    public void addProduct(int count) {
        driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
        wait.until(attributeContains(driver.findElement(By.cssSelector("span.quantity")), "textContent", "" + count + ""));
    }


}
