package com.khanhdv.spring.jwt.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class MessageService {

    @Autowired
    private MessageSource messageSource;

    public String getMessage(String code, String argument) {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage(code, new Object[] {argument}, locale);
    }

}
