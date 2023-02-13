package com.example.chatgptcli.dto.completion.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record CompletionsResponse(@JsonProperty("id") String id,
                                  @JsonProperty("object") String object,
                                  @JsonProperty("created") Instant created,
                                  @JsonProperty("model") String model,
                                  @JsonProperty("choices") List<Choice> choices,
                                  @JsonProperty("usage") Usage usage) {
}
