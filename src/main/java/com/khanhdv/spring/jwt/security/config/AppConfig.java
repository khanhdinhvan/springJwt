package com.khanhdv.spring.jwt.security.config;

import com.khanhdv.spring.jwt.common.enums.ERole;
import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.models.User;
import com.khanhdv.spring.jwt.repository.RoleRepository;
import com.khanhdv.spring.jwt.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Slf4j
@Configuration
public class AppConfig {

    private static final String APP_ADMIN_USERNAME = "admin";

    private static final String APP_ADMIN_PASSWORD = "admin";

    final UserRepository repository;

    final RoleRepository roleRepository;

    final PasswordEncoder passwordEncoder;

    @Autowired
    public AppConfig(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    private void initUser() {
        log.info("Init role");
        Optional<Role> roleAdmin = roleRepository.findByName(ERole.ROLE_ADMIN);
        if(!roleAdmin.isPresent()) {
            Role role = new Role();
            role.setName(ERole.ROLE_ADMIN);
            log.info("Save role_admin success", roleRepository.save(role));
        }

        Optional<Role> roleUser = roleRepository.findByName(ERole.ROLE_USER);
        if(!roleUser.isPresent()) {
            Role role = new Role();
            role.setName(ERole.ROLE_USER);
            log.info("Save role_user success", roleRepository.save(role));
        }

        log.info("Init user");
        Optional<User> admin = repository.findByUsername(APP_ADMIN_USERNAME);
        if(!admin.isPresent()) {
            User initAdmin = new User();
            initAdmin.setUsername(APP_ADMIN_USERNAME);
            initAdmin.setPassword(passwordEncoder.encode(APP_ADMIN_PASSWORD));
            List<Role> userRoles = roleRepository.findAll();
            initAdmin.setEmail("admin@gamil.com");
            initAdmin.setRoles(userRoles);
            initAdmin.setIsActive(true);
            initAdmin.setIsDeleted(false);
            initAdmin.setCreatedBy("ROLE_ADMIN");
            log.info("Save user success", repository.save(initAdmin));
        }
    }

}
