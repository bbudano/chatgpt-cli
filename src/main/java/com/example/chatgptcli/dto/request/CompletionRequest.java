package com.example.chatgptcli.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CompletionRequest(String model, String prompt, Double temperature, @JsonProperty("max_tokens") Integer maxTokens) {
}
