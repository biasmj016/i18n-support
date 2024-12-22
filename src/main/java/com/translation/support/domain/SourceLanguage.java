package com.translation.support.domain;

import java.util.stream.Stream;

public enum SourceLanguage {
    BULGARIAN("BG"),
    CHINESE("ZH"),
    CZECH("CS"),
    DANISH("DA"),
    DUTCH("NL"),
    ENGLISH("EN"),
    ESTONIAN("ET"),
    FINNISH("FI"),
    FRENCH("FR"),
    GERMAN("DE"),
    GREEK("EL"),
    HUNGARIAN("HU"),
    INDONESIAN("ID"),
    ITALIAN("IT"),
    JAPANESE("JA"),
    KOREAN("KO"),
    LATVIAN("LV"),
    LITHUANIAN("LT"),
    POLISH("PL"),
    PORTUGUESE("PT"),
    ROMANIAN("RO"),
    RUSSIAN("RU"),
    SLOVAK("SK"),
    SLOVENIAN("SL"),
    SPANISH("ES"),
    SWEDISH("SV"),
    TURKISH("TR"),
    UKRAINIAN("UK");

    private final String code;

    SourceLanguage(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static SourceLanguage fromCodeOrName(String language) {
        return Stream.of(values())
                .filter(lang -> lang.code.equalsIgnoreCase(language) || lang.name().equalsIgnoreCase(language))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unknown language code or name: " + language));
    }
}