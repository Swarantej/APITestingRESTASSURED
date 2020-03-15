package restassured.oauth2.apitesting;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
public class GoogleOauthAccessToken {
	
	 
	public static void main(String[] args) {
		
		
		String [] expectedCourses = {"Selenium Webdriver Java","Cypressqer","Protractor"};
		
		/**
		 * Open the below URL and login to Gmail and it will redirect to a New URL copy that and paste as URL
		 * https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php
		 */
		
		String url ="https://rahulshettyacademy.com/getCourse.php?code=4%2FxAEBCNx-ont7xq5ozJFOiMKA57HKTreFEg7N90E7lwRSGgxF3ZzkFKuVAk4GzIBh7LtRVgjLwdkN15iVzeogqL4&scope=email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=none#";
		String partialCode = url.split("code=")[1];
		String code = partialCode.split("&scope")[0];
		
		//Get the Access Token URL and from this we can get the code
		String accessTokenPath = given().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath json = new JsonPath(accessTokenPath);
		String accessToken = json.getString("access_token");
		
		
		
		//Now we will convert the Response to the Class of getCourses instead of asString
		// Response will be converetd to Java Object
		//Using defaultParser method mentione what type of response is expected
		getCourses gc = given().queryParam("access_token",accessToken).expect().defaultParser(Parser.JSON)
				.when()
				.get("https://rahulshettyacademy.com/getCourse.php").as(getCourses.class);
				
		
		System.out.println(gc.getInstructor());
		System.out.println(gc.getLinkedIn());
		System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
	List<API> apiCourses = 	gc.getCourses().getApi();
	for(int i=0;i<apiCourses.size();i++) {
		if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")) {
			System.out.println(apiCourses.get(i).getPrice());
		}
	}
	
	//Create a New Array List to compare the expected values to actual values 
	//Since it is dynamic array we declare Array lis
	ArrayList<String> actualCourses = new ArrayList<String>();
	
	List<WebAutomation> webCourses = gc.getCourses().getWebAutomation();
	for(int i =0;i<webCourses.size();i++) {
		actualCourses.add((webCourses.get(i).getCourseTitle()));
	}
	
	//To Compare ArrayList with Array, we will convert the Array to Array List and then compare
	//Arrays.asList(array) will convert the Array to ArrayList
	
		List<String> expectedCoursesArrayList = Arrays.asList(expectedCourses);
		Assert.assertTrue(actualCourses.equals(expectedCoursesArrayList));
	
	
	
		/*
		 * String response = given().queryParam("access_token",accessToken)
		 * .when().log().all()
		 * .get("https://rahulshettyacademy.com/getCourse.php").asString();
		 * System.out.println(response);
		 */
		
		
	}

}
