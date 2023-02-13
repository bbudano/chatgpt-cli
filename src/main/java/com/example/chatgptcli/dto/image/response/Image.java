package com.example.chatgptcli.dto.image.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Image(@JsonProperty("url") String url) {
}
