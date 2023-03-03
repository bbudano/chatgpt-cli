package com.example.chatgptcli.client;

import com.example.chatgptcli.dto.completion.request.CompletionRequest;
import com.example.chatgptcli.dto.completion.response.CompletionResponse;
import com.example.chatgptcli.dto.image.request.ImageRequest;
import com.example.chatgptcli.dto.image.response.ImageResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;

public interface ChatGPTClient {

    @PostExchange("/completions")
    CompletionResponse completion(@RequestBody CompletionRequest completionRequest);

    @PostExchange("/images")
    ImageResponse image(@RequestBody ImageRequest imageRequest);

}
