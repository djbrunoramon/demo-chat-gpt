package br.com.brsystem.demochatgpt.controller;

import br.com.brsystem.demochatgpt.model.ChatQuestion;
import br.com.brsystem.demochatgpt.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/chat")
@CrossOrigin(value = "*")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @PostMapping
    public ResponseEntity<String> getQuestion(@Valid @RequestBody ChatQuestion chatQuestion) throws IOException, InterruptedException {
        return ResponseEntity.ok(chatService.getQuestion(chatQuestion.question()));
    }
}
