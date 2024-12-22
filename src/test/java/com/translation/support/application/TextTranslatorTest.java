package com.translation.support.application;

import com.translation.support.application.request.TranslateRequest;
import com.translation.support.application.response.TranslateResponse;
import com.translation.support.domain.Translation;
import com.translation.support.infrastructure.out.TranslationAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class TextTranslatorTest {

    @Mock
    private TranslationAdapter translationAdapter;

    @InjectMocks
    private TextTranslator.TextTranslatorUseCase textTranslatorUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void translate() {
        TranslateRequest request = new TranslateRequest(List.of("Hello"), "DE");
        Translation translation = new Translation("Hallo", "EN");
        TranslateResponse response = new TranslateResponse(List.of(translation));

        when(translationAdapter.fetch(request)).thenReturn(response);

        Translation result = textTranslatorUseCase.translate(request);

        assertEquals("Hallo", result.text());
        assertEquals("EN", result.sourceLanguage());

        verify(translationAdapter, times(1)).fetch(request);
        System.out.println("TextTranslator Response: " + result);
    }
}