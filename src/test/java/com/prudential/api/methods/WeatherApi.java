package com.prudential.api.methods;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.prudential.api.utilities.RestAPIRequests;
import com.prudential.common.constants.Config;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;

public class WeatherApi {

	
	
	public static SoftAssert asserts;
	
	public WeatherApi() {
		asserts = new SoftAssert();
	}
	
	public void exampleOfLogin() {
	/*	cookie = RestAssured.given()
	            .contentType(ContentType.JSON)
	            .when().get("").then().extract().response().getDetailedCookie("")*/
	}
	
	
	public void Search_via_zip(String Status,String Zip,String Cntry) {		
		String Params= "?zip="+Zip+","+Cntry;
		Response response = RestAPIRequests.GET_Request_With_Parameters(Config.URL,Config.API,Params,Config.Token);
		asserts.assertTrue(response.getStatusLine().contains(Status), "Weatcher Search for"+Cntry+"And"+Zip+"is successful");
//		asserts.assertEquals(response.getStatusLine(), Status, "Weatcher Search for"+Cntry+"And"+Zip+"is successful");
		asserts.assertEquals(response.path("sys.country"), Cntry, "Correct country is displayed in response");
		asserts.assertNotNull(response.jsonPath().param("i", 0).getString("weather[i].description"),"weather Description is displayed");
		asserts.assertAll();
	}
	
}
