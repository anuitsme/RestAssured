package com.Test;

import org.junit.Assert;

import Files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJson {
	
	public static void main(String[] args) {
		JsonPath js=new JsonPath(Payload.ComplexJson());
		System.out.println(js.getInt("courses.size()"));
		System.out.println(js.getInt("dashboard.purchaseAmount"));
		System.out.println(js.getString("courses[0].title"));
		int size=js.getInt("courses.size()");
		int sum=0;
		int priceRPA=0;
		for(int i=0;i<size;i++) {
			System.out.println(js.getString("courses["+i+"].title")+"::"+js.getInt("courses["+i+"].price")+"::"+js.getInt("courses["+i+"].copies"));
			sum=sum+js.getInt("courses["+i+"].price")*js.getInt("courses["+i+"].copies");
			if(js.getString("courses[+"+i+"].title").equalsIgnoreCase("RPA")) {
				priceRPA=js.getInt("courses["+i+"].price");
			}
		}
		System.out.println("Total Purchase calculated="+sum);
		System.out.println("RPA course Price:="+priceRPA);
		Assert.assertEquals(js.getInt("dashboard.purchaseAmount"), sum);
		
	}

}
