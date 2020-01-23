package restassured.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GoogleAPIGetReq {

	@Test

	//public static void main(String[] args) {
	public void getReq() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://maps.googleapis.com";
		given().
			param("location","-33.8670522,151.1957362").
			param("radius","500").
			param("key","AIzaSyBMaMR3EcJFfBxocdJJ_mxpG1ihzhuEKng").
		when().
			get("/maps/api/place/nearbysearch/json").
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and()
		.body("results[0].name", equalTo("Sydney")).and().header("Server", "scaffolding on HTTPServer2");
	}	
}
