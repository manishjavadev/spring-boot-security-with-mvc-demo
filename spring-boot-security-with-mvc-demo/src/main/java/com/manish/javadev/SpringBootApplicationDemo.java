package com.manish.javadev;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.manish.javadev.model.RoleEntity;
import com.manish.javadev.model.UserEntity;
import com.manish.javadev.model.UserRepository;

/**
 * http://www.programming-free.com/2016/01/spring-security-spring-data-jpa.html
 * 
 * @author Manish
 *
 */
@SpringBootApplication
public class SpringBootApplicationDemo implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) throws Throwable {
		SpringApplication.run(SpringBootApplicationDemo.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		saveData();
	}

	private void saveData() {
		RoleEntity roleEntity1 = new RoleEntity("ROLE_ADMIN");
		RoleEntity roleEntity2 = new RoleEntity("ROLE_USER");
		UserEntity userEntity1 = new UserEntity("msmanish95", "Passw0rd", true);
		UserEntity userEntity2 = new UserEntity("msmanish96", "Passw0rd", true);
		userEntity1.getRoles().add(roleEntity1);
		userEntity1.getRoles().add(roleEntity2);
		roleEntity1.setUserEntity(userEntity1);
		roleEntity2.setUserEntity(userEntity1);

		userEntity2.getRoles().add(roleEntity2);
		roleEntity2.setUserEntity(userEntity2);
		System.out.println(userRepository.save(userEntity1));
		System.out.println(userRepository.save(userEntity2));

	}
}