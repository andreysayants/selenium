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
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class NewWindows {
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
    public void newWindowsTest() {
        //        Login
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

//        Countries
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.cssSelector("a.button")).click();

//       Add New Country
        String originalWindow = driver.getWindowHandle();
        Set<String> existingWindows = driver.getWindowHandles();
        List<WebElement> urls = driver.findElements(By.cssSelector("td#content a[target='_blank']"));

        for (int i = 1; i < urls.size(); i++) {
            urls.get(i).click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> newWindows = driver.getWindowHandles();
            newWindows.removeAll(existingWindows);
            driver.switchTo().window(newWindows.toString().replaceAll("\\[", "").replaceAll("]", ""));
            driver.close();
            driver.switchTo().window(originalWindow);

        }

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
