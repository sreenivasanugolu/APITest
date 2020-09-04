package com.api.tests;

import static io.restassured.RestAssured.given;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TestOne {

	@Test
	public void getClients() {
		// end point with path parameters
		// end point with query parameters - ?id=1
		// authentication - basic auth
		// authentication - OAuth - id pwd
		// authentication - most used - web token
		// standard GET, POST, PUT and DELETE

		RestAssured.baseURI = "http://localhost:8080/api";
		Response resp = given().contentType(ContentType.JSON).log().all().get("/clients");
		int statusCode = resp.getStatusCode();
		System.out.println("Status Code : " + statusCode);

		List<Integer> ids = resp.then().extract().path("id");
		List<String> firstName = resp.then().extract().path("firstName");
		List<String> lastName = resp.then().extract().path("lastName");
		List<String> email = resp.then().extract().path("email");

		for (int i = 0; i < ids.size(); i++) {
			System.out.println("id : " + ids.get(i));
			System.out.println("first name : " + firstName.get(i));
			System.out.println("last name : " + lastName.get(i));
			System.out.println("email : " + email.get(i));
			System.out.println("____________________________________");
			resp.prettyPrint();

		}

	}

}
