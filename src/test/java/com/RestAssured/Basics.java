package com.RestAssured;


import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class Basics {
	
	
	@Test
	public void addPlace() {
		
		//POST REQUEST
		baseURI="https://rahulshettyacademy.com";
		System.out.println("************************ POST Request *********************************************");
		String response=given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
		.body(Payload.addPlace()).when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		//System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.get("place_id");
		
		//PUT REQUEST
		System.out.println("************************ PUT Request *********************************************");
		given().log().all().queryParam("key", "qaclick123").headers("Content-Type","application/json")
		.body("{\r\n"
				+ "\"place_id\":\""+placeId+"\",\r\n"
				+ "\"address\":\"Sushant Lok DLF phase1 GGN\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}\r\n"
				+ "")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		//GET REQUEST
		System.out.println("************************ GET Request *********************************************");
		String getResponse=given().log().all().queryParams("key", "qaclick123", "place_id",placeId)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		js=new JsonPath(getResponse);
		String address=js.getString("address");
		Assert.assertEquals(address, "Sushant Lok DLF phase1 GGN");
	}

}
