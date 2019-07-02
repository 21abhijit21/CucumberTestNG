package com.prudential.website.pageutilities;

import org.testng.asserts.SoftAssert;

import com.prudential.common.utilities.Logger;
import com.prudential.common.utilities.WebInteract;
import com.prudential.common.constants.Config;
import com.prudential.common.utilities.DriverManager;
import com.prudential.website.pagefactory.HomePage;

import io.qameta.allure.Step;

public class HomePage_Methods {
	
	protected HomePage HomePage;
	public static SoftAssert asserts;
	
	public HomePage_Methods() {
		HomePage = new HomePage();
		asserts = new SoftAssert();
	}
	
	@Step("Launch Applications")
	public boolean validate_HomePage_landing() {
		Boolean status = false;
		DriverManager.getWebDriver().get(Config.APPURL);
		WebInteract.waitForPageLoad(DriverManager.getWebDriver());		
		WebInteract.waitForVisibility(HomePage.OpenWeather_Logo);
		if(WebInteract.isPresent(HomePage.OpenWeather_Logo, Config.LARGE_PAUSE)) {
			Logger.logMessage("Application launched successfully.");
			status=true;
		}else 
			Logger.logMessage("Application not launched.");
			
		return status;
	}
	
	public void validate_HomePage(String Title) {
		WebInteract.validatePageTitle(Title);
	}
	
	public void HomePage_Header_validations() {
		
		if(DriverManager.getWebDriver().getTitle().equalsIgnoreCase("Сurrent weather and forecast")){
			asserts.assertTrue(HomePage.Weather_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Maps_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Guide_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.API_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Price_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Partners_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Stations_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Widgets_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			asserts.assertTrue(HomePage.Blog_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
		}
		
	}
	
	public void HomePage_Main_validations() {
		
		if(DriverManager.getWebDriver().getTitle().equalsIgnoreCase("Сurrent weather and forecast")){
			asserts.assertTrue(HomePage.SearchCity.isDisplayed(),"SearchBoxShould be displayed");
			asserts.assertTrue(HomePage.SearchButton.isDisplayed(),"SearchButton Should be displayed");
			asserts.assertTrue(HomePage.CurrentLocation.isDisplayed(),"CurrentLocation Button Should be displayed");
			asserts.assertTrue(HomePage.WidgetTitle.isDisplayed(),"Widget Title Should be displayed");
			asserts.assertTrue(HomePage.WidgetTemperature.isDisplayed(),"Temperature Should be displayed");
			asserts.assertTrue(HomePage.WidgetTabMain.isDisplayed(),"Main Tab Should be displayed");
			asserts.assertTrue(HomePage.WidgetTabDaily.isDisplayed(),"Daily Tab Should be displayed");
			asserts.assertTrue(HomePage.WidgetTabHourly.isDisplayed(),"Hourly Tab Should be displayed");
			asserts.assertTrue(HomePage.WidgetTabChart.isDisplayed(),"Chart Tab Should be displayed");
			asserts.assertTrue(HomePage.WidgetTabMap.isDisplayed(),"Map Tab Should be displayed");
		}
		
	}
	
	public void search_city(String City) {
		WebInteract.waitForPageLoad(DriverManager.getWebDriver());
		if(DriverManager.getWebDriver().getTitle().equalsIgnoreCase("Сurrent weather and forecast")){
//			asserts.assertTrue(HomePage.Guide_Label.isDisplayed(),"HomePage Guide Label Should be displayed");
			Logger.logConsoleMessage("Guest user Navigated to HomePage");
			WebInteract.clickWhenReady(HomePage.SearchCity);
			//HomePage.SearchCity.sendKeys(City);
			//HomePage.SearchButton.click();
			WebInteract.setTextByJS(HomePage.SearchCity, City);
			HomePage.SearchButton.click();
			WebInteract.waitForPageLoad(DriverManager.getWebDriver());
			
		}
		
	}
	
	public void Validate_Search_Results(String City) {
		WebInteract.waitForVisibility(HomePage.City_Name);
		asserts.assertTrue(HomePage.City_Name.getText().contains(City), "Search Result is displayed for correct City");
		asserts.assertAll();
	}
	
	
}
