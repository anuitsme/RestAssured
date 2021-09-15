package com.Serialization;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

public class CreateObject {
	
	public SerializedClass setData() {
		SerializedClass sr= new SerializedClass();
		Location loc=new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		sr.setLocation(loc);
		sr.setAccuracy(50);
		sr.setName("Anurag Yadav");
		sr.setAddress("LS-2 627 Sector F Jankipuram Lucknow");
		sr.setPhone_number("(+91) 955 986 6689");
		List<String> types=new ArrayList<String>();
		types.add("Shoe Park");
		types.add("Test Data");
		sr.setTypes(types);
		sr.setWebsite("anuragyadav05@live.in");
		sr.setLanguage("Hindi-IN");
		
		return sr;
	}
	
	@Test
	public void TestAddAPI() {
		baseURI="https://rahulshettyacademy.com";
		String response=given().log().all().queryParam("key", "qaclick123")
		.body(setData())
		.when()
		.post("/maps/api/place/add/json")
		.then().assertThat()
		.statusCode(200).extract().response().asString();
		System.out.println(response);
	}
	
	
}
