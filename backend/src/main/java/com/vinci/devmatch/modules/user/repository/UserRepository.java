package com.vinci.devmatch.modules.user.repository;

import com.vinci.devmatch.modules.user.entity.User;
import com.vinci.devmatch.modules.user.enums.Role;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByAuth0Id(String auth0Id);

    Optional<User> findByEmail(String email);

    boolean existsByAuth0Id(String auth0Id);

    boolean existsByEmail(String email);

    List<User> findByRole(Role role);

    List<User> findByIndustry(String industry);

    List<User> findByProfileCompleted(Boolean profileCompleted);

    Page<User> findByRole(Role role, Pageable pageable);

    Page<User> findByIndustry(String industry, Pageable pageable);

    @Query("SELECT u FROM User u WHERE " +
            "(:role IS NULL OR u.role = :role) AND " +
            "(:industry IS NULL OR u.industry = :industry) AND " +
            "(:profileCompleted IS NULL OR u.profileCompleted = :profileCompleted)")
    Page<User> findByFilters(
            @Param("role") Role role,
            @Param("industry") String industry,
            @Param("profileCompleted") Boolean profileCompleted,
            Pageable pageable
    );
}