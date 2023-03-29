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

public class CountriesSortGeo {
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
    public void countriesSortGeoTest() {
//        Login
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.attributeToBe(By.id("box-apps-menu-wrapper"), "", ""));

//        Countries page
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//span[contains(text(),'Countries')]"), "", ""));

        int countriesCount = driver.findElements(By.cssSelector(".row")).size();
        List<WebElement> countries = driver.findElements(By.cssSelector(".row"));
        ArrayList<String> countriesName = new ArrayList<>();
        ArrayList<String> editCountriesName = new ArrayList<>();
        for (int i = 0; i < countriesCount; i++) {
            String name = countries.get(i).findElement(By.cssSelector("a")).getAttribute("textContent");
            String countOfZones = countries.get(i).findElements(By.cssSelector("td")).get(5).getAttribute("textContent");
            countriesName.add(name);
            if (Integer.parseInt(countOfZones) != 0)
                editCountriesName.add(name);
        }
        ArrayList<String> sortedCountriesName = (ArrayList<String>) countriesName.clone();
        Collections.sort(sortedCountriesName);
        assertEquals(sortedCountriesName, countriesName);

//        Edit Country page
        for (int i = 0; i < editCountriesName.size(); i++) {
            driver.findElement(By.xpath("//a[contains(text(),'"+ editCountriesName.get(i) +"')]")).click();
            int editCountriesCount = driver.findElements(By.cssSelector("#table-zones tr")).size();
            List<WebElement> editCountries = driver.findElements(By.cssSelector("#table-zones tr"));
            ArrayList<String> editsCountriesName = new ArrayList<>();

            for (int j = 1; j < editCountriesCount - 1; j++) {
                String editName = editCountries.get(j).findElements(By.cssSelector("td")).get(2).getAttribute("textContent");
                editsCountriesName.add(editName);
            }
            ArrayList<String> sortedEditCountriesName = (ArrayList<String>) editsCountriesName.clone();
            Collections.sort(sortedEditCountriesName);
            assertEquals(sortedEditCountriesName, editsCountriesName);
            driver.navigate().back();
        }


    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
