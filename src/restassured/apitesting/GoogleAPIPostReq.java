package restassured.apitesting;

import org.testng.annotations.Test;
import static org.hamcrest.Matchers.equalTo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class GoogleAPIPostReq {
	@Test
	public void postReq() {
		RestAssured.baseURI = "http://216.10.245.166";
		given().
		queryParam("key","qaclick123")
		.body("{"+

  "\"location\": {"+

    "\"lat\": -33.8669910,"+

    "\"lng\": 151.1958950"+

  "},"+

  "\"accuracy\": 50,"+

  "\"name\": \"Google Shoes!\","+

  "\"phone_number\": \"(02) 9374 4000\","+

  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+

  "\"types\": [\"shoe_store\"],"+

  "\"website\": \"http://www.google.com.au/\","+

  "\"language\": \"en-AU\""+

"}").

when().

post("/maps/api/place/add/json").

then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().

body("status",equalTo("OK"));
		/*.body("{"+

			  "\"location\": {"+
			
			    "\"lat\": -33.8669710789,"+
			
			    "\"lng\": 151.1958750456"+
			
			  "},"+
			
			  "\"accuracy\": 50,"+
			
			  "\"name\": \"Google Shoes!\","+
			
			  "\"phone_number\": \"(02) 9374 4000\","+
			
			  "\"address\": \"48 Pirrama Road, Pyrmont, NSW 2009, Australia\","+
			
			  "\"types\": [\"shoe_store\"],"+
			
			  "\"website\": \"http://www.google.com.au/\","+
			
			  "\"language\": \"en-AU\""+
			
			"}")
		.when()
		.post("/maps/api/place/add/json")
		.then()
		.assertThat()
		.statusCode(200)
		.and()
		.body("Status", equalTo("OK"));*/
	}

}
