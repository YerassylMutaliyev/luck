package com.example.luck.repositories;

import com.example.luck.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail (String email);

    @Query("SELECT u FROM User u " +
            "WHERE (:fullname IS NULL OR LOWER(u.fullname) LIKE LOWER(CONCAT('%', :fullname, '%'))) " +
            "AND (:email IS NULL OR LOWER(u.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
            "AND (:role IS NULL OR u.role = :role)")
    Page<User> findByFilters(
            @Param("fullname") String fullname,
            @Param("email") String email,
            @Param("role") String role,
            Pageable pageable
    );
}
