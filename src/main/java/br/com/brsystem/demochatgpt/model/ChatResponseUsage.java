package br.com.brsystem.demochatgpt.model;

public record ChatResponseUsage(
        int prompt_tokens,
        int completion_tokens,
        int total_tokens
) {
}
