package com.whtdotest.whtdodemo;

import com.whtdotest.whtdodemo.entity.User;
import com.whtdotest.whtdodemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
		assertNotNull(userRepository);
		User user = new User();
		user.setFirstName("john");
		user.setSurname("Doe");
	}

}
