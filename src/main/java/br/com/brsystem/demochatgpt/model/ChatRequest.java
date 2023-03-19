package br.com.brsystem.demochatgpt.model;

import java.util.List;

public record ChatRequest(String model, List<ChatMessage> messages) {}
