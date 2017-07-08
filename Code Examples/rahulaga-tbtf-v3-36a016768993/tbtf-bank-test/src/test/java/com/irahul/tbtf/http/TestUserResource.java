package com.irahul.tbtf.http;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.Test;

/**
 * Tests using Rest Assured
 * 
 * @author rahul
 *
 */
public class TestUserResource {
	private static final String HTTP_HOST = "http://localhost:9080";

	@Test
	public void testGetUsersNoParams() {
		given().log().all()
		.when().get(HTTP_HOST + "/users")
		.then().log().all().statusCode(409).body("status", equalTo(409))
				.body("code", equalTo("MISSING_DATA")).body("message", equalTo("Atleast 1 required"));

	}

	@Test
	public void testCreateAndGetUser() {
		HttpUser user = new HttpUser();
		user.firstName = "foo";
		user.lastName = "bar";
		user.pin = "1234";

		// execute post and retrieve response
		HttpUser createdUser = 
				given().log().all().contentType("application/json").body(user)
				.when().post(HTTP_HOST + "/users")
				.as(HttpUser.class);

		assertThat(createdUser.firstName).isEqualTo("foo");
		assertThat(createdUser.lastName).isEqualTo("bar");
		assertThat(createdUser.pin).isNull();
		assertThat(createdUser.id).isNotNull();

		given().log().all().pathParam("userId", createdUser.id)
		.when().get(HTTP_HOST + "/users/{userId}")
		.then().log().all().statusCode(200)
				.body("firstName", equalTo("foo")).body("lastName", equalTo("bar"));

	}
}
