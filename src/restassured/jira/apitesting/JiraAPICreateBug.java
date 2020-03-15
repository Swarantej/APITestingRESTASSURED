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


public class JiraAPICreateBug {
	Properties prop = new Properties();
	//@BeforeTest
	
	/*
	 * public void getData() throws IOException { FileInputStream fis = new
	 * FileInputStream(""); prop.load(fis); }
	 */
	@Test
	 public void JiraAPICreatBug() {
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type","application/json")
		.header("Cookie","JSESSIONID="+ReusableMethods.getJiraSessionID())
		.body("{\\r\\n    "
				+ "\\\"fields\\\": {\\r\\n"
				+ " \\\"project\\\":\\r\\n"
				+ "  {\\r\\n"
				+ "\\\"key\\\": \\\"APITEST\\\"\\r\\n"
				+ "},\\r\\n\\\"summary\\\": "
				+ "\\\"second BUG CREATED USING API AUTOMATION.\\\","
				+ "\\r\\n\\\"description\\\": "
				+ "\\\"Creating of an issue using automation script RESTASSURED\\\","
				+ "\\r\\n\\\"issuetype\\\": {\\r\\n\\\"name\\\": "
				+ "\\\"Bug\\\"\\r\\n       }\\r\\n   }\\r\\n}").when().post("/rest/api/2/issue")
		.then().statusCode(201).extract().response();
		JsonPath js = ReusableMethods.rawJSONConversion(res);
		String id = js.get("id");
		System.out.println(id);
		
	}

}
