package restAssuredDataDriven;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoad;
import files.ReusableMethods;

import static io.restassured.RestAssured.given;

public class DataDrivenPostReqExtrnal {

	@Test

	//public static void main(String[] args) {
	public void getReq() throws IOException {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://216.10.245.166";
		Response res = given().
			header("Content-Type","application/json")
			.body(generateStringFromJSON("C:\\Users\\Swaran\\apitesting\\RestAssured\\resources\\payLoad.json"))
		.when().
		post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response();
		JsonPath json = ReusableMethods.rawJSONConversion(res);
		String bookID = json.get("ID");
		System.out.println(bookID);
		System.out.println(res);	
	}	
	/**
	 * Create a Resusable method which converts XML to a string
	 * @throws IOException 
	 */
	
	public static String generateStringFromJSON(String JSONPath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(JSONPath)));
	}
	
}
