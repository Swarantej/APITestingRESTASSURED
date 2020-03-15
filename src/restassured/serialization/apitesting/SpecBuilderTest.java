package restassured.serialization.apitesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;
public class SpecBuilderTest {

	public static void main(String[] args) {
		
		//Create the Object of the POJO CLasses
		
		SerializePostGoogleAddPlace postDetails = new SerializePostGoogleAddPlace();
		postDetails.setAccuracy(50);
		postDetails.setAddress("29, side layout, cohen 09");
		postDetails.setLanguage("French-IN");
		postDetails.setPhone_number("(+91) 983 893 3937");
		postDetails.setWebsite("https://rahulshettyacademy.com");
		postDetails.setName("Frontline House");
		
		//Since the type is LIST so we create a List object and add the details to the list obj and pass the same to SET obj
		
		List<String> myList = new ArrayList<String>();
		myList.add("shoe park");
		myList.add("shop");
		postDetails.setTypes(myList);
		
		//Since the location is nested JSON so we created another class, so set the values to the obj of that class
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		postDetails.setLocation(loc);
		
		//Building Request Spec Builder
		 RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		 .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
		 
		//Builinf Response Spec Builder
		 
		 ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		
		// TODO Auto-generated method stub
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		//Seperating Request seperately from Reponse
		RequestSpecification req = given().spec(request)
		.body(postDetails);
		
		
		Response res = req.when()
		.post("/maps/api/place/add/json")
		.then().spec(response).extract().response();
		String responseResult = res.asString();
		System.out.println(responseResult);
		
	}

}
