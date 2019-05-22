package com.suneee.yangbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@RestController
public class YangboApplication {

	public static void main(String[] args) {
		SpringApplication.run(YangboApplication.class, args);
	}

	@RequestMapping("/")
	public String hello(){
		return "hello yangbo05527 world";
	}

}
