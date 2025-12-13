package com.vinci.devmatch.modules.user.repository;

import com.vinci.devmatch.modules.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String login);

    Optional<User> findByOauthProviderAndOauthSubjectId(String oauthProvider, String oauthSubjectId);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}

