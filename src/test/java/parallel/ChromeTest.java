package parallel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.time.Duration;

public class ChromeTest extends TestBaseParallel {
    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driverPool = WebDriverPool.DEFAULT;
        driver = driverPool.getDriver(new ChromeOptions());
    }
    @Test
    public void chromeTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }
}