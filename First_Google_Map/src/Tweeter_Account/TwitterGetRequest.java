package Tweeter_Account;

import org.testng.annotations.Test;

import com.github.scribejava.apis.GitHubApi;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TwitterGetRequest {
	
	String CONSUMER_KEY = "oDIAynS66RgRxPVyQsXUkDb25";
	String CONSUMER_SECRET = "IRR31DdqWcWulWFUhvdk53c62OP4EOHHIlbsXtrEeBIwfD6srN";
	String ACCESS_TOKEN = "974464648035667968-7aAtDZqsPSOrfhBnTH2rAaxaintxvCl";
	String ACCESS_TOKEN_SECRET = "WM87p8t7Htrao1xVofvaJXwAfkywdKpAdJ9j8CWd3WrAx";
	String tweetid;
	
	@Test()
	public void PostTweet()
	{
		
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath ="/1.1/statuses/";
		
	Response res =	
		given()
			.auth()
			.oauth(CONSUMER_KEY, CONSUMER_SECRET, ACCESS_TOKEN, ACCESS_TOKEN_SECRET)
			.param("status", "My Tweet 11")
			
			.when()
				.post("update.json")
			.then()
			.statusCode(200).extract().response();
	
		tweetid = res.path("id_str");
		System.out.println("Tweet Id " + tweetid);	
		
	}
	
	@Test( dependsOnMethods="PostTweet")
	public void getTweet()
	{
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
		
		System.out.println("The id in gettweet is " + tweetid);
		
		Response res =	
		
		given()
			.auth()
			.oauth(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,ACCESS_TOKEN_SECRET)
			.param("id", tweetid)
		.when()
			.get("/show.json")
		.then()
			.statusCode(200).extract().response();
		
		
		System.out.println("The Text is " + res.path("text").toString());
		
	}
	
	
	@Test(dependsOnMethods="getTweet")
	public void deletetweet()
	{
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
		
		given()
		.auth()
		.oauth(CONSUMER_KEY,CONSUMER_SECRET, ACCESS_TOKEN,ACCESS_TOKEN_SECRET )
		.queryParam("id", tweetid)
		
		.when()
			.post("/destroy.json")
		
		.then()
		.statusCode(200);
	}

	@Test(dependsOnMethods="getTweet" , enabled=false)
	public void deletetweet_PathParam()
	{
		RestAssured.baseURI="https://api.twitter.com";
		RestAssured.basePath="/1.1/statuses";
		
		given()
		.auth()
		.oauth(CONSUMER_KEY,CONSUMER_SECRET, ACCESS_TOKEN,ACCESS_TOKEN_SECRET )
		.pathParam("id", tweetid)
		
		.when()
			.post("/destroy/{id}.json")
		
		.then()
		.statusCode(200);
	}
}
