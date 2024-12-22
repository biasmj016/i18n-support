package com.translation.support.infrastructure.in.web.response;

import com.translation.support.domain.SourceLanguage;

import java.util.List;

public record SupportLanguageResponse(List<SourceLanguage> languages) {
    public SupportLanguageResponse(SourceLanguage[] languages) {
        this(List.of(languages));
    }
}