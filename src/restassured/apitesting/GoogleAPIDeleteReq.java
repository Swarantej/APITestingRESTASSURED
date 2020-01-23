package restassured.apitesting;

import org.testng.annotations.BeforeTest;

import files.PayLoad;
import files.Resources;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GoogleAPIDeleteReq {
	Properties prop = new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
		
		FileInputStream file = new FileInputStream("C:\\Users\\Swaran\\apitesting\\RestAssured\\src\\files\\env.properties");
		prop.load(file);
		//prop.getProperty("URL");
		
	}
	@Test
	public void postReq() {
		RestAssured.baseURI = 	prop.getProperty("URL");
		Response resp = given().
		queryParam("key","qaclick123")
		.body(PayLoad.postPayLoad()).

when().

post(Resources.postData()).

then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

body("status",equalTo("OK"))
.extract()
.response();

	//Since the initial response is in Raw format we are converting to String
		String resStr = resp.asString();
		
	//Now to get the repsonse object we need to traverse to response and response should be in JSON format only
	//COnverting string to JSON using JsonPath
		
		JsonPath js = new JsonPath(resStr);
		String placeID=js.get("place_id");
		System.out.println(placeID);
		//Delete the Query
		
		 given()
		.queryParam("key",prop.getProperty("KEY"))
		.body("{"+"\"place_id\":\""+placeID+"\""+"}")
		.when()
		.post(Resources.deleteResourceData())
		.then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

		body("status",equalTo("OK"));

		
	}

}
