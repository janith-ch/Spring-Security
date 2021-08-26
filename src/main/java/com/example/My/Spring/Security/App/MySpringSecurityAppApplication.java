package com.example.My.Spring.Security.App;

import com.example.My.Spring.Security.App.jwt.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class MySpringSecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySpringSecurityAppApplication.class, args);
	}

}
