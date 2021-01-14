package com.khanhdv.spring.jwt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

public abstract class BaseController {

    @Autowired
    private Validator[] validators;

    @InitBinder
    public void setUpValidators(WebDataBinder webDataBinder) {
        for (Validator validator : validators) {
            if (webDataBinder.getTarget() != null
                    && validator.supports(webDataBinder.getTarget().getClass())
                    && !validator.getClass().getName().contains("org.springframework"))
                webDataBinder.addValidators(validator);
        }
    }
}
