import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Stickers {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void stickersTest() {
        driver.get("http://localhost/litecart/en/");
        wait.until(ExpectedConditions.attributeToBe(By.id("box-most-popular"), "", ""));

        int duckCount = driver.findElements(By.cssSelector(".product")).size();
        List<WebElement> items = driver.findElements(By.cssSelector(".product"));
        for (int i = 0; i < duckCount; i++) {
            int sticker = items.get(i).findElements(By.cssSelector(".sticker")).size();
            assertEquals(1, sticker);
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
