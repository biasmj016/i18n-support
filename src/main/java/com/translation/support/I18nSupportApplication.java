package com.translation.support;

import com.translation.support.infrastructure.out.config.TranslateConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TranslateConfiguration.class)
public class I18nSupportApplication {

    public static void main(String[] args) {
        SpringApplication.run(I18nSupportApplication.class, args);
    }

}
