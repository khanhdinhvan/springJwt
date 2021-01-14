package com.khanhdv.spring.jwt.repository;

import com.khanhdv.spring.jwt.common.enums.ERole;
import com.khanhdv.spring.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
