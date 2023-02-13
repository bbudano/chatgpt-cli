package com.example.chatgptcli.shell;

import com.example.chatgptcli.service.CompletionHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class ChatgptCommands {

    private final CompletionHttpService completionHttpService;

    @ShellMethod("Make request to ChatGPT completion API")
    public String ask(String prompt, @ShellOption(defaultValue = "text-davinci-003") String model,
                      @ShellOption(defaultValue = "0.2") Double temperature,
                      @ShellOption(value = "max-tokens", defaultValue = "100") Integer maxTokens) throws IOException, InterruptedException {
        return completionHttpService.requestCompletion(prompt, model, temperature, maxTokens);
    }

}
