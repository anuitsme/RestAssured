package com.JIRA;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;
public class GetIssueDetails {
	
	@Test
	public void getIssueDetails() {
		baseURI="http://localhost:8080";
		
		String response=given().filter(SessionJIRA.getSession(baseURI))
		.pathParam("key", "10102")
		.queryParam("fields", "comment")
		.log().all()
		.when().get("/rest/api/2/issue/{key}")
		.then().log().all()
		.extract().response().asString();
		System.out.println("**********************************************************");
		System.out.println(response);
	}

}
