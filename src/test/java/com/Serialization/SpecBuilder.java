package com.Serialization;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
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
	public ResponseSpecification SetResponseSpecification() {
		return new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
	}
	
	public RequestSpecification SetSpecBuilder() {
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
		.addQueryParam("key", "qaclick123")
		.setContentType(ContentType.JSON).build();
		return req;
	}
	
	@Test
	public void TestAddAPI() {
		
		RequestSpecification req=given().log().all().spec(SetSpecBuilder()).body(setData());
		Response res=req.when().post("/maps/api/place/add/json")
		.then().assertThat().spec(SetResponseSpecification()).extract().response();
		System.out.println(res.asString() );
	}
	
	
}
