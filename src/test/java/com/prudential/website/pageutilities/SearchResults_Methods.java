package com.prudential.website.pageutilities;

import org.testng.asserts.SoftAssert;
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

}
