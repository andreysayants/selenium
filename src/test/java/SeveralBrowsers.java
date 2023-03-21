import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeveralBrowsers {
    private static WebDriver driver;
    private static WebDriver driverE;
    private static WebDriver driverF;
    private static WebDriver driverO;
    private static WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        if (driver != null && driverE != null && driverF != null && driverO != null)
            return;
        driver = new ChromeDriver();
        driverF = new FirefoxDriver();
        driverO = new OperaDriver();
        driverE = new EdgeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driver.quit(); driver = null;})
        );
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driverF.quit(); driver = null;})
        );
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driverO.quit(); driver = null;})
        );
        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {driverE.quit(); driver = null;})
        );
    }

    @Test
    public void edgeTest() {
        driverE.get("https://www.google.com/");
        driverE.findElement(By.name("q")).sendKeys("webdriver");
        driverE.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void firefoxTest() {
        driverF.get("https://www.google.com/");
        driverF.findElement(By.name("q")).sendKeys("webdriver");
        driverF.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void operaTest() {
        driverO.get("https://www.google.com/");
        driverO.findElement(By.name("q")).sendKeys("webdriver");
        driverO.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @AfterEach
    public void stop() {
/*        driver.quit();
        driver = null;

        driverE.quit();
        driverE = null;

        driverF.quit();
        driverF = null;

        driverO.quit();
        driverO = null;*/
    }
}
