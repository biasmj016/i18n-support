package com.translation.support.infrastructure.in.web;

import com.translation.support.application.TextTranslator;
import com.translation.support.domain.SourceLanguage;
import com.translation.support.domain.Translation;
import com.translation.support.infrastructure.in.web.request.TextTranslateRequest;
import com.translation.support.infrastructure.in.web.response.SupportLanguageResponse;
import com.translation.support.infrastructure.in.web.response.TextTranslateResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/translator")
public class TranslationApiController {
    private final TextTranslator textTranslator;

    public TranslationApiController(TextTranslator textTranslator) {
        this.textTranslator = textTranslator;
    }

    @GetMapping("/support-language")
    public SupportLanguageResponse getSupportLanguage() {
        return new SupportLanguageResponse(SourceLanguage.values());
    }

    @PostMapping
    public TextTranslateResponse translate(@RequestBody TextTranslateRequest request) {
        Translation translation = textTranslator.translate(request.toTranslateRequest());
        return translation.toResponse(request.targetLanguage());
    }

}
