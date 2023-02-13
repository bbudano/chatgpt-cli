package com.example.chatgptcli.dto.completion.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompletionRequest(@JsonProperty("model") String model,
                                @JsonProperty("prompt") String prompt,
                                @JsonProperty("temperature") Double temperature,
                                @JsonProperty("max_tokens") Integer maxTokens) {
}
