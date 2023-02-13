package com.example.chatgptcli.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Usage(@JsonProperty("prompt_tokens") Integer promptTokens,
                    @JsonProperty("completion_tokens") Integer completionTokens,
                    @JsonProperty("total_tokens") Integer totalTokens) {
}
