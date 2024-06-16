import config.AppiumConfig;
import helpers.ContactGenerator;
import models.Contact;
import org.testng.annotations.Test;
import screens.ContactListScreen;
import screens.SplashScreen;

public class EditContactTests extends AppiumConfig {

    @Test
    public void editAnyContactPositive(){
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        Contact contact = ContactGenerator.createCorrectContact();
        ContactListScreen cls = new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .submitContact();
        cls.isContactAddedScroll(contact);


    }

}
