import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.opera.OperaOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.time.Duration;

public class SeveralBrowsersParall {
    private WebDriver driver;
    private WebDriver driverE;
    private WebDriver driverF;
    private WebDriver driverO;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = WebDriverPool.DEFAULT.getDriver(new ChromeOptions());
        driverF = WebDriverPool.DEFAULT.getDriver(new FirefoxOptions());
        driverO = WebDriverPool.DEFAULT.getDriver(new OperaOptions());
        driverE = WebDriverPool.DEFAULT.getDriver(new EdgeOptions());
    }

    @Test
    public void edgeTest() {
        driverE.get("https://www.google.com/");
        driverE.findElement(By.name("q")).sendKeys("webdriver");
        driverE.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driverE, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void chromeTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void fireFoxTest() {
        driverF.get("https://www.google.com/");
        driverF.findElement(By.name("q")).sendKeys("webdriver");
        driverF.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driverF, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void operaTest() {
        driverO.get("https://www.google.com/");
        driverO.findElement(By.name("q")).sendKeys("webdriver");
        driverO.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driverO, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @AfterAll
    public static void stop() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
