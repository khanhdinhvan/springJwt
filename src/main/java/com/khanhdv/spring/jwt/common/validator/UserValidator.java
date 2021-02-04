package com.khanhdv.spring.jwt.common.validator;

import com.khanhdv.spring.jwt.payload.request.SignupRequest;
import com.khanhdv.spring.jwt.repository.UserRepository;
import com.khanhdv.spring.jwt.security.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    final UserRepository userRepository;

    final MessageService messageService;

    @Autowired
    public UserValidator(MessageService messageService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == SignupRequest.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignupRequest request = ((SignupRequest) target);
        if (userRepository.existsByUsername(request.getUsername())) {
            errors.rejectValue("username", request.getUsername(), messageService.getMessage("error.user.name.exist"));
        }
    }
}
