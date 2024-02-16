package com.vk.user.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@Configuration
public class WebConfig {

    private List<Locale> locals = Arrays.asList(new Locale("en_US"), new Locale("vi"));

    @Bean
    public AcceptHeaderLocaleResolver acceptHeaderLocalResolver() {
        return new AcceptHeaderLocaleResolver() {
            @Override
            public Locale resolveLocale(HttpServletRequest request) {
                String headerLang = request.getHeader("Accept-Language");
                return headerLang == null || headerLang.isBlank() ?
                        locals.get(0) : Locale.lookup(Locale.LanguageRange.parse(headerLang), locals);
            }
        };
    }

    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource rs = new ResourceBundleMessageSource();
        rs.setBasename("messages");
        rs.setDefaultEncoding("UTF-8");
        rs.setUseCodeAsDefaultMessage(true);
        return rs;
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }
}
