package com.example.chatgptcli.service;

import com.example.chatgptcli.dto.completion.request.CompletionRequest;
import com.example.chatgptcli.dto.completion.response.CompletionsResponse;
import com.example.chatgptcli.dto.image.request.ImageRequest;
import com.example.chatgptcli.dto.image.response.ImageResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
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
public class ChatgptHttpService {

    @Value("${app-props.chatgpt.api-key}")
    private String apiKey;

    @Value("${app-props.chatgpt.base-url}")
    private String baseUrl;

    private final HttpClient httpClient;

    private final ObjectMapper objectMapper;

    @RegisterReflectionForBinding(CompletionsResponse.class)
    public String requestCompletion(String prompt, String model,
                                    Double temperature, Integer maxTokens) throws IOException, InterruptedException {
        CompletionRequest body = new CompletionRequest(model, prompt, temperature, maxTokens);

        var request = buildRequest(baseUrl + "/completions", body);

        var response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        var chatGptResponse = objectMapper.readValue(response.body(), CompletionsResponse.class);

        return chatGptResponse.choices().get(0).text().replace("\n", "");
    }

    @RegisterReflectionForBinding(ImageResponse.class)
    public String requestImage(String prompt, Integer n, String size,
                               String responseFormat) throws IOException, InterruptedException {
        ImageRequest body = new ImageRequest(prompt, n, size, responseFormat);

        var request = buildRequest(baseUrl + "/images/generations", body);

        var response = httpClient
                .send(request, HttpResponse.BodyHandlers.ofString());

        var chatGptResponse = objectMapper.readValue(response.body(), ImageResponse.class);

        return chatGptResponse.data().get(0).url();
    }

    private HttpRequest buildRequest(String uri, Object requestBody) throws JsonProcessingException {
        return HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + apiKey)
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(requestBody)))
                .build();
    }

}
