package com.digital_chief.test_task.service.impl;

import com.digital_chief.test_task.dto.ProjectDto;
import com.digital_chief.test_task.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Override
    public ProjectDto save(ProjectDto projectDto) {
        return null;
    }

    @Override
    public ProjectDto update(ProjectDto projectDto, Long id) {
        return null;
    }

    @Override
    public List<ProjectDto> findAll() {
        return List.of();
    }

    @Override
    public ProjectDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
