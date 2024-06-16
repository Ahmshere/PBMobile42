import config.AppiumConfig;
import enums.ContactField;
import helpers.*;
import models.Contact;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.AddNewContactScreen;
import screens.ContactListScreen;
import screens.SplashScreen;

public class AddNewContactTests extends AppiumConfig {

    @Test
    public void addNewContactPositive(){
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        Contact contact = new Contact();
        contact.setName(NameAndLastNameGenerator.generateName());
        contact.setLastName(NameAndLastNameGenerator.generateLastName());
        contact.setEmail(EmailGenerator.generateEmail(7,5,3));
        contact.setPhone(PhoneNumberGenerator.generatePhoneNumber());
        contact.setAddress(AddressGenerator.generateAddress());
        contact.setDescription("test");

        ContactListScreen cls =  new ContactListScreen(driver).openNewContactForm()
                .fillTheForm(contact)
                .submitContact();
               Assert.assertTrue(cls.isContactAdded(contact));
    }
    @Test
    public void addContactNegative(){
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        Contact contact = ContactGenerator.createIncorrectContact(ContactField.PHONE_NUMBER, "1");
        AddNewContactScreen addNewContactScreen = new ContactListScreen(driver)
                .openNewContactForm()
                .fillTheForm(contact)
                .submitContact();
        Assert.assertTrue(addNewContactScreen.isThisTheAddNewContactScreen());

    }
    @Test
    public void addNewContacts(){
        new SplashScreen(driver)
                .switchToAuthenticationScreen()
                .fillEmailField("mymegamail@mail.com")
                .fillPasswordField("MyPassword123!")
                .clickLoginButton();
        new ContactListScreen(driver).addMultipleContacts(3);
    }

}
