package br.com.brsystem.demochatgpt.service;

import br.com.brsystem.demochatgpt.model.ChatMessage;
import br.com.brsystem.demochatgpt.model.ChatRequest;
import br.com.brsystem.demochatgpt.model.ChatResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class ChatService {

    private static final String MODEL = "gpt-3.5-turbo";
    private final Logger logger = LoggerFactory.getLogger(ChatService.class);

    @Value("${chat-gpt.api}")
    private String chatGptApi;

    @Value("${chat-gpt.token}")
    private String token;

    public String getQuestion(String question) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        ChatRequest chatRequest = new ChatRequest(MODEL, List.of(new ChatMessage("system", question)));
        String input = objectMapper.writeValueAsString(chatRequest);

        HttpRequest request = HttpRequest
                .newBuilder(URI.create(chatGptApi))
                .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .header("Authorization", "Bearer " + token)
                .POST(HttpRequest.BodyPublishers.ofString(input))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String answer = "";

        if (response.statusCode() == HttpStatus.OK.value()) {
            ChatResponse chatResponse = objectMapper.readValue(response.body(), ChatResponse.class);
            answer = chatResponse.choices()[chatResponse.choices().length -1].message().content();
        } else {
            logger.error("status: %s, %s".formatted(response.statusCode(),response.body()));
        }

        return answer;
    }
}
