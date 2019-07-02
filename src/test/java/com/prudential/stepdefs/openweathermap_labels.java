package com.prudential.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java8.En;

import org.gradle.internal.impldep.org.testng.Assert;
import org.testng.asserts.SoftAssert;

import com.prudential.website.pageutilities.HomePage_Methods;
import com.prudential.website.pageutilities.SearchResults_Methods;

public class openweathermap_labels implements En{
	
	protected HomePage_Methods HomePage_Methods;
	protected SearchResults_Methods SearchResults_Methods;
	public static SoftAssert asserts;

	public openweathermap_labels() {
		
		Given("User navigates to {string}", (String string) -> {
			HomePage_Methods= new HomePage_Methods();
			SearchResults_Methods= new SearchResults_Methods();
			boolean status = HomePage_Methods.validate_HomePage_landing();
			Assert.assertTrue(status, "Unable to Launch website"+string);
		});

		Given("User is on Home Page for {string} App", (String arg) -> {
		    // Write code here that turns the phrase above into concrete actions
			HomePage_Methods.validate_HomePage(arg);
		});

		Then("all Header labels should be accurately displayed", () -> {
			HomePage_Methods.HomePage_Header_validations();
		});

		Then("all Body labels should be accurately displayed", () -> {
			HomePage_Methods.HomePage_Header_validations();
		});

		When("I search for an invalid (.*)", (String arg) -> {
		    // Write code here that turns the phrase above into concrete actions
			HomePage_Methods.search_city(arg);
		});

		Then("appropriate (.*) should be displayed", (String arg1) -> {
		    // Write code here that turns the phrase above into concrete actions
			SearchResults_Methods.validate_invalid_search_result(arg1);
		});
		
		When("I search for a valid (.*)", (String arg1) -> {
		    // Write code here that turns the phrase above into concrete actions
			HomePage_Methods.search_city(arg1);
		});

		Then("Reults should be displayed for correct (.*)", (String arg) -> {
		    // Write code here that turns the phrase above into concrete actions
			SearchResults_Methods.validate_valid_search_result(arg);
			HomePage_Methods.Validate_Search_Results(arg);
			
		});
	}

	
}
