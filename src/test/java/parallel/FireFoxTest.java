package parallel;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FireFoxTest extends TestBaseParallel {
    @Test
    public void fireFoxTest() {
        driverF.get("https://www.google.com/");
        driverF.findElement(By.name("q")).sendKeys("webdriver");
        driverF.findElement(By.name("q")).sendKeys(Keys.ENTER);
        new WebDriverWait(driverF, Duration.ofSeconds(10)).until(ExpectedConditions.titleContains("webdriver - Поиск в Google"));
    }
}
