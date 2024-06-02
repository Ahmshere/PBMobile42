package screens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SplashScreen extends BaseScreen{
    @FindBy(xpath = "//*[@resource-id='com.sheygam.contactapp:id/version_text']")
    MobileElement versionText;
    public SplashScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }
    public String getCurrentVersion(){
        versionText.click();
        return versionText.getText();
    }
    public AuthenticationScreen switchToAuthenticationScreen(){
        return new AuthenticationScreen(driver);
    }
    public boolean isSplashScreenPresent(){
        return versionText.isDisplayed();
    }
    public void  waitForTheSplashScreenToDisappear(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(versionText));
    }
}
