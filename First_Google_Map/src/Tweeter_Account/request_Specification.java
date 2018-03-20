package Tweeter_Account;


import org.junit.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
public class request_Specification {
	

	String CONSUMER_KEY = "oDIAynS66RgRxPVyQsXUkDb25";
	String CONSUMER_SECRET = "IRR31DdqWcWulWFUhvdk53c62OP4EOHHIlbsXtrEeBIwfD6srN";
	String ACCESS_TOKEN = "974464648035667968-7aAtDZqsPSOrfhBnTH2rAaxaintxvCl";
	String ACCESS_TOKEN_SECRET = "WM87p8t7Htrao1xVofvaJXwAfkywdKpAdJ9j8CWd3WrAx";
	
	static RequestSpecification requestSpec;
	AuthenticationScheme auth;
	RequestSpecBuilder reqbuilder;
	static ResponseSpecification response_spec;
	ResponseSpecBuilder resbuilder;


	
	@Test( enabled=false)
	public void Requestspecification()
	{
	
		auth = RestAssured.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		reqbuilder = new RequestSpecBuilder();
		reqbuilder.setBaseUri("https://api.twitter.com");
		reqbuilder.setBasePath("/1.1/statuses");
		reqbuilder.addQueryParam("screen_name", "geetha r");
		reqbuilder.setAuth(auth);
		requestSpec = reqbuilder.build();
			
			given()
				.spec(requestSpec)
				
			.when()
			.get("/user_timeline.json")
			.then()
			.statusCode(200);
				
	}
	
	@Test()
	public void responsespecification()
	{
		
		auth = RestAssured.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET);
		reqbuilder = new RequestSpecBuilder();
		reqbuilder.setBaseUri("https://api.twitter.com");
		reqbuilder.setBasePath("/1.1/statuses");
		reqbuilder.addQueryParam("screen_name", "geetha r");
		reqbuilder.setAuth(auth);
		requestSpec = reqbuilder.build();
		
		resbuilder = new ResponseSpecBuilder();
		resbuilder.expectStatusCode(200);
		resbuilder.expectBody("user.screen_name", hasItem("geethar02306697"));
		response_spec = resbuilder.build();
			
	Response res = 
		
		given()
				.spec(requestSpec)
				
			.when()
			.get("/user_timeline.json")
			.then()
			.spec(response_spec).extract().response();
			
			
			System.out.println(res.path("user.name").toString());
	
	}
	

}
