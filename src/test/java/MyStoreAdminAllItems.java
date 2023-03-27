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

public class MyStoreAdminAllItems {
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
    public void allItemsTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.attributeToBe(By.id("box-apps-menu-wrapper"), "", ""));

        int sizeMenu = driver.findElements(By.cssSelector("#app-")).size();
        for (int i = 0; i < sizeMenu; i++) {
            List<WebElement> items = driver.findElements(By.cssSelector("#app-"));
            WebElement item = items.get(i);
            item.click();
            wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1"), "", ""));
            int sizeSubitems = driver.findElements(By.xpath("//li[contains(@id, 'doc-')]")).size();
            for (int j = 0; j < sizeSubitems; j++) {
                List<WebElement> subitems = driver.findElements(By.xpath("//li[contains(@id, 'doc-')]"));
                WebElement subitem = subitems.get(j);
                subitem.click();
                wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1"), "", ""));
            }
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
