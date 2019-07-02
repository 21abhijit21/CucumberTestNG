package com.prudential.common.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.prudential.common.constants.Config;

/**
 * Class for WebInteract methods
 * @author Abhijit Bhattacharyya
 *
 */
public class WebInteract{	
   /**
    * Method to Wait for Page to load
    * @param driver
    */
	public static void waitForPageLoad(WebDriver driver) 
	{
		 ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() 
	     {
			 public Boolean apply(WebDriver driver) 
			 {
				 return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	          }
	      };
	        
	      WebDriverWait wait = new WebDriverWait(driver, 30);
	      wait.until(pageLoadCondition);
	 }
	
	/**
	 * Method to get fluint wait
	 * @param driver
	 * @return
	 */
	private static FluentWait<WebDriver> getFluentWait() {
		return new FluentWait<WebDriver>(DriverManager.getWebDriver())
				.withTimeout(Config.PAGE_LOADWAI_TTIME, TimeUnit.SECONDS)
				.pollingEvery(Config.POLLING_TIME, TimeUnit.MILLISECONDS);
	}
	
	/**
	 * Method to move to element
	 * @param driver
	 * @param webElement
	 */
	public static void moveToElement(WebElement webElement) {
		((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
	}

	/**
	 * Method to move to element
	 * @param driver
	 * @param webElement
	 */
	public static void moveToElementAndClick(WebElement webElement) {
		((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
		webElement.click();
	}
	
	/**
	 * Method to click on object
	 * @param driver
	 * @param webElement
	 */
	public static void clickWhenReady(WebElement webElement) {
		try {
			((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
			getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement)).click();
		} catch (Exception e) { 
			Logger.logMessage("Exception: Webelement not available to click in clickWhenReady: " + webElement);
		}
	}

	/**
	 * Method to get no stale element and click
	 * @param driver
	 * @param webElement
	 * @return 
	 */
	public static void getNonstaleElementAndClick(WebElement webElement) {
		try {
			((JavascriptExecutor) DriverManager.getWebDriver()).executeScript("arguments[0].scrollIntoView();", webElement);
			getFluentWait().until(ExpectedConditions.elementToBeClickable(webElement)).click();

		} catch (Exception e) {
			for (int i = 0; i <= 10; ++i) {
				try {
					getFluentWait()
					.until(ExpectedConditions.elementToBeClickable(webElement))
					.click();
							
					break;
				} catch (StaleElementReferenceException e1) {
					continue;
				}	
			}
		}
		
	} 
	
	/**
	 * WebDriver wait
	 * @param element
	 * @return 
	 */
	public static void waitForVisibility(WebElement webElement) {
	try {	
			new WebDriverWait(DriverManager.getWebDriver(), Config.PAGE_LOADWAI_TTIME)
             .until(ExpectedConditions.visibilityOf(webElement));
		} catch (Exception e) {
			Logger.logMessage("Exception: Webelement not visible in waitForVisibility: " + webElement);
		}
	}
	
	/**
	 * WebDriver wait
	 * @param element
	 * @return 
	 */
	public static void waitForNonVisibility(WebElement webElement, int timeOut) {
	try {	
			new WebDriverWait(DriverManager.getWebDriver(), timeOut)
             .until(ExpectedConditions.invisibilityOf(webElement));
		} catch (Exception e) {
			Logger.logMessage("Exception: Webelement not visible in waitForNonVisibility: " + webElement);
		}	
	}
	
	 /**
	 * WebDriver wait
	 * @param element
	 * @return 
	 */
	public static void waitForAttribute(WebElement webElement, String attribute, String value) {
	try {	
			new WebDriverWait(DriverManager.getWebDriver(), Config.MEDIUM_PAUSE)
             .until(ExpectedConditions.attributeContains(webElement, attribute, value));
		} catch (Exception e) {
			Logger.logMessage("Exception: Webelement not visible in waitForAttribute: " + webElement);
		}
	} 
    
   /**
    * Verify webelement is present
    * @return
    */
   	public static Boolean isPresent(WebElement webElement, int timeOut) {
	   	Boolean found = false;
	   	
	   	WebInteract.waitForPageLoad(DriverManager.getWebDriver());
	   
	   	try {
		   		new WebDriverWait(DriverManager.getWebDriver(), timeOut)
		   		.until(ExpectedConditions.elementToBeClickable(webElement));
		   		
			   	if (webElement.isDisplayed())
			   		found = true;
		     
	   	} catch(Exception e) {
	   		Logger.logMessage("Exception: Webelement not present in isPresent: " + webElement);
	   		found = false;
	   	}
		  
       return found;
   	}
   
    /**
     * 
     * @param url
     */
    public static void openUrl(String url) {
    	DriverManager.getWebDriver().navigate().to(url);
//    	DriverManager.getWebDriver().manage().window().maximize();
        Logger.logMessage("Open url '" + url + "'.");
    }
    
    /**
	 * Method for mouse hover with JS
	 * @param webElement
	 * @return
	 */
	public static void mouseOverByJS(WebElement webElement) {
        String mouseOverJS = "var evObj = document.createEvent('MouseEvents');evObj.initMouseEvent"
        		+ "(\"mouseover\",true, false, window, 0, 0, 0, 0, 0, false, false, false, false, 0, null);"
        		+ "arguments[0].dispatchEvent(evObj);";
        ((JavascriptExecutor)DriverManager.getWebDriver()).executeScript(mouseOverJS, webElement);
    }
	
	/**
	 * Method to Mouse over the web element using the touch action class.
	 */
    public static void mouseOver(WebElement webElement) {
        new Actions(DriverManager.getWebDriver()).moveToElement(webElement).build().perform();
    }
    
    /**
     * Clicks the identified web element by javascript.
     * @return
     */
    public static void clickByJS(WebElement webElement) {
     	((JavascriptExecutor)DriverManager.getWebDriver()).executeScript("arguments[0].click();", webElement);
     }
	
    /**
     * sets value in the identified web element by javascript.
     * @return
     */
    public static void setTextByJS(WebElement webElement, String value) {
    	JavascriptExecutor js = (JavascriptExecutor)DriverManager.getWebDriver();
    	js.executeScript("arguments[0].value='"+ value +"';", webElement);
    }
    
    /**
     * sets value in the identified web element by javascript.
     * @return
     */
    public static void backtByJS() {
    	waitForPageLoad(DriverManager.getWebDriver());
    	JavascriptExecutor js = (JavascriptExecutor)DriverManager.getWebDriver(); 
    	js.executeScript("window.history.go(-1)");
    }
    
    public static void validatePageTitle(String Expected) {
    	String Title = DriverManager.getWebDriver().getTitle();
    	System.out.println(Title);
    	Assert.assertTrue(Title.contains(Expected));
    }

   }
    

