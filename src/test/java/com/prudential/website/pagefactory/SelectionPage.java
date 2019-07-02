package com.prudential.website.pagefactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.prudential.common.utilities.DriverManager;

public class SelectionPage {
	
	
	
	
	public SelectionPage() {
		PageFactory.initElements(DriverManager.getWebDriver(), this);
	}

}
