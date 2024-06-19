import config.AppiumConfig;
import enums.ContactField;
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
                .submitContact(true);
        cls.isContactAddedScroll(contact);
    }
    @Test
    public void editAnyContactFieldPositive(){
        String newtext = "testmail3@mama.com";
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        new ContactListScreen(driver)
                .editContact(0)
                .editField(ContactField.EMAIL, newtext)
                .submitEditContact()
                .isContactContainsText(newtext, 0);

    }

}
