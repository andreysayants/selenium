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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortGeoZones {
    private WebDriver driver;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
    }

    @Test
    public void sortGeoZonesTest() {
//        Login
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

//        Geo Zones page
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");

//        Edit Geo Zone page

        int countriesCount = driver.findElements(By.cssSelector(".row")).size();
        for (int i = 0; i < countriesCount; i++) {
            List<WebElement> countries = driver.findElements(By.cssSelector(".row"));
            countries.get(i).findElement(By.cssSelector("a")).click();
            List<WebElement> dropdowns = driver.findElements(By.cssSelector("[name *= zone_code]"));
            int dropdownCount = dropdowns.size();
            ArrayList<String> dropdownNames = new ArrayList<>();

            for (int k = 0; k < dropdownCount; k++) {
                String name = dropdowns.get(k).findElement(By.cssSelector("[selected='selected']")).getAttribute("textContent");
                dropdownNames.add(name);
            }
            ArrayList<String> sortedDropDownNames = (ArrayList<String>) dropdownNames.clone();
            Collections.sort(sortedDropDownNames);
            assertEquals(sortedDropDownNames, dropdownNames);
            driver.navigate().back();
        }
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
