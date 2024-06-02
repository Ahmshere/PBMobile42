import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Test;
import screens.SplashScreen;

public class LaunchAppTest extends AppiumConfig {

    @Test
    public void launchTest() {
        long expectedTime = 5000;
        String version = new SplashScreen(driver).getCurrentVersion();
        Assert.assertTrue(version.contains("Version 1.0.0"));
    }

    @Test
    public void splashScreenTimeTest() {
        long expectedTime = 5000;
        SplashScreen splashScreen = new SplashScreen(driver);
        long startTime = System.currentTimeMillis();
        Assert.assertTrue(splashScreen.isSplashScreenPresent(), "The splashscreen is not displayed...");
        splashScreen.waitForTheSplashScreenToDisappear();
        long endTime = System.currentTimeMillis();
        long splashScreenDuration = endTime - startTime;
        System.out.println("Splashscreen presence time: "+splashScreenDuration + " ms.");
        System.out.println("Running test on device: "+driver.getCapabilities().getCapability("deviceName"));
        Assert.assertTrue(splashScreenDuration <= expectedTime, "Splashscreen was present for longer than expected..");
    }
}
