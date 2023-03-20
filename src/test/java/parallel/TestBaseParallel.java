package parallel;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.selenium.factory.ThreadLocalSingleWebDriverPool;
import ru.stqa.selenium.factory.WebDriverPool;

public class TestBaseParallel {
    public WebDriver driver;
    public WebDriver driverE;
    public WebDriver driverF;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        WebDriverPool driverPoolCh = new ThreadLocalSingleWebDriverPool();
        WebDriverPool driverPoolF = new ThreadLocalSingleWebDriverPool();
        WebDriverPool driverPoolE = new ThreadLocalSingleWebDriverPool();

        driver = driverPoolCh.getDriver(new ChromeOptions());
        driverF = driverPoolF.getDriver(new FirefoxOptions());
        driverE = driverPoolE.getDriver(new EdgeOptions());
    }

    @AfterAll
    public static void stop() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
