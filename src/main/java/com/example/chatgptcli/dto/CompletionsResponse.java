package com.example.chatgptcli.dto;

import java.time.Instant;
import java.util.List;

public record CompletionsResponse(String id, String object, Instant created, String model, List<Choice> choices, Usage usage) {
}
