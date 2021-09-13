package com.JIRA;

import static io.restassured.RestAssured.*;


import io.restassured.filter.session.SessionFilter;

public class SessionJIRA {
	
	public static SessionFilter getSession(String url) {
		SessionFilter session=new SessionFilter();
		baseURI=url;
		given().relaxedHTTPSValidation()
		.log().all()
		.header("Content-Type","application/json").
		body("{ \"username\": \"anuitsme\", \"password\": \"Swapnil1$\" }").filter(session)
		.when()
		.post("/rest/auth/1/session")
		.then().log().all()
		.extract().response().asString();
		return session;
	}

}
