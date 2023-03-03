package com.example.chatgptcli.config;

import com.example.chatgptcli.client.ChatGPTClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WebClientConfiguration {

    @Value("${app-props.chatgpt.api-key}")
    private String apiKey;

    @Value("${app-props.chatgpt.base-url}")
    private String baseUrl;

    @Bean
    public ChatGPTClient chatGPTClient() {
        var webClient = WebClient
                .builder()
                .baseUrl(baseUrl)
                .defaultHeaders(httpHeaders -> httpHeaders.addAll(getDefaultHeaders()))
                .build();

        var httpServiceProxyFactory = HttpServiceProxyFactory
                .builder(WebClientAdapter.forClient(webClient))
                .build();

        return httpServiceProxyFactory.createClient(ChatGPTClient.class);
    }

    private HttpHeaders getDefaultHeaders() {
        var httpHeaders = new HttpHeaders();

        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.AUTHORIZATION, "Bearer " + apiKey);

        return httpHeaders;
    }

}
