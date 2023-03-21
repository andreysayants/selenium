package parallel;

import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.WebDriver;
import ru.stqa.selenium.factory.WebDriverPool;

public class TestBaseParallel {
    public WebDriver driver;
    public WebDriverPool driverPool;

    @AfterAll
    public static void stop() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
