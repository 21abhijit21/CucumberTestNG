package com.prudential.website.pageutilities;

import org.testng.asserts.SoftAssert;

import com.prudential.common.utilities.DriverManager;
import com.prudential.common.utilities.Logger;
import com.prudential.common.utilities.WebInteract;
import com.prudential.website.pagefactory.SearchResultsPage;

public class SearchResults_Methods {
	
	protected SearchResultsPage SearchResultsPage;
	public static SoftAssert asserts;
	
	public SearchResults_Methods(){
		SearchResultsPage= new SearchResultsPage();
		asserts = new SoftAssert();
	}
	
	public void validate_invalid_search_result(String Error){
		
		WebInteract.waitForVisibility(SearchResultsPage.SearchResultsPage);
		String Message = SearchResultsPage.SearchListBody.getText();
		asserts.assertEquals(Message, Error, "Invalid Search Error Should Be Diplayed");
		Logger.logMessage("Invalid Search Error Is Diplayed");
		asserts.assertAll();
	}

	public void validate_valid_search_result(String City){
		
		WebInteract.waitForVisibility(SearchResultsPage.SearchResultsPage);
		int result_count = SearchResultsPage.ResultsTable.size();
		for(int i=0;i<result_count;i++) {
			if(SearchResultsPage.ResultsTable.get(i).getText().contains(City))
				SearchResultsPage.ResultsTable.get(i).click();
			Logger.logMessage("Clicked on Matching Search Result");
			break;
		}
		WebInteract.waitForPageLoad(DriverManager.getWebDriver());
		asserts.assertAll();
	}
}
