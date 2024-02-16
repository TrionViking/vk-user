package com.vk.user.util;

import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
@RequiredArgsConstructor
public class TranslatorUtil {

    private final ResourceBundleMessageSource messageSource;

    public String getMessage(Integer code) {
        Locale locale = LocaleContextHolder.getLocale();
        String msgCode = String.valueOf(code);
        return this.messageSource.getMessage(msgCode, (Object[]) null, locale);
    }

}
