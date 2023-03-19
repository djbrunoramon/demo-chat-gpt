package br.com.brsystem.demochatgpt.model;

public record ChatResponse(
        String id,
        String object,
        Long created,
        String model,
        ChatResponseChoice[] choices,
        ChatResponseUsage usage
) {
}
