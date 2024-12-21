package com.translation.support.infrastructure;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

public interface TranslationAdapter {
    TranslateResponse fetch(TranslateRequest request);

    @Component
    class TranslationHttpAdapter implements TranslationAdapter {
        private final WebClient webClient;
        private final TranslateConfiguration translateConfiguration;
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        public TranslationHttpAdapter(WebClient webClient, TranslateConfiguration translateConfiguration) {
            this.webClient = webClient;
            this.translateConfiguration = translateConfiguration;
        }

        @Override
        public TranslateResponse fetch(TranslateRequest request) {
            var apiUrl = "%s%s".formatted(translateConfiguration.getUrl(), request.targetLanguage());

            logger.info("Translation Request: {}", request);

            return webClient.post()
                    .uri(apiUrl)
                    .header(HttpHeaders.AUTHORIZATION, "DeepL-Auth-Key " + translateConfiguration.getApiKey())
                    .bodyValue(request)
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, clientResponse -> {
                        logger.error("Translation API Error: [Status Code: {}, Request: {}]", clientResponse.statusCode(), request);
                        return clientResponse.createException();
                    })
                    .bodyToMono(TranslateResponse.class)
                    .blockOptional()
                    .orElseThrow(() -> new RuntimeException("Translation Fetch Error"));
        }
    }


    record TranslateRequest(
            @JsonProperty("text")
            List<String> text,
            @JsonProperty("target_lang")
            String targetLanguage
    ) {
        @Override
        public String toString() {
            return "target language -> '%s', text -> %s".formatted(targetLanguage, text);
        }
    }

    record TranslateResponse(List<Translation> translations) {
        record Translation(
                @JsonProperty("text") String text,
                @JsonProperty("detected_source_language") String sourceLanguage
        ) { }
    }
}