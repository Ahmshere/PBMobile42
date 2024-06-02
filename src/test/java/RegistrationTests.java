import config.AppiumConfig;
import helpers.EmailGenerator;
import helpers.PasswordStringGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AuthenticationScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RegistrationTests extends AppiumConfig {

    @Test
    public void registrationTestPositive(){
        ContactListScreen contactListScreen = new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField(EmailGenerator.generateEmail(7,7,3))
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();
        Assert.assertTrue(contactListScreen.isContactListActivityPresent());
    }

    @Test
    public void registrationWrongEmailTest(){
        AuthenticationScreen authenticationScreen = new SplashScreen(driver).switchToAuthenticationScreen()
                .fillEmailField("jhdjdkgc.com")
                .fillPasswordField(PasswordStringGenerator.generateRandomPassword())
                .clickByRegistrationButton();
        Assert.assertTrue(authenticationScreen.isItAuthenticationScreen());
    }
}
