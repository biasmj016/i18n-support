package com.translation.support.infrastructure.in.web.request;

import com.translation.support.application.request.TranslateRequest;

import java.util.List;

import static com.translation.support.domain.SourceLanguage.fromCodeOrName;

public record TextTranslateRequest(String text, String targetLanguage) {
    public TextTranslateRequest {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Text is a required field and cannot be blank.");
        }
        if (targetLanguage == null || targetLanguage.isBlank()) {
            throw new IllegalArgumentException("Target language is a required field and cannot be blank.");
        }
    }

    public TranslateRequest toTranslateRequest() {
        return new TranslateRequest(
                List.of(text),
                fromCodeOrName(targetLanguage).getCode()
        );
    }
}