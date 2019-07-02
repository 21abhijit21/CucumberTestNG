package com.prudential.website.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prudential.common.utilities.DriverManager;

public class HomePage {
	
	/**
	 * Header Section
	 */
	@FindBy(xpath=".//*[@class='navbar-brand']/img")
	public WebElement OpenWeather_Logo;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Weather')]")
	public WebElement Weather_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Maps')]")
	public WebElement Maps_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Guide')]")
	public WebElement Guide_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'API')]")
	public WebElement API_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Price')]")
	public WebElement Price_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Partners')]")
	public WebElement Partners_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Stations')]")
	public WebElement Stations_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Widgets')]")
	public WebElement Widgets_Label;
	
	@FindBy(xpath=".//*[@class='nav navbar-nav navbar-right']/li/a[contains(text(),'Blog')]")
	public WebElement Blog_Label;
		
	
	/*
	 * Search Section
	 * 
	 */
	
	@FindBy(xpath="//form[@id='searchform']/div/input")
	public WebElement SearchCity;
	
	@FindBy(xpath="//form[@id='searchform']/button")
	public WebElement SearchButton;
	
	@FindBy(xpath=".//*[@id='searchform']/span/button[contains(text(),'Current location')]")
	public WebElement CurrentLocation;
	
	/*
	 * Weather Forecast Options
	 */
	
	@FindBy(xpath=".//*[@id='widget']/div/div/*[@class='widget__title']")
	public WebElement WidgetTitle;
	
	@FindBy(xpath=".//*[@id='widget']/div/div/*[@class='widget__layout']/*[@class='widget__temperature']")
	public WebElement WidgetTemperature;
	
	@FindBy(xpath=".//*[@class='widget__graphic']/span/form/ul/li/a[contains(text(),'Main')]")
	public WebElement WidgetTabMain;
	
	@FindBy(xpath=".//*[@class='widget__graphic']/span/form/ul/li/a[contains(text(),'Daily')]")
	public WebElement WidgetTabDaily;
	
	@FindBy(xpath=".//*[@class='widget__graphic']/span/form/ul/li/a[contains(text(),'Hourly')]")
	public WebElement WidgetTabHourly;
	
	@FindBy(xpath=".//*[@class='widget__graphic']/span/form/ul/li/a[contains(text(),'Chart')]")
	public WebElement WidgetTabChart;
	
	@FindBy(xpath=".//*[@class='widget__graphic']/span/form/ul/li/a[contains(text(),'Map')]")
	public WebElement WidgetTabMap;
	
	public HomePage() {
		PageFactory.initElements(DriverManager.getWebDriver(), this);
	}

}
