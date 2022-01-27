package com.example.javaspringmysql.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.example.javaspringmysql.dto.Profile;
import com.example.javaspringmysql.util.Status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@RunWith(SpringRunner.class)
public class ProfileControllerTest {

	private WebTestClient webTestClient;

    private static String serverPort = "8080";
	private final static String BASE_URL = "http://localhost:" + serverPort;
	private final static String ABSOLUTE_URI = BASE_URL + "/user";
	
    @BeforeEach
	public void init() {
		webTestClient = WebTestClient.bindToServer().baseUrl(BASE_URL).build();
    }

	@Test
	@Order(1)
	public void test01_post() {
		// Initialization: Profile object to insert
		Profile profile = new Profile("id1", "user1", Status.active);
		// POST - WebTestClient
		this
		.webTestClient
		.post()
		.uri(ABSOLUTE_URI)
		.bodyValue(profile)
		.exchange()
		.expectStatus().isCreated()
		.expectBody(Profile.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.CREATED, "HTTP status code must be 201 CREATED");
			// Testing response body
			assertNotNull(response.getResponseBody(), "Response body must not be null");
			assertTrue(response.getResponseBody() instanceof Profile, "Response body must be a Profile object");
			assertEquals(response.getResponseBody().getUserId(), "id1", "Profile ids must be equals");
			assertEquals(response.getResponseBody().getName(), "user1", "Profile names must be equals");
			assertEquals(response.getResponseBody().getStatus(), Status.active, "PRofile status must be equals");	
		});
	}

	@Test
	@Order(2)
	public void test02_put() {
		// Initialization: Profile object to update
		Profile profile = new Profile("id1", "user1", Status.online);
		// PUT - WebTestClient
		this
		.webTestClient
		.put()
		.uri(ABSOLUTE_URI + "/id1")
		.bodyValue(profile)
		.exchange()
		.expectStatus().isOk()
		.expectBody(Profile.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
			// Testing response body
			assertNotNull(response.getResponseBody(), "Response body must not be null");
			assertTrue(response.getResponseBody() instanceof Profile, "Response body must be a Profile object");
			assertEquals(response.getResponseBody().getUserId(), "id1", "Profile ids must be equals");
			assertEquals(response.getResponseBody().getName(), "user1", "Profile names must be equals");
			assertNotEquals(response.getResponseBody().getStatus(), Status.active, "Profile status must not be active anymore");
			assertEquals(response.getResponseBody().getStatus(), Status.online, "Profile status must now be online");
		});
	}

    @Test
	@Order(3)
	public void test03_get() {
		// GET - WebTestClient
		this
		.webTestClient
		.get()
		.uri(ABSOLUTE_URI)
		.exchange()
		.expectStatus().isOk()
		.expectBodyList(Profile.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must be not null");
			assertEquals(response.getStatus(), HttpStatus.OK, "HTTP status code must be 200 OK");
			// Testing response body
			assertNotNull(response.getResponseBody(), "Response body must not be null");
			assertTrue(response.getResponseBody().size() >= 1, "Response body must have at least 1 object");
			for(Profile profile: response.getResponseBody()){
				assertTrue(profile instanceof Profile, "Response body must be a list of Profile objects");
				if(profile.getUserId().equals("id1")){
					assertEquals(profile.getName(), "user1", "Profile names must be equals");
					assertEquals(profile.getStatus(), Status.online, "Profile status must be online");
				}
				else if(profile.getUserId().equals("id2")){
					assertEquals(profile.getName(), "user2", "Profile names must be equals");
					assertEquals(profile.getStatus(), Status.inactive, "Profile status must be inactive");
				}
			}
		});
	}

	@Test
	@Order(4)
	public void test04_getByKey() {
		// GETBYKEY - WebTestClient
		this
		.webTestClient
		.get()
		.uri(ABSOLUTE_URI + "/id1")
		.exchange()
		.expectStatus().isOk()
		.expectBody(Profile.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response must not be null");
			assertEquals(response.getStatus(), HttpStatus.OK, "Status code must be 200 OK");
			// Testing response body
			assertNotNull(response.getResponseBody(), "Response body must not be null");
			assertTrue(response.getResponseBody() instanceof Profile, "Response body must be an Api object");
			assertEquals(response.getResponseBody().getUserId(), "id1", "Profile ids must be equals");
			assertEquals(response.getResponseBody().getName(), "user1", "Profile names must be equals");
			assertEquals(response.getResponseBody().getStatus(), Status.online, "Profile status must now be online");
		});
	}

	@Test
	@Order(5)
	public void test05_delete() {
		// DELETE - WebTestClient
		this
		.webTestClient
		.delete()
		.uri(ABSOLUTE_URI + "/id1")
		.exchange()
		.expectStatus().isNoContent()
		.expectBody(Void.class)
		.consumeWith(response -> {
			// Testing response
			assertNotNull(response, "Response entity must be not null");
			assertEquals(response.getStatus(), HttpStatus.NO_CONTENT, "HTTP status code must be 204 NO CONTENT");
			// Testing response body
			assertNull(response.getResponseBody(), "Response body must be null");	
		});
	}

}
