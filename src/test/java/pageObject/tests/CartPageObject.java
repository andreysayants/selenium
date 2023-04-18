package pageObject.tests;

import org.junit.jupiter.api.Test;

public class CartPageObject extends TestBase {

    @Test
    public void cartTest() {
        app.openMainPage();
        app.addProducts();
        app.removeProducts();
        app.stop();
    }

}
