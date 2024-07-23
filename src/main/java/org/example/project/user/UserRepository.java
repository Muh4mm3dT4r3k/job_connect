package org.example.project.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u WHERE u.email = :email")
    Optional<User> findUserByEmail(String email);
    
    @Query("""
        SELECT u.id, u.firstName, u.lastName, u.email, u.enabled, u.role, u.createdOn FROM User u
    """)
    List<User>  findAllUsers();

    
    @Query("""
        SELECT CASE WHEN COUNT (u) > 0 THEN TRUE ELSE FALSE END FROM User u WHERE u.email = :email
    """)
    boolean existsByEmail(String email);

    @Query("""
        SELECT u FROM User u WHERE u.role = :userRole
    """)
    List<User> findByRole(UserRole userRole);

    @Query("""
        SELECT u.id, u.firstName, u.lastName, u.email, u.enabled, u.role, u.createdOn
        FROM User u
        WHERE u.role = :userRole
        AND u.enabled = :enabled
    """)
    List<User> findByRoleAndEnabled(UserRole userRole, boolean enabled);

    @Query("""
        SELECT u.id, u.firstName, u.lastName, u.email, u.enabled, u.role, u.createdOn
        FROM User u
        WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :name, '%'))
        OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :name, '%'))
    """)
    List<User> searchByName(String name);

    @Query("""
        SELECT u.id, u.firstName, u.lastName, u.email, u.enabled, u.role, u.createdOn
        FROM User u
        WHERE u.createdOn = :createdOn
    """)
    List<User> findUserByCreatedOn(LocalDate createdOn);

    @Query("""
        SELECT u.id, u.firstName, u.lastName, u.email, u.enabled, u.role, u.createdOn
        FROM User u
        WHERE u.role = :role
        AND u.createdOn BETWEEN :startDate AND :endDate
    """)
    List<User> findUserByRoleAndCreatedOnBetween(UserRole role, LocalDate startDate, LocalDate endDate);

    @Modifying
    @Query("""
        UPDATE User u SET u.enabled = :enabled WHERE u.id = :userId
    """)
    int updateEnabledStatus(Integer userId, boolean enabled);

    @Modifying
    @Query("""
        DELETE User u WHERE u.id =:id
    """)
    int deleteUserById(Integer id);
}
