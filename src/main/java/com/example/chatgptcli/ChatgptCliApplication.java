package com.example.chatgptcli;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.net.http.HttpClient;

@SpringBootApplication
public class ChatgptCliApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatgptCliApplication.class, args);
	}

	@Bean
	HttpClient httpClient() {
		return HttpClient.newBuilder()
				.build();
	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		return objectMapper;
	}

}
