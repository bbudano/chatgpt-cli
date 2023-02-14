package com.example.chatgptcli.shell;

import com.example.chatgptcli.service.ChatgptHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class ChatgptCommands {

    private final ChatgptHttpService chatgptHttpService;

    @ShellMethod("Make request to ChatGPT Completions API")
    public String ask(String prompt,
                      @ShellOption(defaultValue = "text-davinci-003") String model,
                      @ShellOption(defaultValue = "0.2") Double temperature,
                      @ShellOption(value = "max-tokens", defaultValue = "100") Integer maxTokens) throws IOException, InterruptedException {
        return chatgptHttpService.requestCompletion(prompt, model, temperature, maxTokens);
    }

    @ShellMethod("Make request to ChatGPT Images API")
    public String img(String prompt,
                      @ShellOption(defaultValue = "1") Integer n,
                      @ShellOption(defaultValue = "1024x1024") String size,
                      @ShellOption(value = "response_format", defaultValue = "url") String responseFormat) throws IOException, InterruptedException {
        return chatgptHttpService.requestImage(prompt, n, size, responseFormat);
    }

}
