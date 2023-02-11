package com.example.chatgptcli.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Choice(String text, Integer index, String logprobs, @JsonProperty("finish_reason") String finishReason) {
}
