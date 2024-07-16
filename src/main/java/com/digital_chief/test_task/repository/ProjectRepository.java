package com.digital_chief.test_task.repository;

import com.digital_chief.test_task.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProjectRepository extends JpaRepository<Project, UUID> {

}
