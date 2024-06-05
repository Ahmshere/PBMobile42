import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screenactions.ScreenshotUtil;
import screens.ContactListScreen;
import screens.SplashScreen;

public class LoginTest extends AppiumConfig {

    @Test
    public void loginPositiveTest(){

        ContactListScreen contactListScreen = new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        ScreenshotUtil screenshotUtil = new ScreenshotUtil(driver);
        screenshotUtil.takeScreenShot("TestMethod");

        Assert.assertTrue(contactListScreen.isContactListActivityPresent());
    }
}
