package com.JIRA;
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;
public class GetIssueDetails {
	
	
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
	@Test
	public void verifyComment() {
		baseURI="http://localhost:8080";
		SessionFilter session=SessionJIRA.getSession(baseURI);
		
		String message="This is message update from ResstAssured Newest";
		String commentResponse=given().log().all().pathParam("id", "10102").header("Content-Type","application/json")
		.body("{\r\n" + 
				"    \"body\": \""+message+"\",\r\n" + 
				"    \"visibility\": {\r\n" + 
				"        \"type\": \"role\",\r\n" + 
				"        \"value\": \"Administrators\"\r\n" + 
				"    }\r\n" + 
				"}").filter(session)
		.when()
		.post("/rest/api/2/issue/{id}/comment")
		.then().assertThat().statusCode(201).log().all().extract().response().asString();
		
		JsonPath js=new JsonPath(commentResponse);
		String cID=js.getString("id");
		String detailsResponse=given().filter(session)
				.pathParam("key", "10102")
				.queryParam("fields", "comment")
				.log().all()
				.when().get("/rest/api/2/issue/{key}")
				.then().log().all()
				.extract().response().asString();
		js=new JsonPath(detailsResponse);
		int commentSize=js.getInt("fields.comment.comments.size()");
		boolean flag=false;
		for(int i=0;i<commentSize;i++) {
			String commentID=js.get("fields.comment.comments["+i+"].id").toString();
			System.out.println(cID+"::"+commentID);
			if(commentID.equalsIgnoreCase(cID)) {
				flag=true;
				String comment=js.get("fields.comment.comments["+i+"].body").toString();
				Assert.assertEquals(message, comment);
				break;
			}
			
		}
		Assert.assertEquals(true, flag);
	}


}
