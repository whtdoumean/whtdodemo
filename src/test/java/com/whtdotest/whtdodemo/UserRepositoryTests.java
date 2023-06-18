package com.whtdotest.whtdodemo;

import com.whtdotest.whtdodemo.entity.User;
import com.whtdotest.whtdodemo.entity.UserGroup;
import com.whtdotest.whtdodemo.repository.UserGroupRepository;
import com.whtdotest.whtdodemo.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class UserRepositoryTests {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserGroupRepository userGroupRepository;

	private UUID userGroupId;


	@Test
	@Rollback(value = false)
	void contextLoads() {
//		assertNotNull(userRepository);
//		User user = new User();
//		user.setFirstName("john");
//		user.setSurname("Doe");
//		user.setPassportNumber("12345");
//		userRepository.save(user);
	}

//	@Test
//	@Rollback(value = false)
	@PostConstruct
	void addUserGroup() {
		UserGroup userGroup = new UserGroup();
		userGroup.setUserGroupName("students");
		userGroupRepository.save(userGroup);
		userGroupId = userGroup.getId();

		{
			User user = new User();
			user.setFirstName("john");
			user.setSurname("Connor");
			user.setPassportNumber("123456");
			userGroup.addUser(user);
		}
		{
			User user = new User();
			user.setFirstName("john");
			user.setSurname("Connor");
			user.setPassportNumber("123456");
			userGroup.addUser(user);
		}

		userGroupRepository.save(userGroup);
	}

	@Test
//	@Transactional
	void showSelect() {
		UserGroup userGroup = userGroupRepository.findById(userGroupId).orElseThrow();
		List<User> users = userGroup.getUsers();
	}

	@Test
	@Rollback(value = false)
	void addUserGroupInOneSetting() {
		UserGroup userGroup = userGroupRepository.findById(userGroupId).orElseThrow();
		System.out.println("userGroup.getUsers().size() = " + userGroup.getUsers().size());
		assertEquals(2, userGroup.getUsers().size());
	}

}
