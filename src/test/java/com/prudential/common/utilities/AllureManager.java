package com.prudential.common.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import com.prudential.common.constants.Config;
import io.qameta.allure.Attachment;
/**
 * Class for Allure methods
 * @author ABhijit Bhattacharyya
 *
 */
public class AllureManager {
	//private static String reportDir = "./target/allure-results";
	private static String emptyString = "empty string";
	
	/**
	 * Gets the screenshot of the browser window and attaches it to the test case in the allure report.
	 * @return
	 */
    @Attachment(value = "Screenshot", type = "image/png")
    public static byte[] attachScreenshot() {
    	byte[] imageContent = emptyString.getBytes();
    	try {
    			imageContent = ((TakesScreenshot) DriverManager.getWebDriver()).getScreenshotAs(OutputType.BYTES);
    		
    		} catch (Exception e) {
    			Logger.logConsoleMessage("Failed to capture screenshot.");
    			e.printStackTrace();
    		}
    	return imageContent;
    }

    @Attachment(value = "Video", type = "video/mp4")
    public static byte[] attachVideo(String pathToVideoFile) {
    	byte[] videoContent = emptyString.getBytes();
    	try {
    		videoContent = Files.readAllBytes(Paths.get(pathToVideoFile));
    	} catch (Exception e) {
    		Logger.logConsoleMessage("Failed to get video data.");
    		e.printStackTrace();
    	}
    	return videoContent;
    }
    
    /**
     * Gets the selenium log and attaches it to the test case in the allure report.
     * @param pathToLogFile
     * @return
     */
    @Attachment(value = "Selenium Log", type = "text/plain")
    public static byte[] attachSeleniumLog(String pathToLogFile) {
    	byte[] logContent = emptyString.getBytes();
    	try {
    		logContent = Files.readAllBytes(Paths.get(pathToLogFile));
    	} catch (Exception e) {
    		Logger.logConsoleMessage("Failed to get log data.");
    		e.printStackTrace();
    	}
    	return logContent;
    }
    
   /**
    * Creates environment.properties file 
    * @param propertyFileName
    * @param allureProps
    */
   	public static void createAllurePropertyFile(String propertyFileName, LinkedHashMap<String, String> allureProps) {
       	File propertyFile = new File(Config.ALLURE_ENV_PATH + propertyFileName);
           
           propertyFile.mkdirs();
           
           if (propertyFile.exists()) {
           	propertyFile.delete();
           } else {
           	try {
   				propertyFile.createNewFile();
   			} catch (IOException e) {
   				Logger.logConsoleMessage("Failed to generate allure properties file at: " 
   			        + propertyFile.getAbsolutePath());
   				e.printStackTrace();
   			}
           }
           
           BufferedWriter writer = null;
           try {
   			writer = new BufferedWriter(new FileWriter(propertyFile));
   			
   			String props = "";
   			Iterator iterator = allureProps.entrySet().iterator();
   			while (iterator.hasNext()) {
   				Map.Entry pair = (Map.Entry) iterator.next();
   				String prop = pair.getKey() + "=" + pair.getValue() + System.getProperty("line.separator");
   			    props += prop;
   			}
   			writer.write(props);
   			writer.close();
   		} catch (IOException e) {
   			Logger.logConsoleMessage("Failed to write properties to prop file.");
   			e.printStackTrace();
   		}
           //return this;
       }
       
}