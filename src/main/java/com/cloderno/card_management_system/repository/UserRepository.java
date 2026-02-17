package com.cloderno.card_management_system.repository;

import com.cloderno.card_management_system.entity.User;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
