import config.AppiumConfig;
import helpers.*;
import models.Contact;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class RemoveContactTest extends AppiumConfig {

    @Test
    public void removeContactPositive(){
        ContactListScreen contactListScreen = new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        Contact contact = ContactGenerator.createCorrectContact();

        contactListScreen.openNewContactForm().fillTheForm(contact).submitContact();
        contactListScreen.removeAContact();
    }
}
