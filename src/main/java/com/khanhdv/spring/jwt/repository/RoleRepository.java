package com.khanhdv.spring.jwt.repository;

import com.khanhdv.spring.jwt.models.Role;
import com.khanhdv.spring.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(String name);

    List<Role> findAll();

    List<Role> findByIdIn(List<Long> id);

    Role findByIdAndIsDeletedIsFalse(Long id);
    
}
