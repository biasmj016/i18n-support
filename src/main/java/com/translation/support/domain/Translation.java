package com.translation.support.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.translation.support.infrastructure.in.web.response.TextTranslateResponse;

import static com.translation.support.domain.SourceLanguage.fromCodeOrName;

public record Translation(
        @JsonProperty("text") String text,
        @JsonProperty("detected_source_language") String sourceLanguage
) {
    public TextTranslateResponse toResponse(String targetLanguage) {
        return new TextTranslateResponse(
                text,
                fromCodeOrName(targetLanguage).name(),
                fromCodeOrName(sourceLanguage).name()
        );
    }
}
