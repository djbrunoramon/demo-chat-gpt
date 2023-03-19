package br.com.brsystem.demochatgpt.model;

public record ChatResponseChoice(
        int index,
        ChatMessage message,
        String finish_reason
) {
}
