package com.example.chatgptcli.shell;

import com.example.chatgptcli.service.ChatGPTHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class ChatGPTCommands {

    private final ChatGPTHttpService chatgptHttpService;

    @ShellMethod("Make request to ChatGPT Completions API")
    public String completions(String prompt,
                              @ShellOption(defaultValue = "text-davinci-003") String model,
                              @ShellOption(defaultValue = "0.2") Double temperature,
                              @ShellOption(value = "max-tokens", defaultValue = "100") Integer maxTokens) {
        return chatgptHttpService.requestCompletion(prompt, model, temperature, maxTokens);
    }

    @ShellMethod("Make request to ChatGPT Images API")
    public String images(String prompt,
                         @ShellOption(defaultValue = "1") Integer n,
                         @ShellOption(defaultValue = "1024x1024") String size,
                         @ShellOption(value = "response_format", defaultValue = "url") String responseFormat) {
        return chatgptHttpService.requestImage(prompt, n, size, responseFormat);
    }

}
