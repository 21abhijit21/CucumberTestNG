package com.prudential.stepdefs.common;

import com.prudential.common.utilities.DriverManager;
import com.prudential.common.utilities.Logger;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

public class ServiceHooks {
	
    @Before("@WebTests")
    public void initializeWebTest(){
   	String browser= System.getProperty("browser","chrome");
   	String RunEnv = System.getProperty("RunEnv","localhost");
        DriverManager.initiateWebDriver(browser,RunEnv);
    }

    @Before("@API")
    public void initializeApiTest(Scenario scenario){
    	Logger.logMessage("Execution Started For"+scenario.getName());    	
    }
    
    @After("@API")
    public void teardown_API(Scenario scenario) throws Throwable {
    	
    	if(!scenario.isFailed()) {
    		Logger.logMessage(scenario.getName()+" :PASSED");
    	}
    	    	
    	else if (scenario.isFailed()) {
            	Logger.logMessage(scenario.getName()+" :FAILED");
        }
    }
    
    @After("@WebTests")
    public void teardown(Scenario scenario) throws Throwable {
    	
    	if(!scenario.isFailed()) {
    		Logger.logMessage(scenario.getName()+" :PASSED");
    	}
    	    	
    	else if (scenario.isFailed()) {
            	Logger.logMessage(scenario.getName()+" :FAILED");
        }
        String browser= System.getProperty("browser","chrome");
        DriverManager.stopWebDriver(browser);
    }

}
