package com.translation.support.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SourceLanguageTest {

    @Test
    void fromCodeOrName() {
        assertEquals("EN", SourceLanguage.fromCodeOrName("EN"));
        assertEquals("FR", SourceLanguage.fromCodeOrName("FR"));

        assertEquals("EN", SourceLanguage.fromCodeOrName("ENGLISH"));
        assertEquals("FR", SourceLanguage.fromCodeOrName("FRENCH"));
    }

    @Test
    void fromCodeOrName_exception() {
        assertThrows(IllegalArgumentException.class, () -> SourceLanguage.fromCodeOrName("INVALID"));
    }
}