package com.api.tests;

import static io.restassured.RestAssured.given;

import java.io.File;
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
		
		File jsonFile = new File("src/test/resources/jsondata/client.json");
		File jsonFile2 = new File("src/test/resources/jsondata/client2.json");
		//POST with json file
		//given().auth().basic("username", "password").contentType(ContentType.JSON).body(jsonFile).post("/client");
		Response response = given().contentType(ContentType.JSON).log().all().body(jsonFile).post("/client");
		System.out.println("POST status code : " + response.getStatusCode());
		
		//DELETE
		String clientId = "3";
		response = given().pathParam("id", clientId).when().delete("/client/{id}");
		System.out.println("DELETE status code : " + response.getStatusCode());
		
		//PUT
		String updatedClient = "2";
		response = given().contentType(ContentType.JSON).pathParam("id", updatedClient).body(jsonFile2).put("/client/{id}");
		System.out.println("PUT status message : " + response.getStatusCode());
       
        
        //GET
        //Response resp = given().auth().basic("userName", "password").contentType(ContentType.JSON).log().all().get("/clients");
		response = given().contentType(ContentType.JSON).get("/clients");
		int statusCode = response.getStatusCode();
		System.out.println("GET Status Code : " + statusCode);

		List<Integer> ids = response.then().extract().path("id");
		List<String> firstName = response.then().extract().path("firstName");
		List<String> lastName = response.then().extract().path("lastName");
		List<String> email = response.then().extract().path("email");

		for (int i = 0; i < ids.size(); i++) {
			System.out.println("id : " + ids.get(i));
			System.out.println("first name : " + firstName.get(i));
			System.out.println("last name : " + lastName.get(i));
			System.out.println("email : " + email.get(i));
			System.out.println("____________________________________");
			//resp.prettyPrint();

		}

	}

}
