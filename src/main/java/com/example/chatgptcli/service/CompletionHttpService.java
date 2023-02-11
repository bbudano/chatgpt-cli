package com.example.chatgptcli.service;

import com.example.chatgptcli.dto.request.CompletionRequest;
import com.example.chatgptcli.dto.response.CompletionsResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class CompletionHttpService {

    @Value("${app-props.chatgpt.api-key}")
    private String apiKey;

    private final ObjectMapper objectMapper;

    public String requestCompletion(String prompt) throws IOException, InterruptedException {
        CompletionRequest request = new CompletionRequest("text-davinci-003", prompt, 0.2, 100);

        var httpClient = HttpClient.newBuilder()
                .build();

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(request)))
                .build();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var chatGptResponse = objectMapper.readValue(response.body(), CompletionsResponse.class);

        return chatGptResponse.choices().get(0).text();
    }

}