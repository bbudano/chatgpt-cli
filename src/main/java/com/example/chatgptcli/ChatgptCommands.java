package com.example.chatgptcli;

import com.example.chatgptcli.service.CompletionHttpService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.io.IOException;

@ShellComponent
@RequiredArgsConstructor
public class ChatgptCommands {

    private final CompletionHttpService completionHttpService;

    @ShellMethod("ask")
    public String ask(String prompt) throws IOException, InterruptedException {
        return completionHttpService.requestCompletion(prompt);
    }

}
