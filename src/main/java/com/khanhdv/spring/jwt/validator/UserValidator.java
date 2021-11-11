package com.khanhdv.spring.jwt.validator;

import com.khanhdv.spring.jwt.common.utils.Label;
import com.khanhdv.spring.jwt.payload.request.user.UserRegisterRequest;
import com.khanhdv.spring.jwt.repository.UserRepository;
import com.khanhdv.spring.jwt.security.services.MessageService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UserValidator implements Validator {

    final UserRepository userRepository;

    final MessageService messageService;

    private static final String USER_NAME = "username";
    private static final String EMAIL = "email";
    private static final String DATA_EXIST = "error.message.common.exist";


    @Autowired
    public UserValidator(MessageService messageService, UserRepository userRepository) {
        this.messageService = messageService;
        this.userRepository = userRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == UserRegisterRequest.class;
    }

    @SneakyThrows
    @Override
    public void validate(Object target, Errors errors) {
        UserRegisterRequest request = ((UserRegisterRequest) target);
        if (userRepository.existsByUsername(request.getUsername())) {
            errors.rejectValue(USER_NAME, request.getUsername(), messageService.getMessage(DATA_EXIST, Label.getLabel(UserRegisterRequest.class, USER_NAME)));
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            errors.rejectValue(EMAIL, request.getUsername(), messageService.getMessage(DATA_EXIST, Label.getLabel(UserRegisterRequest.class, EMAIL)));
        }
    }
}
