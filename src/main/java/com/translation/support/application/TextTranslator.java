package com.translation.support.application;

import com.translation.support.application.request.TranslateRequest;
import com.translation.support.domain.Translation;
import com.translation.support.infrastructure.out.TranslationAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

public interface TextTranslator {
    Translation translate(TranslateRequest request);

    @Service
    class TextTranslatorUseCase implements TextTranslator {
        private final TranslationAdapter translationAdapter;
        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        public TextTranslatorUseCase(TranslationAdapter translationAdapter) {
            this.translationAdapter = translationAdapter;
        }

        @Override
        public Translation translate(TranslateRequest request) {
            logger.info("TextTranslator Request: {}", request);
            return translationAdapter.fetch(request).translations().getFirst();
        }
    }
}
