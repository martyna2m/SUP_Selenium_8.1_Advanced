package tests.checkout;

import org.junit.jupiter.api.Test;
import pages.account.LogInPage;
import tests.base.TestBase;

public class CheckOutTest extends TestBase {
    @Test
    public void checkOutTest() {
        openPage("loginPage");
        at(LogInPage.class).logIn("alex@hamilton.com", "password"); // YAML!!!!!!


    }
}
