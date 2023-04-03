import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class AddProduct {
    private WebDriver driver;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void addProductTest() {

        Faker faker = new Faker();
        String name = faker.name().title();

//        Login
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

//        Catalog
        driver.findElement(By.xpath("//span[contains(text(),'Catalog')]")).click();
        driver.findElements(By.cssSelector("a.button")).get(1).click();

//       Add New Product
//        General
        driver.findElement(By.cssSelector("input[name='status'][value='1']")).click();
        driver.findElement(By.cssSelector("input[name='name[en]']")).sendKeys(name);
        driver.findElement(By.cssSelector("input[name='code']")).sendKeys("AyCode");
        driver.findElement(By.cssSelector("input[type='checkbox'][value='2']")).click();
        driver.findElement(By.cssSelector("select[name='default_category_id']")).click();
        driver.findElement(By.cssSelector("option[value='2']")).click();
        driver.findElement(By.cssSelector("input[type='checkbox'][value='1-1']")).click();
        driver.findElement(By.cssSelector("input[name='quantity']")).clear();
        driver.findElement(By.cssSelector("input[name='quantity']")).sendKeys("5");
        driver.findElement(By.cssSelector("[name='sold_out_status_id']")).click();
        driver.findElement(By.cssSelector("[name='sold_out_status_id'] option[value='2']")).click();

        String path = new File("src/main/resources/product.png").getAbsolutePath();
        driver.findElement(By.cssSelector("input[type='file']")).sendKeys(path);
        driver.findElement(By.cssSelector("input[name='date_valid_from']")).sendKeys("01.04.2023");
        driver.findElement(By.cssSelector("input[name='date_valid_to']")).sendKeys("01.04.2024");

//        Information
        driver.findElement(By.cssSelector("a[href='#tab-information']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

//        просто попробовал поработать с дропдауном через селект
        Select select = new Select(driver.findElement(By.cssSelector("select[name='manufacturer_id']")));
        select.selectByIndex(1);

        driver.findElement(By.cssSelector("input[name='keywords']")).sendKeys("Aykeywords");
        driver.findElement(By.cssSelector("input[name='short_description[en]']")).sendKeys("AyShort Description");
        driver.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("AyLoooong Description");
        driver.findElement(By.cssSelector("input[name='head_title[en]']")).sendKeys("AyHead Title");
        driver.findElement(By.cssSelector("input[name='meta_description[en]']")).sendKeys("AyMeta Description");

//        Prices
        driver.findElement(By.cssSelector("a[href='#tab-prices']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        driver.findElement(By.cssSelector("input[name='purchase_price']")).clear();
        driver.findElement(By.cssSelector("input[name='purchase_price']")).sendKeys("5");
        driver.findElement(By.cssSelector("select[name='purchase_price_currency_code']")).click();
        driver.findElement(By.cssSelector("option[value='EUR']")).click();
        driver.findElement(By.cssSelector("input[name='prices[USD]']")).sendKeys("4,5");
        driver.findElement(By.cssSelector("input[name='prices[EUR]']")).sendKeys("5");
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[USD]']")).sendKeys("4,8");
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).clear();
        driver.findElement(By.cssSelector("input[name='gross_prices[EUR]']")).sendKeys("5,3");

//        Save
        driver.findElement(By.cssSelector("button[name='save']")).click();

//        Проверка товара (проверяю список на случай совпадения имен)
        boolean empty = driver.findElements(By.xpath("//a[contains(text(),'" + name + "')]")).isEmpty();
        assertFalse(empty);
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
