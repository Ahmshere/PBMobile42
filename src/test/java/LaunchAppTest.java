import config.AppiumConfig;
import org.testng.Assert;
import org.testng.annotations.Listeners;
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
    public void simplePrintTest() {
        System.out.println("This is a simple print test.");
        System.out.flush();
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
        System.out.flush();
        System.out.println("Running test on device: "+driver.getCapabilities().getCapability("deviceName"));
        System.out.flush();
        Assert.assertTrue(splashScreenDuration <= expectedTime, "Splashscreen was present for longer than expected..");
    }

    public static void main(String[] args) {
        System.out.println("MMMDVVDVFSFVEV");
    }
}
