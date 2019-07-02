package com.prudential.common.listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import com.prudential.common.constants.Config;
import com.prudential.common.utilities.AllureManager;
import com.prudential.stepdefs.common.TestRunner;

import java.util.*;
/**
 * Suite listener
 * @author Abhijit Bhattacharyya
 *
 */

public class SuiteListeners extends TestRunner implements ISuiteListener {
    @Override
    public void onStart(ISuite suite) {
    }
    
    @Override
    public void onFinish(ISuite suite) {
    	suite.getResults();
    	LinkedHashMap<String, String> environmentProps = new LinkedHashMap<String, String>();
    	environmentProps.put("Browser", Config.BROWSER);
    	environmentProps.put("Platform", Config.PLATFORM);
    	AllureManager.createAllurePropertyFile("environment.properties", environmentProps);
    }
}