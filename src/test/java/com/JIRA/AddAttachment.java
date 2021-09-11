package com.JIRA;

import org.testng.annotations.Test;


import static io.restassured.RestAssured.*;

import java.io.File;
public class AddAttachment {
	
	@Test
	public void AttachImage() {
		
		baseURI="http://localhost:8080";
		
		
		//Attachment Scenario
		given().log().all()
		.header("X-Atlassian-Token","no-check")
		.header("Content-Type","multipart/form-data")
		.pathParam("key","10102")
		.multiPart("file",new File("Jira.txt"))
		.filter(SessionJIRA.getSession(baseURI)).
		when()
		.post("/rest/api/2/issue/{key}/attachments")
		.then().log().all().assertThat().statusCode(200);
	}

}
