package restuAssure.API.XML;

import org.testng.annotations.Test;

import files.ReusableMethods;

import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class GoogleAPIPostReq {
	@Test
	public void postReq() throws IOException {
		String postData = generateStringFromXML("C:\\Users\\Swaran\\apitesting\\RestAssured\\resources\\sampleXML.xml");
		RestAssured.baseURI = "http://216.10.245.166";
		Response res = given().
		queryParam("key","qaclick123")
		.body(postData).
		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML)
		.extract().response();
		XmlPath xml =ReusableMethods.rawXMLConversion(res);
		String placeid=	xml.get("response.place_id");
		System.out.println(placeid);
		
	}

	/**
	 * Create a Resusable method which converts XML to a string
	 * @throws IOException 
	 */
	
	public static String generateStringFromXML(String xmlPath) throws IOException {
		return new String(Files.readAllBytes(Paths.get(xmlPath)));
	}
}
