import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.HasCapabilities;

import java.time.Duration;

public class CapabilitiesLesson {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-fullscreen");
       // options.setCapability("virtualAuthenticators", false);

        driver = new ChromeDriver(options);
        System.out.println(((HasCapabilities) driver).getCapabilities());


        //driver = new EdgeDriver();
        //driver = new FirefoxDriver();
        //driver = new OperaDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com/");
        driver.findElement(By.name("q")).sendKeys("webdriver");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        wait.until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
