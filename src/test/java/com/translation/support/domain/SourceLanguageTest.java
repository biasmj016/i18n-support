package com.translation.support.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceLanguageTest {

    @Test
    void fromCodeOrName() {
        assertEquals(SourceLanguage.ENGLISH, SourceLanguage.fromCodeOrName("EN"));
        assertEquals(SourceLanguage.FRENCH, SourceLanguage.fromCodeOrName("FR"));

        assertEquals(SourceLanguage.ENGLISH, SourceLanguage.fromCodeOrName("ENGLISH"));
        assertEquals(SourceLanguage.FRENCH, SourceLanguage.fromCodeOrName("FRENCH"));
    }

    @Test
    void fromCodeOrName_exception() {
        assertThrows(IllegalArgumentException.class, () -> SourceLanguage.fromCodeOrName("INVALID"));
    }
}