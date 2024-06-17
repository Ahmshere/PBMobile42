package screens;

import helpers.ContactGenerator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ContactListScreen extends BaseScreen {
    public ContactListScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/action_bar']/android.widget.TextView")
    MobileElement titleViewText;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/add_contact_btn']")
    MobileElement addContactButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowName']")
    List<MobileElement> rowName;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowPhone']")
    List<MobileElement> rowPhone;

    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/rowContainer']")
    List<MobileElement> contacts;
    @FindBy(xpath = "//*[@resource-id='android:id/button1']")
    MobileElement yesButton;
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/emptyTxt']")
    MobileElement emptyContactListText;

    String phoneNumber;
    public boolean isContactListActivityPresent(){
       return isElementPresent(titleViewText, "Contact list", 5);
    }

    public AddNewContactScreen openNewContactForm(){
        waitForAnElement(addContactButton);
        addContactButton.click();
        return new AddNewContactScreen(driver);
    }

    public boolean isContactAdded(Contact contact){
        boolean checkName = checkContainsText(rowName, contact.getName());
        boolean checkPhone = checkContainsText(rowPhone, contact.getPhone());
        return checkName && checkPhone;
    }
    public boolean checkContainsText(List<MobileElement> list, String text){
        for(MobileElement mobileElement : list){
            if(mobileElement.getText().contains(text)){return true;}
        }
        return false;
    }

    public ContactListScreen removeAContact() {
        waitForAnElement(addContactButton);
        MobileElement contact = contacts.get(0);
        phoneNumber = rowPhone.get(0).getText();

        Rectangle rectangle = contact.getRect();
        int startX = rectangle.getX()+ rectangle.getWidth()/8;
        int y = rectangle.getY()+ rectangle.getHeight()/2;
        int endX = startX + rectangle.getWidth()*6/8 ;
        new TouchAction<>(driver)
                .longPress(PointOption.point(startX,y))
                .moveTo(PointOption.point(endX,y))
                .release()
                .perform();
        if(isElementPresent(yesButton, "YES", 5)){
            yesButton.click();
        }
        return this;

    }
    public boolean isContactRemoved(){
        return !rowPhone.contains(phoneNumber);
    }
    public boolean isContactRemovedList(String name, String lastName, String phone){
        boolean nameChecked = checkContainsText(rowName, name+" "+lastName);
        boolean phoneChecked = checkContainsText(rowPhone, phone);
        return !(nameChecked && phoneChecked);
    }

    public ContactListScreen addMultipleContacts(int theNumberOfContactsToBeAdded) {
        for (int iteration=0; iteration< theNumberOfContactsToBeAdded;iteration++){
            Contact contact = ContactGenerator.createCorrectContact();
            ContactListScreen cls = new ContactListScreen(driver)
                    .openNewContactForm()
                    .fillTheForm(contact)
                    .submitContact();
            cls.isContactAdded(contact);
        }
        return this;
    }

    public ContactListScreen removeAllContacts() {
        waitForAnElement(addContactButton);
        while (contacts.size()>0){
            removeAContact();
        }
        return this;
    }
    public boolean isNoContactsMessage(){
        return isElementPresent(emptyContactListText, "No Contacts", 5);
    }

    //********
    public ContactListScreen scrolling(){
        waitForAnElement(addContactButton);
        MobileElement contact = contacts.get(contacts.size()-1);

        Rectangle rectangle = contact.getRect();
        int x = rectangle.getX()+ rectangle.getWidth()/2;
        int y = rectangle.getY()+ rectangle.getHeight()/2;

        new TouchAction<>(driver)
                .longPress(PointOption.point(x,y))
                .moveTo(PointOption.point(x, 0))
                .release()
                .perform();
        return this;
    }
    private boolean isThisTheEndOfTheList(){
        String beforeScroll = getLastContactText();
        scrolling();
        String afterScroll = getLastContactText();
        return beforeScroll.equals(afterScroll);

    }
    private String getLastContactText(){
        return rowName.get(rowName.size()-1).getText()+" "+rowPhone.get(rowPhone.size()-1).getText();
    }
    public  void scrollDown(){
        waitForAnElement(addContactButton);
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }
    public boolean isContactAddedScroll(Contact contact) {
        boolean result = false;
        while (!result && !isThisTheEndOfTheList()){
            boolean checkName = checkContainsText(rowName, contact.getName());
            boolean checkPhone = checkContainsText(rowPhone, contact.getPhone());
            result = checkName && checkPhone;
            if(!result){
                //scrollDown();
                scrolling();
            }
        }
        return result;
    }
}
