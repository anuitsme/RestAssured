package com.RestAssured;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payload;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class LibraryAPI {
	
	@Test(dataProvider="NewBookData")
	public void TestLibraryAPI(String isbn,String aisle) {
		
		baseURI="http://216.10.245.166";
		String response=given().log().all().header("Content-Type", "application/json")
		.body(Payload.AddBook(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200).extract().response().asString();
		JsonPath js=new JsonPath(response);
		System.out.println(js.getString("ID"));
				
		
	}
	
	@DataProvider(name="NewBookData")
	public Object[][] TestData() {
		
		return new Object[][] {{"omelet","123"},{"eggcurry","124"},{"paneerLababdar","125"},{"ButterPaneerMasala","125"}};
		
	}

}
