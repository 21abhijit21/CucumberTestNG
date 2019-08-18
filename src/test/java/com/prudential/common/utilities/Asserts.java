package com.prudential.common.utilities;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public abstract class Asserts {
	
	public static StringBuffer verificationErrors = new StringBuffer();
	static WebInteract WebInteract = new WebInteract();

	public static void assertEqual(String Actual, String Expected, String Message) {
		try {
		assertThat(Actual,equalTo(Expected));
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
		}
	}
	
	public static void assertNull(String Actual, String Expected, String Message) {
		try {
		assertThat(Actual, is(notNullValue()));
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
		}
	}
	
	
	public static void assertContains(String Actual, String SubString, String Message) {
		try {
			Assert.assertTrue(Actual.contains(SubString));
		Logger.logMessage(Message);
		} catch(Error E1) {
			verificationErrors.append(E1.toString());			
			Logger.logMessage(Message+ " :Failed");
		}
	}
	
	public static void verifyVisible(WebElement Element, String Message) {
	    try {
	    	Assert.assertTrue(Element.isDisplayed());
	    	Logger.logMessage(Message+ " :Is Visible");
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	        Logger.logMessage(Message+ " :Is Not Visible");
	      }
	}
	
	public static void verifyElementPresent(WebElement Element, String Message) {
	    try {
	    	Assert.assertTrue(WebInteract.isElementPresent(Element));
	    	Logger.logMessage(Message+ " :Is Present");
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	        Logger.logMessage(Message+ " :Is Not Present");
	      }
	}
	
	public static void verifyElementNotPresent(WebElement Element, String Message) {
	    try {
	    	Assert.assertFalse(WebInteract.isElementPresent(Element));
	    	Logger.logMessage(Message+ " :Is Not Present");
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	        Logger.logMessage(Message+ " :Is Present");
	      }
	}
	
	

}
