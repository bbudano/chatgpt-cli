package com.example.chatgptcli.service;

import com.example.chatgptcli.client.ChatGPTClient;
import com.example.chatgptcli.dto.completion.request.CompletionRequest;
import com.example.chatgptcli.dto.image.request.ImageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aot.hint.annotation.RegisterReflectionForBinding;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ChatGPTHttpService {

    private final ChatGPTClient chatGPTClient;

//    @RegisterReflectionForBinding(CompletionResponse.class)
    public String requestCompletion(String prompt, String model, Double temperature, Integer maxTokens) {
        var completionRequest = new CompletionRequest(model, prompt, temperature, maxTokens);

        var chatGptResponse = chatGPTClient.completion(completionRequest);

        return chatGptResponse.choices().get(0).text().replace("\n", "");
    }

//    @RegisterReflectionForBinding(ImageResponse.class)
    public String requestImage(String prompt, Integer n, String size, String responseFormat) {
        var imageRequest = new ImageRequest(prompt, n, size, responseFormat);

        var chatGptResponse = chatGPTClient.image(imageRequest);

        return chatGptResponse.data().get(0).url();
    }

}
