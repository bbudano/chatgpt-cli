package com.example.chatgptcli.service;

import com.example.chatgptcli.dto.completion.request.CompletionRequest;
import com.example.chatgptcli.dto.completion.response.CompletionsResponse;
import com.example.chatgptcli.dto.image.request.ImageRequest;
import com.example.chatgptcli.dto.image.response.ImageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Service
@RequiredArgsConstructor
@RegisterReflectionForBinding(CompletionsResponse.class)
public class CompletionHttpService {

    @Value("${app-props.chatgpt.api-key}")
    private String apiKey;

    @Value("${app-props.chatgpt.base-url}")
    private String baseUrl;

    private final ObjectMapper objectMapper;

    public String requestCompletion(String prompt, String model,
                                    Double temperature, Integer maxTokens) throws IOException, InterruptedException {
        CompletionRequest request = new CompletionRequest(model, prompt, temperature, maxTokens);

        var httpClient = HttpClient.newBuilder()
                .build();

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/completions"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(request)))
                .build();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var chatGptResponse = objectMapper.readValue(response.body(), CompletionsResponse.class);

        return chatGptResponse.choices().get(0).text();
    }

    public String requestImage(String prompt, Integer n, String size,
                               String responseFormat) throws IOException, InterruptedException {
        ImageRequest request = new ImageRequest(prompt, n, size, responseFormat);

        var httpClient = HttpClient.newBuilder()
                .build();

        var httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/images/generations"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(request)))
                .build();

        var response = httpClient
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var chatGptResponse = objectMapper.readValue(response.body(), ImageResponse.class);

        return chatGptResponse.data().get(0).url();
    }

}
