package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;
import models.Contact;
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
}
