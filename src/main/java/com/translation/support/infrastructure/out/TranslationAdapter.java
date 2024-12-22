package com.translation.support.infrastructure.out;

import com.translation.support.application.request.TranslateRequest;
import com.translation.support.application.response.TranslateResponse;
import com.translation.support.infrastructure.out.config.TranslateConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

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
}