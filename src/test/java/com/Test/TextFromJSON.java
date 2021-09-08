package com.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.annotations.Test;


import io.restassured.path.json.JsonPath;

public class TextFromJSON {
	
	@Test
	public void addPlace() throws IOException {
		Path filePath=Paths.get("D:\\z003sy8d\\Softwares_Updated\\SeleniumWorkspace\\data.json");
		//POST REQUEST
		baseURI="https://rahulshettyacademy.com";
		System.out.println("************************ POST Request *********************************************");
		String response=given()
				.log().all().
				queryParam("key", "qaclick123").
				headers("Content-Type","application/json")
		.body(new String (Files.readAllBytes(filePath)))
		.when()
		.post("/maps/api/place/add/json")
		.then()
		.assertThat()
		.statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)")
		.extract().response().asString();
		
		//System.out.println(response);
		JsonPath js=new JsonPath(response);
		String placeId=js.get("place_id");
		System.out.println(placeId);
		
	}

}
