import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CommonAPI {
    public static final String USERNAME = "zan14";
    public static final String AUTOMATE_KEY = "Fs7PJAifzJnzs8dFMNxx";
    public static final String BROWSERSTACK_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
    public static final String SAUCE_URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@ondemand.saucelabs.com:80/wd/hub";

    public static WebDriver driver = null;

    @Parameters({"platform", "url", "browser", "cloud", "browser-version", "name"})
    @BeforeMethod
    public static WebDriver setupDriver(String platform, String url, @Optional("chrome") String browser, @Optional("false") boolean cloud, String browserVersion, String name) throws MalformedURLException {
        if(cloud){
            getCloudDriver(name, platform,browser,browserVersion);
        }
        else{
            getLocalDriver(platform,url,browser);
        }
        driver.get(url);
        return driver;
    }

    @AfterMethod
    public void quitDriver() {
        driver.close();
        driver.quit();
    }

    public static void getScreenShot(WebDriver driver) {

        DateFormat df = new SimpleDateFormat("(MM.dd.yyyy-HH:mma)");
        Date date = new Date();
        String name = df.format(date);


        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file,new File("C:\\Users\\wormi\\IdeaProjects\\webAutomation\\src\\main\\resources\\screenshots\\screenshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sleepFor(int Seconds) {
        try {
            Thread.sleep(Seconds * 1000);
        } catch (Exception e) {
        }
    }
    public static WebDriver getCloudDriver(String name, String platfrom, String browser, String browserVersion) throws MalformedURLException {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("name", name);
        capabilities.setCapability("browser", browser);
        capabilities.setCapability("browser_version", browserVersion);
        capabilities.setCapability("os", platfrom);

        driver = new RemoteWebDriver(new URL(BROWSERSTACK_URL), capabilities);
        return driver;

    }

    public static WebDriver getLocalDriver(String platform, String url, String browser){
        if(platform.equalsIgnoreCase("win") && browser.equalsIgnoreCase("chrome")){
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\wormi\\IdeaProjects\\webAutomation\\Generic\\src\\main\\resources\\drivers\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);
        }
        if(platform.equalsIgnoreCase("win") && browser.equalsIgnoreCase("mozilla")){
            System.setProperty("webdriver.gecko.driver", "C:\\Users\\wormi\\IdeaProjects\\webAutomation\\Generic\\src\\main\\resources\\drivers\\chromedriver_win32\\geckodriver-v0.24.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
            driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
            driver.manage().window().maximize();
            driver.get(url);
        }

        return  driver;

    }
}
