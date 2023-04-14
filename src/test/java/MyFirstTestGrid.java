import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class MyFirstTestGrid {
    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriver driverff;

    @BeforeEach
    public void start() throws MalformedURLException {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setCapability("browserVersion", "111");
        chromeOptions.setCapability("platformName", "Windows 10");
// Showing a test name instead of the session id in the Grid UI
//        chromeOptions.setCapability("se:name", "My simple test");
// Other type of metadata can be seen in the Grid UI by clicking on the
// session info or via GraphQL
//        chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444"), chromeOptions);

//        driver = new ChromeDriver();

        FirefoxOptions ffOptions = new FirefoxOptions();
        ffOptions.setCapability("platformName", "Windows 10");
        driverff = new RemoteWebDriver(new URL("http://127.0.0.1:4444"), ffOptions);

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @Test
    public void myFirstTest2() {
        driverff.get("https://www.google.com/");
        driverff.findElement(By.name("q")).sendKeys("webdriver");
        driverff.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
