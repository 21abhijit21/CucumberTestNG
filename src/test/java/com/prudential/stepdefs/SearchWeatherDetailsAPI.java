package com.prudential.stepdefs;

import cucumber.api.java8.En;
import io.restassured.response.Response;

import org.testng.asserts.SoftAssert;

import com.prudential.api.methods.WeatherApi;
import com.prudential.api.utilities.RestAPIRequests;
import com.prudential.common.constants.Config;

public class SearchWeatherDetailsAPI  implements En{
	
	protected WeatherApi WeatherApi;
	public static SoftAssert asserts;

	public SearchWeatherDetailsAPI() {
		
		WeatherApi= new WeatherApi();
		asserts = new SoftAssert();
		
		Given("I search for Weather Deatils for (.*) (.*) on the WeatherApp api", (String arg1,String arg2) -> {
		    // Write code here that turns the phrase above into concrete actions
		
		});


		Then("correct  (.*) (.*) and (.*) should be displayed", (String arg1,String arg2,String arg3) -> {
		    // Write code here that turns the phrase above into concrete actions
			WeatherApi.Search_via_zip(arg1, arg2, arg3);

			
		});

	
}
}
