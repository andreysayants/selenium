package parallel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EdgeTest extends TestBaseParallel {
    @Test
    public void edgeTest() {
        driverE.get("https://www.google.com/");
        driverE.findElement(By.name("q")).sendKeys("webdriver");
        driverE.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driverE, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }
}
