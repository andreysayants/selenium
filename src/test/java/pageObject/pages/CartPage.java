package pageObject.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends Page {
    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public CartPage open() {
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        return this;
    }

    @FindBy(css = "td.item")
    public List<WebElement> productRows;

    public void removeProductFromCart(int row) {
        driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click(); //удаляю
        wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td.item"), row - 1));
    }
}
