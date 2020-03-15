package files;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ReusableMethods {
	
	public static XmlPath rawXMLConversion(Response res) {
		String xmlResponse = res.asString();
		XmlPath xml = new XmlPath(xmlResponse);
		return xml;
	}
	
	public static JsonPath rawJSONConversion(Response res) {
		String jsonResponse = res.asString();
		JsonPath json = new JsonPath(jsonResponse);
		return json;
	}
	
	public static String getJiraSessionID() {
		 RestAssured.baseURI = "http://localhost:8080";
		Response res =  given().header("Content-Type","application/json")
		 .body("{ \"username\": \"vswarantej\", \"password\": \"jira@123\" }")
		 .when()
		 .post("/rest/auth/1/session")
		 .then().statusCode(200)
		 .extract().response();
		
		//conver the raw response to JSON
		 JsonPath js = ReusableMethods.rawJSONConversion(res);
		String sessionId=  js.get("session.value");
		 
		 return sessionId;
		
	}
	
	public static String JiraAPICreateBug() {
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
		String bugID = js.get("id");
		System.out.println(bugID);
		return bugID;
		
	}

	public static String JiraAPIAddComment() {
		RestAssured.baseURI = "http://localhost:8080";
		Response res = given().header("Content-Type","application/json")
		.header("Cookie","JSESSIONID="+ReusableMethods.getJiraSessionID())
		.body("{\\r\\n    \\\"body\\\": \\\"Adding COmment using API Automation script.\\\","
				+ "\\r\\n    \\\"visibility\\\": {\\r\\n        \\\"type\\\": "
				+ "\\\"role\\\",\\r\\n        \\\"value\\\": \\\"Administrators\\\"\\r\\n    }\\r\\n}")
		.when().post("rest/api/2/issue/"+ReusableMethods.JiraAPICreateBug()+"/comment")
		.then().statusCode(201).extract().response();
		JsonPath js = ReusableMethods.rawJSONConversion(res);
		String commentID = js.get("id");
		System.out.println(commentID);
		return commentID;
		
	}
	
}
