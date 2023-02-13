package com.example.chatgptcli.dto.image.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;

public record ImageResponse(@JsonProperty("created") Instant created,
                            @JsonProperty("data") List<Image> data) {
}
