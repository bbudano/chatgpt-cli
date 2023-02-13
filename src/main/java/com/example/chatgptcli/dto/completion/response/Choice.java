package com.example.chatgptcli.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(@JsonProperty("text") String text,
                     @JsonProperty("index") Integer index,
                     @JsonProperty("logprobs") String logprobs,
                     @JsonProperty("finish_reason") String finishReason) {
}
