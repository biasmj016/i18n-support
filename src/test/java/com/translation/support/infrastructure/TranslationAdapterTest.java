package com.translation.support.infrastructure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class TranslationAdapterTest {

    @Mock
    private WebClient webClient;

    @Mock
    private WebClient.RequestBodyUriSpec requestBodyUriSpec;

    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private WebClient.ResponseSpec responseSpec;

    @Mock
    private TranslateConfiguration translateConfiguration;

    @InjectMocks
    private TranslationAdapter.TranslationHttpAdapter translationHttpAdapter;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(translateConfiguration.getApiKey()).thenReturn("test-api-key");
        when(translateConfiguration.getUrl()).thenReturn("https://api-free.deepl.com/v2/translate?target_lang=");
    }

    @Test
    public void fetch() {
        TranslationAdapter.TranslateRequest request = new TranslationAdapter.TranslateRequest(List.of("Hello"), "KO");

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.header(HttpHeaders.AUTHORIZATION, "DeepL-Auth-Key test-api-key")).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(request)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TranslationAdapter.TranslateResponse.class)).thenReturn(Mono.just(new TranslationAdapter.TranslateResponse(List.of(new TranslationAdapter.TranslateResponse.Translation("안녕하세요", "EN")))));

        TranslationAdapter.TranslateResponse response = translationHttpAdapter.fetch(request);

        System.out.println(response);
        assertNotNull(response);
        assertNotNull(response.translations());
    }


    @Test
    public void fetchError() {
        TranslationAdapter.TranslateRequest request = new TranslationAdapter.TranslateRequest(List.of("Hello"), "KO");

        when(webClient.post()).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.uri(any(String.class))).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.header(HttpHeaders.AUTHORIZATION, "DeepL-Auth-Key test-api-key")).thenReturn(requestBodyUriSpec);
        when(requestBodyUriSpec.bodyValue(request)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(responseSpec.onStatus(any(), any())).thenReturn(responseSpec);
        when(responseSpec.bodyToMono(TranslationAdapter.TranslateResponse.class)).thenReturn(Mono.error(new RuntimeException("API Error")));

        assertThrows(RuntimeException.class, () -> translationHttpAdapter.fetch(request));
    }
}
