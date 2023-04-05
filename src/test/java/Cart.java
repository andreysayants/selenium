import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.openqa.selenium.support.ui.ExpectedConditions.attributeContains;

public class Cart {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
//        driver = new EdgeDriver();
//        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));

    }

    @Test
    public void addProductTest() {
        driver.get("http://localhost/litecart/en/");

//       Add Product
        for (int i = 1; i < 4; i++) {
            driver.findElement(By.cssSelector("#box-most-popular .shadow")).click();
            if (driver.findElements(By.cssSelector("select[name='options[Size]']")).size()>0){
                driver.findElements(By.cssSelector("select option")).get(1).click();
            }
            driver.findElement(By.cssSelector("button[name='add_cart_product']")).click();
            wait.until(attributeContains(driver.findElement(By.cssSelector("span.quantity")), "textContent", ""+ i +"" ));
            driver.navigate().back();
        }
//        Remove Product
        driver.findElement(By.cssSelector("div#cart a.link")).click();
        driver.findElement(By.cssSelector("td.item")); //проверяю, что в списке появились товары

        for (int i = 2; i > 0; i--) {
            driver.findElement(By.cssSelector("button[name='remove_cart_item']")).click(); //удаляю
            wait.until(ExpectedConditions.numberOfElementsToBe(By.cssSelector("td.item"), i)); //проверяют, что список с товарами обновился
        }



    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
