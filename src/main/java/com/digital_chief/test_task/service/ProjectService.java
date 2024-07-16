package com.digital_chief.test_task.service;

import com.digital_chief.test_task.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto save(ProjectDto projectDto);
    ProjectDto update(ProjectDto projectDto, Long id);
    List<ProjectDto> findAll();
    ProjectDto findById(Long id);
    void deleteById(Long id);
}
