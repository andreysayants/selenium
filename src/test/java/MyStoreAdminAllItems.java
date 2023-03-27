import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyStoreAdminAllItems {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Test
    public void allItemsTest() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(ExpectedConditions.attributeToBe(By.id("box-apps-menu-wrapper"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Appearence')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Template')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Template')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Template')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Logotype')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Logotype')]"), "", ""));

        driver.findElements(By.xpath("//span[contains(text(),'Catalog')]")).get(0).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Catalog')]"), "", ""));
        driver.findElements(By.xpath("//span[contains(text(),'Catalog')]")).get(1).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Catalog')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Product Groups')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Product Groups')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Option Groups')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Option Groups')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Manufacturers')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Manufacturers')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Suppliers')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Suppliers')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Delivery Statuses')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Delivery Statuses')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Sold Out Statuses')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Sold Out Statuses')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Quantity Units')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Quantity Units')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'CSV Import/Export')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'CSV Import/Export')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Countries')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Countries')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Currencies')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Currencies')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Customers')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Customers')]"), "", ""));
        driver.findElement(By.id("doc-customers")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Customers')]"), "", ""));
        driver.findElement(By.id("doc-csv")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'CSV Import/Export')]"), "", ""));
        driver.findElement(By.id("doc-newsletter")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Newsletter')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Geo Zones')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Geo Zones')]"), "", ""));

        driver.findElements(By.xpath("//span[contains(text(),'Languages')]")).get(0).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Languages')]"), "", ""));
        driver.findElements(By.xpath("//span[contains(text(),'Languages')]")).get(1).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Languages')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Storage Encoding')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Storage Encoding')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Modules')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Job Modules')]"), "", ""));
        driver.findElement(By.id("doc-jobs")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Job Modules')]"), "", ""));
        driver.findElement(By.id("doc-customer")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Customer Modules')]"), "", ""));
        driver.findElement(By.id("doc-shipping")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Shipping Modules')]"), "", ""));
        driver.findElement(By.id("doc-payment")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Payment Modules')]"), "", ""));
        driver.findElement(By.id("doc-order_total")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Order Total Modules')]"), "", ""));
        driver.findElement(By.id("doc-order_success")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Order Success Modules')]"), "", ""));
        driver.findElement(By.id("doc-order_action")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Order Action Modules')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Orders')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Orders')]"), "", ""));
        driver.findElement(By.id("doc-orders")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Orders')]"), "", ""));
        driver.findElement(By.id("doc-order_statuses")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Order Statuses')]"), "", ""));


        driver.findElement(By.xpath("//span[contains(text(),'Pages')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Pages')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Reports')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Monthly Sales')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Monthly Sales')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Monthly Sales')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Most Sold Products')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Most Sold Products')]"), "", ""));
        driver.findElement(By.xpath("//span[contains(text(),'Most Shopping Customers')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Most Shopping Customers')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Settings')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-store_info")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-defaults")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-general")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-listings")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-images")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-checkout")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-advanced")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));
        driver.findElement(By.id("doc-security")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Settings')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Slides')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Slides')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Tax')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Tax Classes')]"), "", ""));
        driver.findElement(By.id("doc-tax_classes")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Tax Classes')]"), "", ""));
        driver.findElement(By.id("doc-tax_rates")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Tax Rates')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Translations')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Search Translations')]"), "", ""));
        driver.findElement(By.id("doc-search")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Search Translations')]"), "", ""));
        driver.findElement(By.id("doc-scan")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Scan Files For Translations')]"), "", ""));
        driver.findElement(By.id("doc-csv")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'CSV Import/Export')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'Users')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'Users')]"), "", ""));

        driver.findElement(By.xpath("//span[contains(text(),'vQmods')]")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'vQmods')]"), "", ""));
        driver.findElement(By.id("doc-vqmods")).click();
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//h1[contains(text(),'vQmods')]"), "", ""));
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
