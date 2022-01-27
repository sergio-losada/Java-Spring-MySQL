package com.example.javaspringmysql.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Optional;

import com.example.javaspringmysql.dto.Profile;
import com.example.javaspringmysql.util.Status;
import com.example.javaspringmysql.mock.MockingProfile;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileServiceTest {

	@MockBean
	private ProfileService profileService;

    @BeforeEach
	public void init() {
		MockingProfile.mockingApi(profileService);
    }

	@Test
	@Order(1)
	public void test01_get() {
		// Testing request
		ArrayList<Profile> response = this.profileService.get();
		// Testing response
		assertEquals(response.size(), 2, "Response size must be 2");
		assertEquals(response.get(0).getUserId(), "id1", "Profile ids must be equals");
		assertEquals(response.get(0).getName(), "mockingGet1", "Profile names must be equals");
		assertEquals(response.get(0).getStatus(), Status.active, "Profile status must be equals");	
		assertEquals(response.get(1).getUserId(), "id2", "Profile ids must be equals");
		assertEquals(response.get(1).getName(), "mockingGet2", "Profile names must be equals");
		assertEquals(response.get(1).getStatus(), Status.inactive, "Profile status must be equals");
		// Mockito verification that the method was only invoked once
		Mockito.verify(profileService).get();
	}

	@Test
	@Order(2)
	public void test02_post() {
		// Testing request (no parameterized constructor needed as the object to insert is mocked)
		Profile profile = new Profile();
		Profile response = this.profileService.post(profile);
		// Testing response
		assertEquals(response.getUserId(), "id3", "Profile ids must be equals");
		assertEquals(response.getName(), "mockingPost3", "Profile names must be equals");
		assertEquals(response.getStatus(), Status.active, "Profile status must be equals");	
		// Mockito verification that the method was only invoked once
		Mockito.verify(profileService).post(profile);
	}

    @Test
	@Order(3)
	public void test03_put() {
		// Testing request (no parameterized constructor needed as the object to update is mocked)
		Profile profile = new Profile();
		String userId = new String();
		Profile response = this.profileService.put(profile, userId);
		// Testing response
		assertEquals(response.getUserId(), "id4", "Profile ids must be equals");
		assertEquals(response.getName(), "mockingPut4", "Profile names must be equals");
		assertEquals(response.getStatus(), Status.inactive, "Profile status must be equals");	
	}

    @Test
	@Order(4)
	public void test04_delete() {
		// Testing request (no real API name needed as the object to delete is mocked)
		this.profileService.delete(new String());
		// Mockito verification that the method was only invoked once (No testing response as the method is void)
		Mockito.verify(profileService).delete(new String());
	}

    @Test
	@Order(5)
	public void test05_getByKey() {
		// Testing request (no real API name needed as the object to retrieve is mocked)
		Optional<Profile> response = this.profileService.getByKey(new String());
		// Testing response
		assertTrue(response.isPresent() && !response.isEmpty(), "Response entity must be present");
		assertEquals(response.get().getUserId(), "id1", "Profile ids must be equals");
		assertEquals(response.get().getName(), "mockingGetByKey", "Profile names must be equals");
		assertEquals(response.get().getStatus(), Status.away, "Profile status must be equals");	
		// Mockito verification that the method was only invoked once
		Mockito.verify(profileService).getByKey(new String());
	}

}
