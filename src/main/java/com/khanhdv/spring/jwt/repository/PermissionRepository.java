package com.khanhdv.spring.jwt.repository;

import com.khanhdv.spring.jwt.models.Permission;
import com.khanhdv.spring.jwt.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {

    Optional<Permission> findByUrl(String name);

    List<Permission> findByIdIn(List<Long> id);

    Permission findByIdAndIsDeletedIsFalse(Long roleId);

}
