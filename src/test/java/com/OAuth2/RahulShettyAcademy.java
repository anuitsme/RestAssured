package com.OAuth2;
import static io.restassured.RestAssured.*;
import org.testng.annotations.Test;

import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

public class RahulShettyAcademy {
	
	
	
	public String GetAccessToken() {
		String code="4%2F0AX4XfWjU5LHi46hh3c_j-STOg44-EGv4p1lBKkV47f9fhy5YEQDXjfjtDxs3US1x1n6DTw";
		String response=given().urlEncodingEnabled(false)
		.queryParams("code", code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		queryParams("redirect_uri","https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js=new JsonPath(response);
		System.out.println(response);
		String accessToken= js.get("access_token").toString();
		System.out.println(accessToken);
		return accessToken;
		
	}
	
	@Test
	public void GetCourses() throws InterruptedException {
		String accessToken=
				"ya29.a0ARrdaM-iiGQ3pT79mcLd7bbih5dPXV7u9ez8Etz8KojuJ-6sQGVnh8vcaMvuM0iP7fCOi08O_KNFZi1a7EbR7LqvG1Jbr0CBb8KbPuqKm63MF-TEwvoHuYI8oKA_FFZztPQtkTv-tFNA8sChtNM1CGL74Rk9EQ";
		GetCourse result=given().urlEncodingEnabled(false)
				.queryParam("access_token", accessToken).expect().defaultParser(Parser.JSON)
		.when()
		.get("https://rahulshettyacademy.com/getCourse.php").as(GetCourse.class);
		System.out.println(result);
		
		System.out.println(result.getInstructor());
		System.out.println(result.getExpertise());
		System.out.println(result.getServices());
		System.out.println(result.getUrl());
		System.out.println(result.getCourses().toString());
	}
	
}
