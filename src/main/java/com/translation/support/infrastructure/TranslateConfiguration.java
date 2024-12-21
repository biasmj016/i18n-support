package com.translation.support.infrastructure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "translate.base")
public class TranslateConfiguration {
    private String apiKey;
    private String url;

    public String getApiKey() {
        return apiKey;
    }

    public String getUrl() {
        return url;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}