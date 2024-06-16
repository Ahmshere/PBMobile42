package config;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.html5.Location;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class AppiumConfig {

    public static AppiumDriver<MobileElement> driver;

    @BeforeSuite
    public void setUp() throws MalformedURLException {

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "emulator-5554");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("appPackage", "com.sheygam.contactapp");
        capabilities.setCapability("appActivity", ".SplashActivity");

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium");
        capabilities.setCapability(MobileCapabilityType.APP, "C:\\PROJECTS\\APK\\contacts-android.apk");
        driver = new AppiumDriver<MobileElement>(new URL("http://localhost:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
       // setLocation(37.7749, -122.4194, 10);
    }
    public void setLocation(double latitude, double longitude, double altitude) {
        System.out.println("Setting location to Latitude: " + latitude + ", Longitude: " + longitude + ", Altitude: " + altitude);
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", latitude);
        coordinates.put("longitude", longitude);
        coordinates.put("altitude", altitude);
        driver.executeScript("mobile: shell", ImmutableMap.of("command", "am broadcast -a io.appium.settings.location --es longitude " + longitude + " --es latitude " + latitude));
        System.out.println("Location set successfully.");
    }
    @AfterSuite
    public  void tearDown(){
        driver.quit();
    }
}
