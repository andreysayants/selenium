package pageObject.tests;

import org.junit.jupiter.api.BeforeEach;
import pageObject.app.Application;

public class TestBase {
    public Application app;

    @BeforeEach
    public void start() {
        app = new Application();
    }
}
