package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

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

}
