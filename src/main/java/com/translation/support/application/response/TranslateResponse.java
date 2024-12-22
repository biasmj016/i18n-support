package com.translation.support.application.response;

import com.translation.support.domain.Translation;

import java.util.List;

public record TranslateResponse(List<Translation> translations) {
}