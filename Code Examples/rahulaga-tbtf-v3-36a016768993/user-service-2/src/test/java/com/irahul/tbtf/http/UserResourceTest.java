package com.irahul.tbtf.http;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
import com.irahul.tbtf.entity.impl.UserImpl;
import com.irahul.tbtf.http.entity.HttpUser;
import com.irahul.tbtf.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserResourceTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@MockBean
	private UserRepository userRepository;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testGetUserNoParams() throws Exception {
		MvcResult mockResponse = mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CONFLICT.value());

		String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/users-error-missing-data.json")));
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
	}
	
	@Test
	public void testGetUserFirstName() throws Exception {
		doReturn(Arrays.asList(new UserImpl())).when(userRepository).findByFirstNameContaining("foo");
		
		MvcResult mockResponse = mockMvc.perform(get("/users").param("firstName","foo").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

		String expectedResponseBody = "[{\"id\":0}]";
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
		
		verify(userRepository, times(1)).findByFirstNameContaining("foo");
		verifyNoMoreInteractions(userRepository);
	}
	
	@Test
	public void testGetUserLastName() throws Exception {
		doReturn(Arrays.asList(new UserImpl())).when(userRepository).findByLastNameContaining("bar");
		
		MvcResult mockResponse = mockMvc.perform(get("/users").param("lastName","bar").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

		String expectedResponseBody = "[{\"id\":0}]";
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
		
		verify(userRepository, times(1)).findByLastNameContaining("bar");
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	public void testGetUserById() throws Exception {
		UserImpl mockUser = new UserImpl();
		mockUser.setFirstName("mockFname");
		doReturn(mockUser).when(userRepository).findOne(123l);
		
		MvcResult mockResponse = mockMvc.perform(get("/users/123").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());

		String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/users-get.json")));
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
		
		verify(userRepository, times(1)).findOne(123l);
		verifyNoMoreInteractions(userRepository);
	}

	@Test
	public void testCreateUser() throws Exception {
		UserImpl mockUser = new UserImpl();
		mockUser.setFirstName("foo");
		mockUser.setLastName("bar");
		mockUser.setPin("1234");
		//have to any() matcher as md5 makes exact match not possible
		doReturn(mockUser).when(userRepository).save(any(UserImpl.class));
		
		MvcResult mockResponse = mockMvc.perform(post("/users").accept(MediaType.APPLICATION_JSON).content(getNewUser())
				.contentType(MediaType.APPLICATION_JSON)).andDo(print()).andReturn();
		assertThat(mockResponse.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());

		String expectedResponseBody = new String(Files.readAllBytes(Paths.get("src/test/resources/users-create.json")));
		String mvcResponse = new String(mockResponse.getResponse().getContentAsByteArray());
		JSONAssert.assertEquals(expectedResponseBody, mvcResponse, true);
		
		verify(userRepository, times(1)).save(any(UserImpl.class));
		verifyNoMoreInteractions(userRepository);
	}

	private String getNewUser() throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		HttpUser user = new HttpUser();
		user.firstName = "foo";
		user.lastName = "bar";
		user.pin = "1234";
		return mapper.writeValueAsString(user);
	}
}
