package com.rental.Repositories;

import com.rental.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.id = :id")
    User findByNameAndId(@Param("name") String name, @Param("id") Long id);
}