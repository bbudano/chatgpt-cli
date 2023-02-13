package com.example.chatgptcli.dto.image.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ImageRequest(@JsonProperty("prompt") String prompt,
                           @JsonProperty("n") Integer n,
                           @JsonProperty("size") String size,
                           @JsonProperty("response_format") String responseFormat) {
}
