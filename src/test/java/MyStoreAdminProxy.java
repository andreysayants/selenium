import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogEntry;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MyStoreAdminProxy {
    private WebDriver driver;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

//        Чтобы уронить тест можно повысить уровень протоколирования
//        LoggingPreferences prefs = new LoggingPreferences();
//        prefs.enable("browser", Level.ALL);
//        ChromeOptions options = new ChromeOptions();
//        options.setCapability("goog:loggingPrefs", prefs);
//        driver = new ChromeDriver(options);

//        Proxy
        String proxy = "127.0.0.1:8888"; //Fiddler использует по умолчанию 127.0.0.1:8888
        ChromeOptions options = new ChromeOptions().addArguments("--proxy-server=http://" + proxy);
        driver = new ChromeDriver(options);

//        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void proxyTest() {
//        Login
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

//        Catalog
        driver.get("http://localhost/litecart/admin/?app=catalog&doc=catalog&category_id=1");
        List<WebElement> count = driver.findElements(By.xpath("//*[@style='text-align: right;']/following-sibling::td[a]"));
        for (int i = 0; i < count.size(); i++) {
            List<WebElement> rows = driver.findElements(By.xpath("//*[@style='text-align: right;']/following-sibling::td[a]"));
            rows.get(i).click();
            List<LogEntry> logs = driver.manage().logs().get("browser").getAll();
            logs.forEach(System.out::println);
            assertTrue(logs.isEmpty());
            driver.navigate().back();

        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
