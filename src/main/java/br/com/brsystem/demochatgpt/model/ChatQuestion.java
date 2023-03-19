package br.com.brsystem.demochatgpt.model;

import jakarta.validation.constraints.NotBlank;

public record ChatQuestion(@NotBlank String question) {

}
