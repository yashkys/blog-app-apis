package com.kys.blog_app_bakend;

import com.kys.blog_app_bakend.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BlogAppApisApplicationTests {

	@Autowired
	private UserRepository userRepository;

	@Test
	void contextLoads() {
	}

	@Test
	public void repoTest() {
		String className = this.userRepository.getClass().getName();
		String packageName = this.userRepository.getClass().getPackageName();
		System.out.println("ClassName : " + className + "\nPackageName: " + packageName);

	}


}
