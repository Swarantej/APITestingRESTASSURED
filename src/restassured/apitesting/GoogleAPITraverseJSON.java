package restassured.apitesting;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;

import files.ReusableMethods;

import static io.restassured.RestAssured.given;

public class GoogleAPITraverseJSON {

	@Test

	//public static void main(String[] args) {
	public void getReq() {
		// TODO Auto-generated method stub
		RestAssured.baseURI="https://216.10.245.166";
		Response res = given().
			header("Content-Type","application/json")
			.param("key","AIzaSyBMaMR3EcJFfBxocdJJ_mxpG1ihzhuEKng").log().all().
		when().
		post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response();
		
		
		JsonPath json = ReusableMethods.rawJSONConversion(res);
		String bookID = json.get("ID");
		System.out.println(bookID);
		System.out.println(res);
	
		
	
	}	
}
