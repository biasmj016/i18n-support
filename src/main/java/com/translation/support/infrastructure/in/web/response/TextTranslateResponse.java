package com.translation.support.infrastructure.in.web.response;

public record TextTranslateResponse(String translatedText,
                                    String targetLanguage,
                                    String sourceLanguage) {
}