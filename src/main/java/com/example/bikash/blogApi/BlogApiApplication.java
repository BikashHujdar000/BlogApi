package com.example.bikash.blogApi;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BlogApiApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BlogApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("databse is confermed and running well");

	}

	@Bean
	public ModelMapper modelMapper()
	{

		return new ModelMapper();

	}

	
}
