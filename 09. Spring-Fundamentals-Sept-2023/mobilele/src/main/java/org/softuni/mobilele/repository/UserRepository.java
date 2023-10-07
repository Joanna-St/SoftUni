package org.softuni.mobilele.repository;

import org.softuni.mobilele.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User getUserByUsername(String userName);
    @Query(value = "SELECT u " +
            "FROM User AS u " +
            "WHERE u.isActive = true")
    List<User> getActiveUsers();
    @Query(value = "SELECT u " +
            "FROM User AS u " +
            "WHERE u.role.role = :role")
    List<User> getUsersByRole(@Param("role") String role);
}
