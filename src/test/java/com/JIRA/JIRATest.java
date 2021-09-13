package com.JIRA;

import org.testng.annotations.Test;



import static io.restassured.RestAssured.*;
public class JIRATest {
	
	@Test
	public void CommentTest() {
		
		baseURI="http://localhost:8080";
		//Login Scenario
		
		
		
		//Comment Scenario
		
		given().log().all().pathParam("id", "10102").header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \"This has been commented by Rest Assured API\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(SessionJIRA.getSession(baseURI))
		.when()
		.post("/rest/api/2/issue/{id}/comment")
		.then().assertThat().statusCode(201).log().all();
		
	}

}
