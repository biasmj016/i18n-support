package com.translation.support.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Translation(
        @JsonProperty("text") String text,
        @JsonProperty("detected_source_language") String sourceLanguage
) { }
