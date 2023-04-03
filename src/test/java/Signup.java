import com.github.javafaker.Faker;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Signup {
    private WebDriver driver;

    @BeforeEach
    public void start() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
//        driver = new ChromeDriver();
        driver = new FirefoxDriver();
//        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    }

    @Test
    public void signupTest() {
//        Login
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = "qwerty";

//        Signup
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.xpath("//a[contains(text(),'New customers click here')]")).click();
        driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(faker.name().firstName());
        driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(faker.name().lastName());
        driver.findElement(By.cssSelector("input[name='address1']")).sendKeys(faker.address().streetAddress());
        driver.findElement(By.cssSelector("input[name='postcode']")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[name='city']")).sendKeys(faker.address().cityName());
        driver.findElement(By.cssSelector(".select2")).click();
        driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys("United States");
        driver.findElement(By.cssSelector("input.select2-search__field")).sendKeys(Keys.ENTER);
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='phone']")).sendKeys("89531111111");
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("input[name='confirmed_password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='create_account']")).click();

//        Logout
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();

//        Login
        driver.findElement(By.cssSelector("input[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("button[name='login']")).click();

//        Logout
        driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
    }

    @AfterEach
    public void stop() {
        driver.quit();
        driver = null;
    }
}
