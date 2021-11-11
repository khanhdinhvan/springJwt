package com.khanhdv.spring.jwt.security.config;

import com.khanhdv.spring.jwt.models.Permission;
import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.models.User;
import com.khanhdv.spring.jwt.repository.PermissionRepository;
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

    final PermissionRepository permissionRepository;

    @Autowired
    public AppConfig(UserRepository repository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, PermissionRepository permissionRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.permissionRepository = permissionRepository;
    }

    @PostConstruct
    private void initUser() {

        log.info("Init permission");
        Optional<Permission> permissions = permissionRepository.findByUrl("/auth/signIn");
        if (!permissions.isPresent()) {
            Permission permission = new Permission();
            permission.setUrl("/auth/signIn");
            permission.setIsDeleted(false);
            log.info("Save permission success", permissionRepository.save(permission));
        }

        log.info("Init role");
        Optional<Role> roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        if (!roleAdmin.isPresent()) {
            List<Permission> permissionRole = permissionRepository.findAll();
            Role role = new Role();
            role.setName("ROLE_ADMIN");
            role.setPermissions(permissionRole);
            role.setIsDeleted(false);
            log.info("Save role_admin success", roleRepository.save(role));
        }

        Optional<Role> roleUser = roleRepository.findByName("ROLE_USER");
        if (!roleUser.isPresent()) {
            List<Permission> permissionRole = permissionRepository.findAll();
            Role role = new Role();
            role.setName("ROLE_USER");
            role.setPermissions(permissionRole);
            role.setIsDeleted(false);
            log.info("Save role_user success", roleRepository.save(role));
        }

        log.info("Init user");
        Optional<User> admin = repository.findByUsername(APP_ADMIN_USERNAME);
        if (!admin.isPresent()) {
            List<Role> userRoles = roleRepository.findAll();
            User initAdmin = new User();
            initAdmin.setUsername(APP_ADMIN_USERNAME);
            initAdmin.setPassword(passwordEncoder.encode(APP_ADMIN_PASSWORD));
            initAdmin.setEmail("admin@gamil.com");
            initAdmin.setRoles(userRoles);
            initAdmin.setIsActive(true);
            initAdmin.setIsDeleted(false);
            initAdmin.setCreatedBy("ROLE_ADMIN");
            log.info("Save user success", repository.save(initAdmin));
        }
    }

}
