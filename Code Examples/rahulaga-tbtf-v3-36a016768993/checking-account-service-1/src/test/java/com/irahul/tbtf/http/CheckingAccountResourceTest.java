package com.irahul.tbtf.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irahul.tbtf.http.entity.HttpCheckingAccount;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CheckingAccountResourceTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetAccountById() throws Exception {
		MvcResult mockResponse = mockMvc.perform(get("/checking/123").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

		String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/checking-id.json")));
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
	}

	@Test
	public void testCreateAccount() throws Exception {
		MvcResult mockResponse = mockMvc.perform(post("/checking").accept(MediaType.APPLICATION_JSON).content(getNewUser())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

		String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/checking-create.json")));
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
	}

	private String getNewUser() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		HttpCheckingAccount account = new HttpCheckingAccount();
		account.ownerId=8899;
		account.currency="USD";
		account.balance=0;
		return mapper.writeValueAsString(account);
	}
}
