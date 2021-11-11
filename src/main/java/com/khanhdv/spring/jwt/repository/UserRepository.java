package com.khanhdv.spring.jwt.repository;

import com.khanhdv.spring.jwt.models.Permission;
import com.khanhdv.spring.jwt.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  List<User> findByIdAndUsername(Long id, String username);

  User findByIdAndIsDeletedIsFalse(Long id);

}
