package com.PollutionMd_API.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class ApiApplication extends SpringBootServletInitializer
{

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(ApiApplication.class);
	}
	
	public static void main(String[] args) 
	{
		//run
		SpringApplication.run(ApiApplication.class, args);
	}

}







/*
@SpringBootApplication
public class ApiApplication 
{

	public static void main(String[] args) {
		//run
		SpringApplication.run(ApiApplication.class, args);
	}

}
*/
