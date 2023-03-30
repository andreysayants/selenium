import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CorrectDuck {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
//        driver = new FirefoxDriver();
//        driver = new EdgeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
        wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void correctDuckTest() {
        driver.get("http://localhost/litecart/en/");
//        wait.until(ExpectedConditions.attributeToBe(By.cssSelector("#box-most-popular"), "id", "box-most-popular"));

//        MainPage
        WebElement product = driver.findElement(By.cssSelector("#box-campaigns .product"));

//            а) на главной странице и на странице товара совпадает текст названия товара
//            б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        String mainPageTitle = product.findElement(By.cssSelector("a .name")).getAttribute("textContent");
        String mainPageRegularPrice = product.findElement(By.cssSelector("a .regular-price")).getAttribute("textContent");
        String mainPageCampaignPrice = product.findElement(By.cssSelector("a .campaign-price")).getAttribute("textContent");


//            в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        String mainPageGrey = product.findElement(By.cssSelector("a .regular-price")).getCssValue("color");
        String[] mainPageArrGrey = mainPageGrey.replaceAll("[^\\d ]", "").split(" ");
        assertEquals(mainPageArrGrey[0], mainPageArrGrey[1]);
        assertEquals(mainPageArrGrey[1], mainPageArrGrey[2]);

        String mainPageTextDecoration = product.findElement(By.cssSelector("a .regular-price")).getCssValue("text-decoration");
        assertTrue(mainPageTextDecoration.contains("line-through"));

//            г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        String mainPageBold = product.findElement(By.cssSelector("a .campaign-price")).getCssValue("font-weight");
        boolean mainPageBoldResult = Integer.parseInt(mainPageBold) >= 700;
        assertTrue(mainPageBoldResult);

        String mainPageRed = product.findElement(By.cssSelector("a .campaign-price")).getCssValue("color");
        String[] mainPageArrRed = mainPageRed.replaceAll("[^\\d ]", "").split(" ");
        assertEquals(0, Integer.parseInt(mainPageArrRed[1]));
        assertEquals(mainPageArrRed[1], mainPageArrRed[2]);


//            д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        String mainPageRegularSize = product.findElement(By.cssSelector("a .regular-price")).getCssValue("font-size");
        String mainPageCampaignSize = product.findElement(By.cssSelector("a .campaign-price")).getCssValue("font-size");
        String valueMainPageRegularSize = mainPageRegularSize.replaceAll("[^\\d \\.]", "");
        String valueMainPageCampaignSize = mainPageCampaignSize.replaceAll("[^\\d \\.]", "");
        boolean mainPageSizeResult = Double.parseDouble(valueMainPageCampaignSize) > Double.parseDouble(valueMainPageRegularSize);
        assertTrue(mainPageSizeResult);

//        DuckPage
        product.click();
//        wait.until(ExpectedConditions.attributeToBe(By.id("box-product"), "id", "box-product"));
        WebElement duck = driver.findElement(By.id("box-product"));

//            а) на главной странице и на странице товара совпадает текст названия товара
//            б) на главной странице и на странице товара совпадают цены (обычная и акционная)
        String duckPageTitle = duck.findElement(By.cssSelector("h1")).getAttribute("textContent");
        String duckPageRegularPrice = duck.findElement(By.cssSelector(".regular-price")).getAttribute("textContent");
        String duckPageCampaignPrice = duck.findElement(By.cssSelector(".campaign-price")).getAttribute("textContent");

        assertEquals(mainPageTitle, duckPageTitle);
        assertEquals(mainPageRegularPrice, duckPageRegularPrice);
        assertEquals(mainPageCampaignPrice, duckPageCampaignPrice);

//            в) обычная цена зачёркнутая и серая (можно считать, что "серый" цвет это такой, у которого в RGBa представлении одинаковые значения для каналов R, G и B)
        String duckPageGrey = duck.findElement(By.cssSelector(".regular-price")).getCssValue("color");
        String[] duckPageArrGrey = duckPageGrey.replaceAll("[^\\d ]", "").split(" ");
        assertEquals(duckPageArrGrey[0], duckPageArrGrey[1]);
        assertEquals(duckPageArrGrey[1], duckPageArrGrey[2]);

        String duckPageTextDecoration = duck.findElement(By.cssSelector(".regular-price")).getCssValue("text-decoration");
        assertTrue(duckPageTextDecoration.contains("line-through"));

//            г) акционная жирная и красная (можно считать, что "красный" цвет это такой, у которого в RGBa представлении каналы G и B имеют нулевые значения)
        String duckPageBold = duck.findElement(By.cssSelector(".campaign-price")).getCssValue("font-weight");
        boolean duckPageBoldResult = Integer.parseInt(duckPageBold) >= 700;
        assertTrue(duckPageBoldResult);

        String duckPageRed = duck.findElement(By.cssSelector(".campaign-price")).getCssValue("color");
        String[] duckPageArrRed = duckPageRed.replaceAll("[^\\d ]", "").split(" ");
        assertEquals(0, Integer.parseInt(duckPageArrRed[1]));
        assertEquals(duckPageArrRed[1], duckPageArrRed[2]);


//            д) акционная цена крупнее, чем обычная (это тоже надо проверить на каждой странице независимо)
        String duckPageRegularSize = duck.findElement(By.cssSelector(".regular-price")).getCssValue("font-size");
        String duckPageCampaignSize = duck.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size");
        String valueDuckPageRegularSize = duckPageRegularSize.replaceAll("[^\\d \\.]", "");
        String valueDuckPageCampaignSize = duckPageCampaignSize.replaceAll("[^\\d \\.]", "");
        boolean duckPageSizeResult = Double.parseDouble(valueDuckPageCampaignSize) > Double.parseDouble(valueDuckPageRegularSize);
        assertTrue(duckPageSizeResult);

    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
