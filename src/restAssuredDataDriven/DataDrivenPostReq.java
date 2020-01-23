package restAssuredDataDriven;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.PayLoad;
import files.ReusableMethods;

import static io.restassured.RestAssured.given;

public class DataDrivenPostReq {

	@Test(dataProvider = "BookData")

	//public static void main(String[] args) {
	public void getReq(String isbn , String aisle) {
		// TODO Auto-generated method stub
		RestAssured.baseURI="http://216.10.245.166";
		Response res = given().
			header("Content-Type","application/json")
			.body(PayLoad.addBook(isbn, aisle))
		.when().
		post("/Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response();
		JsonPath json = ReusableMethods.rawJSONConversion(res);
		String bookID = json.get("ID");
		System.out.println(bookID);
		System.out.println(res);	
	}	
	
	@DataProvider(name="BookData")
	public Object[][] getData() {
		
		return new Object[][] {{"ABCxxD","1234"},{"EFxxGH","5678"},{"IJxxKL","9123"}};
	}
}
