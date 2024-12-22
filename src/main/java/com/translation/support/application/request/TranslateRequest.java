package com.translation.support.application.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record TranslateRequest(
        @JsonProperty("text")
        List<String> text,
        @JsonProperty("target_lang")
        String targetLanguage
) {
    @Override
    public String toString() {
        return "target language -> '%s', text -> %s".formatted(targetLanguage, text);
    }
}