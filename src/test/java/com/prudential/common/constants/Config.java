package com.prudential.common.constants;


/**
 * Class to declare constants
 * @author Abhijit Bhattacharyya
 *
 */
public class Config {
	//Grid Config
	public static final String GRID_HUB_IP = "localhost";//"10.68.0.16" ; 
	public static final String  GRID_HUB_PORT = "4444";
	
	
	//Desired Capabilities
	public static final String BROWSER = "firefox"; //{chrome , firefox , htmlunit , internet explorer, opera, safari }.
	public static final String PLATFORM = "Windows";
	
	
	//RemoteWebdriver Config

	public static final int PAGE_LOADWAI_TTIME = 120;
	public static final int XSMALL_PAUSE = 3;
	public static final int SMALL_PAUSE = 10;
	public static final int MEDIUM_PAUSE = 30;
	public static final int LARGE_PAUSE = 60;
	public static final int XLARGE_PAUSE = 180;
	public static final int POLLING_TIME = 500;


	//File Paths
	public static final String TEST_DATA_FILE_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\testdata\\TestData.xlsx";
	public static final String SELENIUM_LOG_PATH = System.getProperty("user.dir")+"\\src\\test\\resources\\logs\\Selenium.logs";
	public static final String ALLURE_ENV_PATH = System.getProperty("user.dir")+"\\build\\allure-results\\";
	
	
	//Application Config
	public static final String APPURL = "https://openweathermap.org/";	
	public static final String URL = "http://api.openweathermap.org/data/2.5";
	public static final String API = "/weather";	
	public static final String Token = "&appid=829d743eb9296d9329a3d6c81ae43405";
	
}
