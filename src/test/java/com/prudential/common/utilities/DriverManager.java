package com.prudential.common.utilities;

import static org.testng.Assert.fail;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.prudential.common.constants.Config;



public class DriverManager {
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<WebDriver>();
    private static StringBuffer verificationErrors = new StringBuffer();
  
  	/**
    * Sets the Remote WebDriver instance for the running session.
    * @param driver
    */
    public static synchronized void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }
    
    /**
     * Gets the Remote WebDriver instance for the running session.
     * @return
     */
    public static synchronized WebDriver getWebDriver() {
        return webDriver.get();
    }
  
    /**
     * Initiates a new instance of Remote WebDriver.
     */
    @SuppressWarnings("deprecation")
	public static void initiateWebDriver(String Browser,String RunEnv) {
    	if (Browser.equalsIgnoreCase("Chrome")) {
    	//Grid on Jenkins
    		Map<String, Object> prefs = new HashMap<String, Object>();
    	    prefs.put("profile.managed_default_content_settings.geolocation", 2);
    	DesiredCapabilities capabilities = DesiredCapabilities.chrome(); 
    		capabilities.setPlatform(Platform.WINDOWS);
    	
    	capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
    	capabilities.setCapability (CapabilityType.ACCEPT_INSECURE_CERTS, true);
//    	capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
    	ChromeOptions options = new ChromeOptions();
    	options.addArguments("start-maximized");
    	options.addArguments("incognito");
    	options.setExperimentalOption("prefs", prefs);
    	capabilities.setCapability(ChromeOptions.CAPABILITY, options);
    	if(!RunEnv.equalsIgnoreCase("localhost")) {
        String hubAddress = "http://" + Config.GRID_HUB_IP + ":" + Config.GRID_HUB_PORT + "/wd/hub";
        try {
        		setWebDriver(new RemoteWebDriver(new URL(hubAddress), capabilities));
        } catch (Exception e) {
            Logger.logConsoleMessage("Session could not be created.");
            e.printStackTrace();
        }
    	}
        else if (RunEnv.equalsIgnoreCase("localhost")) {
        	try {
        		System.setProperty("webdriver.chrome.driver", "Resources/Drivers/chromedriver.exe");
        		setWebDriver(new ChromeDriver(capabilities));
        } catch (Exception e) {
            Logger.logConsoleMessage("Session could not be created.");
            e.printStackTrace();
        }
        }
        
        if (webDriver == null) {
            Logger.logConsoleMessage("The driver was not properly initiated.");
        }
        
        
    }
    else if(Browser.equalsIgnoreCase("firefox")) {
    	//Grid on Jenkins
    	DesiredCapabilities capabilities = DesiredCapabilities.firefox(); 
    	
    	
   		capabilities.setPlatform(Platform.WINDOWS);
    	capabilities.setCapability (CapabilityType.ACCEPT_SSL_CERTS, true);
    	capabilities.setCapability (CapabilityType.ACCEPT_INSECURE_CERTS, true);
    	capabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
    	FirefoxOptions options = new FirefoxOptions().setLegacy(false);;
 //   	options.addCapabilities(capabilities);
//    	options.addArguments("start-maximized");
    	if(!RunEnv.equalsIgnoreCase("localhost")) {
        String hubAddress = "http://" + Config.GRID_HUB_IP + ":" + Config.GRID_HUB_PORT + "/wd/hub";
        try {
        		setWebDriver(new RemoteWebDriver(new URL(hubAddress), capabilities));
        } catch (Exception e) {
            Logger.logConsoleMessage("Session could not be created.");
            e.printStackTrace();
        }
    	}
    	else if (RunEnv.equalsIgnoreCase("localhost")) {
        	try {
        		System.setProperty("webdriver.gecko.driver", "Resources/Drivers/geckodriver.exe");
        		setWebDriver(new FirefoxDriver(capabilities));
        } catch (Exception e) {
            Logger.logConsoleMessage("Session could not be created.");
            e.printStackTrace();
        }
        }
        if (webDriver == null) {
            Logger.logConsoleMessage("The driver was not properly initiated.");
        }   
    }
    }
    
    /**
     * Closes existing instance of Remote WebDriver.
     * @throws Throwable 
     */
    public static void stopWebDriver(String platform) throws Throwable {
    	AllureManager.attachScreenshot();
    	if(platform.equalsIgnoreCase("chrome")){
    	getWebDriver().close();
		getWebDriver().quit();
    }else if (platform.equalsIgnoreCase("firefox")) {
    	getWebDriver().close();
    }
    	
		Logger.logConsoleMessage("Closing browser.");
		
		String verificationErrorString = verificationErrors.toString();
		   if (!"".equals(verificationErrorString)) {
		     fail(verificationErrorString);
		   }
		Thread.sleep(500);
    }
    
}
