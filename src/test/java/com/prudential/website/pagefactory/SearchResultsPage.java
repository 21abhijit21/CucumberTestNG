package com.prudential.website.pagefactory;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prudential.common.utilities.DriverManager;

public class SearchResultsPage {
	
	@FindBy(xpath=".//span[contains(text(),'Weather in your city')]")
	public WebElement SearchResultsPage;
	
	@FindBy(xpath=".//*[@id='forecast_list_ul']")
	public WebElement SearchListBody;
	
	@FindAll({
		@FindBy(xpath=".//*[@id='forecast_list_ul']/table/tbody/tr")
	}	)
	public List<WebElement> ResultsTable;
	
	public SearchResultsPage() {
		PageFactory.initElements(DriverManager.getWebDriver(), this);
	}

}
