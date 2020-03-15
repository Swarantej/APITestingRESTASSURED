package restassured.jira.apitesting;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import files.ReusableMethods;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class JiraAPIUpdComment {
	Properties prop = new Properties();
	//@BeforeTest
	
	/*
	 * public void getData() throws IOException { FileInputStream fis = new
	 * FileInputStream(""); prop.load(fis); }
	 */
	@Test
	 public void JiraAPIUpdComment() {
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type","application/json")
		.header("Cookie","JSESSIONID="+ReusableMethods.getJiraSessionID())
		.body("{\\r\\n    \\\"body\\\": \\\"Adding COmment using API Automation script.\\\","
				+ "\\r\\n    \\\"visibility\\\": {\\r\\n        \\\"type\\\": "
				+ "\\\"role\\\",\\r\\n        \\\"value\\\": \\\"Administrators\\\"\\r\\n    }\\r\\n}")
		.when().put("rest/api/2/issue/"+ReusableMethods.JiraAPICreateBug()+"/comment"+ReusableMethods.JiraAPIAddComment())
		.then().statusCode(201).extract().response();
		JsonPath js = ReusableMethods.rawJSONConversion(res);
		String commentID = js.get("id");
		System.out.println(commentID);
		
	}

}
