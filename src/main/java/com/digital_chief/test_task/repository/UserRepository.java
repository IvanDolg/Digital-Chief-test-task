package com.digital_chief.test_task.repository;

import com.digital_chief.test_task.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}