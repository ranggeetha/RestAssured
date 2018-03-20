package Tweeter_Account;

import static io.restassured.RestAssured.given;

import org.apache.http.auth.AUTH;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import scala.annotation.elidable;

public class Tweet_Post {
	
	//commment added
	String CONSUMER_KEY = "oDIAynS66RgRxPVyQsXUkDb25";
	String CONSUMER_SECRET = "IRR31DdqWcWulWFUhvdk53c62OP4EOHHIlbsXtrEeBIwfD6srN";
	String ACCESS_TOKEN = "974464648035667968-7aAtDZqsPSOrfhBnTH2rAaxaintxvCl";
	String ACCESS_TOKEN_SECRET = "WM87p8t7Htrao1xVofvaJXwAfkywdKpAdJ9j8CWd3WrAx";
	
	@Test(enabled = false)
	public void first_Post_tweet() {
		
		
		RestAssured.baseURI = "https://api.twitter.com";
		RestAssured.basePath = "/1.1/statuses";
Response res = 
		given()
		.auth()
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
		.queryParam("status", "My First Tweet 20")
		.when()
		.post("/update.json")
		
		.then().statusCode(200)
		
		.extract().response();
		
		JsonPath jstring = new JsonPath(res.asString());
//		String name = jstring.get("user.name");
//		String screen_name = jstring.getString("user.screen_name");
//		
		System.out.println(" User Name " + jstring.get("user.name"));
		System.out.println(" Screen Name " + jstring.get("user.screen_name"));
		

	}
	@Test()
	public void Response_ResponseLog()
	{
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
		
		given()
		.log()
		.ifValidationFails()
		//.headers()
		.auth()
	
		.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
		.param("screen_name", "geetha r")
		
		.when()
		.get("/user_timeline.json")
		 
		.then()
				.statusCode(201)
		.log()
		.headers();
			
		
	}
	
	@Test(enabled = false) 
	public void twitter_Extract()
	{
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
	
	Response res =
		given()
			.auth()
			.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
			.param("screen_name", "geetha r")
			
		.when()
			.get("/user_timeline.json")
		.then()
			.statusCode(200).extract().response();
		
		System.out.println(res.path("text[0]").toString());
		
	}
	
	
	
	
}
