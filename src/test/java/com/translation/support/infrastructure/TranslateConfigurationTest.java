package com.translation.support.infrastructure;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TranslateConfigurationTest {

    @Autowired
    private TranslateConfiguration translateConfiguration;

    @Test
    public void testTranslateConfigurationProperties() {
        assertNotNull(translateConfiguration.getApiKey());
        assertEquals("YOUR_API_KEY", translateConfiguration.getApiKey());
        assertNotNull(translateConfiguration.getUrl());
    }
}