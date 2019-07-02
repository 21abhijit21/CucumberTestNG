package com.prudential.api.utilities;

import static io.restassured.RestAssured.given;
import java.io.File;

import io.restassured.RestAssured;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.response.Response;

public class RestAPIRequests {
	
	public  static Response GET_Request(String URL,String API,String accessToken) {
		
		RestAssured.config=RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			       setParam("http.connection.timeout",3000).
			       setParam("http.socket.timeout",3000).
			       setParam("http.connection-manager.timeout",3000));
		
		Response response = given().relaxedHTTPSValidation().config(RestAssured.config).
				urlEncodingEnabled(false)
	            .contentType("application/json")
	            .accept("text/html").
	            when().redirects().follow(true).redirects().max(100).
	            get(URL+API);
		System.out.println(response.asString());
		return response;
	}
	
	public  static Response GET_Request_With_Parameters(String URL,String API,String Parameters,String accessToken) {
		
		RestAssured.config=RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			       setParam("http.connection.timeout",3000).
			       setParam("http.socket.timeout",3000).
			       setParam("http.connection-manager.timeout",3000));
		
		Response response = given().relaxedHTTPSValidation().config(RestAssured.config).
				urlEncodingEnabled(false)
	            .contentType("application/json")
	            .accept("text/html").
	            when().redirects().follow(true).redirects().max(100).
	            get(URL+API+Parameters+accessToken);
		System.out.println(response.asString());
		return response;
	}

	
	public  static Response POST_Request(String URL,String API,String JSON,String accessToken) {
		
		RestAssured.config=RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			       setParam("http.connection.timeout",3000).
			       setParam("http.socket.timeout",3000).
			       setParam("http.connection-manager.timeout",3000));
		
		Response response = 
		        given().relaxedHTTPSValidation().config(RestAssured.config)
		        .auth().oauth2(accessToken)
		            .contentType("application/json")
		            .accept("application/json")
		            .body(JSON)
		        .when()
		        .post(URL+API);
		return response;
	}
	
	public  static Response PUT_Request(String URL,String API,String JSON,String accessToken) {
		
		RestAssured.config=RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			       setParam("http.connection.timeout",3000).
			       setParam("http.socket.timeout",3000).
			       setParam("http.connection-manager.timeout",3000));
		
		Response response = 
		        given().relaxedHTTPSValidation().config(RestAssured.config)
		        .auth().oauth2(accessToken)
		            .contentType("application/json")
		            .accept("application/json")
		            .body(JSON)
		        .when()
		        .put(URL+API);
		return response;
	}
	
	public  static Response PATCH_Request(String URL,String API,String JSON,String accessToken) {
		
		RestAssured.config=RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			       setParam("http.connection.timeout",3000).
			       setParam("http.socket.timeout",3000).
			       setParam("http.connection-manager.timeout",3000));
		
		Response response = 
		        given().relaxedHTTPSValidation().config(RestAssured.config)
		        .auth().oauth2(accessToken)
		            .contentType("application/json")
		            .accept("application/json")
		            .body(JSON)
		        .when()
		        .patch(URL+API);
		return response;
	}
	
	public  static Response POST_MultiPart_Request(String URL,String API,File file,String JSON,String accessToken) {
		
		RestAssured.config=RestAssuredConfig.config().httpClient(HttpClientConfig.httpClientConfig().
			       setParam("http.connection.timeout",3000).
			       setParam("http.socket.timeout",3000).
			       setParam("http.connection-manager.timeout",3000));
		
		Response response = 
		        given().relaxedHTTPSValidation().config(RestAssured.config)
		        .auth().oauth2(accessToken)
		            .multiPart("file", file,"multipart/form-data").
					multiPart("promotion", JSON,"application/json")
		        .when()
		        .post(URL+API);
		return response;
	}


}
