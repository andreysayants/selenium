import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyStoreAdminDifferentBrowsers {
    private static WebDriver driverCh;
    private static WebDriver driverF;
    private static WebDriver driverEdge;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");

        if (driverCh != null || driverF != null || driverEdge != null)
            return;

        driverCh = new ChromeDriver();
        driverF = new FirefoxDriver();
        driverEdge = new EdgeDriver();

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driverCh.quit();
                    driverCh = null;
                })
        );

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driverF.quit();
                    driverF = null;
                })
        );

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> {
                    driverEdge.quit();
                    driverEdge = null;
                })
        );
    }

    @Test
    public void loginTestChrome() {
        driverCh.get("http://localhost/litecart/admin/");
        driverCh.findElement(By.name("username")).sendKeys("admin");
        driverCh.findElement(By.name("password")).sendKeys("admin");
        driverCh.findElement(By.name("login")).click();

    }

    @Test
    public void loginTestFireFox() {
        driverF.get("http://localhost/litecart/admin/");
        driverF.findElement(By.name("username")).sendKeys("admin");
        driverF.findElement(By.name("password")).sendKeys("admin");
        driverF.findElement(By.name("login")).click();

    }

    @Test
    public void loginTestEdge() {
        driverEdge.get("http://localhost/litecart/admin/");
        driverEdge.findElement(By.name("username")).sendKeys("admin");
        driverEdge.findElement(By.name("password")).sendKeys("admin");
        driverEdge.findElement(By.name("login")).click();

    }

    @AfterEach
    public void stop() {

    }
}
