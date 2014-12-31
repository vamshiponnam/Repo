package com.ebay.Ebay_BDD;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * Created by Vamshi.Ponnam on 08/12/2014.
 */
public class DriverSetup extends EventFiringWebDriver{


    private static Browsers browser;


    private static final WebDriver driver() {
        try {
            getBrowser();
        } catch (Exception e) {
            e.printStackTrace();
        }
        switch(browser) {
            case ie:
                try {
                    System.setProperty("webdriver.ie.driver", com.ebay.Ebay_BDD.CommonFunctions.CurrentDirectory() + "/drivers/InternetExplorerDriver.exe");
                    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
                    capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    capabilities.setCapability("ignoreZoomSetting", true);
                    return new InternetExplorerDriver(capabilities);
                } catch (Throwable t) {
                    Assert.fail("Internet Explorer not found");
                }
                break;
            case firefox:
                try {
                    return new FirefoxDriver();
                } catch (Throwable t) {
                    Assert.fail("Firefox not found");
                }
                break;
            case safari:
                try {
                    return new SafariDriver();
                } catch (Throwable t) {
                    Assert.fail("Safari not found");
                }
                break;
            case chrome:
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--start-maximized");
                options.addArguments("--ignore-certificate-errors");
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                //capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, options);
                try {
                    if(CommonFunctions.getOS().contains("Win")) {
                        System.setProperty("webdriver.chrome.driver", CommonFunctions.CurrentDirectory() + "/drivers/chromedriver.exe");
                    }
                    if(CommonFunctions.getOS().contains("Mac")) {
                        System.setProperty("webdriver.chrome.driver", CommonFunctions.CurrentDirectory() + "/drivers/chromedriver");
                    }
                    return new ChromeDriver(options);
                } catch (Throwable t) {
                    Assert.fail("Chrome not found");
                }
                break;
            default:
                System.out.println("No Browser Initialised.");
                break;
        }
        return driver();
    }

    private static final WebDriver REAL_DRIVER = driver();
    private static final Thread CLOSE_THREAD = new Thread() {
        @Override
        public void run() {
            REAL_DRIVER.close();
        }
    };

    static {
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }

    public DriverSetup() {
        super(REAL_DRIVER);
        REAL_DRIVER.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        REAL_DRIVER.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
        REAL_DRIVER.manage().timeouts().setScriptTimeout(20, TimeUnit.SECONDS);
        REAL_DRIVER.manage().window().maximize();
    }

    @Override
    public void close() {
        if(Thread.currentThread() != CLOSE_THREAD) {
            throw new UnsupportedOperationException("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
        }
        super.close();
    }

    @Before
    private void deleteAllCookies() {

        manage().deleteAllCookies();

    }

    @After
    public void embedScreenshot(Scenario result) {
        try {
            byte[] screenshot = this.getScreenshotAs(OutputType.BYTES);
            result.embed(screenshot, "image/png");
        } catch (WebDriverException somePlatformsDontSupportScreenshots) {
            System.err.println(somePlatformsDontSupportScreenshots.getMessage());
        }
    }
    enum Browsers {
        ie, firefox, safari, chrome
    }

    public static Browsers getBrowser() throws Exception {

        if (System.getProperty("Browser") == null || System.getProperty("Browser").isEmpty()){
            browser = Browsers.valueOf(CommonFunctions.ReadPropertiesFile(CommonFunctions.getConfig_file(), "Browser"));
        }

        if (System.getProperty("Browser") != null){
            for (Browsers s : Browsers.values()){
                String match = s.name();
                if (System.getProperty("Browser").toLowerCase().matches(match)){
                    browser = Browsers.valueOf(CommonFunctions.SetPropertiesFile(CommonFunctions.getConfig_file(), "Browser", System.getProperty("Browser").toLowerCase()));
                }
            }
            if(!Arrays.toString(Browsers.values()).contains(System.getProperty("Browser").toLowerCase())){
                CommonFunctions.Log("Browser not found or Incorrect Browser name");
                System.exit(0);
            }
        }
        CommonFunctions.Log("Running tests on: " + browser);
        return browser;
    }
}
